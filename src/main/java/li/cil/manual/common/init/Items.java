package li.cil.manual.common.init;

import li.cil.manual.common.Constants;
import li.cil.manual.common.ProxyCommon;
import li.cil.manual.common.item.ItemBookManual;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * Manages setup, registration and lookup of items.
 */
public final class Items {
    public static Item bookManual;

    // --------------------------------------------------------------------- //

    public static boolean isItem(final ItemStack stack, final Item item) {
        return !stack.isEmpty() && stack.getItem() == item;
    }

    public static boolean isBookManual(final ItemStack stack) {
        return isItem(stack, bookManual);
    }

    // --------------------------------------------------------------------- //

    public static void register(final ProxyCommon proxy) {
        bookManual = proxy.registerItem(Constants.NAME_ITEM_BOOK_MANUAL, ItemBookManual::new);
    }
    // --------------------------------------------------------------------- //

    private Items() {
    }
}
