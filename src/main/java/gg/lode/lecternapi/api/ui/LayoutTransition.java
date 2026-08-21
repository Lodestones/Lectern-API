package gg.lode.lecternapi.api.ui;

/**
 * How a layout takes over from whatever it is replacing.
 *
 * <p>Only meaningful when a layout replaces itself — one scene of a project giving way to another.
 * The incoming scene starts at its own first keyframe, which is where the outgoing one was expected
 * to have finished; end an intro early and that is not where anything is, so cutting to it snaps
 * every element into place at once. Blending eases out of the pose actually on screen instead.
 *
 * <p>Three answers, and the third is the interesting one: {@link #inherit()} leaves the decision to
 * the document, where the author set it per scene. A caller that has no opinion should say so
 * rather than pick a number, or it overrides work the author did deliberately.
 *
 * @param millis how long to blend for, 0 to cut, or negative to leave it to the document
 */
public record LayoutTransition(int millis) {

    /** Snap straight to the new scene's first keyframe. */
    public static LayoutTransition cut() {
        return new LayoutTransition(0);
    }

    /** Ease out of the pose on screen over {@code millis}. */
    public static LayoutTransition blend(int millis) {
        return new LayoutTransition(Math.max(0, millis));
    }

    /** Use whatever the scene itself asks for. */
    public static LayoutTransition inherit() {
        return new LayoutTransition(-1);
    }

    /** Whether this leaves the choice to the document. */
    public boolean isInherited() {
        return millis < 0;
    }
}
