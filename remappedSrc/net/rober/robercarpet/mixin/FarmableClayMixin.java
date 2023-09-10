package net.rober.robercarpet.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.ItemDispenserBehavior;
import net.minecraft.block.entity.DispenserBlockEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPointer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.rober.robercarpet.RoberCarpetSettings;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemDispenserBehavior.class)
public abstract class FarmableClayMixin {
	@Inject( method = "dispenseSilently", at = @At("HEAD"),cancellable = true)
	public void BehaviorMixin(BlockPointer pointer, ItemStack stack, CallbackInfoReturnable<ItemStack> cir) {
		if(RoberCarpetSettings.FarmableClay) {
			World world = pointer.getWorld();
			if (world.isClient) {
				return;
			}
			if (pointer.getBlockState().getBlock() != Blocks.DISPENSER) {
				return;
			}
			BlockPos targetPos = pointer.getPos().offset(pointer.getBlockState().get(DispenserBlock.FACING));
			BlockState block_state = world.getBlockState(targetPos);
			Block block = block_state.getBlock();
			Item item = stack.getItem();
			if (item == Items.POTION && block == Blocks.DIRT) {
				world.setBlockState(targetPos, Blocks.CLAY.getDefaultState());
				cir.setReturnValue(new ItemStack(Items.GLASS_BOTTLE, 1));
				return;
			}
		}
		return;
	}
}
