package thelm.ackroniteore.registry;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import thelm.ackroniteore.blocks.BlockAckroniteOre;
import thelm.ackroniteore.blocks.BlockESPECrystal;
import thelm.ackroniteore.blocks.BlockESPEInfuser;
import thelm.ackroniteore.items.ItemBlockAckroniteOre;
import thelm.ackroniteore.items.ItemBlockESPECrystal;
import thelm.ackroniteore.items.ItemBlockESPEInfuser;


public class BlockRegistry {

	public static void init() {
		BlockAckroniteOre = new BlockAckroniteOre();
		GameRegistry.registerBlock(BlockAckroniteOre, ItemBlockAckroniteOre.class, "BlockAckroniteOre");
		
		BlockESPECrystal = new BlockESPECrystal();
		GameRegistry.registerBlock(BlockESPECrystal, ItemBlockESPECrystal.class, "BlockESPECrystal");
		
		BlockESPEInfuser = new BlockESPEInfuser();
		GameRegistry.registerBlock(BlockESPEInfuser, ItemBlockESPEInfuser.class, "BlockESPEInfuser");
	}
	
	public static Block BlockAckroniteOre;
	public static Block BlockESPECrystal;
	public static Block BlockESPEInfuser;
}
