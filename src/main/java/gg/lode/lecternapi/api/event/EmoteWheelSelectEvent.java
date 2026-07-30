package gg.lode.lecternapi.api.event;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * Fired when a player picks a slot on the Lectern emote wheel. Exactly one of this event or
 * {@link EmoteWheelCancelEvent} fires per opening, so a plugin can rely on hearing back for
 * every wheel it opened.
 */
public class EmoteWheelSelectEvent extends LecternClientEvent {

    private static final HandlerList HANDLERS = new HandlerList();

    private final String slotId;

    public EmoteWheelSelectEvent(Player player, String slotId) {
        super(player, "EMOTE_WHEEL_SELECT");
        this.slotId = slotId;
    }

    /** The id of the chosen slot, exactly as the server defined it. */
    public String getSlotId() {
        return slotId;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
