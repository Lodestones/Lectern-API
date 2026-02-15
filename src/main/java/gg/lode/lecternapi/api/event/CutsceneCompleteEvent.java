package gg.lode.lecternapi.api.event;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class CutsceneCompleteEvent extends LecternClientEvent {

    private static final HandlerList HANDLERS = new HandlerList();

    private final String cutsceneId;

    public CutsceneCompleteEvent(Player player, String cutsceneId) {
        super(player, "CUTSCENE_COMPLETE");
        this.cutsceneId = cutsceneId;
    }

    public String getCutsceneId() {
        return cutsceneId;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
