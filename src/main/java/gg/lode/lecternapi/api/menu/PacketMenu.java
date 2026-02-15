package gg.lode.lecternapi.api.menu;

import org.bukkit.entity.Player;

import gg.lode.lecternapi.api.component.ElementData;
import gg.lode.lecternapi.api.component.PacketComponent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Abstract base class for building custom screen menus sent to Lectern Fabric clients.
 * <p>
 * Subclass this and override {@link #build(Builder)} to define your menu's layout:
 * <pre>
 * public class MyMenu extends PacketMenu {
 *     public MyMenu(Player player) {
 *         super("my_menu", player);
 *     }
 *
 *     &#64;Override
 *     protected void build(Builder builder) {
 *         builder.addTexture("bg", "namespace:textures/gui/bg.png",
 *             MenuTransform.at(0, 0).layer(0), 256, 256);
 *
 *         builder.addButton("close", "namespace:textures/gui/close.png",
 *             MenuTransform.at(100, 10).layer(5), 32, 32,
 *             ButtonListener.onClick(origin -&gt; origin.menu().close()));
 *
 *         builder.addText("title", "&lt;gold&gt;My Menu&lt;/gold&gt;",
 *             MenuTransform.at(0, 20).layer(10).centered(), 2.0f);
 *     }
 * }
 * </pre>
 *
 * Open a menu via {@code LecternAPI.getApi().getPacketMenuManager().open(player, menu)}.
 */
public abstract class PacketMenu {

    private final String id;
    private final Player player;
    private float blur = 0.5f;
    private Runnable closeAction;

    // Built elements (populated by the manager when opening)
    private List<Element> elements;
    private Map<String, ButtonListener> buttonListeners;

    public PacketMenu(String id, Player player) {
        this.id = id;
        this.player = player;
    }

    /**
     * Override this to define the menu layout.
     */
    protected abstract void build(Builder builder);

    /**
     * Sets the background blur amount when the button overlay is active.
     * Default is 0.5f. Set to 0 for no blur.
     */
    protected void setBlur(float blur) {
        this.blur = blur;
    }

    /**
     * Sets an action to run when the menu is closed (either by the player or programmatically).
     */
    protected void onClose(Runnable action) {
        this.closeAction = action;
    }

    /**
     * Closes this menu, removing all elements and the button overlay.
     */
    public void close() {
        var manager = gg.lode.lecternapi.LecternAPI.getApi().getPacketMenuManager();
        if (manager != null) {
            manager.close(player);
        }
    }

    // --- Package-private accessors for the manager ---

    public String getId() {
        return id;
    }

    public Player getPlayer() {
        return player;
    }

    public float getBlur() {
        return blur;
    }

    public Runnable getCloseAction() {
        return closeAction;
    }

    public List<Element> getElements() {
        return elements != null ? Collections.unmodifiableList(elements) : List.of();
    }

    public Map<String, ButtonListener> getButtonListeners() {
        return buttonListeners != null ? Collections.unmodifiableMap(buttonListeners) : Map.of();
    }

    /**
     * Called by the manager to build and populate elements.
     */
    public void initialize() {
        Builder builder = new Builder(id);
        build(builder);
        this.elements = builder.elements;
        this.buttonListeners = builder.buttonListeners;
    }

    // --- Element types ---

    public sealed interface Element permits TextureElement, HeadElement, PlayerElement, ButtonElement, TextElement {}

    public record TextureElement(
            String reference, String textureId,
            MenuTransform transform, float width, float height
    ) implements Element {}

    public record HeadElement(
            String reference, String headUuid,
            MenuTransform transform, float width, float height
    ) implements Element {}

    public record PlayerElement(
            String reference, String identifier,
            MenuTransform transform, float width, float height
    ) implements Element {}

    public record ButtonElement(
            String reference, String textureId,
            MenuTransform transform, int width, int height
    ) implements Element {}

    public record TextElement(
            String reference, String text,
            MenuTransform transform, float scale
    ) implements Element {}

    // --- Builder ---

    /**
     * Fluent builder for composing menu elements.
     */
    public static class Builder {

        private final String menuId;
        private final List<Element> elements = new ArrayList<>();
        private final Map<String, ButtonListener> buttonListeners = new LinkedHashMap<>();
        private int refCounter = 0;

        Builder(String menuId) {
            this.menuId = menuId;
        }

        private String qualifyRef(String reference) {
            return menuId + ":" + reference;
        }

        /**
         * Adds a texture element to the menu.
         *
         * @param reference unique ID for this element within the menu
         * @param textureId the Minecraft texture identifier (e.g. "namespace:textures/gui/bg.png")
         * @param transform positioning data
         * @param width     texture width in pixels
         * @param height    texture height in pixels
         */
        public Builder addTexture(String reference, String textureId, MenuTransform transform, float width, float height) {
            elements.add(new TextureElement(qualifyRef(reference), textureId, transform, width, height));
            return this;
        }

        /**
         * Adds a player head element to the menu.
         *
         * @param reference unique ID for this element within the menu
         * @param headUuid  the UUID of the player whose head to render
         * @param transform positioning data
         * @param width     head width in pixels
         * @param height    head height in pixels
         */
        public Builder addHead(String reference, String headUuid, MenuTransform transform, float width, float height) {
            elements.add(new HeadElement(qualifyRef(reference), headUuid, transform, width, height));
            return this;
        }

        /**
         * Adds a player bust render to the menu.
         *
         * @param reference  unique ID for this element within the menu
         * @param identifier the username or UUID of the player to render
         * @param transform  positioning data
         * @param width      render width in pixels
         * @param height     render height in pixels
         */
        public Builder addPlayer(String reference, String identifier, MenuTransform transform, float width, float height) {
            elements.add(new PlayerElement(qualifyRef(reference), identifier, transform, width, height));
            return this;
        }

        /**
         * Adds a clickable/hoverable button element.
         *
         * @param reference unique ID for this button within the menu
         * @param textureId the button texture identifier
         * @param transform positioning data
         * @param width     button width in pixels
         * @param height    button height in pixels
         * @param listener  click/hover handler
         */
        public Builder addButton(String reference, String textureId, MenuTransform transform, int width, int height, ButtonListener listener) {
            String qualifiedRef = qualifyRef(reference);
            elements.add(new ButtonElement(qualifiedRef, textureId, transform, width, height));
            buttonListeners.put(qualifiedRef, listener);
            return this;
        }

        /**
         * Adds a text component element using MiniMessage format.
         *
         * @param reference unique ID for this element within the menu
         * @param text      the text content (supports MiniMessage tags like {@code <gold>Hello</gold>})
         * @param transform positioning data
         * @param scale     text scale multiplier
         */
        public Builder addText(String reference, String text, MenuTransform transform, float scale) {
            elements.add(new TextElement(qualifyRef(reference), text, transform, scale));
            return this;
        }

        /**
         * Flattens a {@link PacketComponent}'s elements into this menu as static elements.
         * The component is initialized if not already built. Tickable elements are treated
         * as static within menus — no tick loop runs for embedded components.
         *
         * @param component the component to embed
         */
        public Builder addComponent(PacketComponent component) {
            component.initialize();

            for (Map.Entry<String, ElementData> entry : component.getElements().entrySet()) {
                String reference = entry.getKey();
                ElementData data = entry.getValue();
                MenuTransform absolute = component.resolveTransform(data.relativeTransform());

                if (data instanceof ElementData.TextureData tex) {
                    elements.add(new TextureElement(reference, tex.textureId(), absolute, tex.width(), tex.height()));
                } else if (data instanceof ElementData.HeadData head) {
                    elements.add(new HeadElement(reference, head.headUuid(), absolute, head.width(), head.height()));
                } else if (data instanceof ElementData.PlayerData pe) {
                    elements.add(new PlayerElement(reference, pe.identifier(), absolute, pe.width(), pe.height()));
                } else if (data instanceof ElementData.ButtonData btn) {
                    elements.add(new ButtonElement(reference, btn.textureId(), absolute, btn.width(), btn.height()));
                    if (btn.listener() != null) {
                        buttonListeners.put(reference, btn.listener());
                    }
                } else if (data instanceof ElementData.TextData txt) {
                    elements.add(new TextElement(reference, txt.text(), absolute, txt.scale()));
                }
            }
            return this;
        }
    }
}
