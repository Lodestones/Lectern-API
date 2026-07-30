package gg.lode.lecternapi.api.ui;

/**
 * One selectable position on the client's emote wheel. A slot may carry an image, a caption,
 * or both. Server-side mirror of the Lectern client record.
 *
 * @param id      the identifier reported back in the select event — it means whatever the
 *                server needs; the client only echoes it
 * @param text    caption, or empty. Styled: JSON component or MiniMessage tags
 * @param icon    an item id to draw as the image, or empty
 * @param image   a texture id to draw; takes precedence over {@code icon} when both are set
 * @param enabled whether the slot can be selected. A disabled slot still occupies its
 *                position so the wheel's geometry doesn't shift
 */
public record EmoteWheelSlot(
        String id,
        String text,
        String icon,
        String image,
        boolean enabled
) {
    public EmoteWheelSlot(String id, String text, String icon, String image) {
        this(id, text, icon, image, true);
    }

    /** Caption-only slot. */
    public static EmoteWheelSlot text(String id, String text) {
        return new EmoteWheelSlot(id, text, "", "");
    }

    /** Item-icon slot with a caption. */
    public static EmoteWheelSlot item(String id, String itemId, String text) {
        return new EmoteWheelSlot(id, text, itemId, "");
    }

    /** Texture slot with a caption. */
    public static EmoteWheelSlot texture(String id, String textureId, String text) {
        return new EmoteWheelSlot(id, text, "", textureId);
    }
}
