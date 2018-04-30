package li.cil.manual.client;

import com.google.common.base.Preconditions;
import li.cil.manual.api.API;
import li.cil.manual.api.detail.ManualDefinition;
import li.cil.manual.client.gui.GuiHandlerClient;
import li.cil.manual.common.RTFM;
import li.cil.manual.common.ProxyCommon;
import li.cil.manual.common.api.ManualDefinitionImpl;
import li.cil.manual.common.api.ManualFactoryServer;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import vexatos.manualtabs.util.client.BadConfigException;

import javax.annotation.Nullable;
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
        ManualDefinitionImpl.INSTANCE.addDefaultProviders();
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

    @Override
    protected void createManualFactory() {
        API.manualFactory = new ManualFactoryClient();
    }

    private static class ManualFactoryClient extends ManualFactoryServer {
        @Override
        public Item createItemForManual(@Nullable final ManualDefinition manual) {
            Preconditions.checkNotNull(manual);
            return super.createItemForManualReal(manual);
        }

        @Nullable
        @Override
        public ManualDefinition createManual() {
            return new ManualDefinitionImpl();
        }
    }
}
