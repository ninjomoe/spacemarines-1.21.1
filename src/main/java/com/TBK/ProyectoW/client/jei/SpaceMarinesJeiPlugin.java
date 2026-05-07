package com.TBK.ProyectoW.client.jei;

import com.TBK.ProyectoW.SpaceMarines;
import com.TBK.ProyectoW.common.recipes.SmithingArmorRecipe;
import com.TBK.ProyectoW.common.registry.PWItems;
import java.util.List;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.recipe.category.extensions.vanilla.smithing.ISmithingCategoryExtension;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.registration.IVanillaCategoryExtensionRegistration;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.SmithingRecipe;

@JeiPlugin
public class SpaceMarinesJeiPlugin implements IModPlugin {
    private static final ResourceLocation UID = ResourceLocation.fromNamespaceAndPath(SpaceMarines.MODID, "jei_plugin");

    @Override
    public ResourceLocation getPluginUid() {
        return UID;
    }

    @Override
    public void registerVanillaCategoryExtensions(IVanillaCategoryExtensionRegistration registration) {
        registration.getSmithingCategory().addExtension(SmithingArmorRecipe.class, new ISmithingCategoryExtension<>() {
            @Override
            public <T extends mezz.jei.api.gui.builder.IIngredientAcceptor<T>> void setTemplate(SmithingArmorRecipe recipe, T ingredient) {
                ingredient.addIngredients(recipe.getTemplate());
            }

            @Override
            public <T extends mezz.jei.api.gui.builder.IIngredientAcceptor<T>> void setBase(SmithingArmorRecipe recipe, T ingredient) {
                ingredient.addIngredients(recipe.getBase());
            }

            @Override
            public <T extends mezz.jei.api.gui.builder.IIngredientAcceptor<T>> void setAddition(SmithingArmorRecipe recipe, T ingredient) {
                ingredient.addIngredients(recipe.getAddition());
            }

            @Override
            public <T extends mezz.jei.api.gui.builder.IIngredientAcceptor<T>> void setOutput(SmithingArmorRecipe recipe, T ingredient) {
                if (!recipe.getResult().isEmpty()) {
                    ingredient.addItemStack(recipe.getResult());
                }
            }
        });
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        List<RecipeHolder<SmithingRecipe>> recipes = List.of(
                armorRecipe("warhammer_helmet_smithing", Items.NETHERITE_HELMET.getDefaultInstance(), PWItems.WARHAMMER_HELMET.get().getDefaultInstance()),
                armorRecipe("warhammer_chestplate_smithing", Items.NETHERITE_CHESTPLATE.getDefaultInstance(), PWItems.WARHAMMER_CHEST.get().getDefaultInstance()),
                armorRecipe("warhammer_leggings_smithing", Items.NETHERITE_LEGGINGS.getDefaultInstance(), PWItems.WARHAMMER_LEGGINGS.get().getDefaultInstance()),
                armorRecipe("warhammer_boots_smithing", Items.NETHERITE_BOOTS.getDefaultInstance(), PWItems.WARHAMMER_BOOT.get().getDefaultInstance())
        );
        registration.addRecipes(RecipeTypes.SMITHING, recipes);
    }

    private static RecipeHolder<SmithingRecipe> armorRecipe(String name, ItemStack base, ItemStack result) {
        SmithingArmorRecipe recipe = new SmithingArmorRecipe(
                Ingredient.of(PWItems.MARINE_ARMOR_SMITHING_TEMPLATE.get()),
                Ingredient.of(base),
                Ingredient.of(PWItems.CERAMITE_INGOT.get()),
                result
        );
        return new RecipeHolder<>(ResourceLocation.fromNamespaceAndPath(SpaceMarines.MODID, name), recipe);
    }
}
