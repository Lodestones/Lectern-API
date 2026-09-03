package gg.lode.lecternapi.api.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Fired when a client reports its full keybind layout, once after each
 * handshake. Later in-session rebinds arrive as {@link KeybindChangeEvent}.
 * <p>
 * This is configuration only — which physical key each action is bound to —
 * and carries no press activity or timing.
 *
 * @deprecated The client no longer collects the connect-time keybind layout snapshot, so this event is never fired.
 *             Kept so existing listeners still compile.
 */
@Deprecated
public class ClientKeybindsReportEvent extends Event {

    private static final HandlerList HANDLERS = new HandlerList();

    /**
     * @param id       the keybind's translation id, e.g. {@code key.attack}
     * @param boundKey the bound key's translation key, e.g.
     *                 {@code key.mouse.left} or {@code key.keyboard.w};
     *                 {@code key.keyboard.unknown} when unbound
     */
    public record KeybindEntry(String id, String boundKey) {
    }

    private final Player player;
    private final List<KeybindEntry> keybinds;

    public ClientKeybindsReportEvent(Player player, List<KeybindEntry> keybinds) {
        this.player = player;
        this.keybinds = keybinds;
    }

    public Player getPlayer() {
        return player;
    }

    public List<KeybindEntry> getKeybinds() {
        return keybinds;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
