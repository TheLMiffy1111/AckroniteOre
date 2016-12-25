package thelm.ackroniteore.items;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import thelm.ackroniteore.AckroniteOre;
import thelm.ackroniteore.registry.EnumRegistry;

public class ItemResource extends Item {
	public IIcon[] icons = new IIcon[4];
	
	public ItemResource() {
		setHasSubtypes(true);
		setCreativeTab(AckroniteOre.tabAckroniteOre);
		setTextureName("ackroniteore:ItemResource");
		setUnlocalizedName("ackroniteore.ItemResource");
	}
	
	public void registerIcons(IIconRegister reg) {
		for(int i = 0; i < 4; i++) {
			icons[i] = reg.registerIcon(iconString + "." + i);
		}
	}
	
	public IIcon getIconFromDamage(int meta) {
		if(meta >= 4)
			meta = 0;
		
		return icons[meta];
	}
	
	public void getSubItems(Item item, CreativeTabs tab, List list) {
		for(int i = 0; i < 4; i++) {
			list.add(new ItemStack(item, 1, i));
		}
	}
	
	public String getUnlocalizedName(ItemStack s) {
	    return getUnlocalizedName() + "." + s.getItemDamage();
	}
	
	public EnumRarity getRarity(ItemStack s) {
		int meta = s.getItemDamage();
		switch(meta) {
		case 0:
			return EnumRegistry.rarityWind;
		case 1:
			return EnumRarity.uncommon;
		case 2:
			return EnumRarity.uncommon;
		case 3:
			return EnumRarity.uncommon;
		}
		return EnumRarity.common;
	}
}
