//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.TBK.ProyectoW.common.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class PWItemTags {
    public static final TagKey<Item> FACTION_ARMOR = bind("faction_armor");

    public PWItemTags() {
    }

    private static TagKey<Item> bind(String p_203855_) {
        return TagKey.m_203882_(Registries.f_256913_, new ResourceLocation(p_203855_));
    }
}
