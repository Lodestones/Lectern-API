package gg.lode.lecternapi.api.event;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Fired when the Lectern client reports its installed mods to the server.
 */
public class ClientModsReportEvent extends LecternClientEvent {

    private static final HandlerList HANDLERS = new HandlerList();

    private final List<ModInfo> mods;

    public ClientModsReportEvent(Player player, List<ModInfo> mods) {
        super(player, "CLIENT_MODS");
        this.mods = mods;
    }

    public List<ModInfo> getMods() {
        return mods;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    public record ModInfo(String modId, String authors, String version, String name) {
    }
}
