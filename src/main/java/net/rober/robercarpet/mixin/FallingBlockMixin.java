package net.rober.robercarpet.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.rober.robercarpet.RoberCarpetSettings;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(FallingBlockEntity.class)
public abstract class FallingBlockMixin {
    //? if =1.17 {
    /*@Redirect(method = "tick()V",at=@At(value="INVOKE",target="Lnet/minecraft/block/BlockState;isOf(Lnet/minecraft/block/Block;)Z",ordinal = 1))
     *///?} else {
    @Redirect(method = "tick()V",at=@At(value="INVOKE",target="Lnet/minecraft/block/BlockState;isOf(Lnet/minecraft/block/Block;)Z",ordinal = 0))
    //?}
    private boolean FallingBlockBehaviorMixin(BlockState blockHitResult, Block arg){
        FallingBlockEntity self = (FallingBlockEntity) (Object) this;
        //? <1.19 {
        /*BlockPos pos = new BlockPos(self.getPos().getX(),Math.ceil(self.getPos().getY()),self.getPos().getZ()).down();
        *///?} else {
        BlockPos pos = new BlockPos((int) self.getPos().getX(), (int) Math.ceil(self.getPos().getY()), (int) self.getPos().getZ()).down();
        //?}
        //? <1.20.6 {
        /*Block underneath = self.world.getBlockState(pos).getBlock();
        *///?} else {
        Block underneath = self.getWorld().getBlockState(pos).getBlock();
        //?}

        return blockHitResult.isOf(arg)||(underneath==Blocks.AIR&& RoberCarpetSettings.OldFallingBehavior);
    }
    @Redirect(method="tick()V",at=@At(value="INVOKE",target = "Lnet/minecraft/util/math/Vec3d;multiply(DDD)Lnet/minecraft/util/math/Vec3d;",ordinal = 0))
    private Vec3d FrictionMixin(Vec3d vec,double x,double y,double z){
        FallingBlockEntity self = (FallingBlockEntity) (Object) this;
        //? <1.19 {
        /*BlockPos pos = new BlockPos(self.getPos().getX(),Math.ceil(self.getPos().getY()),self.getPos().getZ()).down();
        *///?} else {
        BlockPos pos = new BlockPos((int) self.getPos().getX(), (int) Math.ceil(self.getPos().getY()), (int) self.getPos().getZ()).down();
        //?}
        //? <1.20.6 {
        /*Block underneath = self.world.getBlockState(pos).getBlock();
        *///?} else {
        Block underneath = self.getWorld().getBlockState(pos).getBlock();
        //?}
        return underneath==Blocks.AIR&&RoberCarpetSettings.FallingBlockNoFrictionWithWalls?vec:vec.multiply(x,y,z);
    }

    @ModifyConstant(method = "tick()V",constant = @Constant(intValue = 600))
    public int AgeMixin(int a){
        if(RoberCarpetSettings.FallingBlockDieAge==-1) {
            return (int) Double.POSITIVE_INFINITY;
        } else if (RoberCarpetSettings.FallingBlockDieAge>0) {
            return RoberCarpetSettings.FallingBlockDieAge;
        }
        return 600;
    }

}