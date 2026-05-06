package com.TBK.ProyectoW.common.registry;

import com.TBK.ProyectoW.SpaceMarines;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class PWItemTags {
    public static final TagKey<Item> FACTION_ARMOR = bind("faction_armor");

    public PWItemTags() {
    }

    private static TagKey<Item> bind(String p_203855_) {
        return TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(SpaceMarines.MODID, p_203855_));
    }
}
