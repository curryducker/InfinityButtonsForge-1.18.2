package net.larsmans.infinitybuttons.mixin;

import net.larsmans.infinitybuttons.InfinityButtonsUtil;
import net.larsmans.infinitybuttons.block.custom.emergencybutton.SafeEmergencyButton;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.client.multiplayer.MultiPlayerGameMode;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.protocol.game.ServerboundUseItemOnPacket;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MultiPlayerGameMode.class)
public abstract class MultiPlayerGameModeMixin {

    @Shadow @Final private ClientPacketListener connection;

    @Inject(method = "useItemOn", at = @At("HEAD"), cancellable = true)
    private void useItemOn(LocalPlayer player, ClientLevel world, InteractionHand hand, BlockHitResult hitResult, CallbackInfoReturnable<InteractionResult> cir) {
        BlockState state = world.getBlockState(hitResult.getBlockPos());
        Block block = state.getBlock();
        if (InfinityButtonsUtil.crouchClickOverrides(block)) {
            this.connection.send(new ServerboundUseItemOnPacket(hand, hitResult));
            cir.setReturnValue(block.use(state, world, hitResult.getBlockPos(), player, hand, hitResult));
        }
    }
}
