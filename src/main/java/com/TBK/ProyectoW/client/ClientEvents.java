//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.TBK.ProyectoW.client;

import com.TBK.ProyectoW.common.registry.PWItems;
import net.minecraft.world.item.DyeableLeatherItem;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(
        modid = "space_marines",
        bus = Bus.MOD,
        value = {Dist.CLIENT}
)
public class ClientEvents {
    public ClientEvents() {
    }

    @SubscribeEvent
    public static void onClientSetup(RegisterColorHandlersEvent.Item event) {
        event.register((p_92708_, p_92709_) -> p_92709_ > 0 ? -1 : ((DyeableLeatherItem)p_92708_.m_41720_()).m_41121_(p_92708_), new ItemLike[]{(ItemLike)PWItems.WARHAMMER_HELMET.get(), (ItemLike)PWItems.WARHAMMER_CHEST.get(), (ItemLike)PWItems.WARHAMMER_LEGGINGS.get(), (ItemLike)PWItems.WARHAMMER_BOOT.get()});
    }
}
