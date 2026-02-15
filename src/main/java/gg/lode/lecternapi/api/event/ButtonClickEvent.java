package gg.lode.lecternapi.api.event;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * Fired when a player clicks a button on the Lectern client HUD.
 */
public class ButtonClickEvent extends LecternClientEvent {

    private static final HandlerList HANDLERS = new HandlerList();

    private final String reference;

    public ButtonClickEvent(Player player, String reference) {
        super(player, "BUTTON_CLICK");
        this.reference = reference;
    }

    public String getReference() {
        return reference;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
