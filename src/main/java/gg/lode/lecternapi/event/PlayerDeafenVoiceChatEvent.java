package gg.lode.lecternapi.event;

import gg.lode.bookshelfapi.api.event.BaseEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;

public class PlayerDeafenVoiceChatEvent extends BaseEvent implements Cancellable {

    private final Player player;
    private final boolean isDeafened;
    private boolean isCancelled;

    public PlayerDeafenVoiceChatEvent(Player player, boolean isDeafened) {
        this.player = player;
        this.isDeafened = isDeafened;
    }

    public boolean isDeafened() {
        return isDeafened;
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
