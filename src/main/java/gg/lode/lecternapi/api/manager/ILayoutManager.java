package gg.lode.lecternapi.api.manager;

import gg.lode.lecternapi.api.ui.LayoutAction;
import gg.lode.lecternapi.api.ui.LayoutPage;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.Map;

/**
 * Pages authored in the Lectern UI editor: loading them, opening them, and running what their
 * elements are bound to.
 * <p>
 * This is the layer above {@link IHUDManager#playLayout}. That call plays a layout and stops — the
 * server owns every decision about when it appears and what it does. A page instead carries its own
 * rules, so dropping an exported file into {@code plugins/Lectern/layouts/} is enough to get a
 * command, an opening on join, and buttons that work, with no plugin written for it.
 * <p>
 * Pages are loaded from disk on startup and can be reloaded without a restart. A page opened for a
 * player is tracked, which is what lets a click be matched back to the page that was showing rather
 * than to whatever the client claims.
 *
 * <pre>{@code
 * ILayoutManager layouts = api.getLayoutManager();
 * layouts.open(player, "stats", Map.of("kills", "12"));
 * }</pre>
 */
public interface ILayoutManager {

    /** Every page currently loaded, keyed by id. */
    Collection<LayoutPage> getPages();

    /** The page with this id, or null if none is loaded. */
    LayoutPage getPage(String id);

    /**
     * Registers a page at runtime, replacing any already loaded under the same id.
     * <p>
     * For a plugin that ships its own pages rather than expecting a server owner to install files.
     * A page added this way is dropped on the next {@link #reload()}, which only rereads the folder.
     */
    void register(LayoutPage page);

    /**
     * Rereads {@code plugins/Lectern/layouts}, replacing what is loaded.
     * <p>
     * Commands are re-registered to match, and a page a player has open is left alone: reloading
     * mid-session changes what opens next, not what is already on screen.
     *
     * @return how many pages were loaded
     */
    int reload();

    /** Opens a page for a player, applying its behaviour. */
    void open(Player player, String id);

    /** Opens a page for a player, filling {@code %tokens%} in its text from {@code variables}. */
    void open(Player player, String id, Map<String, String> variables);

    /** Opens an already-resolved page, which need not be one loaded from disk. */
    void open(Player player, LayoutPage page, Map<String, String> variables);

    /** Closes a page for a player. Does nothing if they do not have it open. */
    void close(Player player, String id);

    /** Closes every page the player has open. */
    void closeAll(Player player);

    /** The ids of the pages this player currently has open. */
    Collection<String> getOpenPages(Player player);

    /** Whether this player has this page open. */
    boolean isOpen(Player player, String id);

    /**
     * Runs a trigger against a page the player has open, as though the client had reported it.
     * <p>
     * Exposed so a plugin can drive a page's own bindings — a server-side condition standing in for
     * a click — without duplicating what each action type means.
     *
     * @return how many actions ran
     */
    int fire(Player player, String pageId, String elementRef, LayoutAction.Trigger trigger);
}
