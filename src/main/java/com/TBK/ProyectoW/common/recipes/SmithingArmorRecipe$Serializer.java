//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.TBK.ProyectoW.common.recipes;

import com.google.gson.JsonObject;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;

public class SmithingArmorRecipe$Serializer implements RecipeSerializer<SmithingArmorRecipe> {
    public SmithingArmorRecipe$Serializer() {
    }

    public SmithingArmorRecipe fromJson(ResourceLocation p_267037_, JsonObject p_267004_) {
        Ingredient ingredient = Ingredient.m_43917_(GsonHelper.m_289747_(p_267004_, "template"));
        Ingredient ingredient1 = Ingredient.m_43917_(GsonHelper.m_289747_(p_267004_, "base"));
        return new SmithingArmorRecipe(p_267037_, ingredient, ingredient1);
    }

    public SmithingArmorRecipe fromNetwork(ResourceLocation p_267169_, FriendlyByteBuf p_267251_) {
        Ingredient ingredient = Ingredient.m_43940_(p_267251_);
        Ingredient ingredient1 = Ingredient.m_43940_(p_267251_);
        return new SmithingArmorRecipe(p_267169_, ingredient, ingredient1);
    }

    public void toNetwork(FriendlyByteBuf p_266901_, SmithingArmorRecipe p_266893_) {
        p_266893_.template.m_43923_(p_266901_);
        p_266893_.base.m_43923_(p_266901_);
    }
}
