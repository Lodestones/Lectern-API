package gg.lode.lecternapi.api.event;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * Fired when a player dismisses the Lectern emote wheel without choosing — Escape, or
 * opening another screen over it. The counterpart of {@link EmoteWheelSelectEvent}.
 */
public class EmoteWheelCancelEvent extends LecternClientEvent {

    private static final HandlerList HANDLERS = new HandlerList();

    public EmoteWheelCancelEvent(Player player) {
        super(player, "EMOTE_WHEEL_CANCEL");
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
