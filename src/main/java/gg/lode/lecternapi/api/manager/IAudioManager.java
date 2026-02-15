package gg.lode.lecternapi.api.manager;

import org.bukkit.entity.Player;

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
}
