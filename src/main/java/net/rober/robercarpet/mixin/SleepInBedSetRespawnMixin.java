package net.rober.robercarpet.mixin;

import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
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

//? if <1.19.4 {
/*import net.minecraft.text.LiteralText;
import net.minecraft.util.registry.RegistryKey;
*///?} else {
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;
//?}

@Mixin(value = ServerPlayerEntity.class, priority = 100)
public abstract class SleepInBedSetRespawnMixin {
    @Inject(method="setSpawnPoint", at=@At(value="HEAD"), cancellable = true)
    private void setSpawnPointInjector(RegistryKey<World> dimension, BlockPos pos, float angle, boolean forced, boolean sendMessage, CallbackInfo ci){
        ServerPlayerEntity player = (ServerPlayerEntity) ((Object) this);
        switch(RoberCarpetSettings.SleepInBedSetsRespawn){
            case "always":
                if(RoberCarpetSettings.SendSetRespawnFeedback)send("You have set your respawn point",player);
                return;
            case "sneaking":
                if(player.isSneaking()){
                    send("You have set your respawn point",player);
                    return;
                }else{
                    send("You have slept without setting your spawn point",player);
                    ci.cancel();
                }
                break;
            case "no-sneaking":
                if(!player.isSneaking()){
                    send("You have set your respawn point",player);
                    return;
                }else{
                    send("You have slept without setting your spawn point",player);
                    ci.cancel();
                }
                break;
            case "never":
                send("You have slept without setting your spawn point",player);
                ci.cancel();
        }
    }
    private static void send(String mes, ServerPlayerEntity player){
        //? if <1.19 {
        /*player.sendMessage(new LiteralText(mes),false);
        *///?} else
        player.sendMessage(Text.of(mes),false);
    }
}