package thelm.ackroniteore.registry;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import thelm.ackroniteore.items.ItemResource;

public class ItemRegistry {
	public static void init() {
		ItemResource = new ItemResource();
		GameRegistry.registerItem(ItemResource, "ItemResource");
	}
	
	public static Item ItemResource;
}
