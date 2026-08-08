package gg.lode.lecternapi.api.event;

import gg.lode.lecternapi.api.ui.LayoutPage;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * Fired before a layout page opens for a player.
 * <p>
 * Cancelling stops the page opening — the packet is never sent and the page's {@code OPEN} actions
 * do not run. This is the hook for gating a page that opens itself: a page set to open on join can
 * be held back for a player who has not finished a tutorial, without the page needing to know that
 * such a thing exists.
 */
public class LayoutOpenEvent extends Event implements Cancellable {

    private static final HandlerList HANDLERS = new HandlerList();

    private final Player player;
    private final LayoutPage page;
    private boolean cancelled;

    public LayoutOpenEvent(Player player, LayoutPage page) {
        this.player = player;
        this.page = page;
    }

    public Player getPlayer() {
        return player;
    }

    public LayoutPage getPage() {
        return page;
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
