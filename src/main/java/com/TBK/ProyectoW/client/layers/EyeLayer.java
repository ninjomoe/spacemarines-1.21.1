package com.TBK.ProyectoW.client.layers;

import com.TBK.ProyectoW.SpaceMarines;
import com.TBK.ProyectoW.common.items.MarineShadesItem;
import com.TBK.ProyectoW.common.items.WarHammerArmorItem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoArmorRenderer;
import software.bernie.geckolib.renderer.GeoRenderer;

public class EyeLayer<T extends WarHammerArmorItem> extends ItemGeoRenderLayer<T> {
    public EyeLayer(GeoRenderer<T> entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    public void render(PoseStack poseStack, T animatable, BakedGeoModel model, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        if (this.getRenderer() instanceof GeoArmorRenderer<?> armorRenderer) {
            ItemStack stack = armorRenderer.getCurrentStack();
            Item item = stack.getItem();
            if (item instanceof ArmorItem armorItem && armorItem.getType().getSlot().equals(EquipmentSlot.HEAD)) {
                ResourceLocation texture = this.getTexture(stack);
                RenderType eyeRenderType = RenderType.eyes(texture);
                VertexConsumer consumer = bufferSource.getBuffer(eyeRenderType);
                int visorColor = item instanceof WarHammerArmorItem armor ? armor.getVisorColor(stack) : WarHammerArmorItem.DEFAULT_VISOR_COLOR;
                this.getRenderer().reRender(model, poseStack, bufferSource, animatable, eyeRenderType, consumer, partialTick, LightTexture.FULL_BRIGHT, OverlayTexture.NO_OVERLAY, visorColor);
            }
        }
    }

    public ResourceLocation getTexture(ItemStack stack) {
        String material = stack.getItem() instanceof WarHammerArmorItem armor ? armor.getVisorMaterial(stack) : "redstone";
        String folder = stack.getItem() instanceof MarineShadesItem shades ? (shades.isLow() ? "shades_details_low" : "shades_details") : "eye_details";
        return ResourceLocation.fromNamespaceAndPath(SpaceMarines.MODID, "textures/armor/" + folder + "/" + material + ".png");
    }
}
