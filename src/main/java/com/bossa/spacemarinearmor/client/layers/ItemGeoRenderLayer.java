package com.bossa.spacemarinearmor.client.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;

public class ItemGeoRenderLayer<T extends GeoAnimatable> extends GeoRenderLayer<T> {
    public ItemGeoRenderLayer(GeoRenderer<T> entityRendererIn) {
        super(entityRendererIn);
    }
}
