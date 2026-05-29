package com.bossa.spacemarinearmor.common.registry;

import com.bossa.spacemarinearmor.SpaceMarines;
import com.bossa.spacemarinearmor.common.boost.BoostPackHandler;
import com.bossa.spacemarinearmor.common.network.BoostPackTogglePayload;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

public class PWNetwork {
    public static void registerPayloads(RegisterPayloadHandlersEvent event) {
        PayloadRegistrar registrar = event.registrar(SpaceMarines.MODID).versioned("1");
        registrar.playToServer(BoostPackTogglePayload.TYPE, BoostPackTogglePayload.STREAM_CODEC, (payload, context) ->
                context.enqueueWork(() -> BoostPackHandler.setBoosting(context.player(), payload.active(), payload.strafe(), payload.forward(), payload.sprinting()))
        );
    }
}
