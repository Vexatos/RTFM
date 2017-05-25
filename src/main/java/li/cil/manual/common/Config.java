package li.cil.manual.common;

import li.cil.manual.api.ManualAPI;
import li.cil.manual.api.manual.TabIconRenderer;
import li.cil.manual.api.prefab.manual.ItemStackTabIconRenderer;
import li.cil.manual.api.prefab.manual.TextureTabIconRenderer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Configuration;
import vexatos.manualtabs.manual.ConfigContentProvider;
import vexatos.manualtabs.manual.MainPageContentProvider;

import java.io.File;

public enum Config {
    INSTANCE;

    private Configuration config;

    public void load(final File file) {
        config = new Configuration(file);
        config.load();
    }

    public void init() {
        final String prefix = config.getString("pathPrefix", "general", "rtfm", "The prefix with which to reference manual pages.\nSetting this to 'potato' would allow referencing a manual file at this config folder's 'rtfm/en_us/fish.md'\n"
                                                                                      + "from inside the actual manual by writing 'potato/%LANGUAGE%/fish.md' into the referencing markdown page (relative paths work too, of course).");
        final String mainTab = config.getString("mainTab", "general", "exampletab", "The main tab that will be selected when you first open the manual");
        ManualAPI.addProvider(new ConfigContentProvider(prefix));
        config.setCategoryComment("manual", "You can add as many tabs as you want in here. At least until the space runs out.\nTo regenerate this section, remove all entries from this category.");
        if (config.getCategory("manual").getChildren().size() == 0) {
            config.getInt("tabIconMode", "manual.exampletab", 1, 0, 1, Constants.CONFIG_COMMENT_TAB_ICON_MODE);
            config.getString("tabIcon", "manual.exampletab", "minecraft:potato@0", Constants.CONFIG_COMMENT_TAB_ICON);
            config.getString("tabPath", "manual.exampletab", "%LANGUAGE%/index.md", Constants.CONFIG_COMMENT_TAB_PATH);
            config.getString("tabName", "manual.exampletab", "tooltip.rtfm.manual.tab", Constants.CONFIG_COMMENT_TAB_NAME);
        }

        String mainPath = null;
        for (final ConfigCategory tab : config.getCategory("manual").getChildren()) {
            if (!(tab.containsKey("tabIconMode") && tab.containsKey("tabIcon")
                  && tab.containsKey("tabPath") && tab.containsKey("tabName"))) {
                RTFM.getLog().error(String.format("Invalid manual tab '%s'", tab.getName()));
                continue;
            }

            final int iconMode = tab.get("tabIconMode").getInt(1);
            tab.get("tabIconMode").setComment(Constants.CONFIG_COMMENT_TAB_ICON_MODE);
            final String icon = tab.get("tabIcon").getString();
            tab.get("tabIcon").setComment(Constants.CONFIG_COMMENT_TAB_ICON);
            final TabIconRenderer r;

            if (iconMode == 1) {
                String[] strings = icon.split("@");
                final int meta;
                if (strings.length > 1) {
                    try {
                        meta = Integer.parseInt(strings[1]);
                    } catch (NumberFormatException ex) {
                        config.save();
                        throw RTFM.proxy.throwBadConfigException(icon, ex);
                    }
                } else if (strings.length == 1) {
                    meta = 0;
                } else {
                    config.save();
                    throw RTFM.proxy.throwBadConfigException(icon);
                }

                final Item item = Item.REGISTRY.getObject(new ResourceLocation(strings[0]));
                if (item != null) {
                    r = new ItemStackTabIconRenderer(new ItemStack(item, 1, meta));
                } else {
                    r = new TextureTabIconRenderer(new ResourceLocation("rtfm", "textures/gui/manual_missing.png"));
                }
            } else {
                r = new TextureTabIconRenderer(new ResourceLocation(icon));
            }
            String tabPath = tab.get("tabPath").getString();
            tab.get("tabPath").setComment(Constants.CONFIG_COMMENT_TAB_PATH);
            String tabName = tab.get("tabName").getString();
            tab.get("tabName").setComment(Constants.CONFIG_COMMENT_TAB_NAME);
            ManualAPI.addTab(r, tabName, prefix + "/" + tabPath);

            if (mainTab.equals(tab.getName())) {
                mainPath = prefix + "/" + tabPath;
            }
        }

        if (mainPath != null) {
            ManualAPI.addProvider(new MainPageContentProvider(mainPath));
        } else {
            RTFM.getLog().error(String.format("Invalid main tab '%s'", mainTab));
        }

        config.save();
    }
}
