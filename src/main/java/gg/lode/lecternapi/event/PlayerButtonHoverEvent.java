package gg.lode.lecternapi.event;

import gg.lode.bookshelfapi.api.event.BaseEvent;
import org.bukkit.entity.Player;

public class PlayerButtonHoverEvent extends BaseEvent {
    private final String buttonId;
    private final boolean isHovering;
    private final Player player;

    public PlayerButtonHoverEvent(Player player, String buttonId, boolean isHovering) {
        this.player = player;
        this.buttonId = buttonId;
        this.isHovering = isHovering;
    }

    public Player getPlayer() {
        return player;
    }

    public boolean isHovering() {
        return isHovering;
    }

    public String getButtonId() {
        return buttonId;
    }
}