package li.cil.manual.common.item;

import li.cil.manual.api.detail.ManualDefinition;
import li.cil.manual.common.api.ManualDefinitionImpl;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBook;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

/**
 * The manual!
 */
public final class ItemBookManual extends ItemBook {
    private final ManualDefinition myManual;

    public ItemBookManual(){
        myManual = ManualDefinitionImpl.INSTANCE;
    }

    public ItemBookManual(ManualDefinition m){
        myManual = m;
    }

    public static boolean tryOpenManual(final ManualDefinition manual, final World world, final EntityPlayer player, @Nullable final String path){
        if (path == null) {
            return false;
        }

        if (world.isRemote) {
            manual.openFor(player);
            manual.reset();
            manual.navigate(path);
        }

        return true;
    }

    @Deprecated
    public static boolean tryOpenManual(final World world, final EntityPlayer player, @Nullable final String path) {
        return tryOpenManual(ManualDefinitionImpl.INSTANCE, world, player, path);
    }

    // --------------------------------------------------------------------- //
    // Item

    @Override
    public EnumActionResult onItemUse(final EntityPlayer player, final World world, final BlockPos pos, final EnumHand hand, final EnumFacing facing, final float hitX, final float hitY, final float hitZ) {
        return tryOpenManual(myManual, world, player, myManual.pathFor(world, pos)) ? EnumActionResult.SUCCESS : EnumActionResult.PASS;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(final World world, final EntityPlayer player, final EnumHand hand) {
        if (world.isRemote) {
            if (player.isSneaking()) {
                myManual.reset();
            }
            myManual.openFor(player);
            return ActionResult.newResult(EnumActionResult.SUCCESS, player.getHeldItem(hand));
        }
        return super.onItemRightClick(world, player, hand);
    }

    // --------------------------------------------------------------------- //
    // ItemBook

    @Override
    public boolean isEnchantable(final ItemStack stack) {
        return false;
    }

    @Override
    public int getItemEnchantability() {
        return 0;
    }
}
