package org.goliath.minigamearchitect.capabilities.checkpoint;

import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.goliath.minigamearchitect.MinigameArchitect;

@Mod.EventBusSubscriber(modid = MinigameArchitect.MOD_ID)
public class CheckpointCapabilityEvents {
    @SubscribeEvent
    public void onPlayerCloned(PlayerEvent.Clone event) {
        if (event.getOriginal().level.isClientSide()) return;
        if (event.isWasDeath()) {
            LazyOptional<ICheckpointCapability> loNewCap = event.getPlayer().getCapability(CheckpointCapability.INSTANCE);
            event.getOriginal().reviveCaps();
            LazyOptional<ICheckpointCapability> loOldCap = event.getOriginal().getCapability(CheckpointCapability.INSTANCE);
            event.getOriginal().invalidateCaps();
            loNewCap.ifPresent(newCap -> loOldCap.ifPresent(oldCap -> newCap.deserializeNBT(oldCap.serializeNBT())));
        }
    }
}
