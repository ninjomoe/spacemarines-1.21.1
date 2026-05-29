package com.bossa.spacemarinearmor.common.registry;

import com.bossa.spacemarinearmor.SpaceMarines;
import com.bossa.spacemarinearmor.common.items.Factions;
import com.bossa.spacemarinearmor.common.items.MarineShadesItem;
import com.bossa.spacemarinearmor.common.items.TemplateWarhammerItem;
import com.bossa.spacemarinearmor.common.items.WarHammerArmorItem;
import java.util.EnumMap;
import java.util.List;
import net.minecraft.Util;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ArmorItem.Type;
import net.minecraft.world.item.component.Unbreakable;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class PWItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(SpaceMarines.MODID);
    public static final DeferredRegister<ArmorMaterial> ARMOR_MATERIALS = DeferredRegister.create(BuiltInRegistries.ARMOR_MATERIAL, SpaceMarines.MODID);

    public static final DeferredItem<WarHammerArmorItem> WARHAMMER_HELMET;
    public static final DeferredItem<WarHammerArmorItem> WARHAMMER_CHEST;
    public static final DeferredItem<WarHammerArmorItem> WARHAMMER_LEGGINGS;
    public static final DeferredItem<WarHammerArmorItem> WARHAMMER_BOOT;
    public static final DeferredItem<MarineShadesItem> MARINE_SHADES;
    public static final DeferredItem<MarineShadesItem> MARINE_SHADES_LOW;
    public static final DeferredItem<Item> CERAMITE_INGOT;
    public static final DeferredItem<Item> MARINE_ARMOR_SMITHING_TEMPLATE;
    public static final DeferredItem<Item> MARINE_VISOR_UPGRADE;
    public static final DeferredItem<TemplateWarhammerItem> ROYAL_ZEALOT_TRIM_TEMPLATE;
    public static final DeferredItem<TemplateWarhammerItem> PROTO_SUIT_TRIM_TEMPLATE;
    public static final DeferredItem<TemplateWarhammerItem> INFERNAL_CHAOS_TRIM_TEMPLATE;
    public static final DeferredItem<TemplateWarhammerItem> HOLY_CRUSADER_TRIM_TEMPLATE;
    public static final DeferredItem<TemplateWarhammerItem> SILVER_SKULL_TRIM_TEMPLATE;
    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> CERAMITE;

    public PWItems() {
    }

    public static Item.Properties props() {
        return new Item.Properties();
    }

    public static Item.Properties armorProps(ArmorItem.Type type) {
        return props()
                .durability(type.getDurability(27))
                .component(DataComponents.UNBREAKABLE, new Unbreakable(false))
                .fireResistant();
    }

    static {
        CERAMITE_INGOT = ITEMS.register("ceramite_ingot", () -> new Item(props().fireResistant()));
        CERAMITE = ARMOR_MATERIALS.register("ceramite", () -> new ArmorMaterial(
                Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                    map.put(Type.BOOTS, 5);
                    map.put(Type.LEGGINGS, 9);
                    map.put(Type.CHESTPLATE, 12);
                    map.put(Type.HELMET, 5);
                    map.put(Type.BODY, 11);
                }),
                15,
                SoundEvents.ARMOR_EQUIP_NETHERITE,
                () -> Ingredient.of(CERAMITE_INGOT.get()),
                List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(SpaceMarines.MODID, "ceramite"))),
                4.5F,
                0.15F
        ));
        WARHAMMER_HELMET = ITEMS.register("warhammer_helmet", () -> new WarHammerArmorItem((Holder<ArmorMaterial>) CERAMITE, Type.HELMET, armorProps(Type.HELMET)));
        WARHAMMER_CHEST = ITEMS.register("warhammer_chest", () -> new WarHammerArmorItem((Holder<ArmorMaterial>) CERAMITE, Type.CHESTPLATE, armorProps(Type.CHESTPLATE)));
        WARHAMMER_LEGGINGS = ITEMS.register("warhammer_leggings", () -> new WarHammerArmorItem((Holder<ArmorMaterial>) CERAMITE, Type.LEGGINGS, armorProps(Type.LEGGINGS)));
        WARHAMMER_BOOT = ITEMS.register("warhammer_boots", () -> new WarHammerArmorItem((Holder<ArmorMaterial>) CERAMITE, Type.BOOTS, armorProps(Type.BOOTS)));
        MARINE_SHADES = ITEMS.register("marine_shades", () -> new MarineShadesItem((Holder<ArmorMaterial>) CERAMITE, Type.HELMET, armorProps(Type.HELMET), false));
        MARINE_SHADES_LOW = ITEMS.register("marine_shades_low", () -> new MarineShadesItem((Holder<ArmorMaterial>) CERAMITE, Type.HELMET, armorProps(Type.HELMET), true));
        MARINE_ARMOR_SMITHING_TEMPLATE = ITEMS.register("marine_armor_smithing_template", () -> new Item(new Item.Properties()));
        MARINE_VISOR_UPGRADE = ITEMS.register("marine_visor_upgrade", () -> new Item(new Item.Properties()));
        ROYAL_ZEALOT_TRIM_TEMPLATE = ITEMS.register("royal_zealot_trim_template", () -> new TemplateWarhammerItem(props(), Factions.ROYAL_ZEALOT));
        PROTO_SUIT_TRIM_TEMPLATE = ITEMS.register("proto_suit_trim_template", () -> new TemplateWarhammerItem(props(), Factions.PROTO_SUIT));
        INFERNAL_CHAOS_TRIM_TEMPLATE = ITEMS.register("infernal_chaos_trim_template", () -> new TemplateWarhammerItem(props(), Factions.INFERNAL_CHAOS));
        HOLY_CRUSADER_TRIM_TEMPLATE = ITEMS.register("holy_crusader_trim_template", () -> new TemplateWarhammerItem(props(), Factions.HOLY_CRUSADER));
        SILVER_SKULL_TRIM_TEMPLATE = ITEMS.register("silver_skull_trim_template", () -> new TemplateWarhammerItem(props(), Factions.SILVER_SKULL));
    }
}
