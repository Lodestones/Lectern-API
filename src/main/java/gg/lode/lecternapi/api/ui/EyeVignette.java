package gg.lode.lecternapi.api.ui;

/**
 * An eye-shaped vignette: the player sees the world through an almond, with one colour laid over
 * the inside of it and another over everything outside, each with its own alpha.
 * <p>
 * Made for being blinded, the Dead by Daylight way. Narrow the eye and darken the outside, and
 * put motion blur on top. The client eases between one eye and the next over the transition it is
 * sent with, so a blink is two calls (shut, then open) rather than a stream of them.
 *
 * <pre>{@code
 * api.getScreenManager().setEyeVignette(player, new EyeVignette()
 *         .width(0.8f)
 *         .openness(0.3f)
 *         .outer(0f, 0f, 0f, 1f), 600);
 * }</pre>
 *
 * Colours are red, green, blue and alpha from 0 to 1.
 */
public class EyeVignette {

    private float width = 0.8f;
    private float openness = 1.0f;
    private float feather = 0.05f;
    private float innerR, innerG, innerB, innerA;
    private float outerR, outerG, outerB, outerA = 1.0f;

    /** How wide the eye is, as a fraction of the screen's width. */
    public EyeVignette width(float width) {
        this.width = width;
        return this;
    }

    /** 0 shut, 1 wide open. */
    public EyeVignette openness(float openness) {
        this.openness = Math.max(0.0f, Math.min(1.0f, openness));
        return this;
    }

    /** How soft the edge of the lids is, in fractions of the screen's height. */
    public EyeVignette feather(float feather) {
        this.feather = feather;
        return this;
    }

    /** The colour laid over the inside of the eye. An alpha of 0 leaves the view clear. */
    public EyeVignette inner(float r, float g, float b, float a) {
        innerR = r;
        innerG = g;
        innerB = b;
        innerA = a;
        return this;
    }

    /** The colour laid over everything outside the eye. */
    public EyeVignette outer(float r, float g, float b, float a) {
        outerR = r;
        outerG = g;
        outerB = b;
        outerA = a;
        return this;
    }

    public float getWidth() { return width; }
    public float getOpenness() { return openness; }
    public float getFeather() { return feather; }
    public float getInnerR() { return innerR; }
    public float getInnerG() { return innerG; }
    public float getInnerB() { return innerB; }
    public float getInnerA() { return innerA; }
    public float getOuterR() { return outerR; }
    public float getOuterG() { return outerG; }
    public float getOuterB() { return outerB; }
    public float getOuterA() { return outerA; }
}
