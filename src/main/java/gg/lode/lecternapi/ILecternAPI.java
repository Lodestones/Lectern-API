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

    /**
     * Pages authored in the Lectern UI editor — loaded from {@code plugins/Lectern/layouts},
     * opened with their own behaviour, and bound to their own actions.
     */
    ILayoutManager getLayoutManager();

    IPingManager getPingManager();

    IFlareManager getFlareManager();

    IAuraManager getAuraManager();

    IUIManager getUIManager();


    IImpactFrameManager getImpactFrameManager();

    IExplosionManager getExplosionManager();

    ISkyboxManager getSkyboxManager();

    IParticleManager getParticleManager();

    /** Effekseer particle effects — authored {@code .efkefc} VFX played on the client. */
    default IEffekManager getEffekManager() {
        return new IEffekManager() {};
    }

    IShapeshiftManager getShapeshiftManager();

    ISharedControlManager getSharedControlManager();

    INicknameManager getNicknameManager();

    /** Per-viewer tab-list prefixes, suffixes and sorting. */
    default ITabListManager getTabListManager() {
        return new ITabListManager() {};
    }
}
