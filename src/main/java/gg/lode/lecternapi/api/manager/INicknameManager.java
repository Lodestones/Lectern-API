package gg.lode.lecternapi.api.manager;

import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * Server-side API for client-rendered nicknames.
 * <p>
 * A nickname replaces every rendered instance of the target's real name on
 * the viewer's client — tab list, nametag, chat name slots, join/death
 * messages and command suggestions — while text typed by players (message
 * bodies, whispers) is left untouched. Optionally a scrambled UUID can be
 * supplied, which is what identity-lookup client mods on the viewer's side
 * will see instead of the target's real UUID.
 * <p>
 * Nicknames are per-viewer: only the viewers the packet is sent to see the
 * nick. Typed commands containing a nick are resolved back to the real name
 * server-side for the viewers the nick was sent to.
 */
public interface INicknameManager {

    /**
     * Shows the target to this viewer under the given nickname.
     */
    void setNickname(Player viewer, Player target, String nick);

    /**
     * Shows the target to this viewer under the given nickname and scrambles
     * the identity third-party client mods can read to the given fake UUID.
     *
     * @param fakeUuid the UUID exposed to the viewer's client mods, or null
     *                 to keep the real one
     */
    void setNickname(Player viewer, Player target, String nick, UUID fakeUuid);

    /**
     * Removes the target's nickname on this viewer's client.
     */
    void removeNickname(Player viewer, UUID targetUuid);

    /**
     * Clears every nickname on this viewer's client.
     */
    void clearNicknames(Player viewer);
}
