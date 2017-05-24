package li.cil.manual.common.item;

import li.cil.manual.api.ManualAPI;
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

	public static boolean tryOpenManual(final World world, final EntityPlayer player, @Nullable final String path) {
		if(path == null) {
			return false;
		}

		if(world.isRemote) {
			ManualAPI.openFor(player);
			ManualAPI.reset();
			ManualAPI.navigate(path);
		}

		return true;
	}

	// --------------------------------------------------------------------- //
	// Item

	@Override
	public EnumActionResult onItemUse(final EntityPlayer player, final World world, final BlockPos pos, final EnumHand hand, final EnumFacing facing, final float hitX, final float hitY, final float hitZ) {
		return tryOpenManual(world, player, ManualAPI.pathFor(world, pos)) ? EnumActionResult.SUCCESS : EnumActionResult.PASS;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(final World world, final EntityPlayer player, final EnumHand hand) {
		if(world.isRemote) {
			if(player.isSneaking()) {
				ManualAPI.reset();
			}
			ManualAPI.openFor(player);
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
