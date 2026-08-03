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
 *         .background(0.05f)
 *         .backgroundBlur(20f)
 *         .glow(0xFFD9A33C, 0.35f)
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
    private float backgroundOpacity = 0;
    private int backgroundColor = 0;
    private float backgroundBlur = 0;
    private int glowColor = 0;
    private float glowIntensity = 0;

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

    /**
     * A backdrop panel behind the banner, echoing its shape a little larger.
     *
     * @param opacity 0 for none, 1 for solid. Off by default.
     */
    public AnnouncementBadge background(float opacity) {
        this.backgroundOpacity = opacity;
        return this;
    }

    /** The backdrop's colour as RGB. Black unless set; alpha comes from {@link #background(float)}. */
    public AnnouncementBadge backgroundColor(int rgb) {
        this.backgroundColor = rgb;
        return this;
    }

    /**
     * Softens the backdrop's edge, in pixels of falloff.
     * <p>
     * This is a feathered edge, <b>not</b> a frosted-glass blur of the scene behind — the only
     * available blur pass is screen-wide and would blur the whole game rather than the strip
     * behind the banner.
     *
     * @param pixels 0 for a hard edge; larger values fade out further
     */
    public AnnouncementBadge backgroundBlur(float pixels) {
        this.backgroundBlur = pixels;
        return this;
    }

    /**
     * A coloured halo behind the trapezoid, echoing its outline outward.
     *
     * @param argb      the glow's colour; 0 falls back to the badge's accent colour
     * @param intensity 0 for none; 1 is a strong halo. Off by default.
     */
    public AnnouncementBadge glow(int argb, float intensity) {
        this.glowColor = argb;
        this.glowIntensity = intensity;
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
    public float getBackgroundOpacity() { return backgroundOpacity; }
    public int getBackgroundColor() { return backgroundColor; }
    public float getBackgroundBlur() { return backgroundBlur; }
    public int getGlowColor() { return glowColor; }
    public float getGlowIntensity() { return glowIntensity; }
}
