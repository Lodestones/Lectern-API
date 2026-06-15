package gg.lode.lecternapi.api.manager;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

/**
 * Single-fire anime-style impact-frame VFX.
 * <p>
 * When triggered, the receiver's client freezes the current frame for ~200 ms and
 * flattens the world to high-contrast black-and-white with procedural manga
 * "concentration lines" (speed lines) bursting outward. Roles:
 * <ul>
 *   <li><b>Receiver</b> — the player whose client plays the effect.</li>
 *   <li><b>Target</b> — the focal subject. Its exact silhouette is outlined and
 *       filled as a luma matte so it pops against the flat backdrop; the streaks
 *       clear a focal "eye" around it.</li>
 *   <li><b>Source</b> — where the streaks burst from. If {@code null}, or equal to
 *       the receiver, the burst originates from the receiver's own camera (the
 *       receiver is the cause). Off-screen / behind-camera sources are clamped to
 *       the screen edge so the burst always comes from the correct direction.</li>
 *   <li><b>Strength</b> — how impactful the hit was; scales streak density, width
 *       and reach (typically {@code 0.0}–{@code 1.0}, may exceed 1).</li>
 *   <li><b>Invert</b> — {@code false} = light background (black ink), {@code true}
 *       = black background (white ink).</li>
 * </ul>
 * Visual only — no entity damage, no world state.
 */
public interface IImpactFrameManager {

    /**
     * Fires a one-shot impact frame on the player's client with no focal target
     * (camera-centred burst, default strength). Fire-and-forget.
     *
     * @param player the receiver
     */
    void triggerImpactFrame(Player player);

    /**
     * Fires an impact frame on {@code receiver} focused on {@code target}, with the
     * burst originating from the receiver's camera and default strength.
     *
     * @param receiver the player whose client plays the effect
     * @param target   the focal subject entity
     */
    void triggerImpactFrame(Player receiver, Entity target);

    /**
     * Fires a fully-specified impact frame on {@code receiver}.
     *
     * @param receiver the player whose client plays the effect
     * @param target   the focal subject entity (may be {@code null} for a
     *                 target-less, camera-centred burst)
     * @param source   the entity the streaks burst from; {@code null} or equal to
     *                 {@code receiver} means the receiver is the cause
     * @param strength impact magnitude (typically 0.0–1.0), scales the streaks
     * @param invert   {@code true} for the black-background variant
     */
    void triggerImpactFrame(Player receiver, @Nullable Entity target,
                            @Nullable Entity source, float strength, boolean invert);

    /**
     * Fires an impact frame for every player within {@code radius} blocks of
     * {@code target} (in the target's world). Each such player is a receiver and
     * experiences the burst from {@code source}'s direction relative to their own
     * view; if {@code source} is that receiver, they are treated as the cause.
     *
     * @param target   the focal subject entity (required)
     * @param source   the entity the streaks burst from, or {@code null}
     * @param radius   block radius around the target to notify
     * @param strength impact magnitude (typically 0.0–1.0)
     * @param invert   {@code true} for the black-background variant
     */
    void triggerImpactFrameNearby(Entity target, @Nullable Entity source,
                                  double radius, float strength, boolean invert);
}
