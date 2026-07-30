package gg.lode.lecternapi.api.ui;

/**
 * A Fortnite-style announcement banner: a trapezoid that sweeps open from the centre of the
 * screen, holds, and sweeps shut. Server-side mirror of the Lectern client's badge builder.
 * <p>
 * All text fields accept plain text, a JSON text component, or MiniMessage-style tags
 * ({@code <gold>}, {@code <gradient:red:blue>}). Blank fields take no space. Showing a new
 * badge while one is on screen swaps its contents in place without replaying the opening
 * sweep; the outro plays once no further updates arrive within the hold.
 *
 * <pre>{@code
 * api.getHUDManager().showBadge(player, new AnnouncementBadge()
 *         .title("<gold>MYTHIC")
 *         .description("Deal 25% more damage while airborne")
 *         .icon("minecraft:netherite_sword")
 *         .color(0xFFD9A33C)
 *         .hold(4000));
 * }</pre>
 */
public class AnnouncementBadge {

    private String title = "";
    private String subtitle = "";
    private String description = "";
    private String captionLabel = "";
    private String caption = "";
    private String icon = "";
    private int iconSize = 24;
    private int color = 0;
    private int hold = 4000;
    private float offsetX = 0;
    private float offsetY = 0;
    private float scale = 1;

    /** Headline row. */
    public AnnouncementBadge title(String title) {
        this.title = title == null ? "" : title;
        return this;
    }

    /** Smaller line under the title. */
    public AnnouncementBadge subtitle(String subtitle) {
        this.subtitle = subtitle == null ? "" : subtitle;
        return this;
    }

    /** Body text. */
    public AnnouncementBadge description(String description) {
        this.description = description == null ? "" : description;
        return this;
    }

    /** Bottom caption row: a label chip and its text (either may be empty). */
    public AnnouncementBadge caption(String label, String caption) {
        this.captionLabel = label == null ? "" : label;
        this.caption = caption == null ? "" : caption;
        return this;
    }

    /** An item id ({@code minecraft:netherite_sword}) or a texture path ending in .png. */
    public AnnouncementBadge icon(String icon) {
        this.icon = icon == null ? "" : icon;
        return this;
    }

    /** Icon edge length in scaled pixels. */
    public AnnouncementBadge iconSize(int iconSize) {
        this.iconSize = iconSize;
        return this;
    }

    /** Accent colour as ARGB; 0 keeps the client default. */
    public AnnouncementBadge color(int argb) {
        this.color = argb;
        return this;
    }

    /** How long the badge stays fully open, in milliseconds. */
    public AnnouncementBadge hold(int holdMillis) {
        this.hold = holdMillis;
        return this;
    }

    /** Offset from the default screen anchor, in scaled pixels. */
    public AnnouncementBadge offset(float x, float y) {
        this.offsetX = x;
        this.offsetY = y;
        return this;
    }

    /** Overall size multiplier. */
    public AnnouncementBadge scale(float scale) {
        this.scale = scale;
        return this;
    }

    public String getTitle() { return title; }
    public String getSubtitle() { return subtitle; }
    public String getDescription() { return description; }
    public String getCaptionLabel() { return captionLabel; }
    public String getCaption() { return caption; }
    public String getIcon() { return icon; }
    public int getIconSize() { return iconSize; }
    public int getColor() { return color; }
    public int getHold() { return hold; }
    public float getOffsetX() { return offsetX; }
    public float getOffsetY() { return offsetY; }
    public float getScale() { return scale; }
}
