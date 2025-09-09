package gg.lode.lecternapi.event;

import gg.lode.bookshelfapi.api.event.BaseEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;

public class PlayerMuteVoiceChatEvent extends BaseEvent implements Cancellable {

    private final Player player;
    private final boolean isMuted;
    private boolean isCancelled;

    public PlayerMuteVoiceChatEvent(Player player, boolean isMuted) {
        this.player = player;
        this.isMuted = isMuted;
    }

    public boolean isMuted() {
        return isMuted;
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public boolean isCancelled() {
        return isCancelled;
    }

    @Override
    public void setCancelled(boolean b) {
        isCancelled = b;
    }

}
