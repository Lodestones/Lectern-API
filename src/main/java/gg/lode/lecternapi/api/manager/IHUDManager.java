package gg.lode.lecternapi.api.manager;

import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;

/**
 * Manages HUD rendering effects for players running the Lectern client mod.
 * Controls textures, text, player heads, buttons, letterboxing, and GUI scale.
 */
public interface IHUDManager {

    enum HorizontalAlignment {
        LEFT("left"),
        CENTER("center"),
        RIGHT("right");

        private final String value;

        HorizontalAlignment(String value) { this.value = value; }

        public String value() { return value; }
    }

    enum VerticalAlignment {
        TOP("top"),
        CENTER("center"),
        BOTTOM("bottom");

        private final String value;

        VerticalAlignment(String value) { this.value = value; }

        public String value() { return value; }
    }

    enum TextAlignment {
        AUTO("auto"),
        LEFT("left"),
        CENTER("center"),
        RIGHT("right");

        private final String value;

        TextAlignment(String value) { this.value = value; }

        public String value() { return value; }
    }

    /**
     * Renders a texture on the player's HUD.
     *
     * @param player the target player
     * @param reference a unique identifier for this texture element
     * @param textureId the texture identifier (e.g. "namespace:path")
     * @param x horizontal position
     * @param y vertical position
     * @param layer the render layer (z-index)
     * @param width the texture width
     * @param height the texture height
     * @param alpha the opacity (0.0 to 1.0)
     * @param horizontalAlignment horizontal alignment
     * @param verticalAlignment vertical alignment
     */
    void renderTexture(Player player, String reference, String textureId, float x, float y, int layer, float width, float height, float alpha, HorizontalAlignment horizontalAlignment, VerticalAlignment verticalAlignment);

    /**
     * Removes a rendered texture from the player's HUD.
     *
     * @param player the target player
     * @param reference the unique identifier of the texture to remove
     */
    void removeTexture(Player player, String reference);

    /**
     * Renders a player head on the HUD.
     *
     * @param player the target player
     * @param reference a unique identifier for this head element
     * @param headUuid the UUID of the player whose head to render
     * @param x horizontal position
     * @param y vertical position
     * @param layer the render layer
     * @param width the head width
     * @param height the head height
     * @param horizontalAlignment horizontal alignment
     * @param verticalAlignment vertical alignment
     */
    void renderHead(Player player, String reference, String headUuid, float x, float y, int layer, float width, float height, HorizontalAlignment horizontalAlignment, VerticalAlignment verticalAlignment);

    /**
     * Removes a rendered head from the player's HUD.
     */
    void removeHead(Player player, String reference);

    /**
     * Renders a player bust on the HUD.
     *
     * @param player the target player
     * @param reference a unique identifier for this player element
     * @param identifier the username or UUID of the player to render
     * @param x horizontal position
     * @param y vertical position
     * @param width the render width
     * @param height the render height
     * @param horizontalAlignment horizontal alignment
     * @param verticalAlignment vertical alignment
     */
    void renderPlayer(Player player, String reference, String identifier, float x, float y, float width, float height, HorizontalAlignment horizontalAlignment, VerticalAlignment verticalAlignment);

    /**
     * Removes a rendered player bust from the player's HUD.
     */
    void removePlayer(Player player, String reference);

    /**
     * Starts the button overlay layer with an optional blur amount.
     */
    void startButtonOverlay(Player player, float blur);

    /**
     * Stops the button overlay layer.
     */
    void stopButtonOverlay(Player player);

    /**
     * Adds a button to the button overlay.
     *
     * @param player the target player
     * @param reference a unique identifier for this button
     * @param textureId the button texture identifier
     * @param x horizontal position
     * @param y vertical position
     * @param width the button width
     * @param height the button height
     * @param alpha the opacity
     * @param horizontalAlignment horizontal alignment
     * @param verticalAlignment vertical alignment
     */
    void addButton(Player player, String reference, String textureId, int x, int y, int width, int height, float alpha, HorizontalAlignment horizontalAlignment, VerticalAlignment verticalAlignment);

    /**
     * Removes a button from the button overlay.
     */
    void removeButton(Player player, String reference);

    /**
     * Clears all buttons from the button overlay.
     */
    void clearButtons(Player player);

    /**
     * Enables or disables the letterbox (black bars) effect on the player's screen.
     */
    void setLetterbox(Player player, boolean enabled);

    /**
     * Hides or shows the vanilla HUD on the player's screen.
     */
    void setHudHidden(Player player, boolean hidden);

    /**
     * Overrides the GUI scale on the player's client.
     *
     * @param player the target player
     * @param scale the GUI scale value
     */
    void setGuiScale(Player player, int scale);

    /**
     * Resets the GUI scale to the player's default.
     */
    void resetGuiScale(Player player);

    /**
     * Displays a secondary progress bar on the player's HUD.
     *
     * @param player the target player
     * @param progress the bar progress (0.0 to 100.0)
     * @param red red color component (0-255)
     * @param green green color component (0-255)
     * @param blue blue color component (0-255)
     * @param alpha the opacity (0.0 to 1.0)
     */
    void showSecondBar(Player player, float progress, int red, int green, int blue, float alpha);

    /**
     * Hides the secondary progress bar.
     */
    void hideSecondBar(Player player);

    /**
     * Renders text on the player's HUD.
     * Plain strings are converted to JSON text components. MiniMessage and JSON strings are sent as-is.
     *
     * @param player the target player
     * @param reference a unique identifier for this text element
     * @param text the text content (plain string, MiniMessage format, or JSON text component)
     * @param x horizontal position
     * @param y vertical position
     * @param layer the render layer (z-index)
     * @param scale the text scale
     * @param horizontalAlignment horizontal alignment
     * @param verticalAlignment vertical alignment
     * @param textAlignment text alignment within the element
     */
    void renderText(Player player, String reference, String text, float x, float y, int layer, float scale, HorizontalAlignment horizontalAlignment, VerticalAlignment verticalAlignment, TextAlignment textAlignment);

    /**
     * Renders a Kyori Adventure Component on the player's HUD.
     * The component is serialized to JSON for the client to parse.
     *
     * @param player the target player
     * @param reference a unique identifier for this text element
     * @param component the Adventure component to render
     * @param x horizontal position
     * @param y vertical position
     * @param layer the render layer (z-index)
     * @param scale the text scale
     * @param horizontalAlignment horizontal alignment
     * @param verticalAlignment vertical alignment
     * @param textAlignment text alignment within the element
     */
    void renderText(Player player, String reference, Component component, float x, float y, int layer, float scale, HorizontalAlignment horizontalAlignment, VerticalAlignment verticalAlignment, TextAlignment textAlignment);

    /**
     * Removes rendered text from the player's HUD.
     *
     * @param player the target player
     * @param reference the unique identifier of the text to remove
     */
    void removeText(Player player, String reference);

    /**
     * Adds an item cooldown indicator on the player's HUD.
     *
     * @param player the target player
     * @param id the cooldown identifier
     * @param ticks the duration in ticks
     */
    void addItemCooldown(Player player, String id, int ticks);

    /**
     * Removes an item cooldown indicator from the player's HUD.
     *
     * @param player the target player
     * @param id the cooldown identifier to remove
     */
    void removeItemCooldown(Player player, String id);

    /**
     * Clears all item cooldown indicators from the player's HUD.
     *
     * @param player the target player
     */
    void clearItemCooldowns(Player player);
}
