package gg.lode.lecternapi.api.event;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Fired when a player joins and the HWID system detects potential alt accounts
 * sharing the same hardware fingerprint.
 * <p>
 * Third-party plugins can listen to this event to implement custom alt-detection
 * logic (e.g., auto-mute, flag for review, restrict permissions).
 */
public class HWIDAltDetectedEvent extends LecternClientEvent {

    private static final HandlerList HANDLERS = new HandlerList();

    private final List<AltAccount> alts;

    public HWIDAltDetectedEvent(Player player, List<AltAccount> alts) {
        super(player, "HWID_ALT_DETECTED");
        this.alts = alts;
    }

    /**
     * Returns the list of detected alt accounts for this player.
     */
    public List<AltAccount> getAlts() {
        return alts;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    /**
     * Represents a detected alt account.
     *
     * @param uuid               The alt account's UUID
     * @param username           The alt account's last known username
     * @param matchingComponents Number of matching hardware components (out of 6)
     */
    public record AltAccount(String uuid, String username, int matchingComponents) {
    }
}
