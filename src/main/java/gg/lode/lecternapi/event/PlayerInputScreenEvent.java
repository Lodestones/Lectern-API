package gg.lode.lecternapi.event;

import gg.lode.bookshelfapi.api.event.BaseEvent;
import org.bukkit.entity.Player;

public class PlayerInputScreenEvent extends BaseEvent {
    private final String inputScreenId;
    private final String input;
    private final Player player;

    public PlayerInputScreenEvent(Player player, String inputScreenId, String input) {
        this.inputScreenId = inputScreenId;
        this.player = player;
        this.input = input;
    }

    public Player getPlayer() {
        return player;
    }

    public String getInput() {
        return input;
    }

    public String getInputScreenId() {
        return inputScreenId;
    }
}