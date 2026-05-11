package gg.lode.lecternapi.api.ui;

/**
 * Represents a toast notification to display on the player's client.
 *
 * @param title         the toast title
 * @param message       the toast message body
 * @param type          the toast type (determines color scheme)
 * @param icon          item identifier (e.g. "minecraft:book") or texture path
 * @param displayTimeMs display duration in milliseconds (0 = default ~3000ms)
 * @param customColor   ARGB color int, only used when type is {@link ToastType#CUSTOM}
 */
public record Toast(String title, String message, ToastType type, String icon, int displayTimeMs, int customColor) {

    public enum ToastType {
        INFO(0), WARN(1), ERROR(2), DEBUG(3), FLAT(4), CUSTOM(5);

        private final int id;

        ToastType(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }
    }

    /**
     * Creates an info toast with default display time.
     */
    public static Toast info(String title, String message, String icon) {
        return new Toast(title, message, ToastType.INFO, icon, 0, 0);
    }

    /**
     * Creates a warning toast with default display time.
     */
    public static Toast warn(String title, String message, String icon) {
        return new Toast(title, message, ToastType.WARN, icon, 0, 0);
    }

    /**
     * Creates an error toast with default display time.
     */
    public static Toast error(String title, String message, String icon) {
        return new Toast(title, message, ToastType.ERROR, icon, 0, 0);
    }

    /**
     * Creates a debug toast with default display time.
     */
    public static Toast debug(String title, String message, String icon) {
        return new Toast(title, message, ToastType.DEBUG, icon, 0, 0);
    }

    /**
     * Creates a toast with a custom ARGB color.
     *
     * @param customColor packed ARGB color (e.g. 0xFF9B59B6 for purple)
     */
    public static Toast custom(String title, String message, String icon, int customColor) {
        return new Toast(title, message, ToastType.CUSTOM, icon, 0, customColor);
    }
}
