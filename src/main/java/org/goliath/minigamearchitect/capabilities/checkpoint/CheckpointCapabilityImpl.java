package org.goliath.minigamearchitect.capabilities.checkpoint;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;

public class CheckpointCapabilityImpl implements ICheckpointCapability {
    private static final String NBT_KEY_CHECKPOINT_POS = "checkpointPos";
    private BlockPos checkpointPos = null;
    @Override
    public BlockPos getCheckpoint() {
        return this.checkpointPos;
    }

    @Override
    public void setCheckpoint(BlockPos pos) {
        this.checkpointPos = pos;
    }

    @Override
    public CompoundTag serializeNBT() {
        final CompoundTag nbt = new CompoundTag();
        int x = this.checkpointPos.getX();
        int y = this.checkpointPos.getY();
        int z = this.checkpointPos.getZ();
        nbt.putInt(NBT_KEY_CHECKPOINT_POS + "X", x);
        nbt.putInt(NBT_KEY_CHECKPOINT_POS + "Y", y);
        nbt.putInt(NBT_KEY_CHECKPOINT_POS + "Z", z);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        int x = nbt.getInt(NBT_KEY_CHECKPOINT_POS + "X");
        int y = nbt.getInt(NBT_KEY_CHECKPOINT_POS + "Y");
        int z = nbt.getInt(NBT_KEY_CHECKPOINT_POS + "Z");
        this.checkpointPos = new BlockPos(x, y, z);
    }
}

