package org.goliath.minigamearchitect.capabilities.checkpoint;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;

public class CheckpointCapability {
    public static final Capability<ICheckpointCapability> INSTANCE = CapabilityManager.get(new CapabilityToken<>() {});
}
