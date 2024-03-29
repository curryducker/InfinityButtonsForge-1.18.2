package net.larsmans.infinitybuttons.network.packets;

import net.larsmans.infinitybuttons.network.IBClientPacketHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class LetterButtonScreenPacket {
    private final BlockPos pos;

    public LetterButtonScreenPacket(BlockPos pos) {
        this.pos = pos;
    }

    public static void encode(LetterButtonScreenPacket packet, FriendlyByteBuf buffer) {
        buffer.writeBlockPos(packet.pos);
    }

    public BlockPos getPos() {
        return pos;
    }

    public static LetterButtonScreenPacket decode(FriendlyByteBuf buffer) {
        BlockPos pos = buffer.readBlockPos();
        return new LetterButtonScreenPacket(pos);
    }

    public static void handle(LetterButtonScreenPacket packet, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> IBClientPacketHandler.handleScreenPacket(packet)));
        context.setPacketHandled(true);
    }
}
