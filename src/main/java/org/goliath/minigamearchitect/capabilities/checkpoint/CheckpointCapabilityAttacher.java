package org.goliath.minigamearchitect.capabilities.checkpoint;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.goliath.minigamearchitect.MinigameArchitect;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Mod.EventBusSubscriber(modid = MinigameArchitect.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CheckpointCapabilityAttacher {
    private static class CheckpointCapabilityProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
        public static final ResourceLocation IDENTIFIER = new ResourceLocation(MinigameArchitect.MOD_ID, "checkpoint");
        private final ICheckpointCapability backend = new CheckpointCapabilityImpl();
        private final LazyOptional<ICheckpointCapability> optionalData = LazyOptional.of(() -> backend);

        /**
         * Retrieves the Optional handler for the capability requested on the specific side.
         * The return value <strong>CAN</strong> be the same for multiple faces.
         * Modders are encouraged to cache this value, using the listener capabilities of the Optional to
         * be notified if the requested capability get lost.
         *
         * @param cap  The capability to check
         * @param side The Side to check from,
         *             <strong>CAN BE NULL</strong>. Null is defined to represent 'internal' or 'self'
         * @return The requested an optional holding the requested capability.
         */
        @NotNull
        @Override
        public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
            return CheckpointCapability.INSTANCE.orEmpty(cap, this.optionalData);
        }

        void invalidate() {
            this.optionalData.invalidate();
        }

        @Override
        public CompoundTag serializeNBT() {
            return this.backend.serializeNBT();
        }

        @Override
        public void deserializeNBT(CompoundTag nbt) {
            this.backend.deserializeNBT(nbt);
        }
    }

    @SubscribeEvent
    public static void attachToPlayer(final AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player player) {
            if (!player.getCapability(CheckpointCapability.INSTANCE).isPresent()) {
                event.addCapability(CheckpointCapabilityProvider.IDENTIFIER, new CheckpointCapabilityProvider());
            }
        }
    }



    private CheckpointCapabilityAttacher() {
    }
}
