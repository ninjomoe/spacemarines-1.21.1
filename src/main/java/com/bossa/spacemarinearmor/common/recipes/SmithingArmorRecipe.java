package com.bossa.spacemarinearmor.common.recipes;

import com.bossa.spacemarinearmor.common.items.TemplateWarhammerItem;
import com.bossa.spacemarinearmor.common.items.WarHammerArmorItem;
import com.bossa.spacemarinearmor.common.registry.PWItems;
import com.bossa.spacemarinearmor.common.registry.PWRecipeSerializer;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.stream.Stream;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SmithingRecipe;
import net.minecraft.world.item.crafting.SmithingRecipeInput;
import net.minecraft.world.level.Level;

public class SmithingArmorRecipe implements SmithingRecipe {
    final Ingredient template;
    final Ingredient base;
    final Ingredient addition;
    final ItemStack result;

    public SmithingArmorRecipe(Ingredient template, Ingredient base, Ingredient addition, ItemStack result) {
        this.template = template;
        this.base = base;
        this.addition = addition;
        this.result = result;
    }

    public Ingredient getTemplate() {
        return this.template;
    }

    public Ingredient getBase() {
        return this.base;
    }

    public Ingredient getAddition() {
        return this.addition;
    }

    public ItemStack getResult() {
        return this.result;
    }

    @Override
    public boolean isTemplateIngredient(ItemStack p_266982_) {
        return this.template.test(p_266982_);
    }

    @Override
    public boolean isBaseIngredient(ItemStack p_266962_) {
        return this.base.test(p_266962_);
    }

    @Override
    public boolean isAdditionIngredient(ItemStack p_267132_) {
        return this.addition.test(p_267132_);
    }

    @Override
    public boolean matches(SmithingRecipeInput input, Level level) {
        return this.template.test(input.template()) && this.base.test(input.base()) && this.addition.test(input.addition());
    }

    @Override
    public ItemStack assemble(SmithingRecipeInput input, HolderLookup.Provider registries) {
        ItemStack itemstack = input.base();
        if (this.base.test(itemstack) && this.addition.test(input.addition())) {
            ItemStack templateStack = input.template();
            ItemStack result = itemstack.copy();
            result.setCount(1);
            Item item = result.getItem();
            if (item instanceof WarHammerArmorItem armor) {
                Item templateItem = templateStack.getItem();
                if (templateItem instanceof TemplateWarhammerItem template) {
                    armor.setFaction(template.getFaction(), result);
                    return result;
                }

                if (templateItem == PWItems.MARINE_VISOR_UPGRADE.get()) {
                    armor.saveVisor(result, input.addition());
                    return result;
                }
            }

            if (!this.result.isEmpty()) {
                return this.result.copy();
            }
        }

        return ItemStack.EMPTY;
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider registries) {
        return this.result;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return PWRecipeSerializer.SMITHING_WARHAMMER_RECIPE.get();
    }

    @Override
    public boolean isIncomplete() {
        return Stream.of(this.template, this.base, this.addition).anyMatch(Ingredient::hasNoItems);
    }

    public static class Serializer implements RecipeSerializer<SmithingArmorRecipe> {
        private static final MapCodec<SmithingArmorRecipe> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
                Ingredient.CODEC.fieldOf("template").forGetter(recipe -> recipe.template),
                Ingredient.CODEC.fieldOf("base").forGetter(recipe -> recipe.base),
                Ingredient.CODEC.fieldOf("addition").forGetter(recipe -> recipe.addition),
                ItemStack.STRICT_CODEC.optionalFieldOf("result", ItemStack.EMPTY).forGetter(recipe -> recipe.result)
        ).apply(instance, SmithingArmorRecipe::new));
        private static final StreamCodec<RegistryFriendlyByteBuf, SmithingArmorRecipe> STREAM_CODEC = StreamCodec.of(
                Serializer::toNetwork,
                Serializer::fromNetwork
        );

        @Override
        public MapCodec<SmithingArmorRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, SmithingArmorRecipe> streamCodec() {
            return STREAM_CODEC;
        }

        private static SmithingArmorRecipe fromNetwork(RegistryFriendlyByteBuf buffer) {
            Ingredient ingredient = Ingredient.CONTENTS_STREAM_CODEC.decode(buffer);
            Ingredient ingredient1 = Ingredient.CONTENTS_STREAM_CODEC.decode(buffer);
            Ingredient ingredient2 = Ingredient.CONTENTS_STREAM_CODEC.decode(buffer);
            ItemStack result = ItemStack.OPTIONAL_STREAM_CODEC.decode(buffer);
            return new SmithingArmorRecipe(ingredient, ingredient1, ingredient2, result);
        }

        private static void toNetwork(RegistryFriendlyByteBuf buffer, SmithingArmorRecipe recipe) {
            Ingredient.CONTENTS_STREAM_CODEC.encode(buffer, recipe.template);
            Ingredient.CONTENTS_STREAM_CODEC.encode(buffer, recipe.base);
            Ingredient.CONTENTS_STREAM_CODEC.encode(buffer, recipe.addition);
            ItemStack.OPTIONAL_STREAM_CODEC.encode(buffer, recipe.result);
        }
    }
}
