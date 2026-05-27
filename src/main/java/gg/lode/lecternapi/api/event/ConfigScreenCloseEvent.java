package gg.lode.lecternapi.api.event;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * Fired when a player closes a config screen.
 */
public class ConfigScreenCloseEvent extends LecternClientEvent {

    private static final HandlerList HANDLERS = new HandlerList();

    private final String screenId;

    public ConfigScreenCloseEvent(Player player, String screenId) {
        super(player, "CONFIG_SCREEN_CLOSE");
        this.screenId = screenId;
    }

    public String getScreenId() {
        return screenId;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
