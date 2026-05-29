package gg.lode.lecternapi.api.ui;

import gg.lode.lecternapi.api.menu.HorizontalAlignment;
import gg.lode.lecternapi.api.menu.VerticalAlignment;

/**
 * Announcement toast with an id, alignment, free-floating (x, y) offset, and
 * optional per-part texture overrides.
 * <p>
 * <b>Lifecycle by id.</b> Each toast carries a unique {@link #id}. Sending a
 * toast with a new id pops it in with the animation. Sending a toast with an
 * id that's already live <i>updates</i> its content in place — no re-popup —
 * and, if the (x, y) target moves far enough, eases it to the new position
 * (close-range moves snap immediately). Toasts auto-expire after
 * {@link #displayTimeMs}; you can also remove them early with
 * {@code IUIManager.removeToast(player, id)}.
 * <p>
 * <b>Position.</b> The toast is anchored to the screen using
 * {@link #horizontalAlignment} / {@link #verticalAlignment}, then offset by
 * (x, y) pixels.
 * <p>
 * <b>Textures.</b> Defaults to the FancyToasts-style atlas at
 * {@code lectern:textures/toast/vanilla.png}. You can override:
 * <ul>
 *   <li>{@link #backdrop} — a full 256×256 atlas in the FancyToasts layout
 *       (used for any region not overridden individually below).</li>
 *   <li>{@link #bannerTexture} — a standalone 162×14 PNG drawn into the top
 *       banner strip (overrides the atlas banner region).</li>
 *   <li>{@link #bodyTexture} — a standalone 162×40 PNG drawn into the body
 *       region below the banner (overrides the atlas body region).</li>
 *   <li>{@link #icon} — already independent of the atlas; either an item id
 *       or a 16×16 texture path.</li>
 * </ul>
 * Set part-overrides to empty string to fall back to the atlas.
 *
 * @param id                  unique toast identifier (for updates + removal)
 * @param title               title text (font-rendered)
 * @param message             message body text (font-rendered, may be empty)
 * @param icon                item id or 16×16 texture path (empty = no icon)
 * @param backdrop            atlas path (empty = default vanilla atlas)
 * @param bannerTexture       optional 162×14 standalone PNG for the top banner
 * @param bodyTexture         optional 162×40 standalone PNG for the body region
 * @param x                   pixel offset from the anchor point
 * @param y                   pixel offset from the anchor point
 * @param horizontalAlignment screen anchor on X axis
 * @param verticalAlignment   screen anchor on Y axis
 * @param displayTimeMs       total display duration (≤ 0 = {@link #DEFAULT_DURATION_MS})
 * @param titleColor          ARGB title color (0 = default white)
 * @param messageColor        ARGB message color (0 = default gray)
 * @param textScale           title + message text scale (≤ 0 = 1.0)
 * @param scale               overall toast scale — box, icon, text (≤ 0 = 1.0)
 * @param maxLines            max wrapped message lines shown (≤ 0 = 2)
 * @param animInMs            pop-in animation duration in ms (≤ 0 = 220)
 * @param animOutMs           pop-out animation duration in ms (≤ 0 = 220)
 * @param titleAlign          horizontal text alignment of the title (null = CENTER)
 * @param messageAlign        horizontal text alignment of the message (null = LEFT)
 */
