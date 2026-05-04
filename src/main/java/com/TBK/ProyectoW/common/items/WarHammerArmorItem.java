//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.TBK.ProyectoW.common.items;

import com.TBK.ProyectoW.client.renderers.WarHammerArmorRenderer;
import java.util.List;
import java.util.function.Consumer;
import net.minecraft.ChatFormatting;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.DyeableLeatherItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.renderer.GeoArmorRenderer;
import software.bernie.geckolib.util.GeckoLibUtil;

public class WarHammerArmorItem extends ArmorItem implements GeoItem, DyeableLeatherItem {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private Factions faction;

    public WarHammerArmorItem(ArmorMaterial materialIn, ArmorItem.Type slot, Item.Properties builder) {
        super(materialIn, slot, builder);
        this.faction = Factions.NONE;
    }

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

    public void m_6883_(ItemStack p_41404_, Level p_41405_, Entity p_41406_, int p_41407_, boolean p_41408_) {
        if (!p_41405_.f_46443_) {
            this.setFaction(this.getFaction(p_41404_));
        }

        super.m_6883_(p_41404_, p_41405_, p_41406_, p_41407_, p_41408_);
    }

    public Factions getFaction(ItemStack stack) {
        Factions faction = Factions.NONE;
        CompoundTag nbt = stack.m_41784_();
        if (nbt.m_128441_("faction")) {
            String name = nbt.m_128461_("faction");
            faction = Factions.getForName(name.toUpperCase());
            this.faction = faction;
        }

        return faction;
    }

    public int m_41121_(ItemStack p_41122_) {
        CompoundTag compoundtag = p_41122_.m_41737_("display");
        return compoundtag != null && compoundtag.m_128425_("color", 99) ? compoundtag.m_128451_("color") : 16777215;
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

    public CompoundTag saveFaction(ItemStack stack, String name) {
        CompoundTag nbt = stack.m_41784_();
        nbt.m_128359_("faction", name);
        return nbt;
    }

    public void m_7373_(ItemStack p_41421_, @Nullable Level p_41422_, List<Component> p_41423_, TooltipFlag p_41424_) {
        super.m_7373_(p_41421_, p_41422_, p_41423_, p_41424_);
        p_41423_.add(Component.m_237115_("factions.decrip"));
        Factions var10001 = this.getFaction(p_41421_);
        p_41423_.add(Component.m_237115_("factions." + var10001.getName()).m_130940_(ChatFormatting.BLUE));
    }

    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
    }

    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }
}
