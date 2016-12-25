package thelm.ackroniteore.api;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

public class ESPEInfuserRecipe implements IRecipe {
	public final ItemStack input;
	public final ItemStack output;
	public final float energy;
	
	public ESPEInfuserRecipe(ItemStack isi, ItemStack iso, float f) {
		input = isi;
		output = iso;
		energy = f;
	}
	
	public boolean matches(InventoryCrafting inv, World worldIn) {
		return input.isItemEqual(inv.getStackInSlot(0));
	}
	
	public ItemStack getCraftingResult(InventoryCrafting inv) {
		return output;
	}
	
	public int getRecipeSize() {
		return 2;
	}
	
	public ItemStack getRecipeOutput() {
		return output;
	}
}
