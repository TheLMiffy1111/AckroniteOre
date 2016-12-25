package thelm.ackroniteore.config;

import java.util.Set;

import cpw.mods.fml.client.IModGuiFactory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import thelm.ackroniteore.Woodchopper;
import thelm.ackroniteore.registry.EnumRegistry;

public class ConfigGUIFactory implements IModGuiFactory {
	
	public void initialize(Minecraft minecraftInstance) {}
	
	public Class<? extends GuiScreen> mainConfigGuiClass() {
		return ConfigGUI.class;
	}
	
	public Set<RuntimeOptionCategoryElement> runtimeGuiCategories() {
		return null;
	}
	
	public RuntimeOptionGuiHandler getHandlerFor(RuntimeOptionCategoryElement element) {
		return null;
	}
}
