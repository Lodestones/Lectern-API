package gg.lode.lecternapi.api.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * Called when a player's Lectern mod client successfully completes the handshake.
 * After this event fires, the player can receive Lectern effect packets.
 */
public class LecternHandshakeEvent extends Event {
    private static final HandlerList HANDLERS = new HandlerList();

    private final Player player;
    private final String modId;

    public LecternHandshakeEvent(Player player, String modId) {
        this.player = player;
        this.modId = modId;
    }

    /**
     * Gets the player who completed the Lectern handshake.
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Gets the mod identifier reported by the client.
     */
    public String getModId() {
        return modId;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
