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

    IHiddenOverlayManager getHiddenOverlayManager();

    IAudioManager getAudioManager();

    IEnvironmentManager getEnvironmentManager();

    IEntityManager getEntityManager();

    IInputManager getInputManager();

    /** Raw input reporting for anti-cheat consumers. Off for every player by default. */
    IInputTelemetryManager getInputTelemetryManager();

    IScreenManager getScreenManager();

    IPacketMenuManager getPacketMenuManager();

    IPacketComponentManager getPacketComponentManager();

    IEmoteManager getEmoteManager();

    IVoiceChatManager getVoiceChatManager();

    ICutsceneManager getCutsceneManager();

    IPingManager getPingManager();

    IFlareManager getFlareManager();

    IAuraManager getAuraManager();

    IUIManager getUIManager();

    IImpactFrameManager getImpactFrameManager();

    IExplosionManager getExplosionManager();

    ISkyboxManager getSkyboxManager();

    IParticleManager getParticleManager();

    IShapeshiftManager getShapeshiftManager();

    ISharedControlManager getSharedControlManager();

    INicknameManager getNicknameManager();
}
