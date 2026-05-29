package com.bossa.spacemarinearmor.common.recipes;

import com.bossa.spacemarinearmor.common.registry.PWItems;
import com.bossa.spacemarinearmor.common.registry.PWRecipeSerializer;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;

public class MarineShadesConversionRecipe extends CustomRecipe {
    public MarineShadesConversionRecipe(CraftingBookCategory category) {
        super(category);
    }

    @Override
    public boolean matches(CraftingInput input, Level level) {
        return this.getShadeStack(input) != ItemStack.EMPTY;
    }

    @Override
    public ItemStack assemble(CraftingInput input, HolderLookup.Provider registries) {
        ItemStack shadeStack = this.getShadeStack(input);
        if (shadeStack.isEmpty()) {
            return ItemStack.EMPTY;
        }

        if (shadeStack.is(PWItems.MARINE_SHADES.get())) {
            return shadeStack.transmuteCopy(PWItems.MARINE_SHADES_LOW.get(), 1);
        }

        return shadeStack.transmuteCopy(PWItems.MARINE_SHADES.get(), 1);
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= 1;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return PWRecipeSerializer.MARINE_SHADES_CONVERSION_RECIPE.get();
    }

    private ItemStack getShadeStack(CraftingInput input) {
        ItemStack shadeStack = ItemStack.EMPTY;
        for (int i = 0; i < input.size(); i++) {
            ItemStack stack = input.getItem(i);
            if (stack.isEmpty()) {
                continue;
            }

            if (!stack.is(PWItems.MARINE_SHADES.get()) && !stack.is(PWItems.MARINE_SHADES_LOW.get())) {
                return ItemStack.EMPTY;
            }

            if (!shadeStack.isEmpty()) {
                return ItemStack.EMPTY;
            }

            shadeStack = stack;
        }

        return shadeStack;
    }
}
