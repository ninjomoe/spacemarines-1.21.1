package com.bossa.spacemarinearmor.client.models;

import com.bossa.spacemarinearmor.SpaceMarines;
import com.bossa.spacemarinearmor.common.items.WarHammerArmorItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class WarHammerArmorModel<T extends WarHammerArmorItem> extends GeoModel<T> {
    public WarHammerArmorModel() {
    }

    public ResourceLocation getModelResource(T object) {
        return ResourceLocation.fromNamespaceAndPath(SpaceMarines.MODID, "geo/warhammer_armor.geo.json");
    }

    public ResourceLocation getTextureResource(T object) {
        return ResourceLocation.fromNamespaceAndPath(SpaceMarines.MODID, "textures/armor/warhammer_armor.png");
    }

    public ResourceLocation getAnimationResource(T animatable) {
        return ResourceLocation.fromNamespaceAndPath(SpaceMarines.MODID, "animations/warhammer_armor.animation.json");
    }
}
