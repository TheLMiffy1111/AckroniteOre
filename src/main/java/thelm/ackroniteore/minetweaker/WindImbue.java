package thelm.ackroniteore.minetweaker;

import minetweaker.IUndoableAction;
import minetweaker.MineTweakerAPI;
import minetweaker.api.data.DataInt;
import minetweaker.api.item.IIngredient;
import minetweaker.api.item.IItemStack;
import minetweaker.api.minecraft.MineTweakerMC;
import net.minecraft.item.ItemStack;
import ec3.api.MagicianTableRecipe;
import ec3.api.MagicianTableRecipes;
import ec3.api.WindImbueRecipe;
import scala.actors.threadpool.Arrays;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass("mods.essentialcraft.WindImbue")
public class WindImbue {

	public WindImbue() {}
	
	@ZenMethod
	public static void addRecipe(IItemStack output, IItemStack input, int energy) {
		if(input == null || output == null)
			MineTweakerAPI.getLogger().logError("Items can't be null!");
		else
			MineTweakerAPI.apply(new AddRecipeAction(new WindImbueRecipe(MineTweakerMC.getItemStack(input), MineTweakerMC.getItemStack(output), energy)));
	}
	
	@ZenMethod
	public static void remove(IIngredient output, IIngredient input) {
		if(output == null)
			MineTweakerAPI.getLogger().logError("Target item can't be null!");
		else {
			boolean fail = true;
			for(WindImbueRecipe recipe : WindImbueRecipe.recipes) {
				if(recipe.result.isItemEqual(MineTweakerMC.getItemStack(output)) && (input == null || recipe.transforming.isItemEqual(MineTweakerMC.getItemStack(input)))) {
					MineTweakerAPI.apply(new RemoveAction(recipe));
					fail = false;
				}	
			}
			if(fail)
				MineTweakerAPI.getLogger().logError("Wind Imbue recipe for " + ((IItemStack)output).getDisplayName() + " not found!");
		}
	}
	
	@ZenMethod
	public static void remove(IIngredient output) {
		if(output == null)
			MineTweakerAPI.getLogger().logError("Target item can't be null!");
		else {
			boolean fail = true;
			for(WindImbueRecipe recipe : WindImbueRecipe.recipes) {
				if(recipe.result.isItemEqual(MineTweakerMC.getItemStack(output))) {
					MineTweakerAPI.apply(new RemoveAction(recipe));
					fail = false;
				}	
			}
			if(fail)
				MineTweakerAPI.getLogger().logError("Wind Imbue recipe for " + ((IItemStack)output).getDisplayName() + " not found!");
		}
	}
	
	private static class AddRecipeAction implements IUndoableAction {
		WindImbueRecipe recipe;
		
		public AddRecipeAction(WindImbueRecipe rec) {
			recipe = rec;
		}
		
		public void apply() {
			if(!WindImbueRecipe.recipes.contains(recipe))
				WindImbueRecipe.recipes.add(recipe);
		}
		
		public boolean canUndo() {
			return true;
		}
		
		public void undo() {
			WindImbueRecipe.recipes.remove(recipe);
		}
		
		public String describe() {
			return "Adding Wind Imbue recipe for " + recipe.result.getDisplayName();
		}
		
		public String describeUndo() {
			return "Removing Wind Imbue recipe for " + recipe.result.getDisplayName();
		}
		
		public Object getOverrideKey() {
			return null;
		}
	}
	
	private static class RemoveAction implements IUndoableAction {
		WindImbueRecipe recipe;
		
		public RemoveAction(WindImbueRecipe rec) {
			recipe = rec;
		}
		
		public void apply() {
			WindImbueRecipe.recipes.remove(recipe);
		}
		
		public boolean canUndo() {
			return true;
		}
		
		public void undo() {
			if(!WindImbueRecipe.recipes.contains(recipe))
				WindImbueRecipe.recipes.add(recipe);
		}
		
		public String describe() {
			return "Removing Wind Imbue recipe for " + recipe.result.getDisplayName();
		}
		
		public String describeUndo() {
			return "Restoring Wind Imbue recipe for " + recipe.result.getDisplayName();
		}
		
		public Object getOverrideKey() {
			return null;
		}
	}
}
