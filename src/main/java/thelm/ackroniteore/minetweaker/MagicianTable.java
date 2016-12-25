package thelm.ackroniteore.minetweaker;

import java.util.ArrayList;
import java.util.List;

import DummyCore.Utils.UnformedItemStack;
import minetweaker.IUndoableAction;
import minetweaker.MineTweakerAPI;
import minetweaker.api.data.DataInt;
import minetweaker.api.item.IIngredient;
import minetweaker.api.item.IItemStack;
import minetweaker.api.minecraft.MineTweakerMC;
import net.minecraft.item.ItemStack;
import ec3.api.MagicianTableRecipe;
import ec3.api.MagicianTableRecipes;
import scala.actors.threadpool.Arrays;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass("mods.essentialcraft.MagicianTable")
public class MagicianTable {
	
	public MagicianTable() {}
	
	@ZenMethod
	public static void addRecipe(IItemStack output, IIngredient[] ingredients, int mru) {
		List<UnformedItemStack> items = new ArrayList<UnformedItemStack>();
		if(output == null || ingredients == null)
			MineTweakerAPI.getLogger().logError("Items can't be null!");
		else if(ingredients.length <= 5) {
			for(IIngredient in : ingredients)
				items.add(MineTweakerUtils.toUnformedIS(in));
			
			UnformedItemStack[] stack = new UnformedItemStack[items.size()];
			
			for(int i = 0; i < items.size(); i++)
				stack[i] = items.get(i);
			
			MineTweakerAPI.apply(new AddRecipeAction(new MagicianTableRecipe(stack, MineTweakerMC.getItemStack(output), mru)));
		}
		else
			MineTweakerAPI.getLogger().logError("Items length needs to be less than or equal to 5!");
	}
	
	@ZenMethod
	public static void remove(IItemStack output) {
		if(output == null)
			MineTweakerAPI.getLogger().logError("Target item can't be null!");
		else {
			MagicianTableRecipe recipe = MagicianTableRecipes.getRecipeByResult(MineTweakerMC.getItemStack(output));
			if(recipe != null)
				MineTweakerAPI.apply(new RemoveAction(recipe));
			else
				MineTweakerAPI.getLogger().logError("Magician Table recipe for " + output.getDisplayName() + " not found!");
		}
	}
	
	private static class AddRecipeAction implements IUndoableAction {
		MagicianTableRecipe recipe;
		
		public AddRecipeAction(MagicianTableRecipe rec) {
			recipe = rec;
		}
		
		public void apply() {
			MagicianTableRecipes.addRecipe(recipe);
		}
		
		public boolean canUndo() {
			return true;
		}
		
		public void undo() {
			MineTweakerUtils.removeRecipe(recipe);
		}
		
		public String describe() {
			return "Adding Magician Table recipe for " + recipe.result.getDisplayName();
		}
		
		public String describeUndo() {
			return "Removing Magician Table recipe for " + recipe.result.getDisplayName();
		}
		
		public Object getOverrideKey() {
			return null;
		}
	}
	
	private static class RemoveAction implements IUndoableAction {
		MagicianTableRecipe recipe;
		
		public RemoveAction(MagicianTableRecipe rec) {
			recipe = rec;
		}
		
		public void apply() {
			MineTweakerUtils.removeRecipe(recipe);
		}
		
		public boolean canUndo() {
			return true;
		}
		
		public void undo() {
			MagicianTableRecipes.addRecipe(recipe);
		}
		
		public String describe() {
			return "Removing Magician Table recipe for " + recipe.result.getDisplayName();
		}
		
		public String describeUndo() {
			return "Restoring Magician Table recipe for " + recipe.result.getDisplayName();
		}
		
		public Object getOverrideKey() {
			return null;
		}
	}
}
