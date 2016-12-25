package thelm.ackroniteore.ore;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import thelm.ackroniteore.AckroniteOre;

public class Ore {
	
	//Also kinda ripped off from AOBD
	 
	public static List<Ore> ores = new ArrayList<Ore>();
	public static final List<String> blacklist = new ArrayList<String>();
	
	static {
		blacklist.add("Coal");
		blacklist.add("Iron");
		blacklist.add("Gold");
		blacklist.add("Diamond");
		blacklist.add("Emerald");
		blacklist.add("Quartz");
		blacklist.add("Redstone");	
		blacklist.add("Lapis");
		blacklist.add("Copper");
		blacklist.add("Tin");
		blacklist.add("Lead");
		blacklist.add("Silver");
		blacklist.add("Cobalt");
		blacklist.add("Ardite");
		if(AckroniteOre.isECUnofficial)
			blacklist.add("Nickel");
		blacklist.add("Aluminum");
		blacklist.add("Uranium");
		blacklist.add("Alchemite");
		blacklist.add("Saltpeter");
		blacklist.add("Sulfur");
	}
	
	public static Ore newOre(String prefix, String name, Color color, int amount) {
		boolean valid = true;
		
		for(Ore findDupe : ores) {
			if(findDupe.name.equals(name)) {
				valid = false;
				break;
			}
		}
		
		for(String findDupe : blacklist) {
			if(findDupe.equals(name)) {
				valid = false;
				break;
			}
		}
		
		if(color == null)
			color = Color.WHITE;
		
		if(valid)
			return new Ore(prefix, name, color, amount);
		
		return null;
	}
	
	public static String[] getOreNames() {
		String[] names = new String[ores.size()];
		
		for(int i = 0; i < ores.size(); i++) {
			Ore ore = ores.get(i);
			names[i] = ore.name;
		}
		
		return names;
	}
	
	public static Ore getOreByName(String name) {
		for(Ore ore : ores){
			if(ore.name.equals(name))
				return ore;
		}
		return null;
	}
	
	public String prefix;
	public String name;
	public Color color;
	public int amount;
	
	public Ore(String p, String n, Color c, int a) {
		prefix = p;
		name = n;
		color = c;
		amount = a;
		ores.add(this);
	}
	
	public void setColor(Color c) {
		color = c;
	}
}
