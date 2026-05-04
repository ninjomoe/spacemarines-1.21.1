//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.TBK.ProyectoW.common.items;

import java.util.List;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class TemplateWarhammerItem extends Item {
    private final Factions faction;

    public TemplateWarhammerItem(Item.Properties p_41383_, Factions faction) {
        super(p_41383_);
        this.faction = faction;
    }

    public Factions getFaction() {
        return this.faction;
    }

    public void m_7373_(ItemStack p_41421_, @Nullable Level p_41422_, List<Component> p_41423_, TooltipFlag p_41424_) {
        p_41423_.add(Component.m_237115_("factions.decrip"));
        p_41423_.add(Component.m_237115_("factions." + this.faction.name()).m_130940_(ChatFormatting.BLUE));
    }
}
