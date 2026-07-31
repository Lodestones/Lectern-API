package gg.lode.lecternapi.api.ui;

/**
 * A fade envelope for a group of HUD elements — fade in, hold (optionally forever), fade
 * out — built fluently like {@link AnnouncementBadge}:
 *
 * <pre>{@code
 * api.getPacketComponentManager().show(player, component, new HudAnimation()
 *         .fadeIn(300)
 *         .duration(5000)    // total lifetime; 0 = stays until hidden
 *         .fadeOut(500));
 * }</pre>
 *
 * The whole group animates as one unit — every texture, head, button and text element in
 * the component shares the envelope, so a mixed card of artwork and labels breathes
 * together instead of piecewise. With a duration set, the group removes itself when the
 * envelope ends (the fade-out plays over the final {@code fadeOut} milliseconds of the
 * duration); without one it stays until hidden, and {@code fadeOut} becomes the exit
 * animation for that hide.
 */
public class HudAnimation {

    private int fadeIn = 0;
    private int fadeOut = 0;
    private int duration = 0;
    private float scale = 1f;
    private int scaleIn = 0;
    private int scaleOut = 0;
    private float scaleFrom = 0f;

    /** Milliseconds to fade from invisible to full alpha when shown. */
    public HudAnimation fadeIn(int millis) {
        this.fadeIn = Math.max(0, millis);
        return this;
    }

    /**
     * Milliseconds to fade back out — at the end of {@code duration} when one is set,
     * otherwise when the group is hidden.
     */
    public HudAnimation fadeOut(int millis) {
        this.fadeOut = Math.max(0, millis);
        return this;
    }

    /** Total lifetime in milliseconds, fades included. 0 means "until hidden". */
    public HudAnimation duration(int millis) {
        this.duration = Math.max(0, millis);
        return this;
    }

    /** The group's steady-state scale around its anchor; 1 = authored size. */
    public HudAnimation scale(float scale) {
        this.scale = scale <= 0 ? 1f : scale;
        return this;
    }

    /**
     * Milliseconds to grow from {@code scaleFrom} to {@code scale} when shown. Pairs with
     * the fade but runs on its own clock — a quick fade with a slow pop reads great.
     */
    public HudAnimation scaleIn(int millis) {
        this.scaleIn = Math.max(0, millis);
        return this;
    }

    /** Milliseconds to shrink back to {@code scaleFrom} on exit. */
    public HudAnimation scaleOut(int millis) {
        this.scaleOut = Math.max(0, millis);
        return this;
    }

    /** Where the scale animation starts/ends. 0 grows from nothing; 0.8 is a subtle pop. */
    public HudAnimation scaleFrom(float scale) {
        this.scaleFrom = Math.max(0f, scale);
        return this;
    }

    public int getFadeIn() { return fadeIn; }
    public int getFadeOut() { return fadeOut; }
    public int getDuration() { return duration; }
    public float getScale() { return scale; }
    public int getScaleIn() { return scaleIn; }
    public int getScaleOut() { return scaleOut; }
    public float getScaleFrom() { return scaleFrom; }
}
