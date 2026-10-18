package gg.lode.lecternapi.api.manager;

import org.bukkit.entity.Player;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * Server-side API for the shared control ("puppet") system.
 * <p>
 * One host player has up to seven aspects of their input driven by other
 * players. The server only validates and relays; the host's client injects
 * the inputs locally, so gameplay traffic stays ordinary vanilla packets.
 * A controller may drive multiple aspects of the same host at once.
 */
public interface ISharedControlManager {

    /**
     * An independently delegable slice of the host's input.
     * {@link #ATTACK} is "punch" (left click); {@link #USE} is "place"
     * (right click).
     */
    enum Aspect {MOVE, JUMP, SNEAK, LOOK, ATTACK, USE, CRAFT}

    /**
     * Starts a shared control session for the host. No aspects are assigned
     * yet — use {@link #assign} for each controller.
     *
     * @param host the player whose input will be shared
     * @return true if a session was started, false if one already exists or
     * the host is currently controlling someone else
     */
    boolean start(Player host);

    /**
     * Assigns an aspect of the host to a controller. Assignments are
     * additive: a controller may hold several aspects of the same host.
     * Re-assigning an aspect replaces its previous controller.
     *
     * @return true if the aspect was assigned
     */
    boolean assign(Player host, Aspect aspect, Player controller);

    /**
     * Removes the controller of an aspect, returning it to the host.
     */
    boolean unassign(Player host, Aspect aspect);

    /**
     * Ends the session for a host, releasing all controllers.
     */
    boolean stop(UUID hostId);

    /**
     * Ends every active session.
     */
    void stopAll();

    /**
     * Whether the player currently hosts a session.
     */
    boolean isHost(UUID playerId);

    /**
     * Whether the player currently controls any aspect of any host.
     */
    boolean isController(UUID playerId);

    /**
     * The aspects each controller holds for the given host, or an empty map
     * if the host has no session.
     */
    Map<UUID, Set<Aspect>> getAssignments(UUID hostId);
}
