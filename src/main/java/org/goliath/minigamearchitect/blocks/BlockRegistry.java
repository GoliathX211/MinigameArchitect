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
    private static final RegistryObject<Block> CHECKPOINT_BLOCK = registerBlock("checkpoint_block",
            () -> new CheckpointBlock(Block.Properties.of(Material.HEAVY_METAL)),
            CreativeModeTab.TAB_MISC);
    private static final RegistryObject<Block> SLOW_BLOCK = registerBlock("slow_block",
            () -> new Block(Block.Properties.of(Material.HEAVY_METAL).speedFactor(0.75f)),
            CreativeModeTab.TAB_MISC
    );
    private static final RegistryObject<Block> SLOWER_BLOCK = registerBlock("slower_block",
            () -> new Block(Block.Properties.of(Material.HEAVY_METAL).speedFactor(0.5f)),
            CreativeModeTab.TAB_MISC
    );
    private static final RegistryObject<Block> SLOWEST_BLOCK = registerBlock("slowest_block",
            () -> new Block(Block.Properties.of(Material.HEAVY_METAL).speedFactor(0.25f)),
            CreativeModeTab.TAB_MISC
    );

    private static final RegistryObject<Block> FAST_BLOCK = registerBlock("fast_block",
            () -> new Block(Block.Properties.of(Material.HEAVY_METAL).speedFactor(1.5f)),
            CreativeModeTab.TAB_MISC
    );
    private static final RegistryObject<Block> FASTER_BLOCK = registerBlock("faster_block",
            () -> new Block(Block.Properties.of(Material.HEAVY_METAL).speedFactor(1.75f)),
            CreativeModeTab.TAB_MISC
    );
    private static final RegistryObject<Block> FASTEST_BLOCK = registerBlock("fastest_block",
            () -> new Block(Block.Properties.of(Material.HEAVY_METAL).speedFactor(2.0f)),
            CreativeModeTab.TAB_MISC
    );

    private static final RegistryObject<Block> RESET_BLOCK = registerBlock("reset_block",
            () -> new ResetBlock(Block.Properties.of(Material.HEAVY_METAL)),
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
