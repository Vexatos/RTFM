package li.cil.manual.client.manual.provider;

import li.cil.manual.api.API;
import li.cil.manual.api.manual.ImageProvider;
import li.cil.manual.api.manual.ImageRenderer;
import li.cil.manual.client.manual.segment.render.ItemStackImageRenderer;
import li.cil.manual.client.manual.segment.render.MissingItemRenderer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public final class OreDictImageProvider implements ImageProvider {
    private static final String WARNING_ORE_DICT_MISSING = API.MOD_ID + ".manual.warning.missing.oreDict";

    @Override
    public ImageRenderer getImage(final String data) {
        final ItemStack[] stacks = OreDictionary.getOres(data).stream().filter(stack -> !stack.isEmpty()).toArray(ItemStack[]::new);
        if (stacks != null && stacks.length > 0) {
            return new ItemStackImageRenderer(stacks);
        } else {
            return new MissingItemRenderer(WARNING_ORE_DICT_MISSING);
        }
    }
}
