package gg.lode.lecternapi.api.manager;

import gg.lode.lecternapi.api.ui.MapShape;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * Draws on a player's Lectern map.
 *
 * <p>The map itself is the client's own picture of the world, drawn from the chunks it has — there
 * is no image to render, host or ship, and nothing here sends one. What this sends is the part the
 * client cannot know: where the storm is, who is on the team, where the bus went. All of it in
 * world coordinates, so one update serves a corner minimap and a full-screen map alike.
 *
 * <p>Place the map itself with a {@code map} element in a layout, whose content is the map id used
 * here. Several elements may share an id, and they will all show the same shapes.
 *
 * <pre>{@code
 * // A storm that shrinks smoothly, updated once a second.
 * api.getMapManager().update(player, "royale", List.of(
 *         MapShape.circle("storm.current", cx, cz, radius).color(0x33FF00FF),
 *         MapShape.ring("storm.next", nx, nz, nextRadius).color(0xFFFF55FF).thickness(2f)),
 *     1000);
 * }</pre>
 */
public interface IMapManager {

    /**
     * Sends shapes for one map, replacing anything sharing a key and leaving the rest.
     *
     * @param durationMs how long the client should take moving each shape to its new position and
     *                   radius. Match it to how often you send: a storm sent every second with a
     *                   duration of 1000 shrinks continuously instead of stepping. Zero snaps,
     *                   which is what a marker appearing for the first time wants
     */
    void update(Player player, String mapId, List<MapShape> shapes, int durationMs);

    /** As {@link #update}, but drops any shape this update does not mention. */
    void replace(Player player, String mapId, List<MapShape> shapes, int durationMs);

    /** Removes one shape by key. */
    void remove(Player player, String mapId, String key);

    /** Removes every shape on a map. */
    void clear(Player player, String mapId);

    /** Removes every shape on every map, and forgets each map's focus. */
    void clearAll(Player player);

    /**
     * Points a map at a fixed place instead of at the player.
     *
     * <p>What a lobby preview or an end-of-match overview wants: everyone looking at the same
     * island at the same zoom, rather than each at their own feet.
     *
     * @param blocksPerPixel how much world one reference pixel covers. A 2000-block map in a
     *                       500-pixel panel is 4
     */
    void focus(Player player, String mapId, double centerX, double centerZ, double blocksPerPixel);

    /** Hands a map back to following the player, at whatever zoom the layout element sets. */
    void followPlayer(Player player, String mapId);
}
