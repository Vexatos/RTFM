package li.cil.manual.client.renderer;

import li.cil.manual.api.API;
import net.minecraft.util.ResourceLocation;

public final class TextureLoader {
    public static final ResourceLocation LOCATION_MANUAL_BACKGROUND = new ResourceLocation(API.MOD_ID, "textures/gui/manual.png");
    public static final ResourceLocation LOCATION_MANUAL_TAB = new ResourceLocation(API.MOD_ID, "textures/gui/manual_tab.png");
    public static final ResourceLocation LOCATION_MANUAL_SCROLL = new ResourceLocation(API.MOD_ID, "textures/gui/manual_scroll.png");
    public static final ResourceLocation LOCATION_MANUAL_MISSING = new ResourceLocation(API.MOD_ID, "textures/gui/manual_missing.png");

    public static final TextureLoader INSTANCE = new TextureLoader();

    // --------------------------------------------------------------------- //

    private TextureLoader() {
    }
}
