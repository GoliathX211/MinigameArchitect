package org.goliath.minigamearchitect.blocks;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.goliath.minigamearchitect.MinigameArchitect;
import org.goliath.minigamearchitect.items.ItemRegistry;

import java.util.function.Supplier;

public class BlockRegistry {
    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MinigameArchitect.MOD_ID);
    private static final RegistryObject<Block> CHECKPOINT_BLOCK = registerBlock(
            "checkpoint_block",
            () -> new CheckpointBlock(Block.Properties.of(Material.HEAVY_METAL)),
            CreativeModeTab.TAB_MISC
    );

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> registryObject = BLOCKS.register(name, block);
        registerBlockItem(name, registryObject, tab);
        return registryObject;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab) {
        return ItemRegistry.ITEMS.register(
                name,
                () -> new BlockItem(block.get(), new Item.Properties().tab(tab))
        );
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
