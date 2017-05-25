package li.cil.manual.client;

import li.cil.manual.api.ManualAPI;
import li.cil.manual.client.gui.GuiHandlerClient;
import li.cil.manual.client.manual.provider.BlockImageProvider;
import li.cil.manual.client.manual.provider.ItemImageProvider;
import li.cil.manual.client.manual.provider.OreDictImageProvider;
import li.cil.manual.client.manual.provider.TextureImageProvider;
import li.cil.manual.common.RTFM;
import li.cil.manual.common.ProxyCommon;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import vexatos.manualtabs.util.client.BadConfigException;

import java.util.function.Supplier;

/**
 * Takes care of client-side only setup.
 */
public final class ProxyClient extends ProxyCommon {
    @Override
    public void onPreInit(final FMLPreInitializationEvent event) {
        super.onPreInit(event);
    }

    @Override
    public void onInit(final FMLInitializationEvent event) {
        super.onInit(event);

        // Register GUI handler for fancy GUIs in our almost GUI-less mod!
        NetworkRegistry.INSTANCE.registerGuiHandler(RTFM.instance, new GuiHandlerClient());

        // Add default manual providers for client side stuff.
        ManualAPI.addProvider("", new TextureImageProvider());
        ManualAPI.addProvider("item", new ItemImageProvider());
        ManualAPI.addProvider("block", new BlockImageProvider());
        ManualAPI.addProvider("oredict", new OreDictImageProvider());
    }

    @Override
    public Item registerItem(final String name, final Supplier<Item> constructor) {
        final Item item = super.registerItem(name, constructor);
        setCustomItemModelResourceLocation(item);
        return item;
    }

    @Override
    public RuntimeException throwBadConfigException(String icon) {
        throw new BadConfigException(icon);
    }

    @Override
    public RuntimeException throwBadConfigException(String icon, Throwable t) {
        throw new BadConfigException(icon, t);
    }

    // --------------------------------------------------------------------- //

    private static void setCustomItemModelResourceLocation(final Item item) {
        final ResourceLocation registryName = item.getRegistryName();
        assert registryName != null;
        final ModelResourceLocation location = new ModelResourceLocation(registryName, "inventory");
        ModelLoader.setCustomModelResourceLocation(item, 0, location);
    }
}
