package thelm.ackroniteore.minetweaker;

import java.util.ArrayList;
import java.util.List;

import DummyCore.Utils.UnformedItemStack;
import ec3.api.MagicianTableRecipe;
import ec3.api.MagicianTableRecipes;
import ec3.api.RadiatingChamberRecipe;
import ec3.api.RadiatingChamberRecipes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.item.ItemStack;
import scala.actors.threadpool.Arrays;
import minetweaker.MineTweakerAPI;
import minetweaker.api.item.IIngredient;
import minetweaker.api.item.IItemStack;
import minetweaker.api.minecraft.MineTweakerMC;
import minetweaker.api.oredict.IOreDictEntry;

public class MineTweakerUtils {

	public MineTweakerUtils() {}
	
	public static UnformedItemStack toUnformedIS(IIngredient ingredient) {
		if(ingredient == null)
			return null;
		else {
			if(ingredient instanceof IOreDictEntry) {
				return new UnformedItemStack(((IOreDictEntry)ingredient).getName());
			}
			else if(ingredient instanceof IItemStack) {
				return new UnformedItemStack(MineTweakerMC.getItemStack((IItemStack)ingredient));
			}
			else
				return null;
		}
	}
	
	public static Class<? extends Entity> toEntity(String string) {
		Class<? extends Entity> entity = (Class<? extends Entity>)EntityList.stringToClassMapping.get(string);
		return entity;
	}
	
	public static String entityToString(Class<? extends Entity> entity) {
		String string = (String)EntityList.classToStringMapping.get(entity);
		return string;
	}
	
	public static void removeRecipe(RadiatingChamberRecipe recipe) {
		ItemStack[] req = new ItemStack[recipe.recipeItems.length];
		for(int i = 0; i < req.length; i++) {
			if(recipe.recipeItems[i] != null)
				req[i] = recipe.recipeItems[i].copy();
			else
				req[i] = null;
		}
		for(int i = 0; i < req.length; i++) {
			if(req[i] != null)
				req[i].stackSize = 0;
		}
		if(RadiatingChamberRecipes.recipes.containsKey(Arrays.toString(req)))
			RadiatingChamberRecipes.recipes.remove(Arrays.toString(req));
		
		ItemStack search = recipe.result;
		search.stackSize = 0;
		String searchStr = search.toString();
		
		RadiatingChamberRecipes.recipesByIS.remove(searchStr);
		RadiatingChamberRecipes.craftMatrixByID.remove(Arrays.toString(req));
	}
	
	public static void removeRecipe(MagicianTableRecipe recipe) {
		MagicianTableRecipes.recipes.remove(Arrays.asList(recipe.requiredItems));
		ItemStack search = recipe.result;
		search.stackSize = 0;
		String searchStr = search.toString();
		MagicianTableRecipes.recipesByIS.remove(searchStr);
		MagicianTableRecipes.craftMatrixByID.remove(Arrays.asList(recipe.requiredItems));
	}
}
