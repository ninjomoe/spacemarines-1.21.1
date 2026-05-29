package com.bossa.spacemarinearmor.client.renderers;

import com.bossa.spacemarinearmor.client.layers.EyeLayer;
import com.bossa.spacemarinearmor.client.models.MarineShadesModel;
import com.bossa.spacemarinearmor.common.items.MarineShadesItem;
import software.bernie.geckolib.renderer.GeoArmorRenderer;
import software.bernie.geckolib.util.Color;

public class MarineShadesRenderer<T extends MarineShadesItem> extends GeoArmorRenderer<T> {
    public MarineShadesRenderer() {
        super(new MarineShadesModel<>());
        this.addRenderLayer(new EyeLayer(this));
    }

    @Override
    public Color getRenderColor(T animatable, float partialTick, int packedLight) {
        return Color.WHITE;
    }
}
