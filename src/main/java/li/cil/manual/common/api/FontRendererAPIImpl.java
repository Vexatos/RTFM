package li.cil.manual.common.api;

import li.cil.manual.api.detail.FontRendererAPI;
import li.cil.manual.client.renderer.font.FontRendererSmall;

/**
 * Provide access to our tiny mono-space font.
 */
public final class FontRendererAPIImpl implements FontRendererAPI {
    @Override
    public void drawString(final String string) {
        FontRendererSmall.INSTANCE.drawString(string);
    }

    @Override
    public void drawString(final String string, final int maxChars) {
        FontRendererSmall.INSTANCE.drawString(string, maxChars);
    }

    @Override
    public int getCharWidth() {
        return FontRendererSmall.INSTANCE.getCharWidth();
    }

    @Override
    public int getCharHeight() {
        return FontRendererSmall.INSTANCE.getCharHeight();
    }
}
