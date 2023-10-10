package net.rober.robercarpet.mixin;

import net.minecraft.network.MessageType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.LiteralText;
import net.minecraft.world.level.ServerWorldProperties;
import net.rober.robercarpet.RoberCarpetSettings;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.BooleanSupplier;

@Mixin(ServerWorld.class)
public abstract class ThunderWarnMixin {
    @Shadow protected ServerWorldProperties worldProperties;
@Inject(method = "tick",at=@At("TAIL"))
    private void tickMixin(BooleanSupplier shouldKeepTicking,CallbackInfo ci){
        if(RoberCarpetSettings.ThunderWarn!=-1) {
            ServerWorld server = (ServerWorld) (Object) this;
            if((willBeThunderstorm(RoberCarpetSettings.ThunderWarn,worldProperties))){
                for (ServerPlayerEntity player : server.getPlayers()){
                    player.sendMessage(new LiteralText("A storm will begin in "+RoberCarpetSettings.ThunderWarn/60+" minutes"), MessageType.CHAT,player.getUuid());
                }
            }
        }
    }
    boolean willBeThunderstorm(int delay,ServerWorldProperties worldProperties){
        int thunderTime = worldProperties.getThunderTime();
        int rainTime = worldProperties.getRainTime();
        boolean thundering = worldProperties.isThundering();
        boolean raining = worldProperties.isRaining();
        if(thunderTime==delay){
            return raining == rainTime > delay;
        }else if(rainTime==delay){
            return thundering == thunderTime > delay;
        }
    return false;
    }
}