package net.rober.robercarpet.mixin;

import net.minecraft.server.network.ServerPlayerEntity;
import net.rober.robercarpet.RoberCarpetSettings;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ServerPlayerEntity.class)
public abstract class KeepInventoryMixin {
    @Redirect(method= "copyFrom(Lnet/minecraft/server/network/ServerPlayerEntity;Z)V",at=@At(value = "FIELD",target = "Lnet/minecraft/server/network/ServerPlayerEntity;experienceLevel:I", opcode = Opcodes.PUTFIELD))
    private void LevelMixin(ServerPlayerEntity player, int original){
        player.experienceLevel = RoberCarpetSettings.KeepInventoryResetXP?0:original;
    }
    @Redirect(method= "copyFrom(Lnet/minecraft/server/network/ServerPlayerEntity;Z)V",at=@At(value = "FIELD",target = "Lnet/minecraft/server/network/ServerPlayerEntity;totalExperience:I", opcode = Opcodes.PUTFIELD))
    private void TotalMixin(ServerPlayerEntity player, int original){
        player.totalExperience = RoberCarpetSettings.KeepInventoryResetXP?0:original;
    }
    @Redirect(method= "copyFrom(Lnet/minecraft/server/network/ServerPlayerEntity;Z)V",at=@At(value = "FIELD",target = "Lnet/minecraft/server/network/ServerPlayerEntity;experienceProgress:F", opcode = Opcodes.PUTFIELD))
    private void ProgressMixin(ServerPlayerEntity player, float original){
        player.experienceProgress = RoberCarpetSettings.KeepInventoryResetXP?0:original;
    }
}