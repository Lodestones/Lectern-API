package gg.lode.lecternapi.api.manager;

import org.bukkit.entity.Player;

import java.util.Set;
import java.util.UUID;

/**
 * Controls raw input reporting for a player.
 *
 * <p>Telemetry is off for everyone by default. While it's on for a player their client uploads a
 * batch of key and mouse transitions roughly once a second, delivered server-side as
 * {@code ClientInputTelemetryEvent}. Records carry key identity and timing only — never typed text.
 *
 * <p>Enabling this for the whole server is a deliberate choice: every enabled player adds a packet
 * per second, and the batches are only useful to something that actually consumes them.
 */
public interface IInputTelemetryManager {

    /**
     * Starts reporting for {@code player}. Survives the player's reconnect.
     *
     * @param reportUnboundKeys also report keys bound to nothing in vanilla controls — the signal
     *                          for cheat-menu toggle keys, at the cost of a noisier stream
     */
    void start(Player player, boolean reportUnboundKeys);

    /** Stops reporting for {@code player}. */
    void stop(Player player);

    /** Whether reporting is currently on for this player. */
    boolean isEnabled(UUID playerId);

    /** The players reporting right now. */
    Set<UUID> getEnabledPlayers();
}
