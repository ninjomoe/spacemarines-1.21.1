//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.TBK.ProyectoW.common.registry;

import com.TBK.ProyectoW.common.recipes.SmithingArmorRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class PWRecipeSerializer {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS;
    public static final RegistryObject<RecipeSerializer<SmithingArmorRecipe>> SMITHING_WARHAMMER_RECIPE;
    static final DeferredRegister<RecipeType<?>> RECIPE_TYPES;

    public PWRecipeSerializer() {
    }

    static {
        RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, "space_marines");
        SMITHING_WARHAMMER_RECIPE = RECIPE_SERIALIZERS.register("smithing_warhammer", SmithingArmorRecipe.Serializer::new);
        RECIPE_TYPES = DeferredRegister.create(ForgeRegistries.RECIPE_TYPES, "space_marines");
    }
}
