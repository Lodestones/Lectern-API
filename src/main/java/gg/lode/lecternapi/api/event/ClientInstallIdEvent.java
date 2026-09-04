package gg.lode.lecternapi.api.event;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * Fired when a client reports the id of its Lectern installation, in answer to
 * {@link gg.lode.lecternapi.api.manager.IScreenManager#requestClientReport(Player)}.
 *
 * <p>The id is a random number the client generated on first run and keeps in its config folder.
 * It is <strong>not</strong> derived from the player's hardware — no serials, no MAC address,
 * nothing read off the machine — and the player can see the file and delete it. Treat it as a
 * cookie, because that is what it is.
 *
 * <h2>Reading it correctly</h2>
 *
 * <p>The value is only meaningful in one direction. Two accounts reporting the <em>same</em> id
 * share an installation, which usually means one person. Two accounts reporting <em>different</em>
 * ids tell you nothing at all: a reinstall, a wiped config, a second launcher profile, a new
 * computer and a deliberate deletion are indistinguishable from here, and one player with several
 * Prism or MultiMC instances legitimately reports several ids.
 *
 * <p>So a match is worth acting on and a mismatch is not evidence. Anyone deliberately evading will
 * clear the file — that is expected, and the point is the friction, not the certainty. Score this
 * alongside your other signals rather than punishing on it alone.
 *
 * <p>If you keep it, keep it the way you would keep any identifier tied to a person: say so in your
 * rules, and do not hold it longer than the moderation question it answers.
 */
public class ClientInstallIdEvent extends LecternClientEvent {

    private static final HandlerList HANDLERS = new HandlerList();

    private final String installId;

    public ClientInstallIdEvent(Player player, String installId) {
        super(player, "CLIENT_INSTALL");
        this.installId = installId;
    }

    /** The reporting installation's id. Never null, never blank. */
    public String getInstallId() {
        return installId;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
