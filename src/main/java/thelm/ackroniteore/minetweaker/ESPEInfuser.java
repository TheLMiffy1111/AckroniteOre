package thelm.ackroniteore.minetweaker;

import java.util.List;

import minetweaker.IUndoableAction;
import minetweaker.MineTweakerAPI;
import minetweaker.api.item.IIngredient;
import minetweaker.api.item.IItemStack;
import minetweaker.api.minecraft.MineTweakerMC;
import minetweaker.runtime.ILogger;
import net.minecraft.item.ItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;
import thelm.ackroniteore.api.ESPEInfuserRecipe;
import thelm.ackroniteore.api.ESPEInfuserRecipes;

@ZenClass("mods.ackroniteore.ESPEInfuser")
public class ESPEInfuser {
	
	public ESPEInfuser() {}
	
	@ZenMethod
	public static void addRecipe(IItemStack input, IItemStack output, float energy) {
		if(input == null || output == null)
			MineTweakerAPI.getLogger().logError("Items can't be null!");
		else
			MineTweakerAPI.apply(new AddRecipeAction(new ESPEInfuserRecipe(MineTweakerMC.getItemStack(input), MineTweakerMC.getItemStack(output), energy)));
	}
	
	@ZenMethod
	public static void remove(IIngredient input, IIngredient output) {
		if(input == null)
			MineTweakerAPI.getLogger().logError("Target item can't be null!");
		else {
			boolean fail = true;
			for(ESPEInfuserRecipe recipe : ESPEInfuserRecipes.allRegisteredRecipes) {
				if(recipe.input.isItemEqual(MineTweakerMC.getItemStack(input)) && (output == null || recipe.output.isItemEqual(MineTweakerMC.getItemStack(output)))) {
					MineTweakerAPI.apply(new RemoveAction(recipe));
					fail = false;
				}
			}
			if(fail)
				MineTweakerAPI.getLogger().logError("ESPE Infuser recipe for " + ((IItemStack)input).getDisplayName() + " not found!");
		}
	}
	
	@ZenMethod
	public static void remove(IIngredient input) {
		if(input == null)
			MineTweakerAPI.getLogger().logError("Target item can't be null!");
		else {
			boolean fail = true;
			for(ESPEInfuserRecipe recipe : ESPEInfuserRecipes.allRegisteredRecipes) {
				if(recipe.input.isItemEqual(MineTweakerMC.getItemStack(input))) {
					MineTweakerAPI.apply(new RemoveAction(recipe));
					fail = false;
				}
			}
			if(fail)
				MineTweakerAPI.getLogger().logError("ESPE Infuser recipe for " + ((IItemStack)input).getDisplayName() + " not found!");
		}
	}
	
	private static class AddRecipeAction implements IUndoableAction {
		ESPEInfuserRecipe recipe;
		
		public AddRecipeAction(ESPEInfuserRecipe rec) {
			recipe = rec;
		}
		
		public void apply() {
			ESPEInfuserRecipes.addRecipe(recipe);
		}
		
		public boolean canUndo() {
			return true;
		}
		
		public void undo() {
			ESPEInfuserRecipes.allRegisteredRecipes.remove(recipe);
		}
		
		public String describe() {
			return "Adding ESPE Infuser recipe for " + recipe.input.getDisplayName();
		}
		
		public String describeUndo() {
			return "Removing ESPE Infuser recipe for " + recipe.input.getDisplayName();
		}
		
		public Object getOverrideKey() {
			return null;
		}
	}
	
	private static class RemoveAction implements IUndoableAction {
		ESPEInfuserRecipe recipe;
		
		public RemoveAction(ESPEInfuserRecipe rec) {
			recipe = rec;
		}
		
		public void apply() {
			ESPEInfuserRecipes.allRegisteredRecipes.remove(recipe);
		}
		
		public boolean canUndo() {
			return true;
		}
		
		public void undo() {
			ESPEInfuserRecipes.addRecipe(recipe);
		}
		
		public String describe() {
			return "Removing ESPE Infuser recipe for " + recipe.input.getDisplayName();
		}
		
		public String describeUndo() {
			return "Restoring ESPE Infuser recipe for " + recipe.input.getDisplayName();
		}
		
		public Object getOverrideKey() {
			return null;
		}
	}
}
