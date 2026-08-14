package gg.lode.lecternapi.api.ui;

import org.bukkit.Location;

/**
 * A HUD marker pinned to a point in the world, tracking it as the player looks around.
 * <p>
 * The client draws the marker itself — a pin while the target is in view, an arrow pinned to the
 * screen edge along the bearing to it when it is not, and an optional pair of labels that appear
 * only while the player is actually looking at it. That last part is why the marker is described
 * here rather than assembled from HUD elements: it reacts to where the crosshair is, which
 * changes every frame and no packet could keep up with.
 * <p>
 * The two group prefixes are optional and independent of the marker. Naming them lets a server
 * compose its own elements for the on-screen and off-screen states, which are then positioned by
 * the anchor; leaving them empty gives just the marker.
 *
 * <pre>{@code
 * api.getHUDManager().setAnchor(player, new WorldAnchor("objective", location)
 *         .icon("lectern:textures/gui/anchor_pin.png", 11f)   // or .item("minecraft:iron_ingot")
 *         .arrowColor(0xFFFFC83C)
 *         .labels("<white>Objective", "<gray>%distance%m")
 *         .maxDistance(200f));
 * }</pre>
 */
public class WorldAnchor {

    private final String id;
    private final Location location;

    private String visibleGroup = "";
    private String offscreenGroup = "";
    private float edgeMargin = 10f;
    private float maxDistance = 0f;

    private String icon = "";
    private float iconSize = 11f;
    private int iconColor = 0xFFFFFFFF;
    private int arrowColor = 0xFFFFFFFF;
    private String topText = "";
    private String bottomText = "";
    private String item = "";
    private String itemModel = "";

    /**
     * @param id       the anchor's key; setting another with the same id replaces it, which is
     *                 how a marker on a moving target is updated
     * @param location the world point being tracked
     */
    public WorldAnchor(String id, Location location) {
        this.id = id;
        this.location = location;
    }

    /**
     * Reference prefixes for server-composed groups: one drawn while the point is on screen, the
     * other while it is not. Empty for a marker with no groups of its own.
     */
    public WorldAnchor groups(String visibleGroup, String offscreenGroup) {
        this.visibleGroup = visibleGroup == null ? "" : visibleGroup;
        this.offscreenGroup = offscreenGroup == null ? "" : offscreenGroup;
        return this;
    }

    /** How far in from the screen edge an off-screen marker sits, in scaled GUI pixels. */
    public WorldAnchor edgeMargin(float pixels) {
        this.edgeMargin = pixels;
        return this;
    }

    /** Hide the marker past this many blocks; 0 for no limit. */
    public WorldAnchor maxDistance(float blocks) {
        this.maxDistance = blocks;
        return this;
    }

    /** The texture drawn while the target is on screen, and its size in scaled GUI pixels. */
    public WorldAnchor icon(String icon, float size) {
        this.icon = icon == null ? "" : icon;
        this.iconSize = size;
        return this;
    }

    /**
     * Draw an item at the marker instead of a texture — the client renders the stack itself, the
     * way an inventory slot does.
     * <p>
     * Marking a dropped sword is the case this exists for: a texture path has to be guessed from
     * the item and the guess is wrong wherever a pack's art doesn't sit where the name implies,
     * while an item id is something the client can look up and always render. Set when the marker
     * stands for an item; leave it and use {@link #icon(String, float)} for anything else.
     *
     * @param itemId the registry id, e.g. {@code minecraft:iron_ingot}
     */
    public WorldAnchor item(String itemId) {
        return item(itemId, "");
    }

    /**
     * As above, for an item a resource pack re-skins.
     *
     * @param itemId    the registry id the client looks up
     * @param itemModel the {@code item_model} the stack carries, e.g. {@code lodestone:time_sword};
     *                  empty to draw the item's own art. Applied client-side, so the pack decides
     *                  what it looks like exactly as it would in a hand or a slot
     */
    public WorldAnchor item(String itemId, String itemModel) {
        this.item = itemId == null ? "" : itemId;
        this.itemModel = itemModel == null ? "" : itemModel;
        return this;
    }

    /** ARGB tint for the icon. */
    public WorldAnchor iconColor(int argb) {
        this.iconColor = argb;
        return this;
    }

    /** ARGB tint for the off-screen arrow. */
    public WorldAnchor arrowColor(int argb) {
        this.arrowColor = argb;
        return this;
    }

    /**
     * Lines shown above and below the icon while the player looks at the marker, animating in
     * and out as their attention moves. Either may be empty.
     * <p>
     * Styled like any other Lectern text, and {@code %distance%} expands to the whole number of
     * blocks to the target — a value that changes as the player walks, so the client fills it in
     * rather than the server re-sending the anchor.
     */
    public WorldAnchor labels(String top, String bottom) {
        this.topText = top == null ? "" : top;
        this.bottomText = bottom == null ? "" : bottom;
        return this;
    }

    public String getId() { return id; }
    public Location getLocation() { return location; }
    public String getVisibleGroup() { return visibleGroup; }
    public String getOffscreenGroup() { return offscreenGroup; }
    public float getEdgeMargin() { return edgeMargin; }
    public float getMaxDistance() { return maxDistance; }
    public String getIcon() { return icon; }
    public float getIconSize() { return iconSize; }
    public int getIconColor() { return iconColor; }
    public int getArrowColor() { return arrowColor; }
    public String getTopText() { return topText; }
    public String getBottomText() { return bottomText; }
    public String getItem() { return item; }
    public String getItemModel() { return itemModel; }
}
