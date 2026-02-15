package gg.lode.lecternapi.api.event;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

import java.io.File;

/**
 * Fired when a complete resource pack has been received from a client
 * after a server-initiated resource pack download request.
 */
public class ClientPackDataEvent extends LecternClientEvent {

    private static final HandlerList HANDLERS = new HandlerList();

    private final String packName;
    private final File file;

    public ClientPackDataEvent(Player player, String packName, File file) {
        super(player, "PACK_DATA_END");
        this.packName = packName;
        this.file = file;
    }

    public String getPackName() {
        return packName;
    }

    public File getFile() {
        return file;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
