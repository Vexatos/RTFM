package li.cil.manual.client.manual.provider;

import li.cil.manual.api.API;
import li.cil.manual.api.manual.ImageProvider;
import li.cil.manual.api.manual.ImageRenderer;
import li.cil.manual.client.manual.segment.render.ItemStackImageRenderer;
import li.cil.manual.client.manual.segment.render.MissingItemRenderer;
import li.cil.manual.common.RTFM;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.oredict.OreDictionary;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class OreDictImageProvider implements ImageProvider {
    private static final String WARNING_ORE_DICT_MISSING = API.MOD_ID + ".manual.warning.missing.oreDict";

    @Override
    public ImageRenderer getImage(final String data) {
        final ItemStack[] stacks = OreDictionary.getOres(data).stream()
                .flatMap(stack -> getSubtypes(stack).stream())
                .toArray(ItemStack[]::new);
        if (stacks != null && stacks.length > 0) {
            return new ItemStackImageRenderer(stacks);
        } else {
            return new MissingItemRenderer(WARNING_ORE_DICT_MISSING);
        }
    }

    /**
     * Stolen from JEI.
     *
     * @author mezz
     */
    private static List<ItemStack> getSubtypes(@Nullable ItemStack itemStack) {
        if (itemStack == null) {
            return Collections.emptyList();
        }

        if (itemStack.isEmpty()) {
            return Collections.emptyList();
        }

        if (itemStack.getItemDamage() != OreDictionary.WILDCARD_VALUE) {
            return Collections.singletonList(itemStack);
        }

        return getSubtypes(itemStack.getItem(), itemStack.getCount());
    }

    /**
     * Stolen from JEI.
     *
     * @author mezz
     */
    private static List<ItemStack> getSubtypes(final Item item, final int stackSize) {
        List<ItemStack> itemStacks = new ArrayList<>();

        for (CreativeTabs itemTab : item.getCreativeTabs()) {
            NonNullList<ItemStack> subItems = NonNullList.create();
            try {
                item.getSubItems(itemTab, subItems);
            } catch (RuntimeException | LinkageError e) {
                RTFM.getLog().warn("Caught a crash while getting sub-items of {}", item, e);
            }

            for (ItemStack subItem : subItems) {
                if (subItem == null) {
                    RTFM.getLog().warn("Found a null subItem of {}", item);
                } else if (subItem.isEmpty()) {
                    RTFM.getLog().warn("Found an empty subItem of {}", item);
                } else {
                    if (subItem.getCount() != stackSize) {
                        ItemStack subItemCopy = subItem.copy();
                        subItemCopy.setCount(stackSize);
                        itemStacks.add(subItemCopy);
                    } else {
                        itemStacks.add(subItem);
                    }
                }
            }
        }

        return itemStacks;
    }
}
