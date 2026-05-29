package com.bossa.spacemarinearmor.server;

import com.bossa.spacemarinearmor.SpaceMarines;
import com.bossa.spacemarinearmor.common.boost.BoostPackHandler;
import com.bossa.spacemarinearmor.common.items.WarHammerArmorItem;
import com.bossa.spacemarinearmor.common.registry.PWItemProperties;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.monster.piglin.AbstractPiglin;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.event.entity.living.EnderManAngerEvent;
import net.neoforged.neoforge.event.entity.living.LivingChangeTargetEvent;
import net.neoforged.neoforge.event.entity.living.LivingFallEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

@EventBusSubscriber(modid = SpaceMarines.MODID)
public class Events {
    public Events() {
    }

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(PWItemProperties::register);
    }

    @SubscribeEvent
    public static void onEnderManAnger(EnderManAngerEvent event) {
        if (WarHammerArmorItem.hasMarineHelmet(event.getPlayer())) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void onLivingChangeTarget(LivingChangeTargetEvent event) {
        if (event.getNewAboutToBeSetTarget() instanceof Player player && WarHammerArmorItem.hasMarineHelmet(player)) {
            if (event.getEntity() instanceof EnderMan || event.getEntity() instanceof AbstractPiglin) {
                event.setCanceled(true);
            }
        }
    }

    @SubscribeEvent
    public static void onLivingIncomingDamage(LivingIncomingDamageEvent event) {
        if (WarHammerArmorItem.hasFullMarineSet(event.getEntity()) && event.getSource().is(DamageTypeTags.IS_FIRE)) {
            event.setCanceled(true);
            event.getEntity().clearFire();
        }
    }

    @SubscribeEvent
    public static void onLivingFall(LivingFallEvent event) {
        if (WarHammerArmorItem.hasFullMarineSet(event.getEntity())) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        Player player = event.getEntity();
        if (!player.level().isClientSide() && WarHammerArmorItem.hasFullMarineSet(player)) {
            player.getFoodData().setExhaustion(0.0F);
        }

        BoostPackHandler.tickPlayer(player);
    }
}
