package li.cil.manual.api;


/**
 * Glue / actual references for the cillimanual API.
 */
public final class API {
    /**
     * The ID of the mod, i.e. the internal string it is identified by.
     */
    public static final String MOD_ID = "cillimanual";

    /**
     * The current version of the mod.
     */
    public static final String MOD_VERSION = "@VERSION@";

    // --------------------------------------------------------------------- //

    // Set in cillimanual pre-init, prefer using static entry point classes instead.
    public static li.cil.manual.api.detail.FontRendererAPI fontRendererAPI;
    public static li.cil.manual.api.detail.ManualAPI manualAPI;

    private API() {
    }
}
