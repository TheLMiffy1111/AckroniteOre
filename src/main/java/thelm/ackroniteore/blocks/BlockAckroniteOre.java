package thelm.ackroniteore.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import thelm.ackroniteore.AckroniteOre;

public class BlockAckroniteOre extends Block {
	public BlockAckroniteOre() {
		super(Material.rock);
		setCreativeTab(AckroniteOre.tabAckroniteOre);
		setBlockTextureName("ackroniteore:BlockAckroniteOre");
		setBlockName("ackroniteore.BlockAckroniteOre");
		setHardness(5.0F);
		setHarvestLevel("pickaxe", 3);
	}
}
