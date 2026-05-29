package com.bossa.spacemarinearmor.common.network;

import com.bossa.spacemarinearmor.SpaceMarines;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record BoostPackTogglePayload(boolean active, float strafe, float forward, boolean sprinting) implements CustomPacketPayload {
    public static final Type<BoostPackTogglePayload> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(SpaceMarines.MODID, "boost_pack_toggle"));
    public static final StreamCodec<RegistryFriendlyByteBuf, BoostPackTogglePayload> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.BOOL,
            BoostPackTogglePayload::active,
            ByteBufCodecs.FLOAT,
            BoostPackTogglePayload::strafe,
            ByteBufCodecs.FLOAT,
            BoostPackTogglePayload::forward,
            ByteBufCodecs.BOOL,
            BoostPackTogglePayload::sprinting,
            BoostPackTogglePayload::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
