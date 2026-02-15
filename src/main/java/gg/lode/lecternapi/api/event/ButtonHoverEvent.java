package gg.lode.lecternapi.api.event;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * Fired when a player hovers over or stops hovering a button on the Lectern client HUD.
 */
public class ButtonHoverEvent extends LecternClientEvent {

    private static final HandlerList HANDLERS = new HandlerList();

    private final String reference;
    private final boolean hovering;

    public ButtonHoverEvent(Player player, String reference, boolean hovering) {
        super(player, "BUTTON_HOVER");
        this.reference = reference;
        this.hovering = hovering;
    }

    public String getReference() {
        return reference;
    }

    public boolean isHovering() {
        return hovering;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
