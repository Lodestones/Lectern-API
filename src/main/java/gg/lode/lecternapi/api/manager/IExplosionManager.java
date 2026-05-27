package gg.lode.lecternapi.api.manager;

import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 * Spawns a layered explosion VFX (outer + inner ring shockwave, flash, sparks, smoke)
 * on the player's client. Visual only — no entity damage, no block damage, no world state.
 * <p>
 * Modeled after the MAS Effects SlamEffect pattern.
 */
public interface IExplosionManager {

    /**
     * Spawns the explosion VFX at the given world position on the player's client.
     *
     * @param player the target player
     * @param x      world X
     * @param y      world Y
     * @param z      world Z
     */
    void spawnExplosion(Player player, double x, double y, double z);

    /** Convenience overload using a Bukkit {@link Location}. */
    default void spawnExplosion(Player player, Location location) {
        spawnExplosion(player, location.getX(), location.getY(), location.getZ());
    }
}
