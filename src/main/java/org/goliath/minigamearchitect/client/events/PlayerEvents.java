package org.goliath.minigamearchitect.client.events;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.goliath.minigamearchitect.MinigameArchitect;
import org.goliath.minigamearchitect.blocks.ResetBlock;

@Mod.EventBusSubscriber(modid = MinigameArchitect.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class PlayerEvents {

    @SubscribeEvent
    public static void checkResetBlockBelow(TickEvent.PlayerTickEvent event) {
        Player player = event.player;
        Level level = player.level;
        BlockState blockState = level.getBlockState(player.getOnPos());
        if (blockState.getBlock() instanceof ResetBlock resetBlock) {
            resetBlock.fallOn(player.level, player.getFeetBlockState(), player.blockPosition(), player, 0);
        }
    }
}
