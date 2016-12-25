package thelm.ackroniteore.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockAckroniteOre extends ItemBlock {
	public ItemBlockAckroniteOre(Block b) {
		super(b);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack s) {
		return EnumRarity.uncommon;
	}
}
