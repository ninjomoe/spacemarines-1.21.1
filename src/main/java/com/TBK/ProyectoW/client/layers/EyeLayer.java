//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.TBK.ProyectoW.client.layers;

import com.TBK.ProyectoW.common.items.WarHammerArmorItem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;

public class EyeLayer<T extends WarHammerArmorItem> extends ItemGeoRenderLayer<T> {
    public EyeLayer(GeoRenderer<T> entityRendererIn) {
        super(entityRendererIn);
    }

    public void render(ItemStack stack, PoseStack poseStack, T animatable, BakedGeoModel model, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        Item var12 = stack.m_41720_();
        if (var12 instanceof ArmorItem armorItem) {
            if (armorItem.m_266204_().m_266308_().equals(EquipmentSlot.HEAD)) {
                ResourceLocation texture = this.getTexture();
                renderType = RenderType.m_110488_(texture);
                VertexConsumer consumer = bufferSource.m_6299_(renderType);
                this.getRenderer().reRender(model, poseStack, bufferSource, animatable, renderType, consumer, partialTick, packedLight, OverlayTexture.f_118083_, 1.0F, 1.0F, 1.0F, 1.0F);
            }
        }

    }

    public ResourceLocation getTexture() {
        return new ResourceLocation("space_marines", "textures/armor/eye_details.png");
    }
}
