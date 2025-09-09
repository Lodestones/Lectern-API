package gg.lode.lecternapi.event;

import gg.lode.bookshelfapi.api.event.BaseEvent;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;

public class PlayerSendModList extends BaseEvent {
    private final List<String> modList;
    private final Player player;
    private final HashMap<String, String> modVersionMap;

    public PlayerSendModList(Player player, List<String> modList) {
        this.modList = modList;
        this.player = player;
        this.modVersionMap = new HashMap<>();
    }

    public PlayerSendModList(Player player, List<String> modList, HashMap<String, String> modVersionMap) {
        this.modList = modList;
        this.player = player;
        this.modVersionMap = modVersionMap;
    }

    public Player getPlayer() {
        return player;
    }

    public HashMap<String, String> getModVersionMap() {
        return modVersionMap;
    }

    public List<String> getModList() {
        return modList;
    }
}