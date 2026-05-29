package com.bossa.spacemarinearmor.client.models;

import com.bossa.spacemarinearmor.SpaceMarines;
import com.bossa.spacemarinearmor.common.items.MarineShadesItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class MarineShadesModel<T extends MarineShadesItem> extends GeoModel<T> {
    @Override
    public ResourceLocation getModelResource(T animatable) {
        return ResourceLocation.fromNamespaceAndPath(SpaceMarines.MODID, "geo/warhammer_armor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(T animatable) {
        String texture = animatable.isLow() ? "shades_frame_low.png" : "shades_frame.png";
        return ResourceLocation.fromNamespaceAndPath(SpaceMarines.MODID, "textures/armor/" + texture);
    }

    @Override
    public ResourceLocation getAnimationResource(T animatable) {
        return ResourceLocation.fromNamespaceAndPath(SpaceMarines.MODID, "animations/warhammer_armor.animation.json");
    }
}
