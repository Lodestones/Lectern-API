package gg.lode.lecternapi.api.manager;

import org.bukkit.entity.Player;

/**
 * Manages voice chat state for players running the Lectern client mod.
 * Controls muting, unmuting, deafening, and undeafening — both as suggestions
 * (player can override) and as forced states (player cannot override).
 */
public interface IVoiceChatManager {

    /**
     * Mutes the player's voice chat. The player can override this.
     */
    void mutePlayer(Player player);

    /**
     * Unmutes the player's voice chat. The player can override this.
     */
    void unmutePlayer(Player player);

    /**
     * Deafens the player's voice chat. The player can override this.
     */
    void deafenPlayer(Player player);

    /**
     * Undeafens the player's voice chat. The player can override this.
     */
    void undeafenPlayer(Player player);

    /**
     * Force mutes the player's voice chat. The player cannot override this.
     */
    void forceMutePlayer(Player player);

    /**
     * Force unmutes the player's voice chat. The player cannot override this.
     */
    void forceUnmutePlayer(Player player);

    /**
     * Force deafens the player's voice chat. The player cannot override this.
     */
    void forceDeafenPlayer(Player player);

    /**
     * Force undeafens the player's voice chat. The player cannot override this.
     */
    void forceUndeafenPlayer(Player player);

    /**
     * Stops forcing the mute state, giving the player back control.
     */
    void stopForceMute(Player player);

    /**
     * Stops forcing the unmute state, giving the player back control.
     */
    void stopForceUnmute(Player player);

    /**
     * Stops forcing the deafen state, giving the player back control.
     */
    void stopForceDeafen(Player player);

    /**
     * Stops forcing the undeafen state, giving the player back control.
     */
    void stopForceUndeafen(Player player);
}
