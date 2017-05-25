package li.cil.manual.client.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

import javax.annotation.Nullable;

/**
 * GUI handler for the client side - which is, still, all we need.
 */
public final class GuiHandlerClient implements IGuiHandler {
    public enum GuiId {
        BOOK_MANUAL;

        public static final GuiId[] VALUES = values();
    }

    @Override
    @Nullable
    public Object getServerGuiElement(final int id, final EntityPlayer player, final World world, final int x, final int y, final int z) {
        return null;
    }

    @Override
    @Nullable
    public Object getClientGuiElement(final int id, final EntityPlayer player, final World world, final int x, final int y, final int z) {
        switch (GuiId.VALUES[id]) {
            case BOOK_MANUAL:
                return new GuiManual();
        }
        return null;
    }
}
