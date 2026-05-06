package com.TBK.ProyectoW.client.renderers;

import com.TBK.ProyectoW.client.layers.EyeLayer;
import com.TBK.ProyectoW.client.layers.GoldenDetailsLayer;
import com.TBK.ProyectoW.client.models.WarHammerArmorModel;
import com.TBK.ProyectoW.common.items.WarHammerArmorItem;
import net.minecraft.world.item.component.DyedItemColor;
import software.bernie.geckolib.renderer.GeoArmorRenderer;
import software.bernie.geckolib.util.Color;

public class WarHammerArmorRenderer<T extends WarHammerArmorItem> extends GeoArmorRenderer<T> {
    public WarHammerArmorRenderer() {
        super(new WarHammerArmorModel());
        this.addRenderLayer(new GoldenDetailsLayer(this));
        this.addRenderLayer(new EyeLayer(this));
    }

    @Override
    public Color getRenderColor(T animatable, float partialTick, int packedLight) {
        return Color.ofOpaque(DyedItemColor.getOrDefault(this.currentStack, 0xFFFFFF));
    }
}
