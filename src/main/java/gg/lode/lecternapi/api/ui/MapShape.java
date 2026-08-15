package gg.lode.lecternapi.api.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * One thing drawn on a Lectern map, described in world coordinates.
 *
 * <p>World coordinates, not screen ones. The server knows where the storm is; it has no idea how
 * big the player's map is, whether it is a corner minimap or the whole screen, or how far they have
 * zoomed it. Sending facts about the world and letting the client project them is what lets one
 * update drive a minimap and a full-screen map at once.
 *
 * <p>Every shape carries a {@code key}. An update replaces the shape with the same key rather than
 * adding another beside it — the storm is one circle whose radius changes, not a new circle every
 * second — and that identity is also what lets the client interpolate between updates. Send the
 * storm once a second with a matching duration and it shrinks smoothly on screen.
 *
 * <pre>{@code
 * api.getMapManager().update(player, "royale", List.of(
 *         MapShape.circle("storm.current", x, z, radius).color(0x33FF00FF),
 *         MapShape.ring("storm.next", nextX, nextZ, nextRadius).color(0xFFFFFFFF).thickness(2f)),
 *     1000);
 * }</pre>
 */
public class MapShape {

    public enum Kind {
        /** A disc, filled unless a thickness is set. */
        CIRCLE,
        /** An outline, whatever the thickness. */
        RING,
        /** A polyline through its points. */
        LINE,
        /** An icon, optionally rotated and labelled. */
        MARKER;

        public String getId() {
            return name().toLowerCase(Locale.ROOT);
        }
    }

    private final String key;
    private final Kind kind;
    private double x;
    private double z;
    private double radius;
    private int color = 0xFFFFFFFF;
    private float thickness;
    private String icon = "";
    private float rotation;
    private String label = "";
    private final List<double[]> points = new ArrayList<>();

    private MapShape(String key, Kind kind) {
        this.key = key == null ? "" : key;
        this.kind = kind == null ? Kind.MARKER : kind;
    }

    /** A filled circle at a world position, with a world-space radius — the storm's current edge. */
    public static MapShape circle(String key, double x, double z, double radius) {
        MapShape shape = new MapShape(key, Kind.CIRCLE);
        shape.x = x;
        shape.z = z;
        shape.radius = radius;
        return shape;
    }

    /** An outlined circle — where the storm is going next. */
    public static MapShape ring(String key, double x, double z, double radius) {
        MapShape shape = new MapShape(key, Kind.RING);
        shape.x = x;
        shape.z = z;
        shape.radius = radius;
        shape.thickness = 1.5f;
        return shape;
    }

    /** A marker at a world position. Give it an icon, or leave it as a coloured diamond. */
    public static MapShape marker(String key, double x, double z) {
        MapShape shape = new MapShape(key, Kind.MARKER);
        shape.x = x;
        shape.z = z;
        return shape;
    }

    /** A polyline — a bus route, a border, a path. */
    public static MapShape line(String key) {
        return new MapShape(key, Kind.LINE);
    }

    public MapShape point(double x, double z) {
        points.add(new double[]{x, z});
        return this;
    }

    /** ARGB. Alpha is honoured, so a storm can be a wash rather than a wall. */
    public MapShape color(int argb) {
        this.color = argb;
        return this;
    }

    /** Outline width in screen pixels; 0 fills a circle. */
    public MapShape thickness(float thickness) {
        this.thickness = Math.max(0f, thickness);
        return this;
    }

    /** Texture id for a marker, e.g. {@code lodestone:textures/royale/minimap/player_marker.png}. */
    public MapShape icon(String icon) {
        this.icon = icon == null ? "" : icon;
        return this;
    }

    /**
     * Degrees clockwise, for a marker that points somewhere — a player's facing.
     * <p>
     * Interpolated the short way round between updates, so a player crossing north does not spin
     * the marker the long way to get there.
     */
    public MapShape rotation(float degrees) {
        this.rotation = degrees;
        return this;
    }

    /** Text drawn under a marker. */
    public MapShape label(String label) {
        this.label = label == null ? "" : label;
        return this;
    }

    /**
     * A marker's drawn size, as a world-space radius.
     * <p>
     * Left at zero a marker keeps a fixed size on screen, which is what a player dot wants — it
     * should stay findable however far the map is zoomed out. Set it and the marker scales with the
     * world instead, for something that has a real size, like a capture zone.
     */
    public MapShape size(double worldRadius) {
        this.radius = Math.max(0, worldRadius);
        return this;
    }

    public String getKey() { return key; }
    public Kind getKind() { return kind; }
    public double getX() { return x; }
    public double getZ() { return z; }
    public double getRadius() { return radius; }
    public int getColor() { return color; }
    public float getThickness() { return thickness; }
    public String getIcon() { return icon; }
    public float getRotation() { return rotation; }
    public String getLabel() { return label; }
    public List<double[]> getPoints() { return List.copyOf(points); }
}
