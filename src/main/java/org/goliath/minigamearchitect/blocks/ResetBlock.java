package org.goliath.minigamearchitect.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.util.LazyOptional;
import org.goliath.minigamearchitect.capabilities.checkpoint.CheckpointCapability;
import org.goliath.minigamearchitect.capabilities.checkpoint.ICheckpointCapability;

public class ResetBlock extends Block {
    public ResetBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void fallOn(Level pLevel, BlockState pState, BlockPos pPos, Entity pEntity, float pFallDistance) {
        if (!(pEntity instanceof Player player)) return;
        reset(player);
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        if (!(entity instanceof Player player)) return;
        reset(player);
    }

    private void reset(Player player) {
        LazyOptional<ICheckpointCapability> checkpointCap = player.getCapability(CheckpointCapability.INSTANCE);
        if (!checkpointCap.isPresent()) {
            player.kill();
            return;
        }
        BlockPos checkpoint = checkpointCap.orElseThrow(IllegalStateException::new).getCheckpoint();
        if (checkpoint == null) {
            player.kill();
            return;
        }
        player.teleportTo(checkpoint.getX() + 0.5, checkpoint.getY(), checkpoint.getZ() + 0.5);
    }
}
