package gg.lode.lecternapi.api.manager;

import org.bukkit.entity.Player;

/**
 * Single-fire anime-style impact-frame VFX.
 * <p>
 * When triggered, the player's client captures the current framebuffer and
 * holds it on screen for 4 ticks (~200 ms) while a Sobel-edge + grayscale
 * post-effect is applied, giving the snapshot a flat black-and-white "impact
 * frame" look. After the 4-tick window, the game resumes rendering normally.
 * <p>
 * Visual only — no entity damage, no world state.
 */
public interface IImpactFrameManager {

    /**
     * Fires a one-shot impact frame on the player's client.
     * Fire-and-forget; no response is sent back.
     *
     * @param player the target player
     */
    void triggerImpactFrame(Player player);
}
