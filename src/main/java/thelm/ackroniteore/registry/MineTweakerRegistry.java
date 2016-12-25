package thelm.ackroniteore.registry;

import cpw.mods.fml.common.Loader;
import minetweaker.MineTweakerAPI;
import thelm.ackroniteore.config.ConfigHandler;
import thelm.ackroniteore.minetweaker.DemonTrading;
import thelm.ackroniteore.minetweaker.ESPEInfuser;
import thelm.ackroniteore.minetweaker.MagicianTable;
import thelm.ackroniteore.minetweaker.MithrilineFurnace;
import thelm.ackroniteore.minetweaker.RadiatingChamber;
import thelm.ackroniteore.minetweaker.WindImbue;

public class MineTweakerRegistry {
	public static void init() {
		MineTweakerAPI.registerClass(MagicianTable.class);
		MineTweakerAPI.registerClass(WindImbue.class);
		MineTweakerAPI.registerClass(MithrilineFurnace.class);
		MineTweakerAPI.registerClass(RadiatingChamber.class);
		MineTweakerAPI.registerClass(DemonTrading.class);
		if(Loader.isModLoaded("essenthaum") || !ConfigHandler.MTONLY)
			MineTweakerAPI.registerClass(ESPEInfuser.class);
	}
}
