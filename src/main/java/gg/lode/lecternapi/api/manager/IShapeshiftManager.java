package gg.lode.lecternapi.api.manager;

import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * Manages entity disguise effects for players running the Lectern client mod.
 * Allows rendering a target entity as a different entity type or as a block
 * on specific viewers' clients. Visual only — the entity's actual type, hitbox,
 * and behavior are unchanged server-side.
 */
public interface IShapeshiftManager {

    // --- Shapeshift (entity-to-entity disguise) ---

    /**
     * Renders a target entity as a different entity type on the viewer's client.
     * The target's position, animations, and equipment sync from the real entity.
     *
     * @param viewer the player whose client will render the disguise
     * @param target the entity to disguise
     * @param entityTypeId the registry ID of the entity type to render as (e.g. "minecraft:blaze")
     */
    void shapeshift(Player viewer, Entity target, String entityTypeId);

    /**
     * Renders a target entity as a different entity type with a data-driven variant.
     * Variant names depend on the entity type: registry IDs for data-driven variants
     * (e.g. "minecraft:warm" for pig), or lowercase enum names for others
     * (e.g. "red" for sheep color, "lucy" for axolotl variant).
     *
     * @param viewer the player whose client will render the disguise
     * @param target the entity to disguise
     * @param entityTypeId the registry ID of the entity type to render as
     * @param variant the entity variant, or empty string for default
     */
    void shapeshift(Player viewer, Entity target, String entityTypeId, String variant);

    /**
     * Removes a shapeshift disguise from a target entity on the viewer's client,
     * restoring its normal rendering.
     *
     * @param viewer the player whose client will stop rendering the disguise
     * @param target the entity to un-disguise
     */
    void removeShapeshift(Player viewer, Entity target);

    /**
     * Removes a shapeshift disguise by the target's UUID.
     */
    void removeShapeshift(Player viewer, UUID targetUuid);

    /**
     * Clears all shapeshift disguises on the viewer's client.
     *
     * @param viewer the player whose disguises should be cleared
     */
    void clearShapeshifts(Player viewer);

    // --- Block Shapeshift (entity-to-block disguise) ---

    /**
     * Renders a target entity as a block on the viewer's client.
     * The entity's position and animations sync from the real entity.
     * Block state is specified as a string (e.g. "minecraft:oak_stairs[facing=east,half=top]").
     *
     * @param viewer the player whose client will render the block
     * @param target the entity to render as a block
     * @param blockStateString the block state string (e.g. "minecraft:oak_stairs[facing=east,half=top]")
     */
    void blockShapeshift(Player viewer, Entity target, String blockStateString);

    /**
     * Renders a target entity as a block using Bukkit {@link BlockData}.
     * The BlockData is converted to its string form ({@code getAsString()})
     * for transmission to the client.
     *
     * @param viewer the player whose client will render the block
     * @param target the entity to render as a block
     * @param blockData the block data to render
     */
    default void blockShapeshift(Player viewer, Entity target, BlockData blockData) {
        blockShapeshift(viewer, target, blockData.getAsString());
    }

    /**
     * Removes a block shapeshift disguise from a target entity on the viewer's client,
     * restoring its normal rendering.
     *
     * @param viewer the player whose client will stop rendering the block
     * @param target the entity to un-disguise
     */
    void removeBlockShapeshift(Player viewer, Entity target);

    /**
     * Removes a block shapeshift disguise by the target's UUID.
     */
    void removeBlockShapeshift(Player viewer, UUID targetUuid);

    /**
     * Clears all block shapeshift disguises on the viewer's client.
     *
     * @param viewer the player whose block disguises should be cleared
     */
    void clearBlockShapeshifts(Player viewer);
}
