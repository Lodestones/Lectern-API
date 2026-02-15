package gg.lode.lecternapi;

import gg.lode.lecternapi.api.manager.*;

import org.bukkit.entity.Player;

public interface ILecternAPI {

    /**
     * Resets all active effects for a player, sending the appropriate stop/clear packets
     * to the client and clearing all tracked effect state.
     *
     * @param player the player to reset
     */
    void resetAllEffects(Player player);

    ICameraManager getCameraManager();

    IHUDManager getHUDManager();

    IAudioManager getAudioManager();

    IEnvironmentManager getEnvironmentManager();

    IEntityManager getEntityManager();

    IInputManager getInputManager();

    IScreenManager getScreenManager();

    IPacketMenuManager getPacketMenuManager();

    IPacketComponentManager getPacketComponentManager();

    IEmoteManager getEmoteManager();

    IVoiceChatManager getVoiceChatManager();

    ICutsceneManager getCutsceneManager();

    IPingManager getPingManager();
}
