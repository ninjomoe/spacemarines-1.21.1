//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.TBK.ProyectoW.client.renderers;

import com.TBK.ProyectoW.client.layers.EyeLayer;
import com.TBK.ProyectoW.client.layers.GoldenDetailsLayer;
import com.TBK.ProyectoW.client.layers.ItemGeoRenderLayer;
import com.TBK.ProyectoW.client.models.WarHammerArmorModel;
import com.TBK.ProyectoW.common.items.WarHammerArmorItem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import javax.annotation.Nullable;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.item.DyeableLeatherItem;
import net.minecraft.world.item.Item;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoArmorRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;

public class WarHammerArmorRenderer<T extends WarHammerArmorItem> extends GeoArmorRenderer<T> {
    public WarHammerArmorRenderer() {
        super(new WarHammerArmorModel());
        this.addRenderLayer(new GoldenDetailsLayer(this));
        this.addRenderLayer(new EyeLayer(this));
    }

    public void defaultRender(PoseStack poseStack, T animatable, MultiBufferSource bufferSource, @Nullable RenderType renderType, @Nullable VertexConsumer buffer, float yaw, float partialTick, int packedLight) {
        poseStack.m_85836_();
        float red = 1.0F;
        float green = 1.0F;
        float blue = 1.0F;
        Item var13 = this.currentStack.m_41720_();
        if (var13 instanceof DyeableLeatherItem dyeableLeatherItem) {
            if (dyeableLeatherItem.m_41113_(this.currentStack) && dyeableLeatherItem.m_41121_(this.currentStack) != 0) {
                int i = dyeableLeatherItem.m_41121_(this.currentStack);
                red = (float)(i >> 16 & 255) / 255.0F;
                green = (float)(i >> 8 & 255) / 255.0F;
                blue = (float)(i & 255) / 255.0F;
            }
        }

        int packedOverlay = this.getPackedOverlay(animatable, 0.0F, partialTick);
        BakedGeoModel model = this.getGeoModel().getBakedModel(this.getGeoModel().getModelResource(animatable));
        if (renderType == null) {
            renderType = this.getRenderType(animatable, this.getTextureLocation(animatable), bufferSource, partialTick);
        }

        if (buffer == null) {
            buffer = bufferSource.m_6299_(renderType);
        }

        this.preRender(poseStack, animatable, model, bufferSource, buffer, false, partialTick, packedLight, packedOverlay, red, green, blue, 1.0F);
        if (this.firePreRenderEvent(poseStack, model, bufferSource, partialTick, packedLight)) {
            this.preApplyRenderLayers(poseStack, animatable, model, renderType, bufferSource, buffer, (float)packedLight, packedLight, packedOverlay);
            this.actuallyRender(poseStack, animatable, model, renderType, bufferSource, buffer, false, partialTick, packedLight, packedOverlay, red, green, blue, 1.0F);
            this.applyRenderLayers(poseStack, animatable, model, renderType, bufferSource, buffer, partialTick, packedLight, packedOverlay);
            this.postRender(poseStack, animatable, model, bufferSource, buffer, false, partialTick, packedLight, packedOverlay, red, green, blue, 1.0F);
            this.firePostRenderEvent(poseStack, model, bufferSource, partialTick, packedLight);
        }

        poseStack.m_85849_();
        this.renderFinal(poseStack, animatable, model, bufferSource, buffer, partialTick, packedLight, packedOverlay, red, green, blue, 1.0F);
    }

    public void applyRenderLayers(PoseStack poseStack, T animatable, BakedGeoModel model, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        for(GeoRenderLayer<T> renderLayer : this.getRenderLayers()) {
            ((ItemGeoRenderLayer)renderLayer).render(this.currentStack, poseStack, animatable, model, renderType, bufferSource, buffer, partialTick, packedLight, packedOverlay);
        }

    }
}
