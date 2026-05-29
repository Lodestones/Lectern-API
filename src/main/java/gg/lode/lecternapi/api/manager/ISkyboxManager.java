package gg.lode.lecternapi.api.manager;

import gg.lode.lecternapi.api.sky.Skybox;
import org.bukkit.entity.Player;

/**
 * Manages OptiFine-style custom skyboxes on the player's client.
 * <p>
 * Skyboxes are identified by their {@link Skybox#id()}. Sending a skybox with
 * a fresh id adds it to the active set; sending one with an existing id
 * updates the config in place. {@link #removeSkybox(Player, String)} clears
 * a single skybox; {@link #clearSkyboxes(Player)} clears all.
 */
public interface ISkyboxManager {

    /** Show or update a skybox on the player's client. */
    void showSkybox(Player player, Skybox skybox);

    /** Remove a skybox by id. */
    void removeSkybox(Player player, String id);

    /** Remove every active skybox on the player's client. */
    void clearSkyboxes(Player player);
}
