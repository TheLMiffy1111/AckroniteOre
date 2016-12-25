package thelm.ackroniteore.registry;

import cpw.mods.fml.common.Loader;
import ec3.common.item.ItemsCore;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class OreDictionaryRegistry {
	public static void init() {
		OreDictionary.registerOre("oreAckronite", new ItemStack(BlockRegistry.BlockAckroniteOre, 1, 0));
		OreDictionary.registerOre("blockESPE", new ItemStack(BlockRegistry.BlockESPECrystal, 1, 0));
		OreDictionary.registerOre("blockESPE", new ItemStack(BlockRegistry.BlockESPECrystal, 1, 1));
		OreDictionary.registerOre("blockESPE", new ItemStack(BlockRegistry.BlockESPECrystal, 1, 2));
		OreDictionary.registerOre("gemESPE", new ItemStack(ItemRegistry.ItemResource, 1, 0));
		OreDictionary.registerOre("nuggetAckronite", new ItemStack(ItemRegistry.ItemResource, 1, 1));
		OreDictionary.registerOre("clusterAckronite", new ItemStack(ItemRegistry.ItemResource, 1, 2));
		OreDictionary.registerOre("dustAckronite", new ItemStack(ItemRegistry.ItemResource, 1, 3));
		
		if(Loader.isModLoaded("essentialcraft"))
			OreDictionary.registerOre("ingotAckronite", new ItemStack(ItemsCore.genericItem, 1, 52));
	}
}
