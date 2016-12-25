package thelm.ackroniteore.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import thelm.ackroniteore.AckroniteOre;

public class BlockESPECrystal extends Block {
	public IIcon[] icons = new IIcon[3];
	
	public BlockESPECrystal() {
		super(Material.iron);
		setCreativeTab(AckroniteOre.tabAckroniteOre);
		setBlockTextureName("ackroniteore:BlockESPECrystal");
		setBlockName("ackroniteore.BlockESPECrystal");
		setStepSound(soundTypeMetal);
		setHardness(5.0F);
		setLightLevel(0.5F);
	}
	
	public int damageDropped(int meta) {
	    return meta;
	}
	
	public void registerBlockIcons(IIconRegister reg) {
		for(int i = 0; i < 3; i++) {
			icons[i] = reg.registerIcon(textureName + "." + i);
		}
	}
	
	public IIcon getIcon(int side, int meta) {
		if(meta >= 3)
			meta = 0;
		
		return icons[meta];
	}
	
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
	    for(int i = 0; i < 3; i++) {
	        list.add(new ItemStack(item, 1, i));
	    }
	}
	
	public boolean isBeaconBase(IBlockAccess w, int x, int y, int z, int x2, int y2, int z2) {
		return true;
	}
}
