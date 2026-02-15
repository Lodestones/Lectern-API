package gg.lode.lecternapi.api.menu;

import org.bukkit.entity.Player;

/**
 * Handles click and hover events for a button element in a {@link PacketMenu}.
 * <p>
 * Use the static factory methods for common patterns:
 * <pre>
 * // Click only
 * ButtonListener.onClick(origin -&gt; origin.menu().close());
 *
 * // Click and hover
 * ButtonListener.of(
 *     origin -&gt; player.sendMessage("Clicked!"),
 *     origin -&gt; player.sendMessage("Hovering!"),
 *     origin -&gt; player.sendMessage("Stopped hovering!")
 * );
 * </pre>
 */
public interface ButtonListener {

    /**
     * Context provided to listener callbacks.
     *
     * @param player the player interacting with the button
     * @param menu   the menu containing the button
     * @param reference the button's reference ID
     */
    record Origin(Player player, PacketMenu menu, String reference) {}

    /**
     * Called when the button is clicked.
     */
    default void onClick(Origin origin) {}

    /**
     * Called when the player starts hovering over the button.
     */
    default void onHoverStart(Origin origin) {}

    /**
     * Called when the player stops hovering over the button.
     */
    default void onHoverEnd(Origin origin) {}

    /**
     * Creates a listener that only handles clicks.
     */
    static ButtonListener onClick(java.util.function.Consumer<Origin> handler) {
        return new ButtonListener() {
            @Override
            public void onClick(Origin origin) {
                handler.accept(origin);
            }
        };
    }

    /**
     * Creates a listener with all three callbacks.
     */
    static ButtonListener of(
            java.util.function.Consumer<Origin> onClick,
            java.util.function.Consumer<Origin> onHoverStart,
            java.util.function.Consumer<Origin> onHoverEnd
    ) {
        return new ButtonListener() {
            @Override
            public void onClick(Origin origin) {
                onClick.accept(origin);
            }

            @Override
            public void onHoverStart(Origin origin) {
                onHoverStart.accept(origin);
            }

            @Override
            public void onHoverEnd(Origin origin) {
                onHoverEnd.accept(origin);
            }
        };
    }
}
