package gg.lode.lecternapi.manager;

import org.bukkit.entity.Player;

/**
 * The emote manager is used to manage emotes for players.
 * You can register emotes using the {@link #registerEmotes(String...)} method.
 * <p>
 * For more information about how to create emotes, you can find our
 * documentation at:
 * <a href="https://lode.gg/docs/lectern/features/emotes">...</a>
 */
public interface IEmoteManager {

    /**
     * Registers emotes to be used by the emote manager.
     * This method is basically just a way to make sure that the emote manager knows
     * which emotes are registered.
     * 
     * This means you can use methods like {@link #stopAllEmotes(Player)} to stop
     * all emotes for a player.
     * 
     * @param emoteIds The IDs of the emotes to register.
     */
    void registerEmotes(String... emoteIds);

    /**
     * Stops all emotes for a player.
     * Make sure to register the emotes using the {@link #registerEmotes(String...)}
     * method.
     * 
     * @param player The player to stop all emotes for.
     */
    void stopAllEmotes(Player player);

    /**
     * Plays an emote for a player.
     * 
     * @param player    The player to play the emote for.
     * @param emoteId   The ID of the emote to play.
     * @param priority  The priority of the emote.
     * @param showHands Whether to show the player's hands.
     */
    void playEmote(Player player, String emoteId, int priority, boolean showHands);

    /**
     * Plays an emote for a player.
     * 
     * @param player   The player to play the emote for.
     * @param emoteId  The ID of the emote to play.
     * @param priority The priority of the emote.
     */
    void playEmote(Player player, String emoteId, int priority);

    /**
     * Stops an emote for a player.
     * 
     * @param player   The player to stop the emote for.
     * @param emoteIds The IDs of the emote to stop.
     */
    void stopEmote(Player player, String... emoteIds);

    /**
     * Stops all local emotes for a player.
     * Make sure to register the emotes using the {@link #registerEmotes(String...)}
     * method.
     * 
     * @param player The player to stop all local emotes for.
     */
    void stopAllLocalEmotes(Player player);

    /**
     * Plays a local emote for a player.
     * 
     * @param player    The player to play the local emote for.
     * @param emoteId   The ID of the local emote to play.
     * @param priority  The priority of the local emote.
     * @param showHands Whether to show the player's hands.
     */

    void playLocalEmote(Player player, String emoteId, int priority, boolean showHands);

    /**
     * Plays a local emote for a player.
     * 
     * @param player   The player to play the local emote for.
     * @param emoteId  The ID of the local emote to play.
     * @param priority The priority of the local emote.
     */
    void playLocalEmote(Player player, String emoteId, int priority);

    /**
     * Stops a local emote for a player.
     * 
     * @param player   The player to stop the local emote for.
     * @param emoteIds The IDs of the local emote to stop.
     */
    void stopLocalEmote(Player player, String... emoteIds);

}
