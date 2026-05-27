package gg.lode.lecternapi.api.ui;

import java.util.List;

/**
 * Represents a widget in a config/settings screen.
 * Each type has a byte discriminator for network serialization.
 */
public sealed interface ConfigWidget {

    /** Section header text. */
    record TextHeader(String text) implements ConfigWidget {}

    /** Horizontal divider line. */
    record Divider() implements ConfigWidget {}

    /** Clickable button. Sends reference back to server on click. */
    record Button(String label, String reference, float r, float g, float b) implements ConfigWidget {}

    /** Boolean toggle switch. Sends reference + new value back to server on change. */
    record Toggle(String label, String reference, boolean defaultValue) implements ConfigWidget {}

    /** Numeric slider. Sends reference + new value back to server on change. */
    record Slider(String label, String reference, float min, float max, float defaultValue, String suffix) implements ConfigWidget {}

    /** Dropdown selector. Sends reference + selected index back to server on change. */
    record Selector(String label, String reference, List<String> options, int defaultIndex) implements ConfigWidget {}

    /** Text input field. Sends reference + new value back to server on change. */
    record EditBox(String label, String reference, String defaultValue) implements ConfigWidget {}

    /** Image from a texture identifier (e.g. "mymod:textures/banner.png"). */
    record Image(String textureId) implements ConfigWidget {}

    // --- Byte discriminators for serialization ---

    static byte discriminator(ConfigWidget widget) {
        return switch (widget) {
            case TextHeader ignored -> 0;
            case Divider ignored -> 1;
            case Button ignored -> 2;
            case Toggle ignored -> 3;
            case Slider ignored -> 4;
            case Selector ignored -> 5;
            case EditBox ignored -> 6;
            case Image ignored -> 7;
        };
    }
}
