package gg.lode.lecternapi;

import gg.lode.lecternapi.api.manager.*;

import org.bukkit.entity.Player;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.Nullable;

public interface ILecternAPI {

    /**
     * Resets all active effects for a player, sending the appropriate stop/clear packets
     * to the client and clearing all tracked effect state.
     *
     * @param player the player to reset
     */
    void resetAllEffects(Player player);

    /**
     * Disconnects a player cleanly from the server via a client-side disconnect packet.
     * The player returns to the multiplayer screen or title screen with the provided reason message.
     * <p>
     * This is distinct from kicking via the vanilla protocol: the client initiates the disconnect
     * itself rather than the server forcing it, allowing the client to tear down its world and
     * Lectern state gracefully before showing the disconnect screen.
     *
     * @param player the player to disconnect
     * @param reason an optional disconnect reason to display on the client's disconnect screen.
     *               If null, a default message is shown on the client.
     */
    default void disconnectPlayer(Player player, @Nullable Component reason) {
        // Default rather than abstract so a loader shading this interface still links against an
        // older impl blob: the loader ships on its own schedule, and an abstract method added here
        // would be an AbstractMethodError on every server whose blob has not caught up.
    }

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
