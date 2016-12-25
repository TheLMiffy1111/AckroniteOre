package thelm.ackroniteore.registry;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;
import ec3.api.MagicianTableRecipes;
import ec3.api.RadiatingChamberRecipes;
import ec3.common.block.BlocksCore;
import ec3.common.item.ItemsCore;
import essentialThaumaturgy.common.init.AspectsInit;
import essentialThaumaturgy.common.init.ItemsInit;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import thaumcraft.api.ItemApi;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thelm.ackroniteore.api.ESPEInfuserRecipes;
import thelm.ackroniteore.config.ConfigHandler;

public class RecipeRegistry {
	
	public static void init() {
		initCrafting();
		initSmelting();
		initArcane();
		initCrucible();
		initInfusion();
		initESPE();
	}
	
	public static void initCrafting() {
		ResearchRegistry.recipes.put("BlockESPECrystal", CraftingManager.getInstance().addRecipe(new ItemStack(BlockRegistry.BlockESPECrystal, 1, 0), new Object[]{
				"AAA",
				"AAA",
				"AAA",
				Character.valueOf('A'),
				new ItemStack(ItemRegistry.ItemResource, 1, 0),
		}));
		
		ResearchRegistry.recipes.put("BBlockESPECrystal", CraftingManager.getInstance().addRecipe(new ItemStack(BlockRegistry.BlockESPECrystal, 4, 1), new Object[]{
				"AA",
				"AA",
				Character.valueOf('A'),
				new ItemStack(BlockRegistry.BlockESPECrystal, 1, 0),
		}));
		
		ResearchRegistry.recipes.put("HBlockESPECrystal", CraftingManager.getInstance().addRecipe(new ItemStack(BlockRegistry.BlockESPECrystal, 4, 2), new Object[]{
				"AAA",
				"A A",
				"AAA",
				Character.valueOf('A'),
				new ItemStack(BlockRegistry.BlockESPECrystal, 1, 1),
		}));
		
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
	}
	
	public static void initSmelting() {
		GameRegistry.addSmelting(new ItemStack(BlockRegistry.BlockAckroniteOre, 1, 0), new ItemStack(ItemsCore.genericItem, 1, 52), 2.0F);
		
		GameRegistry.addSmelting(new ItemStack(ItemRegistry.ItemResource, 1, 2), new ItemStack(ItemsCore.genericItem, 2, 52), 1.0F);
			
		ThaumcraftApi.addSmeltingBonus(new ItemStack(BlockRegistry.BlockAckroniteOre, 1, 0), new ItemStack(ItemRegistry.ItemResource, 0, 1));
		
		ThaumcraftApi.addSmeltingBonus(new ItemStack(ItemRegistry.ItemResource, 1, 2), new ItemStack(ItemRegistry.ItemResource, 0, 1));
	}
	
	public static void initArcane() {
		ResearchRegistry.recipes.put("VoidCore", ThaumcraftApi.addArcaneCraftingRecipe("VOIDCORE", new ItemStack(ItemsCore.genericItem, 1, 36), new AspectList().add(Aspect.AIR, 8).add(Aspect.FIRE, 6).add(Aspect.ORDER, 4).add(Aspect.ENTROPY, 6), new Object[]{
				"ABA",
				"BCB",
				"ABA",
				Character.valueOf('A'),
				new ItemStack(ItemsCore.genericItem, 1, 35),
				Character.valueOf('B'),
				new ItemStack(ItemsCore.drops, 1, 4),
				Character.valueOf('C'),
				new ItemStack(ItemsCore.genericItem, 1, 5),
		}));
	}
	
	public static void initCrucible() {
		ResearchRegistry.recipes.put("Titanite", ThaumcraftApi.addCrucibleRecipe("TITANITEDUPLICATION", new ItemStack(ItemsCore.titanite, 2, 0), new ItemStack(ItemsCore.titanite, 1, 0), new AspectList().merge(AspectsInit.MRU, 2).merge(Aspect.EARTH, 2).merge(Aspect.VOID, 2)));
		
		ResearchRegistry.recipes.put("TTitanite", ThaumcraftApi.addCrucibleRecipe("TWINKLINGTITANITE", new ItemStack(ItemsCore.twinkling_titanite, 1, 0), new ItemStack(ItemsCore.titanite, 1, 0), new AspectList().merge(AspectsInit.MRU, 2).merge(Aspect.LIGHT, 2).merge(Aspect.ENERGY, 2)));
		
		ResearchRegistry.recipes.put("ESPECrystal", ThaumcraftApi.addCrucibleRecipe("ESPECRYSTAL", new ItemStack(ItemRegistry.ItemResource, 1, 0), new ItemStack(ItemsCore.genericItem, 1, 47), new AspectList().merge(Aspect.AIR, 2).merge(Aspect.ENERGY, 2).merge(Aspect.ORDER, 2)));
		
		ResearchRegistry.recipes.put("PureAckronite", ThaumcraftApi.addCrucibleRecipe("PUREACKRONITE", new ItemStack(ItemRegistry.ItemResource, 1, 2), new ItemStack(BlockRegistry.BlockAckroniteOre, 1, 0), new AspectList().merge(Aspect.METAL, 1).merge(Aspect.ORDER, 1)));
	}
	
