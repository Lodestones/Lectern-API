package gg.lode.lecternapi.event;

import gg.lode.bookshelfapi.api.event.BaseEvent;
import org.bukkit.entity.Player;

public class PlayerConnectedToLecternEvent extends BaseEvent {

    private final Player player;

    public PlayerConnectedToLecternEvent(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
}
