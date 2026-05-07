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
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ArmorItem.Type;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.renderer.GeoArmorRenderer;
import software.bernie.geckolib.util.GeckoLibUtil;

public class WarHammerArmorItem extends ArmorItem implements GeoItem {
    public static final int DEFAULT_VISOR_COLOR = 0xFFFF3030;
    private static final int EFFECT_DURATION = 400;
    private static final double MAX_LAVA_HORIZONTAL_SPEED = 0.18D;
    private static final double LAVA_HORIZONTAL_BOOST = 1.16D;
    private static final String VISOR_COLOR_TAG = "visor_color";
    private static final String VISOR_MATERIAL_TAG = "visor_material";
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
            if (entity instanceof LivingEntity livingEntity) {
                this.applyArmorEffects(livingEntity);
            }
        }

        super.inventoryTick(stack, level, entity, slotId, isSelected);
    }

    private void applyArmorEffects(LivingEntity livingEntity) {
        if (this.hasMarineHelmet(livingEntity)) {
            livingEntity.addEffect(new MobEffectInstance(MobEffects.CONDUIT_POWER, EFFECT_DURATION, 0, true, false, true));
        }

        if (this.isMarineArmor(livingEntity.getItemBySlot(EquipmentSlot.LEGS))) {
            livingEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, EFFECT_DURATION, 1, true, false, true));
        }

        if (this.isMarineArmor(livingEntity.getItemBySlot(EquipmentSlot.CHEST))) {
            livingEntity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, EFFECT_DURATION, 1, true, false, true));
            livingEntity.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, EFFECT_DURATION, 1, true, false, true));
            livingEntity.addEffect(new MobEffectInstance(MobEffects.REGENERATION, EFFECT_DURATION, 0, true, false, true));
        }

        if (this.hasFullMarineSet(livingEntity)) {
            livingEntity.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, EFFECT_DURATION, 0, true, false, true));
            livingEntity.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, EFFECT_DURATION, 0, true, false, true));
            livingEntity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, EFFECT_DURATION, 1, true, false, true));
            livingEntity.clearFire();
            this.boostLavaMovement(livingEntity);
        }
    }

    private void boostLavaMovement(LivingEntity livingEntity) {
        if (!livingEntity.isInLava()) {
            return;
        }

        Vec3 movement = livingEntity.getDeltaMovement();
        double horizontalSpeed = Math.sqrt(movement.x * movement.x + movement.z * movement.z);
        if (horizontalSpeed <= 0.0D || horizontalSpeed >= MAX_LAVA_HORIZONTAL_SPEED) {
            return;
        }

        double boostedSpeed = Math.min(horizontalSpeed * LAVA_HORIZONTAL_BOOST, MAX_LAVA_HORIZONTAL_SPEED);
        double scale = boostedSpeed / horizontalSpeed;
        livingEntity.setDeltaMovement(movement.x * scale, movement.y, movement.z * scale);
    }

    public static boolean hasMarineHelmet(LivingEntity livingEntity) {
        return isMarineArmor(livingEntity.getItemBySlot(EquipmentSlot.HEAD));
    }

    public static boolean hasMarineChestplate(LivingEntity livingEntity) {
        return isMarineArmor(livingEntity.getItemBySlot(EquipmentSlot.CHEST));
    }

    public static boolean hasFullMarineSet(LivingEntity livingEntity) {
        return isMarineArmor(livingEntity.getItemBySlot(EquipmentSlot.HEAD))
                && isMarineArmor(livingEntity.getItemBySlot(EquipmentSlot.CHEST))
                && isMarineArmor(livingEntity.getItemBySlot(EquipmentSlot.LEGS))
                && isMarineArmor(livingEntity.getItemBySlot(EquipmentSlot.FEET));
    }

    public static boolean isMarineArmor(ItemStack stack) {
        return stack.getItem() instanceof WarHammerArmorItem && !(stack.getItem() instanceof MarineShadesItem);
    }

    @Override
    public boolean makesPiglinsNeutral(ItemStack stack, LivingEntity wearer) {
        return this.getType() == Type.HELMET;
    }

    @Override
    public boolean isEnderMask(ItemStack stack, Player player, EnderMan enderMan) {
        return this.getType() == Type.HELMET;
    }

    @Override
    public boolean canWalkOnPowderedSnow(ItemStack stack, LivingEntity wearer) {
        return this.getType() == Type.BOOTS;
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

    public int getVisorColor(ItemStack stack) {
        CustomData customData = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY);
        if (customData.contains(VISOR_COLOR_TAG)) {
            return customData.copyTag().getInt(VISOR_COLOR_TAG);
        }

        return DEFAULT_VISOR_COLOR;
    }

    public void saveVisorColor(ItemStack stack, int color) {
        CustomData.update(DataComponents.CUSTOM_DATA, stack, tag -> {
            tag.putInt(VISOR_COLOR_TAG, color);
            tag.putString(VISOR_MATERIAL_TAG, getVisorMaterialForColor(color));
        });
    }

    public String getVisorMaterial(ItemStack stack) {
        CustomData customData = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY);
        if (customData.contains(VISOR_MATERIAL_TAG)) {
            return customData.copyTag().getString(VISOR_MATERIAL_TAG);
        }

        if (customData.contains(VISOR_COLOR_TAG)) {
            return getVisorMaterialForColor(customData.copyTag().getInt(VISOR_COLOR_TAG));
        }

        return "redstone";
    }

    public void saveVisor(ItemStack stack, ItemStack material) {
        CustomData.update(DataComponents.CUSTOM_DATA, stack, tag -> {
            tag.putInt(VISOR_COLOR_TAG, getVisorColorForMaterial(material));
            tag.putString(VISOR_MATERIAL_TAG, getVisorMaterialForMaterial(material));
        });
    }

    public static String getVisorMaterialForMaterial(ItemStack material) {
        if (material.is(Items.IRON_INGOT)) {
            return "iron";
        } else if (material.is(Items.COPPER_INGOT)) {
            return "copper";
        } else if (material.is(Items.GOLD_INGOT)) {
            return "gold";
        } else if (material.is(Items.LAPIS_LAZULI)) {
            return "lapis";
        } else if (material.is(Items.EMERALD)) {
            return "emerald";
        } else if (material.is(Items.DIAMOND)) {
            return "diamond";
        } else if (material.is(Items.NETHERITE_INGOT)) {
            return "netherite";
        } else if (material.is(Items.REDSTONE)) {
            return "redstone";
        } else if (material.is(Items.AMETHYST_SHARD)) {
            return "amethyst";
        } else if (material.is(Items.QUARTZ)) {
            return "quartz";
        }

        return "redstone";
    }

    public static int getVisorColorForMaterial(ItemStack material) {
        if (material.is(Items.IRON_INGOT)) {
            return 0xFFE6E6E6;
        } else if (material.is(Items.COPPER_INGOT)) {
            return 0xFFFF8A3D;
        } else if (material.is(Items.GOLD_INGOT)) {
            return 0xFFFFD83D;
        } else if (material.is(Items.LAPIS_LAZULI)) {
            return 0xFF305CFF;
        } else if (material.is(Items.EMERALD)) {
            return 0xFF24E36A;
        } else if (material.is(Items.DIAMOND)) {
            return 0xFF55FFFF;
        } else if (material.is(Items.NETHERITE_INGOT)) {
            return 0xFF4A3F52;
        } else if (material.is(Items.REDSTONE)) {
            return DEFAULT_VISOR_COLOR;
        } else if (material.is(Items.AMETHYST_SHARD)) {
            return 0xFFC06CFF;
        } else if (material.is(Items.QUARTZ)) {
            return 0xFFFFFFFF;
        }

        return DEFAULT_VISOR_COLOR;
    }

    private static String getVisorMaterialForColor(int color) {
        return switch (color & 0x00FFFFFF) {
            case 0xE6E6E6 -> "iron";
            case 0xFF8A3D -> "copper";
            case 0xFFD83D -> "gold";
            case 0x305CFF -> "lapis";
            case 0x24E36A -> "emerald";
            case 0x55FFFF -> "diamond";
            case 0x4A3F52 -> "netherite";
            case 0xC06CFF -> "amethyst";
            case 0xFFFFFF -> "quartz";
            default -> "redstone";
        };
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
