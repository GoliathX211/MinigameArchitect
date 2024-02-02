package org.goliath.minigamearchitect.network;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import org.goliath.minigamearchitect.MinigameArchitect;

import java.util.Optional;

public class PacketHandler {
    private PacketHandler() {
    }

    private static final String PROTOCOL_VERSION = "1";

    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(MinigameArchitect.MOD_ID, "main"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    public static void init() {
        int index = 0;
        INSTANCE.registerMessage(
                index++, ServerboundCheckpointReturnPacket.class,
                ServerboundCheckpointReturnPacket::encode, ServerboundCheckpointReturnPacket::decode,
                ServerboundCheckpointReturnPacket::handle,
                Optional.of(NetworkDirection.PLAY_TO_SERVER)
        );
    }
}
