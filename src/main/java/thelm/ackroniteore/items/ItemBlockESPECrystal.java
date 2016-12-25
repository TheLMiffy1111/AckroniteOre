package thelm.ackroniteore.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;
import thelm.ackroniteore.registry.EnumRegistry;

public class ItemBlockESPECrystal extends ItemBlockWithMetadata {
	public ItemBlockESPECrystal(Block b) {
		super(b, b);
	}
	
	public String getUnlocalizedName(ItemStack s) {
	    return getUnlocalizedName() +  "." + s.getItemDamage();
	}
	
	public EnumRarity getRarity(ItemStack s) {
		return EnumRegistry.rarityWind;
	}
}
