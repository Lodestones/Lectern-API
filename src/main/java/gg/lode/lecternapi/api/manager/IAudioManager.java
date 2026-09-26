package gg.lode.lecternapi.api.manager;

import org.bukkit.SoundCategory;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * Manages audio effects for players running the Lectern client mod.
 * Controls global sound playback and block-break sounds.
 */
public interface IAudioManager {

    /**
     * Plays a sound on the player's client.
     *
     * @param player the target player
     * @param soundId the sound identifier (e.g. "minecraft:entity.wither.spawn")
     * @param volume the playback volume
     * @param pitch the playback pitch
     */
    void playSound(Player player, String soundId, float volume, float pitch);

    /**
     * Enables or disables the block-break sound effect override.
     */
    void setBlockSound(Player player, boolean enabled);

    /**
     * Stops {@code viewer} hearing {@code target}'s footsteps. Nothing else about the target goes
     * quiet, and nobody else is affected: a monster can creep up on its victims silently while
     * everyone watching still hears it.
     *
     * @param viewer the player who stops hearing them
     * @param target the player whose footsteps go silent
     */
    default void muteFootsteps(Player viewer, UUID target) {
        // Backward-compatible no-op fallback; the real implementation overrides this.
    }

    /** Lets {@code viewer} hear {@code target}'s footsteps again. */
    default void unmuteFootsteps(Player viewer, UUID target) {
        // Backward-compatible no-op fallback; the real implementation overrides this.
    }

    /** Lets {@code viewer} hear everybody's footsteps again. */
    default void clearMutedFootsteps(Player viewer) {
        // Backward-compatible no-op fallback; the real implementation overrides this.
    }

    /**
     * Stops a sound by fading it out instead of cutting it off. Every copy of {@code soundId}
     * playing for the player right now comes down to silence over {@code fadeMillis}, then stops.
     *
     * @param player     the player whose sound fades
     * @param soundId    the sound, e.g. {@code "minecraft:music.game"}; empty or null for every sound
     * @param fadeMillis how long the fade takes; 0 stops it at once
     */
    default void fadeOutSound(Player player, String soundId, int fadeMillis) {
        fadeOutSound(player, soundId, null, fadeMillis);
    }

    /**
     * The same, only for copies playing in {@code category}; null for any category.
     */
    default void fadeOutSound(Player player, String soundId, SoundCategory category, int fadeMillis) {
        // Backward-compatible no-op fallback; the real implementation overrides this.
    }
}
