package thelm.ackroniteore.registry;

import cpw.mods.fml.common.registry.GameRegistry;
import thelm.ackroniteore.tile.TileESPEInfuser;

public class TileRegistry {
	public static void init() {
		GameRegistry.registerTileEntity(TileESPEInfuser.class, "ackroniteore:TileESPEInfuser");
	}
}
