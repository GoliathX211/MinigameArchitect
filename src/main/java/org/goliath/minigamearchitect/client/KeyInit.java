package org.goliath.minigamearchitect.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.ClientRegistry;
import org.goliath.minigamearchitect.MinigameArchitect;

public final class KeyInit {
    private KeyInit() {
    }

    public static KeyMapping checkpointReturn;

    public static void init() {
        checkpointReturn = registerKey("return_to_checkpoint", KeyMapping.CATEGORY_GAMEPLAY, InputConstants.KEY_C);
    }
    private static KeyMapping registerKey(String name, String category, int keycode) {
        final KeyMapping key = new KeyMapping(
                "key." + MinigameArchitect.MOD_ID + "." + name,
                keycode,
                category
        );
        ClientRegistry.registerKeyBinding(key);
        return key;
    }
}
