package gg.lode.lecternapi.api.event;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * Fired when a player clicks a button on a scrollable text screen.
 */
public class ScrollableScreenClickEvent extends LecternClientEvent {

    private static final HandlerList HANDLERS = new HandlerList();

    private final String screenId;
    private final String buttonReference;

    public ScrollableScreenClickEvent(Player player, String screenId, String buttonReference) {
        super(player, "SCROLLABLE_SCREEN_CLICK");
        this.screenId = screenId;
        this.buttonReference = buttonReference;
    }

    public String getScreenId() {
        return screenId;
    }

    public String getButtonReference() {
        return buttonReference;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
