package com.bossa.spacemarinearmor.client.layers;

import com.bossa.spacemarinearmor.SpaceMarines;
import com.bossa.spacemarinearmor.common.items.Factions;
import com.bossa.spacemarinearmor.common.items.WarHammerArmorItem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoArmorRenderer;
import software.bernie.geckolib.renderer.GeoRenderer;

public class GoldenDetailsLayer<T extends WarHammerArmorItem> extends ItemGeoRenderLayer<T> {
    public GoldenDetailsLayer(GeoRenderer<T> entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    public void render(PoseStack poseStack, T animatable, BakedGeoModel model, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        if (this.getRenderer() instanceof GeoArmorRenderer<?> armorRenderer) {
            ResourceLocation texture = this.getTexture(animatable.getFaction(armorRenderer.getCurrentStack()));
            RenderType detailRenderType = RenderType.armorCutoutNoCull(texture);
            VertexConsumer consumer = bufferSource.getBuffer(detailRenderType);
            this.getRenderer().reRender(model, poseStack, bufferSource, animatable, detailRenderType, consumer, partialTick, packedLight, OverlayTexture.NO_OVERLAY, -1);
        }
    }

    public ResourceLocation getTexture(Factions factions) {
        String path = "base";
        if (factions.hasDetailGolden()) {
            path = factions.getName();
        }

        return ResourceLocation.fromNamespaceAndPath(SpaceMarines.MODID, "textures/armor/golden_details/" + path + ".png");
    }
}
