package thelm.ackroniteore.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import thelm.ackroniteore.AckroniteOre;
import thelm.ackroniteore.tile.TileESPEInfuser;

public class BlockESPEInfuser extends BlockContainer {
    private final Random random = new Random();
	
	public BlockESPEInfuser() {
		super(Material.iron);
		setCreativeTab(AckroniteOre.tabAckroniteOre);
		setBlockTextureName("ackroniteore:BlockESPEInfuser");
		setBlockName("ackroniteore.BlockESPEInfuser");
		setStepSound(soundTypeMetal);
		setHardness(5.0F);
		setLightLevel(0.5F);
        isBlockContainer = true;
	}
	
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileESPEInfuser();
	}
	
	public void breakBlock(World worldIn, int x, int y, int z, Block block, int meta) {
		TileESPEInfuser tile = (TileESPEInfuser)worldIn.getTileEntity(x, y, z);
		
		if(tile != null) {
			for(int i1 = 0; i1 < tile.getSizeInventory(); ++i1) {
				ItemStack itemstack = tile.getStackInSlot(i1);
				
				if(itemstack != null) {
					float f = random.nextFloat() * 0.8F + 0.1F;
					float f1 = random.nextFloat() * 0.8F + 0.1F;
					float f2 = random.nextFloat() * 0.8F + 0.1F;
					
					while(itemstack.stackSize > 0) {
						int j1 = random.nextInt(21) + 10;
						
						if(j1 > itemstack.stackSize) {
							j1 = itemstack.stackSize;
						}
						
						itemstack.stackSize -= j1;
						EntityItem entityitem = new EntityItem(worldIn, (double)((float)x + f), (double)((float)y + f1), (double)((float)z + f2), new ItemStack(itemstack.getItem(), j1, itemstack.getItemDamage()));
						
						if(itemstack.hasTagCompound()) {
							entityitem.getEntityItem().setTagCompound((NBTTagCompound)itemstack.getTagCompound().copy());
						}
						
						float f3 = 0.05F;
						entityitem.motionX = (double)((float)random.nextGaussian() * f3);
						entityitem.motionY = (double)((float)random.nextGaussian() * f3 + 0.2F);
						entityitem.motionZ = (double)((float)random.nextGaussian() * f3);
						worldIn.spawnEntityInWorld(entityitem);
					}
				}
				
				worldIn.func_147453_f(x, y, z, block);
            }
        }

        super.breakBlock(worldIn, x, y, z, block, meta);
    }
	
	public boolean hasComparatorInputOverride()	{
		return true;
	}
	
	public int getComparatorInputOverride(World worldIn, int x, int y, int z, int meta) {
		return Container.calcRedstoneFromInventory((IInventory)worldIn.getTileEntity(x, y, z));
	}
	
	public boolean isBeaconBase(IBlockAccess w, int x, int y, int z, int x2, int y2, int z2) {
		return true;
	}
}
