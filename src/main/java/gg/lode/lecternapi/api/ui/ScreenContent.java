package gg.lode.lecternapi.api.ui;

/**
 * Represents a content section in a scrollable screen.
 * Each type has a byte discriminator for network serialization.
 */
public sealed interface ScreenContent {

    /** Section heading text. Rendered bold with accent color. */
    record Heading(String text) implements ScreenContent {}

    /** Paragraph text. Rendered with word-wrapping. */
    record Paragraph(String text) implements ScreenContent {}

    /** Bullet point text. Rendered with indent and bullet marker. */
    record Bullet(String text) implements ScreenContent {}

    /** Horizontal divider line. */
    record Divider(int colorArgb) implements ScreenContent {}

    /** Small/dim text, typically for footnotes or metadata. */
    record Subtext(String text) implements ScreenContent {}

    // --- Byte discriminators for serialization ---

    static byte discriminator(ScreenContent content) {
        return switch (content) {
            case Heading ignored -> 1;
            case Paragraph ignored -> 2;
            case Bullet ignored -> 3;
            case Divider ignored -> 4;
            case Subtext ignored -> 5;
        };
    }
}
