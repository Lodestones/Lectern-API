package gg.lode.lecternapi.api.event;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * Fired when a player presses or releases a keybind on the Lectern client.
 */
public class KeybindPressedEvent extends LecternClientEvent {

    private static final HandlerList HANDLERS = new HandlerList();

    private final String key;
    private final boolean pressed;

    public KeybindPressedEvent(Player player, String key, boolean pressed) {
        super(player, "KEYBIND_PRESSED");
        this.key = key;
        this.pressed = pressed;
    }

    public String getKey() {
        return key;
    }

    public boolean isPressed() {
        return pressed;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
