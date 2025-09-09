package gg.lode.lecternapi.event;

import gg.lode.bookshelfapi.api.event.BaseEvent;
import org.bukkit.entity.Player;

public class PlayerKeybindUpdateEvent extends BaseEvent {

    private final Player player;
    private final String key;
    private final int keyCode;

    public PlayerKeybindUpdateEvent(Player player, String key, int keyCode) {
        this.player = player;
        this.key = key;
        this.keyCode = keyCode;
    }

    public Player getPlayer() {
        return player;
    }

    public int getKeyCode() {
        return keyCode;
    }

    public String getKey() {
        return key;
    }

}
