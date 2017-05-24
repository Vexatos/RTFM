package li.cil.manual.client.manual.provider;

import li.cil.manual.api.API;
import li.cil.manual.api.manual.ImageProvider;
import li.cil.manual.api.manual.ImageRenderer;
import li.cil.manual.client.manual.segment.render.MissingItemRenderer;
import li.cil.manual.client.manual.segment.render.TextureImageRenderer;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public final class TextureImageProvider implements ImageProvider {
    private static final String WARNING_IMAGE_MISSING = API.MOD_ID + ".manual.warning.missing.image";

    @Override
    @Nullable
    public ImageRenderer getImage(final String data) {
        try {
            return new TextureImageRenderer(new ResourceLocation(data));
        } catch (final Throwable t) {
            return new MissingItemRenderer(WARNING_IMAGE_MISSING);
        }
    }
}
