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
    private boolean persistent = false;
    private int plateColor = 0;
    private String alignment = "center";
    private boolean subBadge = false;
    private String id = "default";
    private int sweepIn = 420;
    private int sweepOut = 320;
    private int contentFade = 200;

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
    /**
     * @deprecated The client no longer draws a caption — the icon moved out of a left-hand
     *             column and onto a plate above the banner, and the caption went with the
     *             column. Both strings still travel so the fields after them stay aligned, but
     *             nothing renders them.
     */
    @Deprecated
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

    /**
     * Keeps the badge up until something takes it down, rather than closing after the hold.
     * <p>
     * For a badge that states an ongoing condition — a held objective, an active buff, a role —
     * where a banner that vanished after a few seconds would have to be re-sent to stay on
     * screen. {@link #hold(int)} is ignored while this is set. Take it down with
     * {@code hideBadge(player)}, or by showing a non-persistent badge in its place.
     */
    public AnnouncementBadge persistent(boolean persistent) {
        this.persistent = persistent;
        return this;
    }

    /** How long the badge stays fully open, in milliseconds. Ignored when {@link #persistent(boolean)}. */
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

    /**
     * ARGB for the plate the icon sits on, above the banner's top edge.
     * <p>
     * Its own colour rather than the brackets', so a badge can carry a neutral frame with a
     * coloured plate, or the reverse. Left at 0 it follows {@link #color(int)}.
     */
    public AnnouncementBadge plateColor(int argb) {
        this.plateColor = argb;
        return this;
    }

    /**
     * The slot this badge occupies.
     * <p>
     * Badges with different ids stack on screen together; showing one whose id is already up
     * updates that badge in place, keeping its position in the stack rather than replaying its
     * intro. Unset, every badge shares one slot and each replaces the last.
     */
    public AnnouncementBadge id(String id) {
        this.id = id == null || id.isEmpty() ? "default" : id;
        return this;
    }

    /**
     * Draws the badge as a sub-badge: a slimmer hatched bar instead of the full banner.
     * <p>
     * For the secondary line under an announcement — a category, a rarity, a qualifier — where
     * a second full banner would compete with the first. Same text layout, same timings, same
     * optional icon; only the shape is quieter.
     */
    public AnnouncementBadge subBadge(boolean subBadge) {
        this.subBadge = subBadge;
        return this;
    }

    /**
     * Which way the banner faces: {@code center}, {@code left} or {@code right}.
     * <p>
     * The side alignments use different artwork — one flat end to sit flush against the screen
     * edge, the flourish on the inner end only — and are pinned to that edge rather than
     * centred, so they hold their place at any resolution. Their text reads from the same edge,
     * and the banner unrolls away from it instead of parting from the middle.
     */
    public AnnouncementBadge align(String alignment) {
        this.alignment = alignment == null ? "center" : alignment;
        return this;
    }

    /**
     * How long the banner takes to unroll, in milliseconds. Zero opens it already open.
     * <p>
     * The hold is counted from the end of this, so a longer sweep lengthens the badge's life
     * rather than eating into the time it is readable.
     */
    public AnnouncementBadge sweepIn(int millis) {
        this.sweepIn = Math.max(0, millis);
        return this;
    }

    /** How long the closing sweep takes. Zero cuts it rather than playing an outro. */
    public AnnouncementBadge sweepOut(int millis) {
        this.sweepOut = Math.max(0, millis);
        return this;
    }

    /** How long the text takes to fade in once the banner has opened. Zero snaps it on. */
    public AnnouncementBadge contentFade(int millis) {
        this.contentFade = Math.max(0, millis);
        return this;
    }

    /** All three animation timings at once. */
    public AnnouncementBadge timings(int sweepIn, int sweepOut, int contentFade) {
        return sweepIn(sweepIn).sweepOut(sweepOut).contentFade(contentFade);
    }

    public String getTitle() { return title; }
    public String getSubtitle() { return subtitle; }
    public String getDescription() { return description; }
    public String getCaptionLabel() { return captionLabel; }
    public String getCaption() { return caption; }
    public int getPlateColor() { return plateColor; }
    public String getAlignment() { return alignment; }
    public int getSweepIn() { return sweepIn; }
    public int getSweepOut() { return sweepOut; }
    public int getContentFade() { return contentFade; }
    public boolean isSubBadge() { return subBadge; }
    public String getId() { return id; }
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
    public boolean isPersistent() { return persistent; }
}
