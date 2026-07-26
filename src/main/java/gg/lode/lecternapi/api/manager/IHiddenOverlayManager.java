package gg.lode.lecternapi.api.manager;

import gg.lode.lecternapi.api.manager.IHUDManager.HorizontalAlignment;
import gg.lode.lecternapi.api.manager.IHUDManager.VerticalAlignment;
import org.bukkit.entity.Player;

/**
 * Renders text and textures onto a <em>capture-excluded</em> overlay on the
 * player's screen: the content is visible to the player but hidden from OBS-style
 * screen recording/streaming. Ideal for passing a player information mid-recording
 * that should not appear in the video (a code, a clue, a private instruction).
 *
 * <p>Usage is fluent — build an element and call {@link Element#show()}:
 * <pre>{@code
 * LecternAPI.getApi().getHiddenOverlayManager()
 *     .text(player, "clue", "The code is 4821")
 *     .at(0, 20).align(HorizontalAlignment.CENTER, VerticalAlignment.TOP)
 *     .color(255, 215, 0).scale(2f)
 *     .fadeIn(400).duration(6000).fadeOut(600)
 *     .show();
 * }</pre>
 *
 * <p><b>Platform note:</b> capture exclusion is honored by OBS on Windows and
 * macOS. On macOS 15+, full-display ScreenCaptureKit tools (e.g. CleanShot)
 * capture the composited screen and will still see the overlay; on Windows it is
 * hidden from all capture. The overlay is always visible to the player.
 */
public interface IHiddenOverlayManager {

    /**
     * Begins a hidden text element. Plain strings render as-is; formatting is the
     * bitmap font's ASCII set.
     *
     * @param player    the target player
     * @param reference a unique id for this element (reuse to replace, pass to {@link #hide})
     * @param text      the text content
     */
    HiddenText text(Player player, String reference, String text);

    /**
     * Begins a hidden texture element.
     *
     * @param player    the target player
     * @param reference a unique id for this element
     * @param textureId the texture identifier (e.g. {@code "namespace:path"})
     */
    HiddenTexture texture(Player player, String reference, String textureId);

    /** Removes a hidden element by reference. */
    void hide(Player player, String reference);

    /** Removes all hidden elements for the player. */
    void hideAll(Player player);

    /** Common fluent options shared by text and texture elements. */
    interface Element<T extends Element<T>> {
        /** Screen position, in GUI-scaled pixels, relative to the chosen alignment. */
        T at(float x, float y);

        /** RGB tint, each component 0-255. Defaults to white (255,255,255). */
        T color(int red, int green, int blue);

        /** Base opacity 0.0-1.0, multiplied by the fade envelope. Defaults to 1.0. */
        T alpha(float alpha);

        /** Draw order; higher layers render on top. Defaults to 0. */
        T layer(int layer);

        /** Alignment of {@link #at(float, float)} against the screen. */
        T align(HorizontalAlignment horizontal, VerticalAlignment vertical);

        /** Fade-in duration in milliseconds (0 = instant). */
        T fadeIn(long millis);

        /** Fade-out duration in milliseconds (0 = instant). */
        T fadeOut(long millis);

        /**
         * Auto-remove after this many milliseconds (fade-out starts so it finishes
         * at expiry). 0 (default) keeps it until {@link #hide} is called.
         */
        T duration(long millis);

        /** Sends the element to the client and shows it. */
        void show();
    }

    /** Fluent builder for a hidden text element. */
    interface HiddenText extends Element<HiddenText> {
        /** Text scale multiplier. Defaults to 1.0. */
        HiddenText scale(float scale);
    }

    /** Fluent builder for a hidden texture element. */
    interface HiddenTexture extends Element<HiddenTexture> {
        /** Rendered size in GUI-scaled pixels. */
        HiddenTexture size(float width, float height);
    }
}
