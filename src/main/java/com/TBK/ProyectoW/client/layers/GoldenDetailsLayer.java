//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.TBK.ProyectoW.client.layers;

import com.TBK.ProyectoW.common.items.Factions;
import com.TBK.ProyectoW.common.items.WarHammerArmorItem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;

@OnlyIn(Dist.CLIENT)
public class GoldenDetailsLayer<T extends WarHammerArmorItem> extends ItemGeoRenderLayer<T> {
    public GoldenDetailsLayer(GeoRenderer<T> entityRendererIn) {
        super(entityRendererIn);
    }

    public void render(ItemStack stack, PoseStack poseStack, T animatable, BakedGeoModel model, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        ResourceLocation texture = this.getTexture(animatable.getFaction(stack));
        renderType = RenderType.m_110431_(texture);
        VertexConsumer consumer = bufferSource.m_6299_(renderType);
        this.getRenderer().reRender(model, poseStack, bufferSource, animatable, renderType, consumer, partialTick, packedLight, OverlayTexture.f_118083_, 1.0F, 1.0F, 1.0F, 1.0F);
    }

    public ResourceLocation getTexture(Factions factions) {
        String path = "base";
        if (factions.hasDetailGolden()) {
            path = factions.getName();
        }

        return new ResourceLocation("space_marines", "textures/armor/golden_details/" + path + ".png");
    }
}
