package thelm.ackroniteore.ore;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.imageio.ImageIO;

import cpw.mods.fml.common.Loader;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.oredict.OreDictionary;
import thelm.ackroniteore.Woodchopper;

public class OreFinder {
	
	//Basically everything here is ripped off from AOBD
	
	public static Set<String> getMaterialsWithPrefixes(String prefix1, String prefix2) {
		Set<String> ores = new LinkedHashSet<String>();
		for(String name : OreDictionary.getOreNames())
			if(name.startsWith(prefix1) && !OreDictionary.getOres(name).isEmpty()) {
				String oreName = name.substring(prefix1.length());
				for(String n : OreDictionary.getOreNames())
					if(n.equals(prefix2 + oreName) && !OreDictionary.getOres(n).isEmpty())
						ores.add(oreName);
			}
		if(ores.contains("Aluminum") && ores.contains("Aluminium"))
			ores.remove("Aluminium");
		if(ores.contains("Aluminum") && ores.contains("NaturalAluminum"))
			ores.remove("NaturalAluminum");

		return ores;
	}
	
	public static Color getColor(String prefix, String oreName) {
		List<ItemStack> ores = OreDictionary.getOres(prefix + oreName);
		if(ores.isEmpty())
			return null;
		
		Set<Color> colors = new LinkedHashSet<Color>();
		for(ItemStack stack : ores) {
			try {
				BufferedImage texture = ImageIO.read(Minecraft.getMinecraft().getResourceManager().getResource(getIconResource(stack)).getInputStream());
				Color texColor = getAverageColor(texture);
				colors.add(texColor);
				for(int pass = 0; pass < stack.getItem().getRenderPasses(stack.getItemDamage()); pass++) {
					int c = getStackColor(stack, pass);
					if(c != 0xFFFFFF) {
						colors.add(new Color(c));
						colors.remove(texColor);
					}
				}
			}
			catch (Exception e) {
				continue;
			}
		}

		float red = 0;
		float green = 0;
		float blue = 0;
		for (Color c : colors) {
			red += c.getRed();
			green += c.getGreen();
			blue += c.getBlue();
		}
		float count = colors.size();
		return new Color((int)(red / count), (int)(green / count), (int)(blue / count));
	}
	
	private static int getStackColor(ItemStack stack, int pass) {
		if(Loader.isModLoaded("gregtech")) { 
			try {
				Class<?> cls = Class.forName("gregtech.api.items.GT_MetaGenerated_Item");
				Class<?> itemCls = stack.getItem().getClass();
				if(cls.isAssignableFrom(itemCls)) {
					Method m = itemCls.getMethod("getRGBa", ItemStack.class);
					short[] rgba = (short[])m.invoke(stack.getItem(), stack);
					return new Color(rgba[0], rgba[1], rgba[2], rgba[3]).getRGB();
				}
			}
			catch (Exception e) {}
		}
		return stack.getItem().getColorFromItemStack(stack, pass);
	}
	
	private static Color getAverageColor(BufferedImage image) {
		float red = 0.0F;
		float green = 0.0F;
		float blue = 0.0F;
		float count = 0.0F;
		for(int i = 0; i < image.getWidth(); i++) {
			for(int j = 0; j < image.getHeight(); j++) {
				Color c = new Color(image.getRGB(i, j));
				if((c.getAlpha() == 255) && ((c.getRed() > 10) || (c.getBlue() > 10) || (c.getGreen() > 10))) {
					red += c.getRed();
					green += c.getGreen();
					blue += c.getBlue();
					count += 1.0F;
				}
			}
		}
		return new Color((int)(red / count), (int)(green / count), (int)(blue / count));
	}
	
	private static String getIconName(ItemStack stack) {
		IIcon icon = stack.getIconIndex();
		if(icon != null) {
			return icon.getIconName();
		}
		return null;
	}
	
	private static ResourceLocation getIconResource(ItemStack stack) {
		String iconName = getIconName(stack);
		if(iconName == null) {
			return null;
		}

		String string = "minecraft";

		int colonIndex = iconName.indexOf(":");
		if(colonIndex >= 0) {
			if(colonIndex > 1)
				string = iconName.substring(0, colonIndex);

			iconName = iconName.substring(colonIndex + 1, iconName.length());
		}

		string = string.toLowerCase();
		iconName = "textures/items/" + iconName + ".png";
		Woodchopper.debug("Temporary debug " + iconName);
		return new ResourceLocation(string, iconName);
	}
}
