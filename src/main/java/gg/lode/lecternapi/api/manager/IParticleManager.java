package gg.lode.lecternapi.api.manager;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

/**
 * Spawns Lectern's custom client-registered particles on a player's client, using
 * the same semantics as the vanilla {@code /particle} command. This lets a server
 * spawn particles that are only registered in the Lectern Fabric client's registry
 * (e.g. {@code chronite_spark}) — which the vanilla server-side {@code /particle}
 * command cannot do, since the server has never heard of them.
 * <p>
 * The particle key is the path of a particle registered under the {@code lectern}
 * namespace on the client. Currently available keys:
 * <ul>
 *     <li>{@code chronite_spark} — small glowing amber spark</li>
 *     <li>{@code explosion_spark} — debris spark fragment</li>
 *     <li>{@code explosion_smoke} — slow smoke puff</li>
 *     <li>{@code explosion_shockwave_inner} — inner ground shockwave ring</li>
 *     <li>{@code explosion_shockwave_outer} — outer ground shockwave ring</li>
 * </ul>
 * Visual only — no entity, block, or world state is affected.
 */
public interface IParticleManager {

    /**
     * Spawns a Lectern particle on the player's client, mirroring vanilla
     * {@code /particle} semantics.
     *
     * @param player   the player whose client will render the particle
     * @param particle the client particle key (path under the {@code lectern} namespace)
     * @param x        world X of the spawn centre
     * @param y        world Y of the spawn centre
     * @param z        world Z of the spawn centre
     * @param deltaX   spread on X (per-particle gaussian offset when {@code count > 0});
     *                 when {@code count == 0}, the X velocity direction instead
     * @param deltaY   spread / velocity on Y (see {@code deltaX})
     * @param deltaZ   spread / velocity on Z (see {@code deltaX})
     * @param speed    particle speed multiplier
     * @param count    number of particles; {@code 0} spawns a single directional particle
     *                 with velocity {@code (deltaX, deltaY, deltaZ) * speed}
     */
    void spawnParticle(Player player, String particle,
                       double x, double y, double z,
                       double deltaX, double deltaY, double deltaZ,
                       double speed, int count);

    /** Convenience overload using a Bukkit {@link Location} for the spawn centre. */
    default void spawnParticle(Player player, String particle, Location location,
                               double deltaX, double deltaY, double deltaZ,
                               double speed, int count) {
        spawnParticle(player, particle, location.getX(), location.getY(), location.getZ(),
                deltaX, deltaY, deltaZ, speed, count);
    }

    /**
     * Strikes a lightning bolt between two world points on the player's client — a jagged,
     * crackling arc that re-rolls its path every couple of ticks, glowing yellow by default.
     * Purely visual: no damage, no fire, no entity.
     *
     * @param player   the player whose client will render the bolt
     * @param id       addressable id — re-sending under the same id replaces the bolt (a
     *                 cheap way to sustain an arc); {@code ""} for fire-and-forget
     * @param x1,y1,z1 start point
     * @param x2,y2,z2 end point
     * @param durationTicks lifetime in ticks; {@code 0} = short flash (~12 ticks)
     * @param width    thickness multiplier, {@code 1} = default
     * @param argb     colour as ARGB, {@code 0} = default warm yellow
     */
    default void spawnLightning(Player player, String id,
                                double x1, double y1, double z1,
                                double x2, double y2, double z2,
                                int durationTicks, float width, int argb) {
    }

    /** Yellow flash between two locations, default look. */
    default void spawnLightning(Player player, Location from, Location to) {
        spawnLightning(player, "", from.getX(), from.getY(), from.getZ(),
                to.getX(), to.getY(), to.getZ(), 0, 1, 0);
    }

    /**
     * Lightning strung from one entity to another. Both ends track their entity on the
     * client every couple of ticks — the arc follows movement with no further packets.
     * Anchored at the chest (55% of height); {@code 0,0,0} offsets put it there exactly.
     * While an endpoint's entity isn't visible to the client (unloaded, dead, other
     * dimension), the bolt hides but keeps aging.
     */
    default void spawnLightning(Player player, String id,
                                Entity from, double fromOffsetX, double fromOffsetY, double fromOffsetZ,
                                Entity to, double toOffsetX, double toOffsetY, double toOffsetZ,
                                int durationTicks, float width, int argb) {
    }

    /** Lightning from an entity's chest to a fixed point. */
    default void spawnLightning(Player player, String id,
                                Entity from, double x, double y, double z,
                                int durationTicks, float width, int argb) {
    }

    /** Lightning from a fixed point to an entity's chest. */
    default void spawnLightning(Player player, String id,
                                double x, double y, double z, Entity to,
                                int durationTicks, float width, int argb) {
    }

    /** Yellow flash between two entities, default look. */
    default void spawnLightning(Player player, Entity from, Entity to) {
        spawnLightning(player, "", from, 0, 0, 0, to, 0, 0, 0, 0, 1, 0);
    }

    /** Yellow flash from an entity to a location, default look. */
    default void spawnLightning(Player player, Entity from, Location to) {
        spawnLightning(player, "", from, to.getX(), to.getY(), to.getZ(), 0, 1, 0);
    }

    /** Removes a named lightning bolt before its lifetime ends. */
    default void removeLightning(Player player, String id) {
    }

    /** Removes every lightning bolt on the player's client. */
    default void clearLightning(Player player) {
    }
}
