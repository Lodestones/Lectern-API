package gg.lode.lecternapi.api.ui;

/**
 * A solid drawn on the HUD — rectangle, circle, triangle, star — filled or outlined, at any
 * opacity.
 * <p>
 * Shapes are ordinary HUD elements, so everything the HUD already does applies to them: they
 * group by reference prefix, fade and scale with their group, glide with a move, and layer
 * against text and textures. A panel behind a component is a rectangle at low opacity; a divider
 * is a thin one; a marker is a rotated triangle.
 *
 * <pre>{@code
 * api.getHUDManager().renderShape(player, new HudShape("panel:bg", Kind.RECTANGLE)
 *         .at(0, 0).size(320, 90)
 *         .color(0xFF101018).opacity(0.75f)
 *         .align(HorizontalAlignment.CENTER, VerticalAlignment.CENTER));
 * }</pre>
 */
public class HudShape {

    /** The solids the client can draw. Unknown names fall back to a rectangle. */
    public enum Kind {
        RECTANGLE, SQUARE, CIRCLE, ELLIPSE, TRIANGLE, RIGHT_TRIANGLE,
        DIAMOND, PENTAGON, HEXAGON, OCTAGON, STAR, ARROW, PARALLELOGRAM;

        public String wireName() {
            return name().toLowerCase(java.util.Locale.ROOT);
        }
    }

    private final String reference;
    private final Kind kind;

    private float x, y;
    private int layer = 0;
    private float width = 64, height = 64;
    private int color = 0xFFFFFFFF;
    private float opacity = 1f;
    private float thickness = 0f;
    private float rotation = 0f;
    private String horizontalAlignment = "left";
    private String verticalAlignment = "top";

    public HudShape(String reference, Kind kind) {
        this.reference = reference;
        this.kind = kind == null ? Kind.RECTANGLE : kind;
    }

    /** Position in the 1920x1080 reference space, relative to the alignment corner. */
    public HudShape at(float x, float y) {
        this.x = x;
        this.y = y;
        return this;
    }

    public HudShape size(float width, float height) {
        this.width = width;
        this.height = height;
        return this;
    }

    public HudShape layer(int layer) {
        this.layer = layer;
        return this;
    }

    /** ARGB fill or outline colour. */
    public HudShape color(int argb) {
        this.color = argb;
        return this;
    }

    /**
     * 0..1, multiplied into the colour's own alpha — so a shape can be dimmed without its colour
     * being rewritten, and an element, its group and its animation all compose.
     */
    public HudShape opacity(float opacity) {
        this.opacity = opacity;
        return this;
    }

    /** Outline thickness in reference pixels; 0 or less fills the shape. */
    public HudShape outline(float thickness) {
        this.thickness = thickness;
        return this;
    }

    /** Degrees clockwise about the shape's own centre. */
    public HudShape rotate(float degrees) {
        this.rotation = degrees;
        return this;
    }

    public HudShape align(String horizontal, String vertical) {
        this.horizontalAlignment = horizontal == null ? "left" : horizontal;
        this.verticalAlignment = vertical == null ? "top" : vertical;
        return this;
    }

    public String getReference() { return reference; }
    public Kind getKind() { return kind; }
    public float getX() { return x; }
    public float getY() { return y; }
    public int getLayer() { return layer; }
    public float getWidth() { return width; }
    public float getHeight() { return height; }
    public int getColor() { return color; }
    public float getOpacity() { return opacity; }
    public float getThickness() { return thickness; }
    public float getRotation() { return rotation; }
    public String getHorizontalAlignment() { return horizontalAlignment; }
    public String getVerticalAlignment() { return verticalAlignment; }
}
