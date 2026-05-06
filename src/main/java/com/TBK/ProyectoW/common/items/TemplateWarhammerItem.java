package com.TBK.ProyectoW.common.items;

import java.util.List;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

public class TemplateWarhammerItem extends Item {
    private final Factions faction;

    public TemplateWarhammerItem(Item.Properties p_41383_, Factions faction) {
        super(p_41383_);
        this.faction = faction;
    }

    public Factions getFaction() {
        return this.faction;
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.translatable("factions.decrip"));
        tooltip.add(Component.translatable("factions." + this.faction.getName()).withStyle(ChatFormatting.BLUE));
    }
}
