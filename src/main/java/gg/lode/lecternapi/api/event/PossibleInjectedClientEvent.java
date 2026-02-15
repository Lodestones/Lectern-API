package gg.lode.lecternapi.api.event;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * Fired when the Lectern client detects a potentially malicious mod (hack client).
 * that was directly injected into the client.
 * <p>
 * This is still experimental, but scans through a list of verified hack clients.
 */
public class PossibleInjectedClientEvent extends LecternClientEvent {

    private static final HandlerList HANDLERS = new HandlerList();

    private final String jarFile;

    public PossibleInjectedClientEvent(Player player, String jarFile) {
        super(player, "BAD_MOD");
        this.jarFile = jarFile;
    }

    public String getJarFile() {
        return jarFile;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
