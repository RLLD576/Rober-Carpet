package net.rober.robercarpet.mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.rober.robercarpet.RoberCarpetSettings;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(PlayerEntity.class)
public abstract class SleepingDelayMixin {
    //? if =1.17.1 {
    /*@ModifyConstant(method = "isSleepingLongEnough",constant = @Constant(intValue = 100))
    *///?} else {
    @ModifyConstant(method = "canResetTimeBySleeping",constant = @Constant(intValue = 100))
    //?}
    private int SleepingDelayMixin(int a){
        return RoberCarpetSettings.SleepingDelay;
    }
}
