package thelm.ackroniteore.registry;

import cpw.mods.fml.common.registry.GameRegistry;
import ec3.api.MagicianTableRecipes;
import ec3.api.RadiatingChamberRecipes;
import ec3.common.block.BlocksCore;
import ec3.common.item.ItemsCore;
import essentialThaumaturgy.common.init.ItemsInit;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import thaumcraft.api.ThaumcraftApi;
import thelm.ackroniteore.api.ESPEInfuserRecipes;
import thelm.ackroniteore.config.ConfigHandler;

public class RecipeNoThaumRegistry {
	
	public static void init() {
		initCrafting();
		initSmelting();
		initRadiatingChamber();
		initMagicianTable();
		initESPE();
	}
	
	public static void initCrafting() {
		CraftingManager.getInstance().addRecipe(new ItemStack(BlockRegistry.BlockESPECrystal, 1, 0), new Object[]{
				"AAA",
				"AAA",
				"AAA",
				Character.valueOf('A'),
				new ItemStack(ItemRegistry.ItemResource, 1, 0),
		});
		
		CraftingManager.getInstance().addRecipe(new ItemStack(BlockRegistry.BlockESPECrystal, 4, 1), new Object[]{
				"AA",
				"AA",
				Character.valueOf('A'),
				new ItemStack(BlockRegistry.BlockESPECrystal, 1, 0),
		});
		
		CraftingManager.getInstance().addRecipe(new ItemStack(BlockRegistry.BlockESPECrystal, 4, 2), new Object[]{
				"AAA",
				"A A",
				"AAA",
				Character.valueOf('A'),
				new ItemStack(BlockRegistry.BlockESPECrystal, 1, 1),
		});
		
		CraftingManager.getInstance().addRecipe(new ItemStack(BlockRegistry.BlockESPECrystal, 4, 0), new Object[]{
				"AA",
				"AA",
				Character.valueOf('A'),
				new ItemStack(BlockRegistry.BlockESPECrystal, 1, 1),
		});
		
		CraftingManager.getInstance().addRecipe(new ItemStack(BlockRegistry.BlockESPECrystal, 4, 0), new Object[]{
				"AA",
				"AA",
				Character.valueOf('A'),
				new ItemStack(BlockRegistry.BlockESPECrystal, 1, 2),
		});
		
		CraftingManager.getInstance().addShapelessRecipe(new ItemStack(ItemRegistry.ItemResource, 9, 0), new Object[]{
				new ItemStack(BlockRegistry.BlockESPECrystal, 1, 0),
		});
		
		CraftingManager.getInstance().addShapelessRecipe(new ItemStack(ItemRegistry.ItemResource, 9, 0), new Object[]{
				new ItemStack(BlockRegistry.BlockESPECrystal, 1, 1),
		});
		
		CraftingManager.getInstance().addShapelessRecipe(new ItemStack(ItemRegistry.ItemResource, 9, 0), new Object[]{
				new ItemStack(BlockRegistry.BlockESPECrystal, 1, 2),
		});
		
		CraftingManager.getInstance().addRecipe(new ItemStack(ItemsCore.genericItem, 1, 52), new Object[]{
				"AAA",
				"AAA",
				"AAA",
				Character.valueOf('A'),
				new ItemStack(ItemRegistry.ItemResource, 9, 1),
		});
		
		CraftingManager.getInstance().addShapelessRecipe(new ItemStack(ItemRegistry.ItemResource, 9, 1), new Object[]{
				new ItemStack(ItemsCore.genericItem, 1, 52),
		});
		
		CraftingManager.getInstance().addRecipe(new ItemStack(ItemsCore.genericItem, 1, 36), new Object[]{
				"ABA",
				"BCB",
				"ABA",
				Character.valueOf('A'),
				new ItemStack(ItemsCore.genericItem, 1, 35),
				Character.valueOf('B'),
				new ItemStack(ItemsCore.drops, 1, 4),
				Character.valueOf('C'),
				new ItemStack(ItemsCore.genericItem, 1, 5),
		});
		
		CraftingManager.getInstance().addRecipe(new ItemStack(ItemsCore.genericItem, 1, 37), new Object[]{
				"ABA",
				"CDC",
				"AEA",
				Character.valueOf('A'),
				new ItemStack(ItemsCore.genericItem, 1, 35),
				Character.valueOf('B'),
				new ItemStack(ItemsCore.genericItem, 1, 29),
				Character.valueOf('C'),
				new ItemStack(ItemsCore.genericItem, 1, 23),
				Character.valueOf('D'),
				new ItemStack(ItemsCore.genericItem, 1, 36),
				Character.valueOf('E'),
				new ItemStack(ItemsCore.genericItem, 1, 25),
		});
		
		CraftingManager.getInstance().addRecipe(new ItemStack(ItemsCore.genericItem, 1, 36), new Object[]{
				"ABA",
				"CDE",
				"ABA",
				Character.valueOf('A'),
				new ItemStack(BlockRegistry.BlockESPECrystal, 1, 0),
				Character.valueOf('B'),
				new ItemStack(BlocksCore.mithrilineCrystal, 1, 6),
				Character.valueOf('C'),
				new ItemStack(ItemsCore.bound_gem, 1, 0),
				Character.valueOf('D'),
				new ItemStack(BlocksCore.radiatingChamber, 1, 0),
				Character.valueOf('E'),
				new ItemStack(ItemsCore.genericItem, 1, 55),
		});
	}
	
