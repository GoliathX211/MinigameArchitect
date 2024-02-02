package org.goliath.minigamearchitect.client.events;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.goliath.minigamearchitect.MinigameArchitect;
import org.goliath.minigamearchitect.client.KeyInit;

@Mod.EventBusSubscriber(modid = MinigameArchitect.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value= Dist.CLIENT)
public final class ClientModEvents {
    private ClientModEvents() {
    }
    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        KeyInit.init();
    }
}
