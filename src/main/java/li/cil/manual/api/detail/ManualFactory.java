package li.cil.manual.api.detail;

import net.minecraft.item.Item;

import javax.annotation.Nullable;


public interface ManualFactory {
    /**
     * Creates a new manual instance for your use.
     * @return the new manual instance or null on servers.
     */
    @Nullable
    ManualDefinition createManual();

    /**
     * Creates an Item book that will open the specified manual.
     * On servers the manual should be null as manuals don't exist serverside.
     * @param manual the manual returned from {@link ManualFactory#createManual}.
     * @return and Item that you will need to register on both client and server.
     */
    Item createItemForManual(@Nullable ManualDefinition manual);
}
