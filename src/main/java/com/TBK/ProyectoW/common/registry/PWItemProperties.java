//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.TBK.ProyectoW.common.registry;

import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public class PWtemProperties {
    public PWtemProperties() {
    }

    public static void register() {
        ItemProperties.register((Item)PWItems.WARHAMMER_HELMET.get(), new ResourceLocation("space_marines", "faction"), (p_239426_0_, p_239426_1_, p_239426_2_, intIn) -> 1.0F);
        ItemProperties.register((Item)PWItems.WARHAMMER_CHEST.get(), new ResourceLocation("space_marines", "faction"), (p_239426_0_, p_239426_1_, p_239426_2_, intIn) -> 1.0F);
        ItemProperties.register((Item)PWItems.WARHAMMER_LEGGINGS.get(), new ResourceLocation("space_marines", "faction"), (p_239426_0_, p_239426_1_, p_239426_2_, intIn) -> 1.0F);
        ItemProperties.register((Item)PWItems.WARHAMMER_BOOT.get(), new ResourceLocation("space_marines", "faction"), (p_239426_0_, p_239426_1_, p_239426_2_, intIn) -> 1.0F);
    }
}
