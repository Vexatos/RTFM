package li.cil.manual.common;

import li.cil.manual.api.API;
import li.cil.manual.common.api.ManualAPIImpl;
import li.cil.manual.common.init.Items;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import vexatos.manualtabs.util.BadConfigException;

import java.util.function.Supplier;

/**
 * Takes care of common setup.
 */
public class ProxyCommon {
    public void onPreInit(final FMLPreInitializationEvent event) {
        Config.INSTANCE.load(event.getSuggestedConfigurationFile());

        API.manualAPI = ManualAPIImpl.INSTANCE;
        Items.register(this);
    }

    public void onInit(final FMLInitializationEvent event) {
        Config.INSTANCE.init();

        // Register Ore Dictionary entries
        OreDictionary.registerOre("book", Items.bookManual);
    }

    public Item registerItem(final String name, final Supplier<Item> constructor) {
        final Item item = constructor.get().
                setUnlocalizedName(API.MOD_ID + "." + name).
                setCreativeTab(CreativeTabs.TOOLS).
                setRegistryName(name);
        GameRegistry.findRegistry(Item.class).register(item);
        return item;
    }

    public RuntimeException throwBadConfigException(String icon) {
        throw new BadConfigException(icon);
    }

    public RuntimeException throwBadConfigException(String icon, Throwable t) {
        throw new BadConfigException(icon, t);
    }
}
