package org.goliath.minigamearchitect.capabilities.checkpoint;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.util.INBTSerializable;

public interface ICheckpointCapability extends INBTSerializable<CompoundTag> {
    BlockPos getCheckpoint();
    void setCheckpoint(BlockPos pos);
}
