package thelm.ackroniteore.registry;

import java.awt.Color;
import java.util.Set;

import ec3.utils.common.EnumOreColoring;
import net.minecraft.item.EnumRarity;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.common.util.EnumHelper;
import thelm.ackroniteore.Woodchopper;
import thelm.ackroniteore.config.ConfigHandler;
import thelm.ackroniteore.ore.Ore;
import thelm.ackroniteore.ore.OreFinder;

public class EnumRegistry {
	public static EnumRarity rarityWind = EnumHelper.addRarity("WIND", EnumChatFormatting.GREEN, "Wind");
	
	public static void init() {
		getOreIngots();
		getOreGems();
		getOreDusts();
	}
	
	public static void postInitServer() {
		ConfigHandler.INSTANCE.initOres();
		initConfigReload();
		initOreEnums();
	}
	
	public static void postInitClient() {
		initOreColors();
		ConfigHandler.INSTANCE.initOres();
		initConfigReload();
		initOreEnums();
	}
	
	public static void getOreIngots() {
		Set<String> ores = OreFinder.getMaterialsWithPrefixes("ore", "ingot");
		for(String ore : ores)
			Ore.newOre("ingot", ore, null, 1);
	}
	
	
	public static void getOreGems() {
		Set<String> ores = OreFinder.getMaterialsWithPrefixes("ore", "gem");
		for(String ore : ores)
			Ore.newOre("gem", ore, null, 1);
	}
	
	public static void getOreDusts() {
		Set<String> ores = OreFinder.getMaterialsWithPrefixes("ore", "dust");
		for(String ore : ores)
			Ore.newOre("dust", ore, null, 5);
	}
	
	public static void initOreColors() {
		for(Ore ore : Ore.ores)
			ore.setColor(OreFinder.getColor(ore.prefix, ore.name));
	}
	
	public static void initConfigReload() {
		Ore.ores.clear();
		for(String reloader : ConfigHandler.ALLORES) {
			String[] values = reloader.split(",");
			Ore.newOre(values[0], values[1], Color.getColor(null, Integer.parseInt(values[2])), Integer.parseInt(values[3]));
		}
	}
	
	public static void initOreEnums() {
		for(Ore ore : Ore.ores) {
			String prefix = ore.prefix;
			String name = ore.name;
			int color = 0x1000000 + ore.color.getRGB();
			int amount = ore.amount;
			EnumHelper.addEnum(EnumOreColoring.class, name.toUpperCase(), new Class[] {String.class, String.class, Integer.TYPE, Integer.TYPE}, new Object[] {"ore" + name, prefix + name, color, amount});
			Woodchopper.debug("Added Enum " + name.toUpperCase() + "[ore" + name + "," + prefix + name + "," + color + "," + amount + "]");
		}
	}
}
