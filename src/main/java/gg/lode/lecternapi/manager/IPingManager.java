package gg.lode.lecternapi.manager;

import gg.lode.lecternapi.ILecternAPI;
import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 * The ping manager is used to manage pings per player.
 * You can get the manager using the {@link ILecternAPI#getPingManager()}
 * method.
 * <p>
 * For more information about how to create pings, you can find our
 * documentation at:
 * <a href="https://lode.gg/docs/lectern/features/pings">...</a>
 */
public interface IPingManager {

    record Ping(String id, String title, Location location) {
    }

    /**
     * Registers a ping for a player.
     * 
     * @param player The player to register the ping for
     * @param pingId The id of the ping
     * @param title The title of the ping
     * @param location The location of the ping
     */
    void registerPing(Player player, String pingId, String title, Location location);

    /**
     * Unregisters a ping for a player.
     * 
     * @param player The player to unregister the ping for
     * @param pingId The id of the ping
     */
    void unregisterPing(Player player, String pingId);

    /**
     * Unregisters all pings for a player.
     * 
     * @param player The player to unregister all pings for
     */
    void unregisterAllPings(Player player);

    /**
     * Unregisters all pings.
     */
    void unregisterAllPings();

    /**
     * Registers a global ping.
     * 
     * @param pingId The id of the ping
     * @param title The title of the ping
     * @param location The location of the ping
     */
    void registerGlobalPing(String pingId, String title, Location location);

    /**
     * Unregisters a global ping.
     * 
     * @param pingId The id of the ping
     */
    void unregisterGlobalPing(String pingId);

    /**
     * Unregisters all player pings for a player.
     * 
     * @param player The player to unregister all player pings for
     */
    void unregisterAllPlayerPings(Player player);

    /**
     * Registers a player ping for a player.
     * 
     * @param player The player to register the player ping for
     * @param target The target to register the player ping for
     */
    void registerPlayerPing(Player player, Player target);

    /**
     * Unregisters a player ping for a player.
     * 
     * @param player The player to unregister the player ping for
     * @param target The target to unregister the player ping for
     */
    void unregisterPlayerPing(Player player, Player target);

}
