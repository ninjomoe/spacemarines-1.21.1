package com.TBK.ProyectoW.common.items;

import com.TBK.ProyectoW.client.renderers.WarHammerArmorRenderer;
import java.util.List;
import java.util.function.Consumer;
import net.minecraft.ChatFormatting;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.renderer.GeoArmorRenderer;
import software.bernie.geckolib.util.GeckoLibUtil;

public class WarHammerArmorItem extends ArmorItem implements GeoItem {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private Factions faction;

    public WarHammerArmorItem(Holder<ArmorMaterial> materialIn, ArmorItem.Type slot, Item.Properties builder) {
        super(materialIn, slot, builder);
        this.faction = Factions.NONE;
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            private GeoArmorRenderer<?> renderer;

            public @NotNull HumanoidModel<?> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack itemStack, EquipmentSlot equipmentSlot, HumanoidModel<?> original) {
                if (this.renderer == null) {
                    this.renderer = new WarHammerArmorRenderer();
                }

                this.renderer.prepForRender(livingEntity, itemStack, equipmentSlot, original);
                return this.renderer;
            }
        });
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        if (!level.isClientSide()) {
            this.setFaction(this.getFaction(stack));
        }

        super.inventoryTick(stack, level, entity, slotId, isSelected);
    }

    public Factions getFaction(ItemStack stack) {
        Factions faction = Factions.NONE;
        CustomData customData = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY);
        if (customData.contains("faction")) {
            String name = customData.copyTag().getString("faction");
            faction = Factions.getForName(name);
            this.faction = faction;
        }

        return faction;
    }

    public Factions getFaction() {
        return this.faction;
    }

    public void setFaction(Factions faction) {
        this.faction = faction;
    }

    public void setFaction(Factions faction, ItemStack stack) {
        this.faction = faction;
        this.saveFaction(stack, this.faction.name());
    }

    public void saveFaction(ItemStack stack, String name) {
        CustomData.update(DataComponents.CUSTOM_DATA, stack, tag -> tag.putString("faction", name));
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, context, tooltip, flag);
        tooltip.add(Component.translatable("factions.decrip"));
        Factions faction = this.getFaction(stack);
        tooltip.add(Component.translatable("factions." + faction.getName()).withStyle(ChatFormatting.BLUE));
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }
}
