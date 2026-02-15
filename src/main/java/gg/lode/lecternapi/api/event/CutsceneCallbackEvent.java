package gg.lode.lecternapi.api.event;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class CutsceneCallbackEvent extends LecternClientEvent {

    private static final HandlerList HANDLERS = new HandlerList();

    private final String cutsceneId;
    private final int callbackId;

    public CutsceneCallbackEvent(Player player, String cutsceneId, int callbackId) {
        super(player, "CUTSCENE_CALLBACK");
        this.cutsceneId = cutsceneId;
        this.callbackId = callbackId;
    }

    public String getCutsceneId() {
        return cutsceneId;
    }

    public int getCallbackId() {
        return callbackId;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
