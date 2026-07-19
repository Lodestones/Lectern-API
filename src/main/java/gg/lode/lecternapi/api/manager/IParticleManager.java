package gg.lode.lecternapi.api.manager;

import org.bukkit.Location;
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
}
