package li.cil.manual.common;

import li.cil.manual.api.API;
import li.cil.manual.api.ManualAPI;
import li.cil.manual.api.manual.TabIconRenderer;
import li.cil.manual.api.prefab.manual.ItemStackTabIconRenderer;
import li.cil.manual.api.prefab.manual.TextureTabIconRenderer;
import li.cil.manual.common.api.ManualAPIImpl;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Configuration;
import vexatos.manualtabs.manual.ConfigContentProvider;

import java.io.File;

public enum Config {
    INSTANCE;

    private Configuration config;

    public void load(final File file) {
        config = new Configuration(file);
        config.load();
    }

    public boolean giveManualToNewPlayers;

    public void init() {
        final String mainTab = config.getString("mainTab", "general", "exampletab", "The main tab that will be selected when you first open the manual");
        giveManualToNewPlayers = config.getBoolean("giveManualToNewPlayers", "general", false, "Whether to give a new player a free copy of the manual. This will only happen once per player.");
        ManualAPI.addProvider(new ConfigContentProvider());
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
                    r = new TextureTabIconRenderer(new ResourceLocation(API.MOD_ID, "textures/gui/manual_missing.png"));
                }
            } else {
                r = new TextureTabIconRenderer(new ResourceLocation(icon));
            }
            String tabPath = tab.get("tabPath").getString();
            tab.get("tabPath").setComment(Constants.CONFIG_COMMENT_TAB_PATH);
            String tabName = tab.get("tabName").getString();
            tab.get("tabName").setComment(Constants.CONFIG_COMMENT_TAB_NAME);
            ManualAPI.addTab(r, tabName, tabPath);

            if (mainTab.equals(tab.getName())) {
                mainPath = tabPath;
            }
        }

        if (mainPath != null) {
            ManualAPIImpl.setDefaultPage(mainPath);
        } else {
            RTFM.getLog().error(String.format("Invalid main tab '%s'", mainTab));
        }

        config.save();
    }
}
