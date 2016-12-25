package thelm.ackroniteore;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLLoadCompleteEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ec3.common.mod.EssentialCraftCore;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.MinecraftForge;
import thelm.ackroniteore.config.ConfigHandler;
import thelm.ackroniteore.registry.BlockRegistry;
import thelm.ackroniteore.registry.EnumRegistry;
import thelm.ackroniteore.registry.ItemRegistry;
import thelm.ackroniteore.registry.MineTweakerRegistry;
import thelm.ackroniteore.registry.OreDictionaryRegistry;
import thelm.ackroniteore.registry.RecipeNoThaumRegistry;
import thelm.ackroniteore.registry.RecipeRegistry;
import thelm.ackroniteore.registry.ResearchRegistry;
import thelm.ackroniteore.registry.TileRegistry;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION_NUMBER, dependencies = Reference.DEPENDENCIES, guiFactory = Reference.GUI_FACTORY_CLASS)
public class AckroniteOre {
	
	public static boolean isECUnofficial = false;
	public static boolean hasFinishedPostInit = false;
	public static boolean hasFinishedLoading = false;
	
	@Instance
	public static AckroniteOre instance;
	
	public static CreativeTabs tabAckroniteOre = new CreativeTabs(Reference.MOD_ID) {
		public Item getTabIconItem() {
			return Item.getItemFromBlock(BlockRegistry.BlockAckroniteOre);
		}
	};
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		Woodchopper.info("Pre-Initializing...");
		
		Woodchopper.debug("Generating and Registering Config File");
		ConfigHandler.INSTANCE.init(event.getSuggestedConfigurationFile());
		FMLCommonHandler.instance().bus().register(ConfigHandler.INSTANCE);
		MinecraftForge.EVENT_BUS.register(this);
		
		try {
			isECUnofficial = EssentialCraftCore.Unofficial;
		}
		catch(Throwable e) {}
		
		Woodchopper.debug("Registering Blocks");
		BlockRegistry.init();
		Woodchopper.debug("Registering Items");
		ItemRegistry.init();
		Woodchopper.debug("Registering TileEntities");
		TileRegistry.init();
		
		if(!Loader.isModLoaded("essenthaum"))
			Woodchopper.warn("Essential Thaumaturgy not detected. Automatically switching to No Thaumaturgy mode.");
		
		if(Loader.isModLoaded("MineTweaker3")) {
			try {
				Woodchopper.debug("Registering MineTweaker Integration");
				MineTweakerRegistry.init();
			}
			catch(Throwable e) {
				Woodchopper.warn("Unable to add MineTweaker integration!");
				e.printStackTrace();
			}
		}
		else
			Woodchopper.warn("MineTweaker not detected. Will not add MineTweaker integration.");
		
		Woodchopper.info("Done!");
	}
	
	@EventHandler
	public static void init(FMLInitializationEvent event) {
		Woodchopper.info("Initializing...");
		
		Woodchopper.debug("Registering OreDict Entries");
		OreDictionaryRegistry.init();
		
		if(!ConfigHandler.MTONLY) {
			Woodchopper.debug("Registering Recipes");
			if(Loader.isModLoaded("essenthaum") && !ConfigHandler.NOTHAUM)
				RecipeRegistry.init();
			else {
				Woodchopper.warn("No Thaumaturgy mode is on. Registering fallback recipes.");
				RecipeNoThaumRegistry.init();
			}
		}
		else
			Woodchopper.warn("MineTweaker Only mode is on. Will not register recipes.");
		
		Woodchopper.info("Done!");
	}
	
	@EventHandler
	public static void postInit(FMLPostInitializationEvent event)
	{
		Woodchopper.info("Post-Initializing...");
		
		if(Loader.isModLoaded("essenthaum") && !ConfigHandler.NOTHAUM && !ConfigHandler.MTONLY) {
			Woodchopper.debug("Registering Thaumcraft Research");
			ResearchRegistry.init();
		}
		else if(ConfigHandler.MTONLY)
			Woodchopper.warn("MineTweaker Only mode is on. Will not register researches.");
		else
			Woodchopper.warn("No Thaumaturgy mode is on. Will not register researches.");
		
		if(ConfigHandler.ORES) {
			Woodchopper.debug("Pre-Registering Magmatic Smelter Enums");
			EnumRegistry.init();
		}
		else
			Woodchopper.warn("Magical Alloys is off. Will not pre-register enums.");
		
		hasFinishedPostInit = true;
		
		Woodchopper.info("Done!");
		
		if(event.getSide() == Side.SERVER) {
		
			Woodchopper.info("Post-Post-Initializing...");
		
			if(ConfigHandler.ORES) {
				Woodchopper.debug("Registering Magmatic Smelter Enums");
				EnumRegistry.postInitServer();
			}
			else
				Woodchopper.warn("Magical Alloys is off. Will not register enums.");
			
			hasFinishedLoading = true;
			
			Woodchopper.info("Done!");
		}
	}
	
	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void postPostInitClient(TextureStitchEvent.Post event) {
		if(hasFinishedPostInit && !hasFinishedLoading && event.map.getTextureType() == 1) {
			//lol
			
			Woodchopper.info("Post-Post-Initializing...");
			
			if(ConfigHandler.ORES) {
				Woodchopper.debug("Registering Magmatic Smelter Enums");
				EnumRegistry.postInitClient();
			}
			else
				Woodchopper.warn("Magical Alloys is off. Will not register enums.");
			
			hasFinishedLoading = true;
			
			Woodchopper.info("Done!");
		}
	}
}
