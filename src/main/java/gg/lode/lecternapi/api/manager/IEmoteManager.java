package gg.lode.lecternapi.api.manager;

import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.UUID;

/**
 * Manages emote playback for players running the Lectern client mod.
 * <p>
 * Provides three levels of targeting:
 * <ul>
 *   <li><b>Targeted</b> — play an emote on a specific entity, visible to a specific viewer</li>
 *   <li><b>Local</b> — play an emote on a player, visible only to themselves</li>
 *   <li><b>Broadcast</b> — play an emote on an entity, visible to all online players
 *       or a specific collection of viewers</li>
 * </ul>
 *
 * All methods use the same underlying {@code emote_renderer} packet — no Fabric client
 * changes are required.
 */
public interface IEmoteManager {

    // --- Targeted (single viewer → single entity) ---

    /**
     * Plays an emote on an entity as seen by the target player.
     *
     * @param viewer     the player who will see the emote
     * @param entityUuid the UUID of the entity to animate
     * @param emoteId    the emote identifier (registered in the client animation registry)
     * @param showHands  whether to show the entity's hands during the emote
     * @param priority   the animation layer priority (higher = on top)
     */
    void playEmote(Player viewer, UUID entityUuid, String emoteId, boolean showHands, int priority);

    /**
     * Stops a specific emote on an entity as seen by the target player.
     */
    void stopEmote(Player viewer, UUID entityUuid, String emoteId);

    /**
     * Stops all emotes on an entity as seen by the target player.
     */
    void stopAllEmotes(Player viewer, UUID entityUuid);

    /**
     * Clears all emote state on the target player's client.
     */
    void clearEmotes(Player viewer);

    // --- Local (player sees their own emote) ---

    /**
     * Plays an emote on the player, visible only to themselves.
     *
     * @param player    the player to animate (also the viewer)
     * @param emoteId   the emote identifier
     * @param showHands whether to show hands during the emote
     * @param priority  the animation layer priority
     */
    void playLocalEmote(Player player, String emoteId, boolean showHands, int priority);

    /**
     * Stops a specific local emote on the player.
     */
    void stopLocalEmote(Player player, String emoteId);

    /**
     * Stops all local emotes on the player.
     */
    void stopAllLocalEmotes(Player player);

    // --- Broadcast (all online players see the emote) ---

    /**
     * Plays an emote on an entity, visible to all online players.
     *
     * @param entityUuid the UUID of the entity to animate
     * @param emoteId    the emote identifier
     * @param showHands  whether to show hands during the emote
     * @param priority   the animation layer priority
     */
    void broadcastEmote(UUID entityUuid, String emoteId, boolean showHands, int priority);

    /**
     * Plays an emote on a player, visible to all online players.
     * Convenience overload that extracts the player's UUID.
     *
     * @param target    the player to animate
     * @param emoteId   the emote identifier
     * @param showHands whether to show hands during the emote
     * @param priority  the animation layer priority
     */
    void broadcastEmote(Player target, String emoteId, boolean showHands, int priority);

    /**
     * Stops a specific emote on an entity for all online players.
     */
    void broadcastStopEmote(UUID entityUuid, String emoteId);

    /**
     * Stops a specific emote on a player for all online players.
     */
    void broadcastStopEmote(Player target, String emoteId);

    /**
     * Stops all emotes on an entity for all online players.
     */
    void broadcastStopAllEmotes(UUID entityUuid);

    /**
     * Stops all emotes on a player for all online players.
     */
    void broadcastStopAllEmotes(Player target);

    // --- Collection-based (specific set of viewers) ---

    /**
     * Plays an emote on an entity, visible to a specific collection of viewers.
     *
     * @param viewers    the players who will see the emote
     * @param entityUuid the UUID of the entity to animate
     * @param emoteId    the emote identifier
     * @param showHands  whether to show hands during the emote
     * @param priority   the animation layer priority
     */
    void playEmote(Collection<? extends Player> viewers, UUID entityUuid, String emoteId, boolean showHands, int priority);

    /**
     * Stops a specific emote on an entity for a collection of viewers.
     */
    void stopEmote(Collection<? extends Player> viewers, UUID entityUuid, String emoteId);

    /**
     * Stops all emotes on an entity for a collection of viewers.
     */
    void stopAllEmotes(Collection<? extends Player> viewers, UUID entityUuid);

    /**
     * Clears all emote state for a collection of viewers.
     */
    void clearEmotes(Collection<? extends Player> viewers);
}
