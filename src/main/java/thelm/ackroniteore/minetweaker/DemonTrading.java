package thelm.ackroniteore.minetweaker;

import ec3.api.DemonTrade;
import ec3.api.WindImbueRecipe;
import minetweaker.IUndoableAction;
import minetweaker.MineTweakerAPI;
import minetweaker.api.item.IItemStack;
import minetweaker.api.minecraft.MineTweakerMC;
import net.minecraft.entity.Entity;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass("mods.essentialcraft.DemonTrade")
public class DemonTrading {
	
	public DemonTrading() {}
	
	@ZenMethod
	public static void addRecipe(IItemStack input) {
		if(input == null)
			MineTweakerAPI.getLogger().logError("Item can't be null!");
		else {
			MineTweakerAPI.apply(new AddRecipeAction(new DemonTrade(MineTweakerMC.getItemStack(input))));
		}
	}
	
	@ZenMethod
	public static void addRecipe(String input) {
		if(input == null)
			MineTweakerAPI.getLogger().logError("Entity can't be null!");
		else {
			MineTweakerAPI.apply(new AddEntityRecipeAction(new DemonTrade(MineTweakerUtils.toEntity(input))));
		}
	}
	
	@ZenMethod
	public static void remove(IItemStack input) {
		if(input == null)
			MineTweakerAPI.getLogger().logError("Target item can't be null!");
		else {
			boolean fail = true;
			for(DemonTrade recipe : DemonTrade.trades) {
				if(recipe.desiredItem.isItemEqual(MineTweakerMC.getItemStack(input))) {
					MineTweakerAPI.apply(new RemoveAction(recipe));
					fail = false;
				}	
			}
			if(fail)
				MineTweakerAPI.getLogger().logError("Demon Trade recipe for " + input.getDisplayName() + " not found!");
		}
	}
	
	@ZenMethod
	public static void remove(String input) {
		if(input == null)
			MineTweakerAPI.getLogger().logError("Target entity can't be null!");
		else {
			boolean fail = true;
			for(DemonTrade recipe : DemonTrade.trades) {
				if(recipe.entityType.equals(MineTweakerUtils.toEntity(input))) {
					MineTweakerAPI.apply(new RemoveEntityAction(recipe));
					fail = false;
				}	
			}
			if(fail)
				MineTweakerAPI.getLogger().logError("Demon Trade recipe for " + input + " not found!");
		}
	}
	
	private static class AddRecipeAction implements IUndoableAction {
		DemonTrade recipe;
		
		public AddRecipeAction(DemonTrade rec) {
			recipe = rec;
		}
		
		public void apply() {
			if(!DemonTrade.trades.contains(recipe))
				DemonTrade.trades.add(recipe);
		}
		
		public boolean canUndo() {
			return true;
		}
		
		public void undo() {
			DemonTrade.trades.remove(recipe);
		}
		
		public String describe() {
			return "Adding Demon Trade recipe for " + recipe.desiredItem.getDisplayName();
		}
		
		public String describeUndo() {
			return "Removing Demon Trade recipe for " + recipe.desiredItem.getDisplayName();
		}
		
		public Object getOverrideKey() {
			return null;
		}
	}
	
	private static class AddEntityRecipeAction implements IUndoableAction {
		DemonTrade recipe;
		
		public AddEntityRecipeAction(DemonTrade rec) {
			recipe = rec;
		}
		
		public void apply() {
			if(!DemonTrade.trades.contains(recipe)) {
				DemonTrade.trades.add(recipe);
				DemonTrade.allMobs.add(recipe.entityType);
			}
		}
		
		public boolean canUndo() {
			return true;
		}
		
		public void undo() {
			DemonTrade.trades.remove(recipe);
			DemonTrade.allMobs.remove(recipe.entityType);
		}
		
		public String describe() {
			return "Adding Demon Trade recipe for " + MineTweakerUtils.entityToString(recipe.entityType);
		}
		
		public String describeUndo() {
			return "Removing Demon Trade recipe for " + MineTweakerUtils.entityToString(recipe.entityType);
		}
		
		public Object getOverrideKey() {
			return null;
		}
	}
	
	private static class RemoveAction implements IUndoableAction {
		DemonTrade recipe;
		
		public RemoveAction(DemonTrade rec) {
			recipe = rec;
		}
		
		public void apply() {
			DemonTrade.trades.remove(recipe);
		}
		
		public boolean canUndo() {
			return true;
		}
		
		public void undo() {
			if(!DemonTrade.trades.contains(recipe))
				DemonTrade.trades.add(recipe);
		}
		
		public String describe() {
			return "Removing Demon Trade recipe for " + recipe.desiredItem.getDisplayName();
		}
		
		public String describeUndo() {
			return "Restoring Demon Trade recipe for " + recipe.desiredItem.getDisplayName();
		}
		
		public Object getOverrideKey() {
			return null;
		}
	}
	
	private static class RemoveEntityAction implements IUndoableAction {
		DemonTrade recipe;
		
		public RemoveEntityAction(DemonTrade rec) {
			recipe = rec;
		}
		
		public void apply() {
			DemonTrade.trades.remove(recipe);
			DemonTrade.allMobs.remove(recipe.entityType);
		}
		
		public boolean canUndo() {
			return true;
		}
		
		public void undo() {
			if(!DemonTrade.trades.contains(recipe)) {
				DemonTrade.trades.add(recipe);
				DemonTrade.allMobs.add(recipe.entityType);
			}
		}
		
		public String describe() {
			return "Removing Demon Trade recipe for " + MineTweakerUtils.entityToString(recipe.entityType);
		}
		
		public String describeUndo() {
			return "Restoring Demon Trade recipe for " + MineTweakerUtils.entityToString(recipe.entityType);
		}
		
		public Object getOverrideKey() {
			return null;
		}
	}
}
