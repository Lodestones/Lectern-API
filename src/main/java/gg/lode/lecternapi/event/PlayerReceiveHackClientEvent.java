package gg.lode.lecternapi.event;

import gg.lode.bookshelfapi.api.event.BaseEvent;
import org.bukkit.entity.Player;

public class PlayerReceiveHackClientEvent extends BaseEvent {

    private final String jarFile;
    private final Player player;

    public PlayerReceiveHackClientEvent(Player player, String jarFile) {
        this.jarFile = jarFile;
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public String getJarFile() {
        return jarFile;
    }

}