package gg.lode.lecternapi.api.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * Base event for all C2S (client-to-server) Lectern events.
 */
public class LecternClientEvent extends Event {

    private static final HandlerList HANDLERS = new HandlerList();

    private final Player player;
    private final String eventId;

    public LecternClientEvent(Player player, String eventId) {
        this.player = player;
        this.eventId = eventId;
    }

    public Player getPlayer() {
        return player;
    }

    public String getEventId() {
        return eventId;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