public record Toast(
        String id,
        String title,
        String message,
        String icon,
        String backdrop,
        String bannerTexture,
        String bodyTexture,
        float x,
        float y,
        HorizontalAlignment horizontalAlignment,
        VerticalAlignment verticalAlignment,
        int displayTimeMs,
        int titleColor,
        int messageColor,
        float textScale,
        float scale,
        int maxLines,
        int animInMs,
        int animOutMs,
        HorizontalAlignment titleAlign,
        HorizontalAlignment messageAlign
) {

    public static final int DEFAULT_DURATION_MS = 5000;

    public static final float DEFAULT_X = 0F;
    public static final float DEFAULT_Y = 60F;
    public static final HorizontalAlignment DEFAULT_H = HorizontalAlignment.CENTER;
    public static final VerticalAlignment DEFAULT_V = VerticalAlignment.TOP;

    /** Default text alignment: title centered, message left. */
    public static final HorizontalAlignment DEFAULT_TITLE_ALIGN = HorizontalAlignment.CENTER;
    public static final HorizontalAlignment DEFAULT_MESSAGE_ALIGN = HorizontalAlignment.LEFT;

    /** Normalizes null text-alignment components to their defaults. */
    public Toast {
        if (titleAlign == null) titleAlign = DEFAULT_TITLE_ALIGN;
        if (messageAlign == null) messageAlign = DEFAULT_MESSAGE_ALIGN;
    }

    public static final int COLOR_TITLE_WHITE = 0xFFFFFFFF;
    public static final int COLOR_TITLE_YELLOW = 0xFFFFAA00;
    public static final int COLOR_TITLE_RED = 0xFFFF5555;
    public static final int COLOR_TITLE_AQUA = 0xFF55FFFF;
    public static final int COLOR_MESSAGE_GRAY = 0xFFAAAAAA;

    /** Default position: centered horizontally, 60px from the top. */
    public static Toast of(String id, String title, String message, String icon) {
        return new Toast(id, title, message, icon, "", "", "",
                DEFAULT_X, DEFAULT_Y, DEFAULT_H, DEFAULT_V, 0, 0, 0,
                0F, 0F, 0, 0, 0, null, null);
    }

    /** Default position + custom backdrop atlas. */
    public static Toast withBackdrop(String id, String title, String message, String icon, String backdrop) {
        return new Toast(id, title, message, icon, backdrop, "", "",
                DEFAULT_X, DEFAULT_Y, DEFAULT_H, DEFAULT_V, 0, 0, 0,
                0F, 0F, 0, 0, 0, null, null);
    }

    /** Default position + per-part texture overrides. Pass empty string to skip an override. */
    public static Toast withParts(String id, String title, String message, String icon,
                                  String bannerTexture, String bodyTexture) {
        return new Toast(id, title, message, icon, "", bannerTexture, bodyTexture,
                DEFAULT_X, DEFAULT_Y, DEFAULT_H, DEFAULT_V, 0, 0, 0,
                0F, 0F, 0, 0, 0, null, null);
    }

    /** Custom position. */
    public static Toast at(String id, String title, String message, String icon,
                           float x, float y,
                           HorizontalAlignment h, VerticalAlignment v) {
        return new Toast(id, title, message, icon, "", "", "", x, y, h, v, 0, 0, 0,
                0F, 0F, 0, 0, 0, null, null);
    }

    /** White title, default position. */
    public static Toast info(String id, String title, String message, String icon) {
        return new Toast(id, title, message, icon, "", "", "",
                DEFAULT_X, DEFAULT_Y, DEFAULT_H, DEFAULT_V, 0,
                COLOR_TITLE_WHITE, COLOR_MESSAGE_GRAY, 0F, 0F, 0, 0, 0, null, null);
    }

    /** Yellow title, default position. */
    public static Toast warn(String id, String title, String message, String icon) {
        return new Toast(id, title, message, icon, "", "", "",
                DEFAULT_X, DEFAULT_Y, DEFAULT_H, DEFAULT_V, 0,
                COLOR_TITLE_YELLOW, COLOR_MESSAGE_GRAY, 0F, 0F, 0, 0, 0, null, null);
    }

    /** Red title, default position. */
    public static Toast error(String id, String title, String message, String icon) {
        return new Toast(id, title, message, icon, "", "", "",
                DEFAULT_X, DEFAULT_Y, DEFAULT_H, DEFAULT_V, 0,
                COLOR_TITLE_RED, COLOR_MESSAGE_GRAY, 0F, 0F, 0, 0, 0, null, null);
    }

    /** Aqua title, default position. */
    public static Toast debug(String id, String title, String message, String icon) {
        return new Toast(id, title, message, icon, "", "", "",
                DEFAULT_X, DEFAULT_Y, DEFAULT_H, DEFAULT_V, 0,
                COLOR_TITLE_AQUA, COLOR_MESSAGE_GRAY, 0F, 0F, 0, 0, 0, null, null);
    }

    // ── Chainable copy-withers (layer styling onto any factory above) ────────

    /** Copy with a new display duration in ms. */
    public Toast withDuration(int displayTimeMs) {
        return new Toast(id, title, message, icon, backdrop, bannerTexture, bodyTexture,
                x, y, horizontalAlignment, verticalAlignment, displayTimeMs, titleColor, messageColor,
                textScale, scale, maxLines, animInMs, animOutMs, titleAlign, messageAlign);
    }

    /** Copy with a new title + message text scale. */
    public Toast withTextScale(float textScale) {
        return new Toast(id, title, message, icon, backdrop, bannerTexture, bodyTexture,
                x, y, horizontalAlignment, verticalAlignment, displayTimeMs, titleColor, messageColor,
                textScale, scale, maxLines, animInMs, animOutMs, titleAlign, messageAlign);
    }

    /** Copy with a new overall toast scale (box, icon, text). */
    public Toast withScale(float scale) {
        return new Toast(id, title, message, icon, backdrop, bannerTexture, bodyTexture,
                x, y, horizontalAlignment, verticalAlignment, displayTimeMs, titleColor, messageColor,
                textScale, scale, maxLines, animInMs, animOutMs, titleAlign, messageAlign);
    }

    /** Copy with a new max message-line count. */
    public Toast withMaxLines(int maxLines) {
        return new Toast(id, title, message, icon, backdrop, bannerTexture, bodyTexture,
                x, y, horizontalAlignment, verticalAlignment, displayTimeMs, titleColor, messageColor,
                textScale, scale, maxLines, animInMs, animOutMs, titleAlign, messageAlign);
    }

    /** Copy with new pop-in / pop-out animation durations in ms. */
    public Toast withAnimation(int animInMs, int animOutMs) {
        return new Toast(id, title, message, icon, backdrop, bannerTexture, bodyTexture,
                x, y, horizontalAlignment, verticalAlignment, displayTimeMs, titleColor, messageColor,
                textScale, scale, maxLines, animInMs, animOutMs, titleAlign, messageAlign);
    }

    /** Copy with new title + message colors (ARGB). */
    public Toast withColors(int titleColor, int messageColor) {
        return new Toast(id, title, message, icon, backdrop, bannerTexture, bodyTexture,
                x, y, horizontalAlignment, verticalAlignment, displayTimeMs, titleColor, messageColor,
                textScale, scale, maxLines, animInMs, animOutMs, titleAlign, messageAlign);
    }

    /** Copy with new title + message horizontal text alignment. */
    public Toast withTextAlign(HorizontalAlignment titleAlign, HorizontalAlignment messageAlign) {
        return new Toast(id, title, message, icon, backdrop, bannerTexture, bodyTexture,
                x, y, horizontalAlignment, verticalAlignment, displayTimeMs, titleColor, messageColor,
                textScale, scale, maxLines, animInMs, animOutMs, titleAlign, messageAlign);
    }

    /** Copy with a new (x, y) pixel offset and screen anchor. */
    public Toast withPosition(float x, float y, HorizontalAlignment horizontalAlignment, VerticalAlignment verticalAlignment) {
        return new Toast(id, title, message, icon, backdrop, bannerTexture, bodyTexture,
                x, y, horizontalAlignment, verticalAlignment, displayTimeMs, titleColor, messageColor,
                textScale, scale, maxLines, animInMs, animOutMs, titleAlign, messageAlign);
    }

    /** Copy with a new icon (item id or 16×16 texture path; empty = no icon). */
    public Toast withIcon(String icon) {
        return new Toast(id, title, message, icon, backdrop, bannerTexture, bodyTexture,
                x, y, horizontalAlignment, verticalAlignment, displayTimeMs, titleColor, messageColor,
                textScale, scale, maxLines, animInMs, animOutMs, titleAlign, messageAlign);
    }
}
