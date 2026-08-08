package gg.lode.lecternapi.api.event;

import gg.lode.lecternapi.api.ui.LayoutAction;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * Fired when a player interacts with an element of a layout page they have open.
 * <p>
 * Fired before the page's own {@link LayoutAction}s run, and cancelling it stops them — which is
 * how a plugin gates a page's buttons on something the page itself cannot express, like a
 * permission or a cooldown, without having to strip the bindings out of the file.
 * <p>
 * Only fires for a page the server believes the player has open. A client reporting a click on a
 * page that is not showing is ignored rather than trusted.
 */
public class LayoutInteractEvent extends LecternClientEvent implements Cancellable {

    private static final HandlerList HANDLERS = new HandlerList();

    private final String pageId;
    private final String elementRef;
    private final LayoutAction.Trigger trigger;
    private boolean cancelled;

    public LayoutInteractEvent(Player player, String pageId, String elementRef,
                               LayoutAction.Trigger trigger) {
        super(player, "LAYOUT_INTERACT");
        this.pageId = pageId;
        this.elementRef = elementRef;
        this.trigger = trigger;
    }

    /** The page the interaction happened on. */
    public String getPageId() {
        return pageId;
    }

    /** The element's reference, or empty when the interaction is the page's own. */
    public String getElementRef() {
        return elementRef;
    }

    public LayoutAction.Trigger getTrigger() {
        return trigger;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
