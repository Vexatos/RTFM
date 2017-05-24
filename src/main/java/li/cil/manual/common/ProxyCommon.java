package li.cil.manual.common;

import li.cil.manual.api.API;
import li.cil.manual.api.ManualAPI;
import li.cil.manual.api.prefab.manual.ResourceContentProvider;
import li.cil.manual.common.api.FontRendererAPIImpl;
import li.cil.manual.common.api.ManualAPIImpl;
import li.cil.manual.common.init.Items;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

import java.util.function.Supplier;

/**
 * Takes care of common setup.
 */
public class ProxyCommon {

	public void onPreInit(final FMLPreInitializationEvent event) {
		API.fontRendererAPI = new FontRendererAPIImpl();
		API.manualAPI = ManualAPIImpl.INSTANCE;
		Items.register(this);
	}

	public void onInit(final FMLInitializationEvent event) {
		// Register Ore Dictionary entries
		OreDictionary.registerOre("book", Items.bookManual);
	}

	public Item registerItem(final String name, final Supplier<Item> constructor) {
		final Item item = constructor.get().
			setUnlocalizedName(API.MOD_ID + "." + name).
			setCreativeTab(CreativeTabs.TOOLS).
			setRegistryName(name);
		GameRegistry.register(item);
		return item;
	}
}
