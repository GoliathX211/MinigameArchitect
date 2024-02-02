package org.goliath.minigamearchitect.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.goliath.minigamearchitect.capabilities.checkpoint.CheckpointCapability;

public class CheckpointBlock extends Block {
    private static final String CHECKPOINT_SET_TRANSLATION_KEY = "feedback.minigamearchitect.checkpoint_set";
    public CheckpointBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        if (!(entity instanceof Player player)) {
            return;
        }
        setCheckpoint(player, pos);
    }
    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        setCheckpoint(player, pos);
        return super.use(state, level, pos, player, hand, hit);
    }

    private void setCheckpoint(Player player, BlockPos pos) {

        player.getCapability(CheckpointCapability.INSTANCE).ifPresent(checkpoint -> checkpoint.setCheckpoint(pos.above()));
        player.displayClientMessage(
                new TranslatableComponent(CHECKPOINT_SET_TRANSLATION_KEY,
                        pos.getX(),
                        pos.getY() + 1,
                        pos.getZ()
                ),
                true
        );
    }
}
