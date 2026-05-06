package com.TBK.ProyectoW.common.registry;

import com.TBK.ProyectoW.SpaceMarines;
import com.TBK.ProyectoW.common.recipes.SmithingArmorRecipe;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class PWRecipeSerializer {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS;
    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<SmithingArmorRecipe>> SMITHING_WARHAMMER_RECIPE;
    static final DeferredRegister<RecipeType<?>> RECIPE_TYPES;

    public PWRecipeSerializer() {
    }

    static {
        RECIPE_SERIALIZERS = DeferredRegister.create(BuiltInRegistries.RECIPE_SERIALIZER, SpaceMarines.MODID);
        SMITHING_WARHAMMER_RECIPE = RECIPE_SERIALIZERS.register("smithing_warhammer", SmithingArmorRecipe.Serializer::new);
        RECIPE_TYPES = DeferredRegister.create(BuiltInRegistries.RECIPE_TYPE, SpaceMarines.MODID);
    }
}
