package com.TBK.ProyectoW.client;

import com.TBK.ProyectoW.SpaceMarines;
import com.TBK.ProyectoW.common.items.WarHammerArmorItem;
import com.mojang.blaze3d.shaders.FogShape;
import net.minecraft.client.Camera;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.material.FogType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ViewportEvent;

@EventBusSubscriber(modid = SpaceMarines.MODID, value = Dist.CLIENT)
public class ClientArmorEvents {
    @SubscribeEvent
    public static void onRenderFog(ViewportEvent.RenderFog event) {
        if (event.getType() != FogType.LAVA || !hasFullMarineSet(event.getCamera())) {
            return;
        }

        event.setNearPlaneDistance(0.0F);
        event.setFarPlaneDistance(Math.max(event.getFarPlaneDistance(), 96.0F));
        event.setFogShape(FogShape.SPHERE);
        event.setCanceled(true);
    }

    private static boolean hasFullMarineSet(Camera camera) {
        Entity entity = camera.getEntity();
        return entity instanceof LivingEntity livingEntity && WarHammerArmorItem.hasFullMarineSet(livingEntity);
    }
}
