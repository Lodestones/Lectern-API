package gg.lode.lecternapi.event;

import gg.lode.bookshelfapi.api.event.BaseEvent;
import org.bukkit.entity.Player;

public class PlayerKeyPressEvent extends BaseEvent {
    private final String keyPressId;
    private final boolean isPressed;
    private final Player player;

    public PlayerKeyPressEvent(Player player, String keyPressId, boolean isPressed) {
        this.keyPressId = keyPressId;
        this.isPressed = isPressed;
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public boolean isPressed() {
        return isPressed;
    }

    public String getKeyPressId() {
        return keyPressId;
    }
}