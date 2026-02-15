package gg.lode.lecternapi.api.menu;

/**
 * Immutable positioning data for a menu element.
 * <p>
 * Use the fluent builder methods to create transforms:
 * <pre>
 * MenuTransform.at(10, 20).layer(5).alpha(0.8f).align(CENTER, TOP)
 * </pre>
 */
public record MenuTransform(
        float x, float y, int layer, float alpha,
        HorizontalAlignment horizontalAlignment,
        VerticalAlignment verticalAlignment
) {

    public static final MenuTransform DEFAULT = new MenuTransform(
            0, 0, 0, 1.0f, HorizontalAlignment.LEFT, VerticalAlignment.TOP);

    /**
     * Creates a transform at the given position with default layer, full opacity, and top-left alignment.
     */
    public static MenuTransform at(float x, float y) {
        return new MenuTransform(x, y, 0, 1.0f, HorizontalAlignment.LEFT, VerticalAlignment.TOP);
    }

    public MenuTransform layer(int layer) {
        return new MenuTransform(x, y, layer, alpha, horizontalAlignment, verticalAlignment);
    }

    public MenuTransform alpha(float alpha) {
        return new MenuTransform(x, y, layer, alpha, horizontalAlignment, verticalAlignment);
    }

    public MenuTransform align(HorizontalAlignment h, VerticalAlignment v) {
        return new MenuTransform(x, y, layer, alpha, h, v);
    }

    /**
     * Shortcut for centering both axes.
     */
    public MenuTransform centered() {
        return align(HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
    }
}
