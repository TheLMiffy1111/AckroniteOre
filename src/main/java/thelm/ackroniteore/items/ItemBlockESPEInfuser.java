package thelm.ackroniteore.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import thelm.ackroniteore.AckroniteOre;
import thelm.ackroniteore.registry.EnumRegistry;

public class ItemBlockESPEInfuser extends ItemBlock {
	public ItemBlockESPEInfuser(Block b) {
		super(b);
	}
	
	public EnumRarity getRarity(ItemStack s) {
		return EnumRegistry.rarityWind;
	}
}
