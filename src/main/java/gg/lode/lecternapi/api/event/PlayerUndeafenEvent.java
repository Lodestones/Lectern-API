package gg.lode.lecternapi.api.event;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * Fired when a Lectern client reports that the player has undeafened their voice chat.
 */
public class PlayerUndeafenEvent extends LecternClientEvent {

    private static final HandlerList HANDLERS = new HandlerList();

    public PlayerUndeafenEvent(Player player) {
        super(player, "PLAYER_UNDEAFEN");
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
