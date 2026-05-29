package gg.lode.lecternapi.api.manager;

import gg.lode.lecternapi.api.ui.*;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * Manages client-side UI elements: toasts, scrollable text screens, and config/settings screens.
 */
public interface IUIManager {

    // ── Toasts ────────────────────────────────────────────────

    /**
     * Displays a toast notification on the player's client.
     * This is fire-and-forget; no response is sent back.
     *
     * @param player the target player
     * @param toast  the toast to display
     */
    void showToast(Player player, Toast toast);

    /**
     * Displays a simple info toast with the given title and message.
     * Uses {@code title} as the toast id, which means resending will update
     * in place instead of re-popping.
     *
     * @param player  the target player
     * @param title   the toast title (also used as the toast id)
     * @param message the toast message
     */
    void showToast(Player player, String title, String message);

    /**
     * Removes an active toast by id. Triggers the pop-out animation if the
     * toast is currently live; no-op otherwise.
     *
     * @param player the target player
     * @param id     the id of the toast to remove
     */
    void removeToast(Player player, String id);

    // ── Scrollable Text Screen ────────────────────────────────

    /**
     * Displays a scrollable text screen with content sections and buttons.
     * Content can include headings, paragraphs, bullets, dividers, and subtext.
     * Button clicks send a {@link gg.lode.lecternapi.api.event.ScrollableScreenClickEvent}
     * back to the server with the screen ID and button reference.
     *
     * @param player   the target player
     * @param screenId a unique identifier for this screen instance
     * @param title    the screen title displayed at the top
     * @param content  ordered list of content sections
     * @param buttons  buttons displayed at the bottom of the screen
     */
    void showScrollableScreen(Player player, String screenId, String title,
                              List<ScreenContent> content, List<ScreenButton> buttons);

    /**
     * Closes the currently displayed scrollable screen on the player's client.
     *
     * @param player the target player
     */
    void closeScrollableScreen(Player player);

    // ── Config/Settings Screen ────────────────────────────────

    /**
     * Displays a config/settings screen with a left navigation panel and right content area.
     * Widget interactions send a {@link gg.lode.lecternapi.api.event.ConfigScreenChangeEvent}
     * back to the server. Screen closure sends a {@link gg.lode.lecternapi.api.event.ConfigScreenCloseEvent}.
     *
     * @param player         the target player
     * @param screenId       a unique identifier for this screen instance
     * @param title          the screen title
     * @param panelWidgets   widgets for the left navigation panel
     * @param contentWidgets widgets for the main content area
     */
    void showConfigScreen(Player player, String screenId, String title,
                          List<ConfigWidget> panelWidgets, List<ConfigWidget> contentWidgets);

    /**
     * Closes the currently displayed config screen on the player's client.
     *
     * @param player the target player
     */
    void closeConfigScreen(Player player);
}
