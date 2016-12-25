package thelm.ackroniteore.minetweaker;

import net.minecraft.item.ItemStack;
import ec3.api.MagicianTableRecipe;
import ec3.api.MithrilineFurnaceRecipe;
import ec3.api.MithrilineFurnaceRecipes;
import minetweaker.IUndoableAction;
import minetweaker.MineTweakerAPI;
import minetweaker.api.data.DataFloat;
import minetweaker.api.data.DataInt;
import minetweaker.api.item.IIngredient;
import minetweaker.api.item.IItemStack;
import minetweaker.api.minecraft.MineTweakerMC;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass("mods.essentialcraft.MithrilineFurnace")
public class MithrilineFurnace {

	public MithrilineFurnace() {}
	
	@ZenMethod
	public static void addRecipe(IItemStack output, IIngredient ingredient, float enderpower) {
		if(ingredient == null || output == null)
			MineTweakerAPI.getLogger().logError("Items can't be null!");
		else
			MineTweakerAPI.apply(new AddRecipeAction(new MithrilineFurnaceRecipe(MineTweakerUtils.toUnformedIS(ingredient), MineTweakerMC.getItemStack(output), enderpower, ingredient.getAmount())));
	}
	
	@ZenMethod
	public static void remove(IIngredient output, IIngredient input) {
		if(output == null)
			MineTweakerAPI.getLogger().logError("Target item can't be null!");
		else {
			boolean fail = true;
			for(MithrilineFurnaceRecipe recipe : MithrilineFurnaceRecipes.allRegisteredRecipes) {
				if(recipe.result.isItemEqual(MineTweakerMC.getItemStack(output)) && (input == null || recipe.smelted.itemStackMatches(MineTweakerMC.getItemStack(input)))) {
					MineTweakerAPI.apply(new RemoveAction(recipe));
					fail = false;
				}
			}
			if(fail)
				MineTweakerAPI.getLogger().logError("Mithriline Furnace recipe for " + ((IItemStack)output).getDisplayName() + " not found!");
		}
	}
	
	@ZenMethod
	public static void remove(IIngredient output) {
		if(output == null)
			MineTweakerAPI.getLogger().logError("Target item can't be null!");
		else {
			boolean fail = true;
			for(MithrilineFurnaceRecipe recipe : MithrilineFurnaceRecipes.allRegisteredRecipes) {
				if(recipe.result.isItemEqual(MineTweakerMC.getItemStack(output))) {
					MineTweakerAPI.apply(new RemoveAction(recipe));
					fail = false;
				}
			}
			if(fail)
				MineTweakerAPI.getLogger().logError("Mithriline Furnace recipe for " + ((IItemStack)output).getDisplayName() + " not found!");
		}
	}
	
	private static class AddRecipeAction implements IUndoableAction {
		MithrilineFurnaceRecipe recipe;
		
		public AddRecipeAction(MithrilineFurnaceRecipe rec) {
			recipe = rec;
		}
		
		public void apply() {
			MithrilineFurnaceRecipes.addRecipe(recipe);
		}
		
		public boolean canUndo() {
			return true;
		}
		
		public void undo() {
			MithrilineFurnaceRecipes.allRegisteredRecipes.remove(recipe);
		}
		
		public String describe() {
			return "Adding Mithriline Furnace recipe for " + recipe.result.getDisplayName();
		}
		
		public String describeUndo() {
			return "Removing Mithriline Furnace recipe for " + recipe.result.getDisplayName();
		}
		
		public Object getOverrideKey() {
			return null;
		}
	}
	
	private static class RemoveAction implements IUndoableAction {
		MithrilineFurnaceRecipe recipe;
		
		public RemoveAction(MithrilineFurnaceRecipe rec) {
			recipe = rec;
		}
		
		public void apply() {
			MithrilineFurnaceRecipes.allRegisteredRecipes.remove(recipe);
		}
		
		public boolean canUndo() {
			return true;
		}
		
		public void undo() {
			MithrilineFurnaceRecipes.addRecipe(recipe);
		}
		
		public String describe() {
			return "Removing Mithriline Furnace recipe for " + recipe.result.getDisplayName();
		}
		
		public String describeUndo() {
			return "Restoring Mithriline Furnace recipe for " + recipe.result.getDisplayName();
		}
		
		public Object getOverrideKey() {
			return null;
		}
	}
}
