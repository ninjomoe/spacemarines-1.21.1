package com.TBK.ProyectoW.common.registry;

import com.TBK.ProyectoW.SpaceMarines;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class PWCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> TABS;
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> PW_MOBS_TAB;

    public PWCreativeTabs() {
    }

    static {
        TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, SpaceMarines.MODID);
        PW_MOBS_TAB = TABS.register("space_marines", () -> CreativeModeTab.builder()
                .icon(() -> new ItemStack(PWItems.WARHAMMER_HELMET.get()))
                .title(Component.translatable("itemGroup.warhammer_tab"))
                .displayItems((parameters, output) -> {
                    output.accept(PWItems.WARHAMMER_HELMET.get());
                    output.accept(PWItems.WARHAMMER_CHEST.get());
                    output.accept(PWItems.WARHAMMER_LEGGINGS.get());
                    output.accept(PWItems.WARHAMMER_BOOT.get());
                    output.accept(PWItems.MARINE_ARMOR_SMITHING_TEMPLATE.get());
                    output.accept(PWItems.HOLY_CRUSADER_TRIM_TEMPLATE.get());
                    output.accept(PWItems.ROYAL_ZEALOT_TRIM_TEMPLATE.get());
                    output.accept(PWItems.SILVER_SKULL_TRIM_TEMPLATE.get());
                    output.accept(PWItems.PROTO_SUIT_TRIM_TEMPLATE.get());
                    output.accept(PWItems.INFERNAL_CHAOS_TRIM_TEMPLATE.get());
                    output.accept(PWItems.CERAMITE_INGOT.get());
                })
                .build());
    }
}
