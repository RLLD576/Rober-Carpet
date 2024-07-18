package net.rober.robercarpet.mixin;

import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.LiteralText;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;
import net.rober.robercarpet.RoberCarpetSettings;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;

@Mixin(value = ServerPlayerEntity.class, priority = 100)
public abstract class SleepInBedSetRespawnMixin {
    //@Redirect(method = "trySleep", at = @At(value = "INVOKE",
            //target = "Lnet/minecraft/server/network/ServerPlayerEntity;setSpawnPoint(Lnet/minecraft/util/registry/RegistryKey;Lnet/minecraft/util/math/BlockPos;FZZ)V"))
    @Unique
    private void setSpawnPointRedirect(ServerPlayerEntity player, RegistryKey<World> dimension, @Nullable BlockPos pos, float angle, boolean spawnPointSet, boolean sendMessage){
        switch(RoberCarpetSettings.SleepInBedSetRespawn){
            case "always":
                player.setSpawnPoint(dimension, pos, angle, spawnPointSet, sendMessage);
                if(RoberCarpetSettings.SendSetRespawnFeedback)player.sendMessage(new LiteralText("You have set your respawn point"),true);
                break;
            case "sneaking":
            case "no-sneaking":
                boolean sneaking = player.isSneaking();
                if(sneaking && (Objects.equals(RoberCarpetSettings.SleepInBedSetRespawn, "sneaking"))) {
                    player.setSpawnPoint(dimension, pos, angle, spawnPointSet, sendMessage);
                    player.sendMessage(new LiteralText("You have set your respawn point"),false);
                }
                else{
                    player.sendMessage(new LiteralText("You have slept without setting your spawn point"),false);
                }
                break;
            case "never":
                player.sendMessage(new LiteralText("You have slept without setting your spawn point"),false);
        }
    }
    @Inject(method="setSpawnPoint", at=@At(value="HEAD"), cancellable = true)
    private void setSpawnPointInjector(RegistryKey<World> dimension, @Nullable BlockPos pos, float angle, boolean spawnPointSet, boolean sendMessage, CallbackInfo ci){
        ServerPlayerEntity player = (ServerPlayerEntity) ((Object) this);
        switch(RoberCarpetSettings.SleepInBedSetRespawn){
            case "always":
                if(RoberCarpetSettings.SendSetRespawnFeedback)player.sendMessage(new LiteralText("You have set your respawn point"),true);
                return;
            case "sneaking":
                if(player.isSneaking()){
                    player.sendMessage(new LiteralText("You have set your respawn point"),false);
                    return;
                }else{
                    player.sendMessage(new LiteralText("You have slept without setting your spawn point"),false);
                    ci.cancel();
                }
                break;
            case "no-sneaking":
                if(!player.isSneaking()){
                    player.sendMessage(new LiteralText("You have set your respawn point"),false);
                    return;
                }else{
                    player.sendMessage(new LiteralText("You have slept without setting your spawn point"),false);
                    ci.cancel();
                }
                break;
            case "never":
                player.sendMessage(new LiteralText("You have slept without setting your spawn point"),false);
                ci.cancel();
        }
    }
}