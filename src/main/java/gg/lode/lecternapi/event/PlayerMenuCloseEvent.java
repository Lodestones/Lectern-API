package gg.lode.lecternapi.event;

import gg.lode.bookshelfapi.api.event.BaseEvent;
import org.bukkit.entity.Player;

public class PlayerMenuCloseEvent extends BaseEvent {
    private final Player player;

    public PlayerMenuCloseEvent(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
}