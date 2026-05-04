//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.TBK.ProyectoW.common.recipes;

import com.TBK.ProyectoW.common.items.TemplateWarhammerItem;
import com.TBK.ProyectoW.common.items.WarHammerArmorItem;
import com.TBK.ProyectoW.common.registry.PWRecipeSerializer;
import com.google.gson.JsonObject;
import java.util.Optional;
import net.minecraft.core.Holder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.armortrim.ArmorTrim;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.item.armortrim.TrimMaterials;
import net.minecraft.world.item.armortrim.TrimPattern;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SmithingRecipe;
import net.minecraft.world.level.Level;

public class SmithingArmorRecipe implements SmithingRecipe {
    private final ResourceLocation id;
    final Ingredient template;
    final Ingredient base;

    public SmithingArmorRecipe(ResourceLocation p_267235_, Ingredient p_267298_, Ingredient p_266862_) {
        this.id = p_267235_;
        this.template = p_267298_;
        this.base = p_266862_;
    }

    public boolean m_266166_(ItemStack p_266982_) {
        return this.template.test(p_266982_);
    }

    public boolean m_266343_(ItemStack p_266962_) {
        return this.base.test(p_266962_);
    }

    public boolean m_266253_(ItemStack p_267132_) {
        return false;
    }

    public boolean m_5818_(Container p_44002_, Level p_44003_) {
        return this.template.test(p_44002_.m_8020_(0)) && this.base.test(p_44002_.m_8020_(1));
    }

    public ItemStack m_5874_(Container p_44001_, RegistryAccess p_267165_) {
        ItemStack itemstack = p_44001_.m_8020_(1);
        if (this.base.test(itemstack)) {
            ItemStack itemstack1 = p_44001_.m_8020_(0);
            ItemStack itemstack2 = itemstack.m_41777_();
            itemstack2.m_41764_(1);
            Item var8 = itemstack2.m_41720_();
            if (var8 instanceof WarHammerArmorItem) {
                WarHammerArmorItem armor = (WarHammerArmorItem)var8;
                var8 = itemstack1.m_41720_();
                if (var8 instanceof TemplateWarhammerItem) {
                    TemplateWarhammerItem template = (TemplateWarhammerItem)var8;
                    armor.setFaction(template.getFaction(), itemstack2);
                    return itemstack2;
                }
            }
        }

        return ItemStack.f_41583_;
    }

    public ItemStack m_8043_(RegistryAccess p_266948_) {
        ItemStack itemstack = new ItemStack(Items.f_42469_);
        Optional<Holder.Reference<TrimPattern>> optional = p_266948_.m_175515_(Registries.f_266063_).m_203611_().findFirst();
        if (optional.isPresent()) {
            Optional<Holder.Reference<TrimMaterial>> optional1 = p_266948_.m_175515_(Registries.f_266076_).m_203636_(TrimMaterials.f_265870_);
            if (optional1.isPresent()) {
                ArmorTrim armortrim = new ArmorTrim((Holder)optional1.get(), (Holder)optional.get());
                ArmorTrim.m_266570_(p_266948_, itemstack, armortrim);
            }
        }

        return itemstack;
    }

    public ResourceLocation m_6423_() {
        return this.id;
    }

    public RecipeSerializer<?> m_7707_() {
        return (RecipeSerializer)PWRecipeSerializer.SMITHING_WARHAMMER_RECIPE.get();
    }

    public static class Serializer implements RecipeSerializer<SmithingArmorRecipe> {
        public Serializer() {
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
}
