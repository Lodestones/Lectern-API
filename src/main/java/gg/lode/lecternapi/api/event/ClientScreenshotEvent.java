package gg.lode.lecternapi.api.event;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

import java.io.File;

/**
 * Fired when a screenshot has been received from a client
 * after a server-initiated screenshot request.
 *
 * @deprecated The client no longer collects client screenshots, so this event is never fired.
 *             Kept so existing listeners still compile.
 */
@Deprecated
public class ClientScreenshotEvent extends LecternClientEvent {

    private static final HandlerList HANDLERS = new HandlerList();

    private final String fileName;
    private final File file;

    public ClientScreenshotEvent(Player player, String fileName, File file) {
        super(player, "SCREENSHOT_DATA_END");
        this.fileName = fileName;
        this.file = file;
    }

    public String getFileName() {
        return fileName;
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
