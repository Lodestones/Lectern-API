package gg.lode.lecternapi.api.event;

import gg.lode.lecternapi.api.ui.LayoutPage;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * Fired after a layout page closes for a player.
 * <p>
 * Not cancellable: by the time it fires the page is already gone, and a close that could be refused
 * would leave the server and the client disagreeing about what is on screen. Use
 * {@link LayoutOpenEvent} to stop a page appearing in the first place.
 */
public class LayoutCloseEvent extends Event {

    private static final HandlerList HANDLERS = new HandlerList();

    /** Why the page closed. */
    public enum Reason {
        /** A plugin or a command closed it. */
        REQUESTED,
        /** Its {@code closeOnDeath} rule fired. */
        DEATH,
        /** Its {@code closeOnDamage} rule fired. */
        DAMAGE,
        /** Another page opened and this one was not marked keep-open. */
        REPLACED,
        /** The player disconnected. */
        QUIT
    }

    private final Player player;
    private final LayoutPage page;
    private final Reason reason;

    public LayoutCloseEvent(Player player, LayoutPage page, Reason reason) {
        this.player = player;
        this.page = page;
        this.reason = reason;
    }

    public Player getPlayer() {
        return player;
    }

    public LayoutPage getPage() {
        return page;
    }

    public Reason getReason() {
        return reason;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