	public static void initInfusion() {
		ResearchRegistry.recipes.put("BlockAckroniteOre", ThaumcraftApi.addInfusionCraftingRecipe("ACKRONITEORE", new ItemStack(BlockRegistry.BlockAckroniteOre, ConfigHandler.ORE1 ? 1 : 2, 0), 4, new AspectList().add(Aspect.LIGHT, 4).add(Aspect.FIRE, 4).add(Aspect.METAL, 4).add(AspectsInit.MRU, 4), new ItemStack(Blocks.netherrack, 1, 0), new ItemStack[]{
				new ItemStack(ItemsCore.genericItem, 1, 52),
				new ItemStack(Blocks.glowstone, 1, 0),
				ItemApi.getItem("itemResource", 17),
				ItemApi.getItem("itemShard", 1),
		}));
		
		ResearchRegistry.recipes.put("WindCrystal", ThaumcraftApi.addInfusionCraftingRecipe("WINDCRYSTAL", new ItemStack(ItemsCore.genericItem, 1, 55), 4, new AspectList().add(AspectsInit.RADIATION, 8).add(AspectsInit.MRU, 8).add(Aspect.ENERGY, 4).add(Aspect.AIR, 16).add(Aspect.CRYSTAL, 4), new ItemStack(Items.diamond, 1, 0), new ItemStack[]{
				new ItemStack(BlockRegistry.BlockESPECrystal, 1, 0),
				new ItemStack(BlockRegistry.BlockESPECrystal, 1, 0),
				ItemApi.getItem("itemShard", 0),
				new ItemStack(BlockRegistry.BlockESPECrystal, 1, 0),
		}));
		
		ResearchRegistry.recipes.put("AirPotion", ThaumcraftApi.addInfusionCraftingRecipe("AIRPOTION", new ItemStack(ItemsCore.air_potion, 1, 0), 4, new AspectList().add(AspectsInit.RADIATION, 2).add(AspectsInit.MRU, 2).add(Aspect.WATER, 4).add(Aspect.AIR, 4), new ItemStack(Items.potionitem, 1, 0), new ItemStack[]{
				new ItemStack(ItemRegistry.ItemResource, 1, 0),
				ItemApi.getItem("itemShard", 0),
		}));
		
		ResearchRegistry.recipes.put("ESPEInfuser", ThaumcraftApi.addInfusionCraftingRecipe("ESPEINFUSER", new ItemStack(BlockRegistry.BlockESPEInfuser, 1, 0), 8, new AspectList().add(AspectsInit.RADIATION, 32).add(AspectsInit.MRU, 32).add(Aspect.ENERGY, 32).add(Aspect.AIR, 64).add(Aspect.CRYSTAL, 16).add(Aspect.MECHANISM, 64), new ItemStack(BlocksCore.radiatingChamber, 1, 0), new ItemStack[]{
				new ItemStack(BlocksCore.mithrilineCrystal, 1, 6),
				new ItemStack(BlockRegistry.BlockESPECrystal, 1, 0),
				new ItemStack(ItemsInit.primordialCore, 1, 0),
				new ItemStack(BlocksCore.mithrilineCrystal, 1, 6),
				new ItemStack(ItemsCore.bound_gem, 1, 0),
				new ItemStack(BlockRegistry.BlockESPECrystal, 1, 0),
				new ItemStack(BlocksCore.mithrilineCrystal, 1, 6),
				new ItemStack(BlockRegistry.BlockESPECrystal, 1, 0),
				new ItemStack(ItemsInit.primordialCore, 1, 0),
				new ItemStack(BlocksCore.mithrilineCrystal, 1, 6),
				new ItemStack(ItemsCore.genericItem, 1, 55),
				new ItemStack(BlockRegistry.BlockESPECrystal, 1, 0),				
		}));
		
		ResearchRegistry.recipes.put("MRUReactor", ThaumcraftApi.addInfusionCraftingRecipe("MRUREACTOR", new ItemStack(ItemsCore.genericItem, 1, 37), 4, new AspectList().add(AspectsInit.RADIATION, 4).add(AspectsInit.MRU, 4).add(Aspect.MECHANISM, 4).add(Aspect.VOID, 2).add(Aspect.DARKNESS, 2), new ItemStack(ItemsCore.genericItem, 1, 36), new ItemStack[]{
				new ItemStack(ItemsCore.genericItem, 1, 29),
				new ItemStack(ItemsCore.genericItem, 1, 35),
				new ItemStack(ItemsCore.genericItem, 1, 23),
				new ItemStack(ItemsCore.genericItem, 1, 35),
				new ItemStack(ItemsCore.genericItem, 1, 25),
				new ItemStack(ItemsCore.genericItem, 1, 35),
				new ItemStack(ItemsCore.genericItem, 1, 23),
				new ItemStack(ItemsCore.genericItem, 1, 35),
		}));
	}
	
	public static void initESPE() {
		ESPEInfuserRecipes.addRecipe(new ItemStack(ItemRegistry.ItemResource, 1, 0), new ItemStack(ItemsCore.genericItem, 1, 44), 360.0F);
		
		ESPEInfuserRecipes.addRecipe(new ItemStack(BlockRegistry.BlockESPECrystal, 1, 0), new ItemStack(ItemsCore.genericItem, 9, 44), 3240.0F);
		
		ESPEInfuserRecipes.addRecipe(new ItemStack(ItemsInit.pearl, 1, 3), new ItemStack(ItemsInit.pearl, 1, 1), 46080.0F);
	}
}
