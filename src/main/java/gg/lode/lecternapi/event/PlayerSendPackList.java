package gg.lode.lecternapi.event;

import gg.lode.bookshelfapi.api.event.BaseEvent;
import org.bukkit.entity.Player;

import java.util.List;

public class PlayerSendPackList extends BaseEvent {
    private final List<String> packList;
    private final Player player;

    public PlayerSendPackList(Player player, List<String> packList) {
        this.player = player;
        this.packList = packList;
    }

    public Player getPlayer() {
        return player;
    }

    public List<String> getPackList() {
        return packList;
    }
}