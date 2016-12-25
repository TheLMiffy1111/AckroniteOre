package thelm.ackroniteore.tile;

import ec3.common.item.ItemsCore;
import ec3.common.tile.TileMithrilineCrystal;
import essentialThaumaturgy.common.init.ItemsInit;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import thelm.ackroniteore.api.ESPEInfuserRecipe;
import thelm.ackroniteore.api.ESPEInfuserRecipes;
import thelm.ackroniteore.config.ConfigHandler;
import thelm.ackroniteore.registry.BlockRegistry;
import thelm.ackroniteore.registry.ItemRegistry;

public class TileESPEInfuser extends TileEntity implements ISidedInventory {
	public static float maxEnergy = ConfigHandler.ESPEIME;
	public float energy;
	private ItemStack[] infuserItemStacks = new ItemStack[2];
	private String name;
	
	public int getSizeInventory() {
		return infuserItemStacks.length;
	}
	
	public ItemStack getStackInSlot(int slot) {
		return infuserItemStacks[slot];
	}
	
	public ItemStack decrStackSize(int slot, int decr) {
		if(infuserItemStacks[slot] != null) {
			ItemStack itemstack;
			
			if(infuserItemStacks[slot].stackSize <= decr) {
                itemstack = infuserItemStacks[slot];
				infuserItemStacks[slot] = null;
				return itemstack;
			}
			else {
				itemstack = infuserItemStacks[slot].splitStack(decr);
				
				if(infuserItemStacks[slot].stackSize == 0)
					infuserItemStacks[slot] = null;
				
				return itemstack;
			}
		}
		return null;
	}
	
	public ItemStack getStackInSlotOnClosing(int slot) {
		if(infuserItemStacks[slot] != null) {
			ItemStack itemstack = infuserItemStacks[slot];
			infuserItemStacks[slot] = null;
			return itemstack;
		}
		return null;
	}
	
	public void setInventorySlotContents(int slot, ItemStack stack) {
		infuserItemStacks[slot] = stack;
		
		if(stack != null && stack.stackSize > getInventoryStackLimit())
			stack.stackSize = getInventoryStackLimit();
	}
	
	public String getInventoryName() {
		return "ackroniteore.container.ESPEInfuser";
	}
	
	public boolean hasCustomInventoryName() {
		return false;
	}
	
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		NBTTagList nbttaglist = compound.getTagList("Items", 10);
		infuserItemStacks = new ItemStack[getSizeInventory()];
		
		for(int i = 0; i < nbttaglist.tagCount(); ++i) {
			NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
			byte b0 = nbttagcompound1.getByte("Slot");
			
			if(b0 >= 0 && b0 < infuserItemStacks.length)
				infuserItemStacks[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
		}
		
		energy = compound.getFloat("Energy");
		
		if(compound.hasKey("CustomName", 8))
			name = compound.getString("CustomName");
    }
	
	public void writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		compound.setFloat("energy", energy);
		NBTTagList nbttaglist = new NBTTagList();
		
		for(int i = 0; i < infuserItemStacks.length; ++i) {
			if(infuserItemStacks[i] != null) {
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte)i);
				infuserItemStacks[i].writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}
		}
		
		compound.setTag("Items", nbttaglist);
	}
	
	public int getInventoryStackLimit() {
		return 64;
	}
	
	public void updateEntity() {
		boolean mark = false;
		int[][] possibleTargets = new int[][] {
			{0, 1, 0},
			{0, 0, 1},
			{0, 0, -1},
			{1, 0, 0},
			{-1, 0, 0},
			{0, -1, 1},
			{0, -1, -1},
			{1, -1, 0},
			{-1, -1, 0},
			{0, -2, 1},
			{0, -2, -1},
			{1, -2, 0},
			{-1, -2, 0},
			{0, -3, 0},
		};
		
		if(getStackInSlot(0) != null) {
			ESPEInfuserRecipe rec = ESPEInfuserRecipes.findRecipeByInput(getStackInSlot(0));
			if(rec != null && getStackInSlot(0).stackSize >= rec.input.stackSize && (getStackInSlot(1) == null || getStackInSlot(1).isItemEqual(rec.output) && getStackInSlot(1).stackSize + rec.output.stackSize <= getStackInSlot(1).getMaxStackSize())) {
				if(energy + rec.energy <= maxEnergy) {
					energy += rec.energy;
					decrStackSize(0, rec.input.stackSize);
					if(getStackInSlot(1) == null) {
						setInventorySlotContents(1, rec.output);
					}
					else {
						getStackInSlot(1).stackSize += rec.output.stackSize;
					}
					mark = true;	
				}
			}
		}
		
		if(energy > 0) {
			for(int i = 0; i < possibleTargets.length; i++) {
				int[] coords = possibleTargets[i];
				int dX = coords[0];
				int dY = coords[1];
				int dZ = coords[2];
				TileEntity tile = worldObj.getTileEntity(xCoord + dX, yCoord + dY, zCoord + dZ);
				if(tile != null && tile instanceof TileMithrilineCrystal) {
					TileMithrilineCrystal crystal = (TileMithrilineCrystal)tile;
					if(energy + crystal.energy <= crystal.maxEnergy) {
						crystal.energy += energy;
						energy = 0;
						mark = true;
					}
					else {
						float energyReq = crystal.maxEnergy - crystal.energy;
						if(energy >= energyReq) {
							crystal.energy = crystal.maxEnergy;
							energy -= energyReq;
							mark = true;
						}
					}
				}
			}
		}
		
		if(mark) {
			markDirty();
		}
	}
	
	public boolean isUseableByPlayer(EntityPlayer player) {
		return false;
	}
	
	public void openInventory() {}
	
	public void closeInventory() {}
	
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		return slot == 1 ? false : ESPEInfuserRecipes.doesRecipeExistByInput(stack);
	}
	
	public int[] getAccessibleSlotsFromSide(int side) {
		return new int[] {0, 1};
	}
	
	public boolean canInsertItem(int slot, ItemStack stack, int side) {
		return isItemValidForSlot(slot, stack);
	}
	
	public boolean canExtractItem(int slot, ItemStack stack, int side) {
		return slot == 1;
	}
}
