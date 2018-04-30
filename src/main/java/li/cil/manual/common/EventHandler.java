package li.cil.manual.common;

import li.cil.manual.api.API;
import li.cil.manual.common.init.Items;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * @author Vexatos
 */
public class EventHandler {

    public static final EventHandler INSTANCE = new EventHandler();

    private EventHandler() {
    }

    @SubscribeEvent
    public void onEntityJoinWorld(EntityJoinWorldEvent e) {
        if (Config.INSTANCE.giveManualToNewPlayers && !e.getWorld().isRemote && e.getEntity() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) e.getEntity();
            if (!(player instanceof FakePlayer)) {
                NBTTagCompound persistedData = getPersistedData(player);
                if (!persistedData.getBoolean(API.MOD_ID + ":receivedManual")) {
                    persistedData.setBoolean(API.MOD_ID + ":receivedManual", true);
                    player.inventory.addItemStackToInventory(new ItemStack(Items.bookManual, 1, 0));
                }
            }
        }
    }

    private NBTTagCompound getPersistedData(EntityPlayer player) {
        NBTTagCompound nbt = player.getEntityData();
        if (!nbt.hasKey(EntityPlayer.PERSISTED_NBT_TAG)) {
            nbt.setTag(EntityPlayer.PERSISTED_NBT_TAG, new NBTTagCompound());
        }
        return nbt.getCompoundTag(EntityPlayer.PERSISTED_NBT_TAG);
    }
}
