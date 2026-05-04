//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.TBK.ProyectoW.common.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class PWCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> TABS;
    public static final RegistryObject<CreativeModeTab> PW_MOBS_TAB;

    public PWCreativeTabs() {
    }

    static {
        TABS = DeferredRegister.create(Registries.f_279569_, "space_marines");
        PW_MOBS_TAB = TABS.register("space_marines", () -> CreativeModeTab.builder().m_257737_(() -> new ItemStack((ItemLike)PWItems.WARHAMMER_CHEST.get())).m_257941_(Component.m_237115_("itemGroup.warhammer_tab")).m_257501_((s, a) -> {
            a.m_246326_((ItemLike)PWItems.WARHAMMER_HELMET.get());
            a.m_246326_((ItemLike)PWItems.WARHAMMER_CHEST.get());
            a.m_246326_((ItemLike)PWItems.WARHAMMER_LEGGINGS.get());
            a.m_246326_((ItemLike)PWItems.WARHAMMER_BOOT.get());
            a.m_246326_((ItemLike)PWItems.MARINE_ARMOR_SMITHING_TEMPLATE.get());
            a.m_246326_((ItemLike)PWItems.HOLY_CRUSADER_TRIM_TEMPLATE.get());
            a.m_246326_((ItemLike)PWItems.ROYAL_ZEALOT_TRIM_TEMPLATE.get());
            a.m_246326_((ItemLike)PWItems.SILVER_SKULL_TRIM_TEMPLATE.get());
            a.m_246326_((ItemLike)PWItems.PROTO_SUIT_TRIM_TEMPLATE.get());
            a.m_246326_((ItemLike)PWItems.INFERNAL_CHAOS_TRIM_TEMPLATE.get());
            a.m_246326_((ItemLike)PWItems.CERAMITE_INGOT.get());
        }).m_257652_());
    }
}
