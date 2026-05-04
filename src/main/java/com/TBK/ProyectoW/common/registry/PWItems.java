//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.TBK.ProyectoW.common.registry;

import com.TBK.ProyectoW.common.items.Factions;
import com.TBK.ProyectoW.common.items.TemplateWarhammerItem;
import com.TBK.ProyectoW.common.items.WarHammerArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ArmorItem.Type;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class PWItems {
    public static final DeferredRegister<Item> ITEMS;
    public static final RegistryObject<Item> WARHAMMER_HELMET;
    public static final RegistryObject<Item> WARHAMMER_CHEST;
    public static final RegistryObject<Item> WARHAMMER_LEGGINGS;
    public static final RegistryObject<Item> WARHAMMER_BOOT;
    public static final RegistryObject<Item> CERAMITE_INGOT;
    public static final RegistryObject<Item> MARINE_ARMOR_SMITHING_TEMPLATE;
    public static final RegistryObject<Item> ROYAL_ZEALOT_TRIM_TEMPLATE;
    public static final RegistryObject<Item> PROTO_SUIT_TRIM_TEMPLATE;
    public static final RegistryObject<Item> INFERNAL_CHAOS_TRIM_TEMPLATE;
    public static final RegistryObject<Item> HOLY_CRUSADER_TRIM_TEMPLATE;
    public static final RegistryObject<Item> SILVER_SKULL_TRIM_TEMPLATE;

    public PWItems() {
    }

    public static Item.Properties props() {
        return new Item.Properties();
    }

    static {
        ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, "space_marines");
        WARHAMMER_HELMET = ITEMS.register("warhammer_helmet", () -> new WarHammerArmorItem(PWArmorMaterials.CERAMITE, Type.HELMET, props()));
        WARHAMMER_CHEST = ITEMS.register("warhammer_chest", () -> new WarHammerArmorItem(PWArmorMaterials.CERAMITE, Type.CHESTPLATE, props()));
        WARHAMMER_LEGGINGS = ITEMS.register("warhammer_leggings", () -> new WarHammerArmorItem(PWArmorMaterials.CERAMITE, Type.LEGGINGS, props()));
        WARHAMMER_BOOT = ITEMS.register("warhammer_boot", () -> new WarHammerArmorItem(PWArmorMaterials.CERAMITE, Type.BOOTS, props()));
        CERAMITE_INGOT = ITEMS.register("ceramite_ingot", () -> new Item(props().m_41486_()));
        MARINE_ARMOR_SMITHING_TEMPLATE = ITEMS.register("marine_armor_smithing_template", () -> new Item(new Item.Properties()));
        ROYAL_ZEALOT_TRIM_TEMPLATE = ITEMS.register("royal_zealot_trim_template", () -> new TemplateWarhammerItem(props(), Factions.ROYAL_ZEALOT));
        PROTO_SUIT_TRIM_TEMPLATE = ITEMS.register("proto_suit_trim_template", () -> new TemplateWarhammerItem(props(), Factions.PROTO_SUIT));
        INFERNAL_CHAOS_TRIM_TEMPLATE = ITEMS.register("infernal_chaos_trim_template", () -> new TemplateWarhammerItem(props(), Factions.INFERNAL_CHAOS));
        HOLY_CRUSADER_TRIM_TEMPLATE = ITEMS.register("holy_crusader_trim_template", () -> new TemplateWarhammerItem(props(), Factions.NONE));
        SILVER_SKULL_TRIM_TEMPLATE = ITEMS.register("silver_skull_trim_template", () -> new TemplateWarhammerItem(props(), Factions.SILVER_SKULL));
    }
}
