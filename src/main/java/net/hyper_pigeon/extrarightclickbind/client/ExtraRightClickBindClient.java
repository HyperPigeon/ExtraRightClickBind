package net.hyper_pigeon.extrarightclickbind.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

@Environment(EnvType.CLIENT)
public class ExtraRightClickBindClient implements ClientModInitializer {

    private static KeyBinding rightClickKey;
    private static boolean isRightClicking = false;
    @Override
    public void onInitializeClient() {
        rightClickKey = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.extrarightclickbind.right_click", InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_R, "Extra Right Click"));
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (rightClickKey.isPressed() && !isRightClicking) {
                isRightClicking = true;
                KeyBinding.setKeyPressed(InputUtil.Type.MOUSE.createFromCode(1), true);
            }
            else if(!rightClickKey.isPressed() && isRightClicking) {
                isRightClicking = false;
                KeyBinding.setKeyPressed(InputUtil.Type.MOUSE.createFromCode(1), false);
            }
        });
    }
}
