//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.TBK.ProyectoW.common.registry;

import java.util.EnumMap;
import java.util.function.Supplier;
import net.minecraft.Util;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ArmorItem.Type;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

public enum PWArmorMaterials implements ArmorMaterial {
    CERAMITE("ceramite", 27, (EnumMap)Util.m_137469_(new EnumMap(ArmorItem.Type.class), (p_266655_) -> {
        p_266655_.put(Type.BOOTS, 3);
        p_266655_.put(Type.LEGGINGS, 6);
        p_266655_.put(Type.CHESTPLATE, 8);
        p_266655_.put(Type.HELMET, 3);
    }), 15, SoundEvents.f_11679_, 4.0F, 0.2F, () -> Ingredient.m_43929_(new ItemLike[]{(ItemLike)PWItems.CERAMITE_INGOT.get()}));

    private static final EnumMap<ArmorItem.Type, Integer> HEALTH_FUNCTION_FOR_TYPE = (EnumMap)Util.m_137469_(new EnumMap(ArmorItem.Type.class), (p_266653_) -> {
        p_266653_.put(Type.BOOTS, 13);
        p_266653_.put(Type.LEGGINGS, 15);
        p_266653_.put(Type.CHESTPLATE, 16);
        p_266653_.put(Type.HELMET, 11);
    });
    private final String name;
    private final int durabilityMultiplier;
    private final EnumMap<ArmorItem.Type, Integer> protectionFunctionForType;
    private final int enchantmentValue;
    private final SoundEvent sound;
    private final float toughness;
    private final float knockbackResistance;
    private final LazyLoadedValue<Ingredient> repairIngredient;

    private PWArmorMaterials(String p_268171_, int p_268303_, EnumMap<ArmorItem.Type, Integer> p_267941_, int p_268086_, SoundEvent p_268145_, float p_268058_, float p_268180_, Supplier<Ingredient> p_268256_) {
        this.name = p_268171_;
        this.durabilityMultiplier = p_268303_;
        this.protectionFunctionForType = p_267941_;
        this.enchantmentValue = p_268086_;
        this.sound = p_268145_;
        this.toughness = p_268058_;
        this.knockbackResistance = p_268180_;
        this.repairIngredient = new LazyLoadedValue(p_268256_);
    }

    public int m_266425_(ArmorItem.Type p_266807_) {
        return (Integer)HEALTH_FUNCTION_FOR_TYPE.get(p_266807_) * this.durabilityMultiplier;
    }

    public int m_7366_(ArmorItem.Type p_267168_) {
        return (Integer)this.protectionFunctionForType.get(p_267168_);
    }

    public int m_6646_() {
        return this.enchantmentValue;
    }

    public SoundEvent m_7344_() {
        return this.sound;
    }

    public Ingredient m_6230_() {
        return (Ingredient)this.repairIngredient.m_13971_();
    }

    public String m_6082_() {
        return this.name;
    }

    public float m_6651_() {
        return this.toughness;
    }

    public float m_6649_() {
        return this.knockbackResistance;
    }
}
