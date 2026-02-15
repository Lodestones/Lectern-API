package gg.lode.lecternapi.api.manager;

import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * Manages entity-related effects for players running the Lectern client mod.
 * Controls custom capes, skins, nametags, entity tinting, visibility, emotes, and model attachments.
 */
public interface IEntityManager {

    // --- Capes ---

    /**
     * Sets a custom cape on an entity as seen by the target player.
     *
     * @param player the player who will see the cape
     * @param entityUuid the UUID of the entity to apply the cape to
     * @param capeId the cape texture identifier (e.g. "namespace:textures/cape.png")
     */
    void setCape(Player player, UUID entityUuid, String capeId);

    /**
     * Removes a custom cape from an entity as seen by the target player.
     */
    void removeCape(Player player, UUID entityUuid);

    /**
     * Clears all custom capes on the target player's client.
     */
    void clearCapes(Player player);

    // --- Skins ---

    /**
     * Overrides the skin of an entity as seen by the target player using a texture path.
     *
     * @param player the player who will see the skin
     * @param entityUuid the UUID of the entity to reskin
     * @param skinId the skin texture identifier (e.g. "namespace:textures/entity/skin.png")
     */
    void setSkin(Player player, UUID entityUuid, String skinId);

    /**
     * Overrides the skin of an entity to match another player's skin, looked up by player name.
     * The client fetches the skin from the Mojang API.
     *
     * @param player the player who will see the skin
     * @param entityUuid the UUID of the entity to reskin
     * @param playerName the username of the player whose skin to use
     */
    void setSkinToPlayer(Player player, UUID entityUuid, String playerName);

    /**
     * Overrides the skin of an entity to match another player's skin, looked up by UUID.
     * The client fetches the skin from the Mojang API.
     *
     * @param player the player who will see the skin
     * @param entityUuid the UUID of the entity to reskin
     * @param skinUuid the UUID of the player whose skin to use
     */
    void setSkinToUuid(Player player, UUID entityUuid, UUID skinUuid);

    /**
     * Removes a skin override from an entity as seen by the target player.
     */
    void removeSkin(Player player, UUID entityUuid);

    /**
     * Clears all skin overrides on the target player's client.
     */
    void clearSkins(Player player);

    // --- Nametags ---

    /**
     * Overrides the nametag text of an entity as seen by the target player.
     *
     * @param player the player who will see the nametag
     * @param entityUuid the UUID of the entity
     * @param name the nametag text
     */
    void setNametag(Player player, UUID entityUuid, String name);

    /**
     * Overrides the nametag color of an entity as seen by the target player.
     *
     * @param player the player who will see the color
     * @param entityUuid the UUID of the entity
     * @param color the color as a packed RGB integer
     */
    void setNametagColor(Player player, UUID entityUuid, int color);

    /**
     * Removes a nametag text override from an entity.
     */
    void removeNametag(Player player, UUID entityUuid);

    /**
     * Removes a nametag color override from an entity.
     */
    void removeNametagColor(Player player, UUID entityUuid);

    /**
     * Clears all nametag text overrides on the target player's client.
     */
    void clearNametags(Player player);

    /**
     * Clears all nametag color overrides on the target player's client.
     */
    void clearNametagColors(Player player);

    // --- Entity Tinting ---

    /**
     * Tints an entity with a color as seen by the target player.
     *
     * @param player the player who will see the tint
     * @param entityUuid the UUID of the entity to tint
     * @param red red component (0-255)
     * @param green green component (0-255)
     * @param blue blue component (0-255)
     * @param alpha the tint opacity (0.0 to 1.0)
     */
    void tintEntity(Player player, UUID entityUuid, int red, int green, int blue, float alpha);

    /**
     * Removes a tint from an entity.
     */
    void removeTint(Player player, UUID entityUuid);

    /**
     * Clears all entity tints on the target player's client.
     */
    void clearTints(Player player);

    // --- Entity Visibility ---

    /**
     * Hides an entity on the target player's client.
     */
    void hideEntity(Player player, UUID entityUuid);

    /**
     * Shows a previously hidden entity on the target player's client.
     */
    void showEntity(Player player, UUID entityUuid);

    /**
     * Clears all entity visibility overrides on the target player's client.
     */
    void clearEntityVisibility(Player player);

    // --- Hand Rendering ---

    /**
     * Hides or shows the player's hand rendering.
     */
    void setHandVisible(Player player, boolean visible);

    // --- Emotes ---

    /**
     * Plays an emote animation on an entity as seen by the target player.
     *
     * @param player the player who will see the emote
     * @param entityUuid the UUID of the entity to animate
     * @param emoteId the emote identifier (e.g. "lodestone:wave")
     * @param showHands whether to show the entity's hands during the emote
     * @param priority the animation priority
     */
    void playEmote(Player player, UUID entityUuid, String emoteId, boolean showHands, int priority);

    /**
     * Stops a specific emote on an entity.
     */
    void stopEmote(Player player, UUID entityUuid, String emoteId);

    /**
     * Stops all emotes on an entity.
     */
    void stopAllEmotes(Player player, UUID entityUuid);

    /**
     * Clears all emote state on the target player's client.
     */
    void clearEmotes(Player player);

    // --- Model Attachments ---

    /**
     * Attaches a model to a body part of an entity as seen by the target player.
     *
     * @param player the player who will see the attachment
     * @param attachmentId a unique identifier for this attachment
     * @param entityUuid the UUID of the entity to attach to
     * @param itemId the item identifier for the model
     * @param customModel the custom model data string
     * @param bodyPart the body part ("head", "hat", "body", "right_arm", "left_arm", "right_leg", "left_leg")
     * @param offsetX X offset from the body part
     * @param offsetY Y offset from the body part
     * @param offsetZ Z offset from the body part
     * @param scaleX X scale factor
     * @param scaleY Y scale factor
     * @param scaleZ Z scale factor
     * @param rotationX X rotation in degrees
     * @param rotationY Y rotation in degrees
     * @param rotationZ Z rotation in degrees
     */
    void attachModel(Player player, String attachmentId, UUID entityUuid, String itemId, String customModel, String bodyPart, float offsetX, float offsetY, float offsetZ, float scaleX, float scaleY, float scaleZ, float rotationX, float rotationY, float rotationZ);

    /**
     * Removes a model attachment by its unique identifier.
     */
    void removeAttachment(Player player, String attachmentId);

    /**
     * Clears all model attachments on the target player's client.
     */
    void clearAttachments(Player player);

    // --- Visual Chain ---

    /**
     * Enables or disables the visual chain leash effect on the target player's client.
     * When enabled, leash connections render as chain blocks instead of the default leash rope.
     *
     * @param player the target player
     * @param enabled true to enable chain rendering, false to disable
     */
    void setVisualChain(Player player, boolean enabled);
}
