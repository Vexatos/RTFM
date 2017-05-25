package vexatos.manualtabs.reference;

/**
 * @author Vexatos
 */
public class Constants {

    public static final String
            CONFIG_COMMENT_TAB_ICON_MODE = "If this value is 0, tabIcon must be the path to a texture in some mod file's assets folder. It can be changed using Resource Packs.\nIf this value is 1, tabIcon must be an ItemStack to display",
            CONFIG_COMMENT_TAB_ICON = "Texture path: 'modid:path/to/texture', example: 'minecraft:textures/items/brick.png'. Note that it will have a black background.\nItemStack: 'modid:itemname@metadata', example: 'minecraft:potato'. metadata is optional, default 0.",
            CONFIG_COMMENT_TAB_PATH = "Default: '%LANGUAGE%/index.md'. The path to the manual page that will open when the tab is clicked.\n'%LANGUAGE%' will be replaced with the currently selected language, for instance 'en_US'",
            CONFIG_COMMENT_TAB_NAME = "The name of the tab. May be a string to display or a localization key which can be changed using resource packs.";

}
