package org.goliath.minigamearchitect.network;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;
import org.goliath.minigamearchitect.capabilities.checkpoint.CheckpointCapability;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Supplier;

public class ServerboundCheckpointReturnPacket {
    public ServerboundCheckpointReturnPacket() {
    }

    public void encode(FriendlyByteBuf buffer) {

    }

    public static ServerboundCheckpointReturnPacket decode(FriendlyByteBuf buffer) {
        return new ServerboundCheckpointReturnPacket();
    }

    public boolean handle(Supplier<NetworkEvent.Context> context) {
        final AtomicBoolean success = new AtomicBoolean(false);
        context.get().enqueueWork(() -> {
            ServerPlayer sPlayer = context.get().getSender();
            if (sPlayer == null) {
                return;
            }
            sPlayer.getCapability(CheckpointCapability.INSTANCE).ifPresent(checkpoint -> {
                BlockPos checkpointPos = checkpoint.getCheckpoint();
                sPlayer.teleportTo(checkpointPos.getX() + 0.5, checkpointPos.getY(), checkpointPos.getZ() + 0.5);
                success.set(true);
            });
        });
        context.get().setPacketHandled(true);
        return success.get();
    }

}
