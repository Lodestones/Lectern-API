package gg.lode.lecternapi.api.event;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

/**
 * Fired when the server finishes downloading all resource packs from a client.
 * Contains the list of file paths found inside those packs.
 */
public class ClientDownloadResourcePackEvent extends LecternClientEvent {

    private static final HandlerList HANDLERS = new HandlerList();

    private final List<String> files;

    public ClientDownloadResourcePackEvent(Player player, List<String> files) {
        super(player, "PACK_UPLOAD_COMPLETE");
        this.files = Collections.unmodifiableList(files);
    }

    /**
     * Returns the list of file paths found inside the client's uploaded resource packs.
     * Paths use forward slashes (e.g. {@code "assets/minecraft/textures/item/custom.png"}).
     */
    public List<String> getFiles() {
        return files;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
