package thelm.ackroniteore.minetweaker;

import java.util.ArrayList;
import java.util.List;

import DummyCore.Utils.UnformedItemStack;
import minetweaker.IUndoableAction;
import minetweaker.MineTweakerAPI;
import minetweaker.api.data.DataFloat;
import minetweaker.api.data.DataInt;
import minetweaker.api.item.IIngredient;
import minetweaker.api.item.IItemStack;
import minetweaker.api.minecraft.MineTweakerMC;
import net.minecraft.item.ItemStack;
import ec3.api.RadiatingChamberRecipe;
import ec3.api.RadiatingChamberRecipes;
import scala.actors.threadpool.Arrays;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass("mods.essentialcraft.RadiatingChamber")
public class RadiatingChamber {

	public RadiatingChamber() {}
	
	@ZenMethod
	public static void addRecipe(IItemStack output, IItemStack[] ingredients, int mru, float upperBalance, float lowerBalance, float modifier) {
		if(output == null || ingredients == null)
			MineTweakerAPI.getLogger().logError("Items can't be null!");
		else if(ingredients.length <= 2)
			MineTweakerAPI.apply(new AddRecipeAction(new RadiatingChamberRecipe(MineTweakerMC.getItemStacks(ingredients), MineTweakerMC.getItemStack(output), mru, new float[] {upperBalance, lowerBalance}, modifier)));
		else
			MineTweakerAPI.getLogger().logError("Items length needs to be less than or equal to 2!");
	}
	
	@ZenMethod
	public static void addRecipe(IItemStack output, IItemStack[] ingredients, int mru, float upperBalance, float lowerBalance) {
		if(output == null || ingredients == null)
			MineTweakerAPI.getLogger().logError("Items can't be null!");
		else if(ingredients.length <= 2)
			MineTweakerAPI.apply(new AddRecipeAction(new RadiatingChamberRecipe(MineTweakerMC.getItemStacks(ingredients), MineTweakerMC.getItemStack(output), mru, new float[] {upperBalance, lowerBalance}, 1)));
		else
			MineTweakerAPI.getLogger().logError("Items length needs to be less than or equal to 2!");
	}
	
	@ZenMethod
	public static void addRecipe(IItemStack output, IItemStack[] ingredients, int mru, float modifier) {
		if(output == null || ingredients == null)
			MineTweakerAPI.getLogger().logError("Items can't be null!");
		else if(ingredients.length <= 2)
			MineTweakerAPI.apply(new AddRecipeAction(new RadiatingChamberRecipe(MineTweakerMC.getItemStacks(ingredients), MineTweakerMC.getItemStack(output), mru, new float[] {0.0F, 2.0F}, modifier)));
		else
			MineTweakerAPI.getLogger().logError("Items length needs to be less than or equal to 2!");	
	}
	
	@ZenMethod
	public static void addRecipe(IItemStack output, IItemStack[] ingredients, int mru) {
		if(output == null || ingredients == null)
			MineTweakerAPI.getLogger().logError("Items can't be null!");
		else if(ingredients.length <= 2)
			MineTweakerAPI.apply(new AddRecipeAction(new RadiatingChamberRecipe(MineTweakerMC.getItemStacks(ingredients), MineTweakerMC.getItemStack(output), mru, new float[] {0.0F, 2.0F}, 1, 1)));
		else
			MineTweakerAPI.getLogger().logError("Items length needs to be less than or equal to 2!");	
	}
	
	@ZenMethod
	public static void remove(IIngredient output) {
		if(output == null)
			MineTweakerAPI.getLogger().logError("Target item can't be null!");
		else {
			RadiatingChamberRecipe recipe = RadiatingChamberRecipes.getRecipeByResult(MineTweakerMC.getItemStack(output));
			if(recipe != null)
				MineTweakerAPI.apply(new RemoveAction(recipe));
			else
				MineTweakerAPI.getLogger().logError("Radiating Chamber Recipe for " + ((IItemStack)output).getDisplayName() + " not found!");
		}
	}
	
	
	private static class AddRecipeAction implements IUndoableAction {
		RadiatingChamberRecipe recipe;
		
		public AddRecipeAction(RadiatingChamberRecipe rec) {
			recipe = rec;
		}
		
		public void apply() {	
			RadiatingChamberRecipes.addRecipe(recipe);
		}
		
		public boolean canUndo() {
			return true;
		}
		
		public void undo() {
			MineTweakerUtils.removeRecipe(recipe);
		}
		
		public String describe() {
			return "Adding Radiating Chamber recipe for " + recipe.result.getDisplayName();
		}
		
		public String describeUndo() {
			return "Removing Radiating Chamber recipe for " + recipe.result.getDisplayName();
		}
		
		public Object getOverrideKey() {
			return null;
		}
		
	}
	
	private static class RemoveAction implements IUndoableAction {
		RadiatingChamberRecipe recipe;
		
		public RemoveAction(RadiatingChamberRecipe rec) {
			recipe = rec;
		}
		
		public void apply() {
			MineTweakerUtils.removeRecipe(recipe);
		}
		
		public boolean canUndo() {
			return true;
		}
		
		public void undo() {
			RadiatingChamberRecipes.addRecipe(recipe);
		}
		
		public String describe() {
			return "Removing Radiating Chamber recipe for " + recipe.getRecipeOutput().getDisplayName();
		}
		
		public String describeUndo() {
			return "Restoring Radiating Chamber recipe for " + recipe.getRecipeOutput().getDisplayName();
		}
		
		public Object getOverrideKey() {
			return null;
		}
		
	}
}
