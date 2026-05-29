package com.bossa.spacemarinearmor.common.registry;

import com.bossa.spacemarinearmor.SpaceMarines;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;

public class PWItemProperties {
    public PWItemProperties() {
    }

    public static void register() {
        ResourceLocation faction = ResourceLocation.fromNamespaceAndPath(SpaceMarines.MODID, "faction");
        ItemProperties.register(PWItems.WARHAMMER_HELMET.get(), faction, (stack, level, entity, seed) -> 1.0F);
        ItemProperties.register(PWItems.WARHAMMER_CHEST.get(), faction, (stack, level, entity, seed) -> 1.0F);
        ItemProperties.register(PWItems.WARHAMMER_LEGGINGS.get(), faction, (stack, level, entity, seed) -> 1.0F);
        ItemProperties.register(PWItems.WARHAMMER_BOOT.get(), faction, (stack, level, entity, seed) -> 1.0F);
    }
}
