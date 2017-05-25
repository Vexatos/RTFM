package vexatos.manualtabs.util.client;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiErrorScreen;
import net.minecraftforge.fml.client.CustomModLoadingErrorDisplayException;

/**
 * @author Vexatos
 */
public class BadConfigException extends CustomModLoadingErrorDisplayException {
	protected final String icon;

	public BadConfigException(String icon, Throwable t) {
		super(String.format("Invalid tab icon set in config: '%s'", icon), t);
		this.icon = icon;
	}

	public BadConfigException(String icon) {
		super();
		this.icon = icon;
	}

	@Override
	public void initGui(GuiErrorScreen errorScreen, FontRenderer fontRenderer) {

	}

	@Override
	public void drawScreen(GuiErrorScreen errorScreen, FontRenderer fr, int mouseRelX, int mouseRelY, float tickTime) {
		int offs = (errorScreen.height / 2) - 70;
		//int offs = 10;
		int centre = errorScreen.width / 2;
		drawCenteredString(fr, "Could not load additional RTFM Manual Tab", centre, offs, 0xffffff);
		offs += 15;
		drawCenteredString(fr, "Tab icon was set to '\u00a74" + icon + "\u00a7r'", centre, offs, 0xeeeeee);
		offs += 15;
		drawCenteredString(fr, "But this cannot be parsed.", centre, offs, 0xeeeeee);
		offs += 30;
		drawCenteredString(fr, "Please specify a valid tab icon or disable the mod in the config.", centre, offs, 0xffffff);
	}

	private void drawCenteredString(FontRenderer fontRenderer, String string, int x, int y, int colour) {
		String fixedString = string.replaceAll("\\P{InBasic_Latin}", "");
		int fixedStringWidth = fontRenderer.getStringWidth(fixedString);
		int centeredX = x - fixedStringWidth / 2;

		fontRenderer.drawStringWithShadow(string, centeredX, y, colour);
	}
}
