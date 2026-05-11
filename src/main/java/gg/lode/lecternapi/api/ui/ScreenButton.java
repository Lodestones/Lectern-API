package gg.lode.lecternapi.api.ui;

/**
 * Represents a button on a scrollable screen or config screen.
 *
 * @param label     the button display text
 * @param reference the reference string sent back to the server on click
 * @param r         red color component (0.0-1.0)
 * @param g         green color component (0.0-1.0)
 * @param b         blue color component (0.0-1.0)
 */
public record ScreenButton(String label, String reference, float r, float g, float b) {

    /**
     * Creates a button with the given color.
     */
    public static ScreenButton of(String label, String reference, float r, float g, float b) {
        return new ScreenButton(label, reference, r, g, b);
    }

    /**
     * Creates a white button.
     */
    public static ScreenButton of(String label, String reference) {
        return new ScreenButton(label, reference, 1f, 1f, 1f);
    }
}
