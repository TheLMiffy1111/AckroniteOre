package thelm.ackroniteore.config;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.config.Configuration;
import thelm.ackroniteore.Reference;
import thelm.ackroniteore.ore.Ore;

public class ConfigHandler {
	
	public static ConfigHandler INSTANCE = new ConfigHandler();
	public Configuration configFile;
	public Set<String> usedCategories = new HashSet<String>();
	
	public static boolean MTONLY;
	public static boolean NOTHAUM;
	public static boolean ORE1;
	public static boolean TOOGREEN;
	public static boolean ORES;
	
	public static float ESPEIME;
	
	public static List<String> ALLORES = new ArrayList<String>();
	
	public void init(File file) {
		configFile = new Configuration(file, true);
		
		initThing();
		usedCategories.add("Main");
		usedCategories.add("Ores");
	}
	
	private void initThing() {
		
		MTONLY = getBooleanWithComment("Main", "MineTweaker Only", false, "Should only MineTweaker integration be registered.");
		ORE1 = getBooleanWithComment("Main", "Make One Ore", false, "Should only one piece of ackronite ore be made per infusion.");
		TOOGREEN = getBooleanWithComment("Main", "Too Green", false, "Should the thaumcraft research tab be default because it is too green.");
		NOTHAUM = getBooleanWithComment("Main", "No Thaumaturgy", false, "Should the content not be thaumcraft-based and be a bit easier.");
		ORES = getBooleanWithComment("Main", "Magical Alloys", true, "Should the mod add ores to the magmatic smelter.");
		
		ESPEIME = getFloatWithComment("Main", "ESPE Infuser Max Energy", 100000.0F, "The max energy the ESPE infuser can hold.");
		
		if(configFile.hasChanged())
			configFile.save();
	}
	
	public void initOres() {
		List<String> names = new ArrayList<String>(Arrays.asList(Ore.getOreNames()));
		
		for(int i = 0; !names.isEmpty(); i++) {
			String getter = getString("Ores", Integer.toString(i), "");
			String[] gotter = getter.split(",");
			if(gotter.length == 4) {
				try {
					ALLORES.add(getter);
					names.remove(gotter[1]);
				}
				catch(Throwable e) {}
			}
			else {
				removeProperty("Ores", Integer.toString(i));
				Ore ore = Ore.getOreByName(names.get(0));
				ALLORES.add(getString("Ores", Integer.toString(i), ore.prefix + "," + ore.name + "," + (0x1000000 + ore.color.getRGB()) + "," + ore.amount));
				names.remove(0);
			}
		}
		
		if(configFile.hasChanged())
			configFile.save();
	}
	
	private boolean getBooleanWithComment(String category, String name, boolean def, String comment) {
		return configFile.get(category, name, def, comment).setRequiresMcRestart(true).getBoolean(def);
	}
	
	private float getFloatWithComment(String category, String name, float def, String comment) {
		return (float)configFile.get(category, name, def, comment).setRequiresMcRestart(false).getDouble((double)def);
	}
	
	private String getString(String category, String name, String def) {
		return configFile.get(category, name, def).setRequiresMcRestart(true).getString();
	}
	
	private void removeProperty(String category, String name) {
		configFile.getCategory(category).remove(name);
	}
	
	@SubscribeEvent
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent eventArgs) {
		if(Reference.MOD_ID.equals(eventArgs.modID)) {
			configFile.load();
			
			initThing();
			initOres();
		}
	}
}
