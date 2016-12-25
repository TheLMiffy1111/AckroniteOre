package thelm.ackroniteore.registry;

import java.util.HashMap;

import ec3.common.item.ItemsCore;
import essentialThaumaturgy.common.init.AspectsInit;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.CrucibleRecipe;
import thaumcraft.api.crafting.IArcaneRecipe;
import thaumcraft.api.crafting.InfusionRecipe;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchCategoryList;
import thaumcraft.api.research.ResearchPage;
import thelm.ackroniteore.config.ConfigHandler;
import thelm.ackroniteore.research.AckroniteOreResearchItem;

public class ResearchRegistry {
	public static final String AO = "ACKRONITEORE";
	public static HashMap recipes = new HashMap();
	
	public static ItemStack getOriginalItem(String name, String category) {
		return ((ResearchCategoryList) ResearchCategories.researchCategories.get(category)).research.get(name).icon_item;
	}
	
	public static void init() {
		ResearchCategories.registerCategory(AO, new ResourceLocation("ackroniteore:textures/misc/IconThaumonomiconTab.png"), ConfigHandler.TOOGREEN ? new ResourceLocation("thaumcraft:textures/gui/gui_researchback.png") : new ResourceLocation("ackroniteore:textures/gui/GUIThaumonomiconTab.png"));
		
		new AckroniteOreResearchItem("ackroniteore.ACKRONITEORE", AO, new AspectList().add(Aspect.LIGHT, 1).add(Aspect.FIRE, 1).add(Aspect.METAL, 1).add(AspectsInit.MRU, 1), 3, 0, 2, new ItemStack(BlockRegistry.BlockAckroniteOre)).setConcealed().setParents(new String[] {
				"INFUSION",
				"ELDRITCHMINOR",
				"et.research.mruVol3",
		}).setPages(new ResearchPage[] {
				new ResearchPage("1"),
				new ResearchPage((InfusionRecipe)recipes.get("BlockAckroniteOre")),
				new ResearchPage(new ItemStack(BlockRegistry.BlockAckroniteOre, 1, 0)),
		}).registerResearchItem();
		ThaumcraftApi.addWarpToResearch("ackroniteore.ACKRONITEORE", 2);
		
		new AckroniteOreResearchItem("ackroniteore.TITANITEDUPLICATION", AO, new AspectList().add(AspectsInit.MRU, 1).add(Aspect.EARTH, 1).add(Aspect.VOID, 1), -3, -1, 1, new ItemStack(ItemsCore.titanite)).setParents(new String[] {
				"ALCHEMICALDUPLICATION",
		}).setPages(new ResearchPage[] {
				new ResearchPage("1"),
				new ResearchPage((CrucibleRecipe)recipes.get("Titanite")),
		}).registerResearchItem();
		
		new AckroniteOreResearchItem("ackroniteore.TWINKLINGTITANITE", AO, new AspectList().add(AspectsInit.MRU, 1).add(Aspect.LIGHT, 1).add(Aspect.ENERGY, 1), -3, 1, 1, new ItemStack(ItemsCore.twinkling_titanite)).setParents(new String[] {
				"ALCHEMICALMANUFACTURE",
		}).setPages(new ResearchPage[] {
				new ResearchPage("1"),
				new ResearchPage((CrucibleRecipe)recipes.get("TTitanite")),
		}).registerResearchItem();
		
		new AckroniteOreResearchItem("ackroniteore.ESPECRYSTAL", AO, new AspectList().add(AspectsInit.MRU, 1).add(Aspect.AIR, 1).add(Aspect.ORDER, 1).add(AspectsInit.RADIATION, 1), 0, -1, 2, new ItemStack(ItemRegistry.ItemResource)).setConcealed().setParents(new String[] {
				"ALCHEMICALMANUFACTURE",
				"et.research.mruVol2",
		}).setPages(new ResearchPage[] {
				new ResearchPage("1"),
				new ResearchPage((CrucibleRecipe)recipes.get("ESPECrystal")),
				new ResearchPage((IRecipe)recipes.get("BlockESPECrystal")),
				new ResearchPage((IRecipe)recipes.get("BBlockESPECrystal")),
				new ResearchPage((IRecipe)recipes.get("HBlockESPECrystal")),
		}).registerResearchItem();
		
		new AckroniteOreResearchItem("ackroniteore.WINDCRYSTAL", AO, new AspectList().add(AspectsInit.MRU, 1).add(Aspect.AIR, 1).add(Aspect.ENERGY, 1).add(AspectsInit.RADIATION, 1).add(Aspect.CRYSTAL, 1), 1, 1, 2, new ItemStack(ItemsCore.genericItem, 1, 55)).setConcealed().setParents(new String[] {
				"INFUSION",
				"ackroniteore.ESPECRYSTAL",
				"et.research.mruVol3",
		}).setPages(new ResearchPage[] {
				new ResearchPage("1"),
				new ResearchPage((InfusionRecipe)recipes.get("WindCrystal")),
		}).registerResearchItem();
		
		new AckroniteOreResearchItem("ackroniteore.AIRPOTION", AO, new AspectList().add(AspectsInit.MRU, 1).add(Aspect.AIR, 1).add(AspectsInit.RADIATION, 1).add(Aspect.WATER, 1), -1, 1, 1, new ItemStack(ItemsCore.air_potion, 1, 0)).setConcealed().setParents(new String[] {
				"INFUSION",
				"ackroniteore.ESPECRYSTAL",
				"et.research.mruVol3",
		}).setPages(new ResearchPage[] {
				new ResearchPage("1"),
				new ResearchPage((InfusionRecipe)recipes.get("AirPotion")),
		}).registerResearchItem();
		
		new AckroniteOreResearchItem("ackroniteore.ESPEINFUSER", AO, new AspectList().add(AspectsInit.MRU, 1).add(Aspect.AIR, 1).add(Aspect.ENERGY, 1).add(AspectsInit.RADIATION, 1).add(Aspect.CRYSTAL, 1).add(Aspect.MECHANISM, 1), 4, -2, 3, new ItemStack(BlockRegistry.BlockESPEInfuser, 1, 0)).setConcealed().setParents(new String[] {
				"INFUSION",
				"ackroniteore.ESPECRYSTAL",
				"et.research.mruLeft",
		}).setPages(new ResearchPage[] {
				new ResearchPage("1"),
				new ResearchPage((InfusionRecipe)recipes.get("ESPEInfuser")),
				new ResearchPage("2"),
				new ResearchPage("et.research.pureLeft", "3"),
		}).registerResearchItem();
		ThaumcraftApi.addWarpToResearch("ackroniteore.ESPEINFUSER", 4);
		
		new AckroniteOreResearchItem("ackroniteore.VOIDCORE", AO, new AspectList().add(AspectsInit.MRU, 1).add(Aspect.VOID, 1).add(Aspect.DARKNESS, 1), -1, 3, 1, new ItemStack(ItemsCore.genericItem, 1, 36)).setConcealed().setParents(new String[] {
				"et.research.mruVol2",
		}).setPages(new ResearchPage[] {
				new ResearchPage("1"),
				new ResearchPage((IArcaneRecipe)recipes.get("VoidCore")),
		}).registerResearchItem();
		
		new AckroniteOreResearchItem("ackroniteore.MRUREACTOR", AO, new AspectList().add(AspectsInit.MRU, 1).add(Aspect.MECHANISM, 1).add(Aspect.VOID, 1).add(AspectsInit.RADIATION, 1), 1, 3, 2, new ItemStack(ItemsCore.genericItem, 1, 37)).setConcealed().setParents(new String[] {
				"INFUSION",
				"et.research.mruVol3",
		}).setPages(new ResearchPage[] {
				new ResearchPage("1"),
				new ResearchPage((InfusionRecipe)recipes.get("MRUReactor")),
		}).registerResearchItem();
		
		new AckroniteOreResearchItem("ackroniteore.PUREACKRONITE", AO, new AspectList().add(Aspect.METAL, 3).add(Aspect.ORDER, 2).add(AspectsInit.MRU, 1), 3, 2, 1, new ItemStack(ItemRegistry.ItemResource, 1, 2)).setConcealed().setSecondary().setParents(new String[] {
				"ackroniteore.ACKRONITEORE",
				"PUREIRON",
		}).setPages(new ResearchPage[] {
				new ResearchPage("1"),
				new ResearchPage((CrucibleRecipe)recipes.get("PureAckronite")),
		}).registerResearchItem();
	}
}
