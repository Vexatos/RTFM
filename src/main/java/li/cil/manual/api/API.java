package li.cil.manual.api;


import li.cil.manual.api.detail.ManualDefinition;
import li.cil.manual.api.detail.ManualFactory;

/**
 * Glue / actual references for the RTFM API.
 */
public final class API {
    /**
     * The ID of the mod, i.e. the internal string it is identified by.
     */
    public static final String MOD_ID = "rtfm";

    /**
     * The current version of the mod.
     */
    public static final String MOD_VERSION = "@VERSION@";

    // --------------------------------------------------------------------- //

    // The default manual book. Set in RTFM pre-init. You should generally not modify this.
    public static ManualDefinition manualAPI;

    // Factory for creating your own manuals, and the item form of it.
    public static ManualFactory manualFactory;

    private API() {
    }
}
