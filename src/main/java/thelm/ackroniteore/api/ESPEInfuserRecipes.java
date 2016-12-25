package thelm.ackroniteore.api;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;

public class ESPEInfuserRecipes {
	public final static List<ESPEInfuserRecipe> allRegisteredRecipes = new ArrayList();
	
	public static void addRecipe(ItemStack input, ItemStack output, float energy) {
		addRecipe(new ESPEInfuserRecipe(input, output, energy));
	}
	
	public static void addRecipe(ESPEInfuserRecipe rec) {
		if(allRegisteredRecipes.contains(rec))
			return;
		allRegisteredRecipes.add(rec);
	}
	
	public static ESPEInfuserRecipe findRecipeByInput(ItemStack is) {
		for(int i = 0; i < allRegisteredRecipes.size(); i++) {
			ESPEInfuserRecipe recipe = (ESPEInfuserRecipe)allRegisteredRecipes.get(i);
			if (recipe != null && recipe.input.isItemEqual(is)) {
				return recipe;
			}
		}
		return null;
	}
	
	public static ESPEInfuserRecipe findRecipeByOutput(ItemStack is) {
		for(int i = 0; i < allRegisteredRecipes.size(); i++) {
			ESPEInfuserRecipe recipe = (ESPEInfuserRecipe)allRegisteredRecipes.get(i);
			if(recipe != null && recipe.input.isItemEqual(is)) {
				return recipe;
			}
		}
		return null;
	}
	
	public static boolean doesRecipeExistByInput(ItemStack is) {
		for(int i = 0; i < allRegisteredRecipes.size(); i++) {
			ESPEInfuserRecipe recipe = (ESPEInfuserRecipe)allRegisteredRecipes.get(i);
			if (recipe != null && recipe.input.isItemEqual(is)) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean doesRecipeExistByOutput(ItemStack is) {
		for(int i = 0; i < allRegisteredRecipes.size(); i++) {
			ESPEInfuserRecipe recipe = (ESPEInfuserRecipe)allRegisteredRecipes.get(i);
			if (recipe != null && recipe.output.isItemEqual(is)) {
				return true;
			}
		}
		return false;
	}
}
