package org.goliath.minigamearchitect.client.events;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.goliath.minigamearchitect.MinigameArchitect;
import org.goliath.minigamearchitect.client.KeyInit;
import org.goliath.minigamearchitect.network.PacketHandler;
import org.goliath.minigamearchitect.network.ServerboundCheckpointReturnPacket;

@Mod.EventBusSubscriber(modid = MinigameArchitect.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public final class ClientForgeEvents {
    private ClientForgeEvents() {
    }

    @SubscribeEvent
    public static void clientTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            while (KeyInit.checkpointReturn.consumeClick()) {
                PacketHandler.INSTANCE.sendToServer(new ServerboundCheckpointReturnPacket());
            }
        }
    }
}
