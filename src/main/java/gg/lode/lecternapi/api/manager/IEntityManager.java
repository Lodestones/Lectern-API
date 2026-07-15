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
     * Overrides the skin of an entity using a raw Mojang texture property
     * (the base64 {@code value} and its {@code signature}), the standard skin
     * format used by GameProfile properties. The client decodes the value to
     * resolve the skin URL and model (slim/wide) and downloads the texture.
     * <p>
     * Use this when you already hold the texture property (e.g. from a cached
     * profile) and want to avoid a Mojang API lookup by name/UUID.
     *
     * @param player the player who will see the skin
     * @param entityUuid the UUID of the entity to reskin
     * @param textureValue the base64-encoded textures property value
     * @param textureSignature the property signature (may be null/empty; used for authenticity, not required to render)
     */
    default void setSkinToTexture(Player player, UUID entityUuid, String textureValue, String textureSignature) {
        // Backward-compatible no-op fallback; the real implementation overrides this.
    }

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

    // --- Disintegration ---

    /**
     * Disintegrates a player entity on the target player's client (Thanos-snap
     * style): the entity's pose freezes, the model turns into skin-pixel voxels,
     * and the voxels dissolve into wind-blown dust. The entity stays hidden on
     * the client after the dissolve until {@link #stopDisintegrate} is sent.
     * <p>
     * The effect is purely visual — pair it with a server-side freeze so the
     * frozen statue doesn't move with the live entity.
     *
     * @param player the player who will see the effect
     * @param entityUuid the UUID of the player entity to disintegrate
     * @param durationMs dissolve duration in milliseconds ({@code <= 0} = default 4000)
     * @param holdMs frozen-statue beat before the dissolve starts ({@code <= 0} = none)
     * @param sweepUp true to dissolve feet-first upward, false head-first downward
     */
    void disintegrate(Player player, UUID entityUuid, int durationMs, int holdMs, boolean sweepUp);

    /**
     * Disintegrates a player entity, optionally in reverse.
     *
     * @param inverted true to play the dissolve in reverse — the voxels start fully
     *                 dispersed and reassemble back into the player, then the real
     *                 player takes over rendering
     */
    default void disintegrate(Player player, UUID entityUuid, int durationMs, int holdMs, boolean sweepUp, boolean inverted) {
        // Backward-compatible fallback: older implementations play the normal (non-inverted) dissolve.
        disintegrate(player, entityUuid, durationMs, holdMs, sweepUp);
    }

    /**
     * Disintegrates a player entity with the default feet-first upward sweep.
     */
    void disintegrate(Player player, UUID entityUuid, int durationMs, int holdMs);

    /**
     * Disintegrates a player entity with default timing (4s dissolve, 600ms hold).
     */
    void disintegrate(Player player, UUID entityUuid);

    /**
     * Cancels a disintegration (or unhides the entity after one finished) on
     * the target player's client.
     */
    void stopDisintegrate(Player player, UUID entityUuid);
}
