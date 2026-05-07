package com.TBK.ProyectoW.common.boost;

import com.TBK.ProyectoW.common.items.WarHammerArmorItem;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

public class BoostPackHandler {
    public static final int MAX_BOOST_TICKS = 10;
    public static final double VERTICAL_ACCELERATION = 0.105D;
    public static final double WALK_AIR_ACCELERATION = 0.035D;
    public static final double SPRINT_AIR_ACCELERATION = 0.06D;
    public static final double SPRINT_START_HORIZONTAL_IMPULSE = 0.34D;
    public static final double SPRINT_START_VERTICAL_IMPULSE = 0.24D;
    private static final double MIN_VERTICAL_SPEED = 0.18D;
    private static final double MAX_VERTICAL_SPEED = 0.72D;
    private static final double WALK_MAX_HORIZONTAL_SPEED = 0.42D;
    private static final double SPRINT_MAX_HORIZONTAL_SPEED = 0.70D;
    private static final Map<UUID, BoostState> BOOST_STATES = new HashMap<>();

    public static void setBoosting(Player player, boolean active, float strafe, float forward, boolean sprinting) {
        if (player.level().isClientSide()) {
            return;
        }

        BoostState state = BOOST_STATES.computeIfAbsent(player.getUUID(), uuid -> new BoostState());
        boolean wasActive = state.active;
        state.active = active && WarHammerArmorItem.hasMarineChestplate(player) && state.remainingTicks > 0;
        state.strafe = strafe;
        state.forward = forward;
        state.sprinting = sprinting;
        if (state.active && !wasActive) {
            state.startBoostPending = sprinting && forward > 0.0F;
        }
    }

    public static void reset(Player player) {
        BOOST_STATES.remove(player.getUUID());
    }

    public static int getRemainingTicks(Player player) {
        BoostState state = BOOST_STATES.get(player.getUUID());
        return state == null ? MAX_BOOST_TICKS : state.remainingTicks;
    }

    public static boolean isActive(Player player) {
        BoostState state = BOOST_STATES.get(player.getUUID());
        return state != null && state.active;
    }

    public static boolean isDepleted(Player player) {
        BoostState state = BOOST_STATES.get(player.getUUID());
        return state != null && state.remainingTicks <= 0;
    }

    public static boolean isPartiallyDepleted(Player player) {
        BoostState state = BOOST_STATES.get(player.getUUID());
        return state != null && state.remainingTicks < MAX_BOOST_TICKS;
    }

    public static void refillIfGrounded(Player player) {
        if (player.onGround()) {
            BOOST_STATES.remove(player.getUUID());
        }
    }

    public static void tickPlayer(Player player) {
        if (player.level().isClientSide()) {
            return;
        }

        BoostState state = BOOST_STATES.get(player.getUUID());
        if (state == null) {
            return;
        }

        if (player.onGround()) {
            state.remainingTicks = MAX_BOOST_TICKS;
            if (!state.active) {
                BOOST_STATES.remove(player.getUUID());
                return;
            }
        }

        if (!WarHammerArmorItem.hasMarineChestplate(player)) {
            BOOST_STATES.remove(player.getUUID());
            return;
        }

        if (!state.active || state.remainingTicks <= 0) {
            state.active = false;
            return;
        }

        if (state.startBoostPending) {
            player.addDeltaMovement(getSprintStartBoostDelta(player, state.strafe, state.forward));
            state.startBoostPending = false;
        }

        Vec3 boost = getBoostDelta(player, state.strafe, state.forward, state.sprinting);
        player.addDeltaMovement(boost);
        ensurePoweredLift(player);
        limitVelocity(player, state.sprinting);
        player.hurtMarked = true;
        player.fallDistance = 0.0F;
        spawnBoostParticles(player);
        state.remainingTicks--;
        if (state.remainingTicks <= 0) {
            state.remainingTicks = 0;
            state.active = false;
        }
    }

    public static Vec3 getBoostDelta(Player player, float strafeInput, float forwardInput, boolean sprinting) {
        return getInputDirectedDelta(player, strafeInput, forwardInput, sprinting ? SPRINT_AIR_ACCELERATION : WALK_AIR_ACCELERATION, VERTICAL_ACCELERATION);
    }

    public static Vec3 getSprintStartBoostDelta(Player player, float strafeInput, float forwardInput) {
        return getInputDirectedDelta(player, strafeInput, forwardInput, SPRINT_START_HORIZONTAL_IMPULSE, SPRINT_START_VERTICAL_IMPULSE);
    }

    private static Vec3 getInputDirectedDelta(Player player, float strafeInput, float forwardInput, double horizontalStrength, double verticalStrength) {
        double strafe = strafeInput;
        double forward = forwardInput;
        double inputLength = Math.sqrt(strafe * strafe + forward * forward);
        double x = 0.0D;
        double z = 0.0D;

        if (inputLength > 0.0D) {
            strafe /= inputLength;
            forward /= inputLength;
            double yaw = Math.toRadians(player.getYRot());
            double sin = Math.sin(yaw);
            double cos = Math.cos(yaw);
            x += (strafe * cos - forward * sin) * horizontalStrength;
            z += (forward * cos + strafe * sin) * horizontalStrength;
        }

        return new Vec3(x, verticalStrength, z);
    }

    private static void limitVelocity(Player player, boolean sprinting) {
        Vec3 movement = player.getDeltaMovement();
        double x = movement.x;
        double z = movement.z;
        double maxHorizontalSpeed = sprinting ? SPRINT_MAX_HORIZONTAL_SPEED : WALK_MAX_HORIZONTAL_SPEED;
        double horizontalSpeed = Math.sqrt(x * x + z * z);
        if (horizontalSpeed > maxHorizontalSpeed) {
            double scale = maxHorizontalSpeed / horizontalSpeed;
            x *= scale;
            z *= scale;
        }

        double y = Math.min(movement.y, MAX_VERTICAL_SPEED);
        if (x != movement.x || y != movement.y || z != movement.z) {
            player.setDeltaMovement(x, y, z);
        }
    }

    private static void ensurePoweredLift(Player player) {
        Vec3 movement = player.getDeltaMovement();
        if (movement.y < MIN_VERTICAL_SPEED) {
            player.setDeltaMovement(movement.x, MIN_VERTICAL_SPEED, movement.z);
        }
    }

    private static void spawnBoostParticles(Player player) {
        if (!(player.level() instanceof ServerLevel serverLevel)) {
            return;
        }

        Vec3 look = player.getLookAngle();
        double x = player.getX() - look.x * 0.45D;
        double y = player.getY() + player.getBbHeight() * 0.62D;
        double z = player.getZ() - look.z * 0.45D;
        serverLevel.sendParticles(ParticleTypes.CLOUD, x, y, z, 6, 0.18D, 0.12D, 0.18D, 0.015D);
        serverLevel.sendParticles(ParticleTypes.END_ROD, x, y, z, 2, 0.08D, 0.06D, 0.08D, 0.01D);
    }

    private static class BoostState {
        private boolean active;
        private boolean sprinting;
        private boolean startBoostPending;
        private float strafe;
        private float forward;
        private int remainingTicks = MAX_BOOST_TICKS;
    }
}
