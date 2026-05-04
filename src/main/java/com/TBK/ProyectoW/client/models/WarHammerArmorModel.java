//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.TBK.ProyectoW.client.models;

import com.TBK.ProyectoW.common.items.WarHammerArmorItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class WarHammerArmorModel<T extends WarHammerArmorItem> extends GeoModel<T> {
    public WarHammerArmorModel() {
    }

    public ResourceLocation getModelResource(T object) {
        return new ResourceLocation("space_marines", "geo/warhammer_armor.geo.json");
    }

    public ResourceLocation getTextureResource(T object) {
        return new ResourceLocation("space_marines", "textures/armor/warhammer_armor.png");
    }

    public ResourceLocation getAnimationResource(T animatable) {
        return new ResourceLocation("space_marines", "animations/warhammer_armor.animation.json");
    }
}
