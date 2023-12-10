package net.rober.robercarpet.register.dispenser;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.FallibleItemDispenserBehavior;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPointer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.rober.robercarpet.RoberCarpetSettings;

public  class FarmableClayBehavior {
    public static void init(){
        DispenserBlock.registerBehavior(Items.POTION, new FallibleItemDispenserBehavior() {
            @Override
            public ItemStack dispenseSilently(BlockPointer pointer, ItemStack stack) {
                if(!RoberCarpetSettings.FarmableClay)return super.dispenseSilently(pointer,stack);
                World world = pointer.getWorld();
                if (world.isClient) {
                    return super.dispenseSilently(pointer, stack);
                }
                if (pointer.getBlockState().getBlock() != Blocks.DISPENSER) {
                    return super.dispenseSilently(pointer,stack);
                }
                BlockPos targetPos = pointer.getPos().offset(pointer.getBlockState().get(DispenserBlock.FACING));
                BlockState block_state = world.getBlockState(targetPos);
                Block block = block_state.getBlock();
                if(block==Blocks.DIRT){
                    world.setBlockState(targetPos,Blocks.CLAY.getDefaultState());
                return new ItemStack(Items.GLASS_BOTTLE,1);
                }
                return super.dispenseSilently(pointer, stack);
            }
        });
    }
}
