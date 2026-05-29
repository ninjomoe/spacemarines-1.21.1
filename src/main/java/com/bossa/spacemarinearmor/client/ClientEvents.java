package com.bossa.spacemarinearmor.client;

import com.bossa.spacemarinearmor.SpaceMarines;
import com.bossa.spacemarinearmor.common.items.MarineShadesItem;
import com.bossa.spacemarinearmor.common.items.WarHammerArmorItem;
import com.bossa.spacemarinearmor.common.registry.PWItems;
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
                (stack, tintIndex) -> {
                    if (tintIndex == 0) {
                        return DyedItemColor.getOrDefault(stack, -1);
                    }

                    if (tintIndex == 2 && stack.getItem() instanceof WarHammerArmorItem armor) {
                        return armor.getVisorColor(stack);
                    }

                    return -1;
                },
                PWItems.WARHAMMER_HELMET.get(),
                PWItems.WARHAMMER_CHEST.get(),
                PWItems.WARHAMMER_LEGGINGS.get(),
                PWItems.WARHAMMER_BOOT.get()
        );
        event.register(
                (stack, tintIndex) -> tintIndex == 1 && stack.getItem() instanceof MarineShadesItem shades ? shades.getVisorColor(stack) : -1,
                PWItems.MARINE_SHADES.get(),
                PWItems.MARINE_SHADES_LOW.get()
        );
    }
}
