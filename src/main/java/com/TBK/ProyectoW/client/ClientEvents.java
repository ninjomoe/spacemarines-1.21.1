package com.TBK.ProyectoW.client;

import com.TBK.ProyectoW.SpaceMarines;
import com.TBK.ProyectoW.common.registry.PWItems;
import net.minecraft.world.item.component.DyedItemColor;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.EventBusSubscriber.Bus;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;

@EventBusSubscriber(
        modid = SpaceMarines.MODID,
        bus = Bus.MOD,
        value = {Dist.CLIENT}
)
public class ClientEvents {
    public ClientEvents() {
    }

    @SubscribeEvent
    public static void onClientSetup(RegisterColorHandlersEvent.Item event) {
        event.register(
                (stack, tintIndex) -> tintIndex > 0 ? -1 : DyedItemColor.getOrDefault(stack, -1),
                PWItems.WARHAMMER_HELMET.get(),
                PWItems.WARHAMMER_CHEST.get(),
                PWItems.WARHAMMER_LEGGINGS.get(),
                PWItems.WARHAMMER_BOOT.get()
        );
    }
}
