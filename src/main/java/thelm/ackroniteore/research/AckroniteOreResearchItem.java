package thelm.ackroniteore.research;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchItem;
import thaumcraft.api.research.ResearchPage;

public class AckroniteOreResearchItem extends ResearchItem {

	public AckroniteOreResearchItem(String key, String category) {
		super(key, category);
	}

	public AckroniteOreResearchItem(String key, String category, AspectList tags, int col, int row, int complex, ItemStack icon) {
		super(key, category, tags, col, row, complex, icon);
	}

	public AckroniteOreResearchItem(String key, String category, AspectList tags, int col, int row, int complex, ResourceLocation icon) {
		super(key, category, tags, col, row, complex, icon);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public String getName() {
		return StatCollector.translateToLocal("research.name." + key);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public String getText() {
		return StatCollector.translateToLocal("research.tag." + key);
	}
	
	@Override
	public ResearchItem setPages(ResearchPage... par) {
		for(ResearchPage page : par) {
			if(page.type == ResearchPage.PageType.TEXT || page.type == ResearchPage.PageType.TEXT_CONCEALED) {
				page.text = "research.text." + key + "." + page.text;
			}
		}
		return super.setPages(par);
	}
}