	public static void initSmelting() {
		GameRegistry.addSmelting(new ItemStack(BlockRegistry.BlockAckroniteOre, 1, 0), new ItemStack(ItemsCore.genericItem, 1, 52), 2.0F);
	}
	
	public static void initRadiatingChamber() {
		RadiatingChamberRecipes.addRecipeIS(new ItemStack[] {
				new ItemStack(ItemsCore.genericItem, 1, 47),
		}, new ItemStack(ItemRegistry.ItemResource, 1, 0), 100, new float[] {0.0F, 2.0F}, 10.0F);
		
		RadiatingChamberRecipes.addRecipeIS(new ItemStack[] {
				new ItemStack(ItemsCore.titanite, 1, 0),
				new ItemStack(Blocks.stone, 1, 0)
		}, new ItemStack(ItemsCore.twinkling_titanite, 2, 0), 100, new float[] {0.0F, 2.0F});
		
		RadiatingChamberRecipes.addRecipeIS(new ItemStack[] {
				new ItemStack(ItemsCore.titanite, 1, 0),
				new ItemStack(Items.glowstone_dust, 1, 0)
		}, new ItemStack(ItemsCore.twinkling_titanite, 1, 0), 100, new float[] {0.0F, 2.0F});
	}
	
	public static void initMagicianTable() {
		MagicianTableRecipes.addRecipeIS(new ItemStack[] {
				new ItemStack(Blocks.netherrack, 1, 0),
				new ItemStack(ItemsCore.genericItem, 1, 52),
				new ItemStack(Blocks.glowstone, 1, 0),
				new ItemStack(ItemsCore.genericItem, 1, 38),
				new ItemStack(ItemsCore.drops, 1, 0),
				}, new ItemStack(BlockRegistry.BlockAckroniteOre, ConfigHandler.ORE1 ? 1 : 2, 0), 1000);
		
		MagicianTableRecipes.addRecipeIS(new ItemStack[] {
				new ItemStack(Items.diamond, 1, 0),
				new ItemStack(BlockRegistry.BlockESPECrystal, 1, 0),
				new ItemStack(BlockRegistry.BlockESPECrystal, 1, 0),
				new ItemStack(BlockRegistry.BlockESPECrystal, 1, 0),
				new ItemStack(ItemsCore.drops, 1, 3),
				}, new ItemStack(ItemsCore.genericItem, 1, 55), 500);
		
		MagicianTableRecipes.addRecipeIS(new ItemStack[] {
				new ItemStack(Items.potionitem, 1, 0),
				new ItemStack(ItemRegistry.ItemResource, 1, 0),
				new ItemStack(ItemsCore.drops, 1, 3),
				}, new ItemStack(ItemsCore.air_potion, 1, 0), 100);
	}
	
	public static void initESPE() {
		ESPEInfuserRecipes.addRecipe(new ItemStack(ItemRegistry.ItemResource, 1, 0), new ItemStack(ItemsCore.genericItem, 1, 44), 360.0F);
		
		ESPEInfuserRecipes.addRecipe(new ItemStack(BlockRegistry.BlockESPECrystal, 1, 0), new ItemStack(ItemsCore.genericItem, 9, 44), 3240.0F);
	}
}
