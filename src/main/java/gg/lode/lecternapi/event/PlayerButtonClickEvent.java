package gg.lode.lecternapi.event;

import gg.lode.bookshelfapi.api.event.BaseEvent;
import org.bukkit.entity.Player;

public class PlayerButtonClickEvent extends BaseEvent {
    private final String buttonId;
    private final Player player;

    public PlayerButtonClickEvent(Player player, String buttonId) {
        this.buttonId = buttonId;
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public String getButtonId() {
        return buttonId;
    }
}