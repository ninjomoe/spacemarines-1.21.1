package com.TBK.ProyectoW.client;

import com.TBK.ProyectoW.SpaceMarines;
import com.TBK.ProyectoW.common.boost.BoostPackHandler;
import com.TBK.ProyectoW.common.items.WarHammerArmorItem;
import com.TBK.ProyectoW.common.network.BoostPackTogglePayload;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.event.RenderGuiEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import org.lwjgl.glfw.GLFW;

@EventBusSubscriber(modid = SpaceMarines.MODID, value = Dist.CLIENT)
public class BoostPackClient {
    private static final KeyMapping BOOST_KEY = new KeyMapping(
            "key.space_marines.boost_pack",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_J,
            "key.categories.space_marines"
    );
    private static boolean boostPackEnabled;
    private static boolean sentBoosting;
    private static int clientBoostTicks = BoostPackHandler.MAX_BOOST_TICKS;

    @SubscribeEvent
    public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
        event.register(BOOST_KEY);
    }

    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Post event) {
        Minecraft minecraft = Minecraft.getInstance();
        Player player = minecraft.player;
        if (player == null) {
            boostPackEnabled = false;
            sentBoosting = false;
            clientBoostTicks = BoostPackHandler.MAX_BOOST_TICKS;
            return;
        }

        while (BOOST_KEY.consumeClick()) {
            boostPackEnabled = !boostPackEnabled;
            if (!boostPackEnabled) {
                setBoosting(false);
            }
        }

        if (!WarHammerArmorItem.hasMarineChestplate(player)) {
            boostPackEnabled = false;
            setBoosting(false);
            clientBoostTicks = BoostPackHandler.MAX_BOOST_TICKS;
            return;
        }

        if (player.onGround()) {
            clientBoostTicks = BoostPackHandler.MAX_BOOST_TICKS;
        }

        boolean jumpDown = minecraft.options.keyJump.isDown();
        boolean shouldBoost = boostPackEnabled && jumpDown && clientBoostTicks > 0;
        if (shouldBoost && !sentBoosting) {
            setBoosting(true);
        } else if (!shouldBoost && sentBoosting) {
            setBoosting(false);
        }

        if (sentBoosting && clientBoostTicks > 0) {
            PacketDistributor.sendToServer(new BoostPackTogglePayload(true, player.xxa, player.zza, player.isSprinting()));
            clientBoostTicks--;
            if (clientBoostTicks <= 0) {
                setBoosting(false);
            }
        }
    }

    private static void setBoosting(boolean boosting) {
        if (sentBoosting == boosting) {
            return;
        }

        sentBoosting = boosting;

        Minecraft minecraft = Minecraft.getInstance();
        Player player = minecraft.player;
        float strafe = player == null ? 0.0F : player.xxa;
        float forward = player == null ? 0.0F : player.zza;
        boolean sprinting = player != null && player.isSprinting();
        PacketDistributor.sendToServer(new BoostPackTogglePayload(boosting, strafe, forward, sprinting));
    }

    @SubscribeEvent
    public static void onRenderGui(RenderGuiEvent.Post event) {
        if (clientBoostTicks >= BoostPackHandler.MAX_BOOST_TICKS) {
            return;
        }

        GuiGraphics graphics = event.getGuiGraphics();
        Minecraft minecraft = Minecraft.getInstance();
        int width = minecraft.getWindow().getGuiScaledWidth();
        int height = minecraft.getWindow().getGuiScaledHeight();
        int barWidth = 18;
        int barHeight = 30;
        int hotbarWidth = 182;
        int x = (width - hotbarWidth) / 2 - barWidth - 6;
        int y = height - 24;
        float progress = clientBoostTicks / (float) BoostPackHandler.MAX_BOOST_TICKS;
        int stripes = 10;
        int activeStripes = Math.round(stripes * progress);

        graphics.fill(x, y, x + barWidth, y + barHeight, 0x99000000);
        for (int i = 0; i < stripes; i++) {
            int stripeY = y + 2 + i * 3;
            boolean depleted = i < stripes - activeStripes;
            int color = depleted ? 0xFF555555 : 0xFFFFFFFF;
            graphics.fill(x + 3, stripeY, x + barWidth - 3, stripeY + 2, color);
        }
    }
}
