package com.bossa.spacemarinearmor.common.registry;

import com.bossa.spacemarinearmor.SpaceMarines;
import com.bossa.spacemarinearmor.common.recipes.MarineShadesConversionRecipe;
import com.bossa.spacemarinearmor.common.recipes.SmithingArmorRecipe;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class PWRecipeSerializer {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS;
    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<SmithingArmorRecipe>> SMITHING_WARHAMMER_RECIPE;
    public static final DeferredHolder<RecipeSerializer<?>, SimpleCraftingRecipeSerializer<MarineShadesConversionRecipe>> MARINE_SHADES_CONVERSION_RECIPE;
    static final DeferredRegister<RecipeType<?>> RECIPE_TYPES;

    public PWRecipeSerializer() {
    }

    static {
        RECIPE_SERIALIZERS = DeferredRegister.create(BuiltInRegistries.RECIPE_SERIALIZER, SpaceMarines.MODID);
        SMITHING_WARHAMMER_RECIPE = RECIPE_SERIALIZERS.register("smithing_warhammer", SmithingArmorRecipe.Serializer::new);
        MARINE_SHADES_CONVERSION_RECIPE = RECIPE_SERIALIZERS.register("marine_shades_conversion", () -> new SimpleCraftingRecipeSerializer<>(MarineShadesConversionRecipe::new));
        RECIPE_TYPES = DeferredRegister.create(BuiltInRegistries.RECIPE_TYPE, SpaceMarines.MODID);
    }
}
