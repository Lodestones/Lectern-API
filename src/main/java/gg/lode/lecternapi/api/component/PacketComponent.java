package gg.lode.lecternapi.api.component;

import gg.lode.lecternapi.LecternAPI;
import gg.lode.lecternapi.api.menu.ButtonListener;
import gg.lode.lecternapi.api.menu.MenuTransform;

import org.bukkit.entity.Player;

import java.util.*;
import java.util.function.Consumer;

/**
 * Abstract base class for composable HUD components sent to Lectern Fabric clients.
 * <p>
 * Components group HUD elements (textures, text, heads, player busts, buttons) under a shared
 * coordinate space with relative positioning. Elements marked as {@code tickable()} are
 * monitored each server tick — only changed elements are re-sent to the client.
 * <p>
 * Subclass and override {@link #build(Builder)} to define layout, and optionally
 * override {@link #tick()} for dynamic updates:
 * <pre>
 * public class HealthBar extends PacketComponent {
 *     private final Player target;
 *
 *     public HealthBar(Player target) {
 *         super("health_bar", target, MenuTransform.at(10, 10));
 *         this.target = target;
 *     }
 *
 *     &#64;Override
 *     protected void build(Builder builder) {
 *         builder.addTexture("bg", "mygame:hud/bar_bg", MenuTransform.at(0, 0), 200, 20);
 *         builder.addTexture("fill", "mygame:hud/bar_fill", MenuTransform.at(2, 2), 196, 16)
 *                .tickable();
 *         builder.addText("label", formatHealth(), MenuTransform.at(100, 10).centered(), 1.0f)
 *                .tickable();
 *     }
 *
 *     &#64;Override
 *     protected void tick() {
 *         float pct = (float) target.getHealth() / 20f;
 *         update("fill", texture -&gt; texture.width(196 * pct));
 *         update("label", text -&gt; text.text(formatHealth()));
 *     }
 * }
 * </pre>
 *
 * Show/hide via {@code LecternAPI.getApi().getPacketComponentManager().show(player, component)}.
 */
public abstract class PacketComponent {

    private final String id;
    private final Player player;
    private final MenuTransform transform;

    // Preserves insertion order for deterministic rendering
    private final LinkedHashMap<String, ElementData> elements = new LinkedHashMap<>();
    private final Set<String> tickableRefs = new LinkedHashSet<>();
    private final Map<String, ButtonListener> buttonListeners = new LinkedHashMap<>();

    private boolean built = false;

    public PacketComponent(String id, Player player, MenuTransform transform) {
        this.id = id;
        this.player = player;
        this.transform = transform;
    }

    /**
     * Override to define the component's element layout.
     * Called once during {@link #initialize()}.
     */
    protected abstract void build(Builder builder);

    /**
     * Override to update tickable elements each server tick.
     * Use {@link #update(String, Consumer)} to modify element properties.
     */
    protected void tick() {}

    // --- Public API ---

    /**
     * Shows this component to the player.
     */
    public void show() {
        var manager = LecternAPI.getApi().getPacketComponentManager();
        if (manager != null) {
            manager.show(player, this);
        }
    }

    /**
     * Hides this component from the player.
     */
    public void hide() {
        var manager = LecternAPI.getApi().getPacketComponentManager();
        if (manager != null) {
            manager.hide(player, id);
        }
    }

    // --- Update API (for use in tick()) ---

    /**
     * Updates a tickable element's properties during {@link #tick()}.
     * The consumer receives an {@link ElementUpdater} appropriate for the element's type.
     *
     * @param reference the element reference (as passed to the builder)
     * @param updater   consumer that modifies element properties
     */
    @SuppressWarnings("unchecked")
    protected <T extends ElementUpdater> void update(String reference, Consumer<T> updater) {
        String qualifiedRef = qualifyRef(reference);
        ElementData data = elements.get(qualifiedRef);
        if (data == null) return;

        ElementUpdater eu = createUpdater(data);
        ((Consumer<ElementUpdater>) updater).accept(eu);
        elements.put(qualifiedRef, eu.toData());
    }

    // --- Accessors (package-private for manager) ---

    public String getId() {
        return id;
    }

    public Player getPlayer() {
        return player;
    }

    public MenuTransform getTransform() {
        return transform;
    }

    public Map<String, ElementData> getElements() {
        return Collections.unmodifiableMap(elements);
    }

    public Set<String> getTickableRefs() {
        return Collections.unmodifiableSet(tickableRefs);
    }

    public Map<String, ButtonListener> getButtonListeners() {
        return Collections.unmodifiableMap(buttonListeners);
    }

    public boolean hasTickableElements() {
        return !tickableRefs.isEmpty();
    }

    /**
     * Called by the manager to build and populate elements.
     */
    public void initialize() {
        if (built) return;
        Builder builder = new Builder(id);
        build(builder);
        built = true;
    }

    /**
     * Called by the manager each tick for components with tickable elements.
     */
    public void executeTick() {
        tick();
    }

    /**
     * Resolves a child element's transform to absolute screen coordinates.
     */
    public MenuTransform resolveTransform(MenuTransform childRelative) {
        return new MenuTransform(
                transform.x() + childRelative.x(),
                transform.y() + childRelative.y(),
                childRelative.layer(),
                childRelative.alpha(),
                childRelative.horizontalAlignment(),
                childRelative.verticalAlignment()
        );
    }

    private String qualifyRef(String reference) {
        return id + ":" + reference;
    }

    // --- Element Updaters ---

    /**
     * Sealed interface for type-safe element mutation during tick.
     */
    public sealed interface ElementUpdater permits TextureUpdater, HeadUpdater, PlayerUpdater, ButtonUpdater, TextUpdater {
        ElementData toData();
    }

    public static final class TextureUpdater implements ElementUpdater {
        private String textureId;
        private MenuTransform relativeTransform;
        private float width;
        private float height;

        TextureUpdater(ElementData.TextureData data) {
            this.textureId = data.textureId();
            this.relativeTransform = data.relativeTransform();
            this.width = data.width();
            this.height = data.height();
        }

        public TextureUpdater textureId(String textureId) { this.textureId = textureId; return this; }
        public TextureUpdater transform(MenuTransform t) { this.relativeTransform = t; return this; }
        public TextureUpdater width(float w) { this.width = w; return this; }
        public TextureUpdater height(float h) { this.height = h; return this; }

        @Override
        public ElementData toData() {
            return new ElementData.TextureData(textureId, relativeTransform, width, height);
        }
    }

    public static final class HeadUpdater implements ElementUpdater {
        private String headUuid;
        private MenuTransform relativeTransform;
        private float width;
        private float height;

        HeadUpdater(ElementData.HeadData data) {
            this.headUuid = data.headUuid();
            this.relativeTransform = data.relativeTransform();
            this.width = data.width();
            this.height = data.height();
        }

        public HeadUpdater headUuid(String headUuid) { this.headUuid = headUuid; return this; }
        public HeadUpdater transform(MenuTransform t) { this.relativeTransform = t; return this; }
        public HeadUpdater width(float w) { this.width = w; return this; }
        public HeadUpdater height(float h) { this.height = h; return this; }

        @Override
        public ElementData toData() {
            return new ElementData.HeadData(headUuid, relativeTransform, width, height);
        }
    }

    public static final class PlayerUpdater implements ElementUpdater {
        private String identifier;
        private MenuTransform relativeTransform;
        private float width;
        private float height;

        PlayerUpdater(ElementData.PlayerData data) {
            this.identifier = data.identifier();
            this.relativeTransform = data.relativeTransform();
            this.width = data.width();
            this.height = data.height();
        }

        public PlayerUpdater identifier(String id) { this.identifier = id; return this; }
        public PlayerUpdater transform(MenuTransform t) { this.relativeTransform = t; return this; }
        public PlayerUpdater width(float w) { this.width = w; return this; }
        public PlayerUpdater height(float h) { this.height = h; return this; }

        @Override
        public ElementData toData() {
            return new ElementData.PlayerData(identifier, relativeTransform, width, height);
        }
    }

    public static final class ButtonUpdater implements ElementUpdater {
        private String textureId;
        private MenuTransform relativeTransform;
        private int width;
        private int height;
        private ButtonListener listener;

        ButtonUpdater(ElementData.ButtonData data) {
            this.textureId = data.textureId();
            this.relativeTransform = data.relativeTransform();
            this.width = data.width();
            this.height = data.height();
            this.listener = data.listener();
        }

        public ButtonUpdater textureId(String id) { this.textureId = id; return this; }
        public ButtonUpdater transform(MenuTransform t) { this.relativeTransform = t; return this; }
        public ButtonUpdater width(int w) { this.width = w; return this; }
        public ButtonUpdater height(int h) { this.height = h; return this; }
        public ButtonUpdater listener(ButtonListener l) { this.listener = l; return this; }

        @Override
        public ElementData toData() {
            return new ElementData.ButtonData(textureId, relativeTransform, width, height, listener);
        }
    }

    public static final class TextUpdater implements ElementUpdater {
        private String text;
        private MenuTransform relativeTransform;
        private float scale;

        TextUpdater(ElementData.TextData data) {
            this.text = data.text();
            this.relativeTransform = data.relativeTransform();
            this.scale = data.scale();
        }

        public TextUpdater text(String text) { this.text = text; return this; }
        public TextUpdater transform(MenuTransform t) { this.relativeTransform = t; return this; }
        public TextUpdater scale(float s) { this.scale = s; return this; }

        @Override
        public ElementData toData() {
            return new ElementData.TextData(text, relativeTransform, scale);
        }
    }

    private static ElementUpdater createUpdater(ElementData data) {
        if (data instanceof ElementData.TextureData d) return new TextureUpdater(d);
        if (data instanceof ElementData.HeadData d) return new HeadUpdater(d);
        if (data instanceof ElementData.PlayerData d) return new PlayerUpdater(d);
        if (data instanceof ElementData.ButtonData d) return new ButtonUpdater(d);
        if (data instanceof ElementData.TextData d) return new TextUpdater(d);
        throw new IllegalStateException("Unknown element data type: " + data.getClass());
    }

    // --- Builder ---

    /**
     * Fluent builder for defining component elements.
     */
    public class Builder {

        private final String componentId;

        Builder(String componentId) {
            this.componentId = componentId;
        }

        /**
         * Adds a texture element to the component.
         */
        public ElementHandle addTexture(String reference, String textureId, MenuTransform relativeTransform, float width, float height) {
            String qualifiedRef = qualifyRef(reference);
            elements.put(qualifiedRef, new ElementData.TextureData(textureId, relativeTransform, width, height));
            return new ElementHandle(qualifiedRef);
        }

        /**
         * Adds a player head element to the component.
         */
        public ElementHandle addHead(String reference, String headUuid, MenuTransform relativeTransform, float width, float height) {
            String qualifiedRef = qualifyRef(reference);
            elements.put(qualifiedRef, new ElementData.HeadData(headUuid, relativeTransform, width, height));
            return new ElementHandle(qualifiedRef);
        }

        /**
         * Adds a player bust render to the component.
         */
        public ElementHandle addPlayer(String reference, String identifier, MenuTransform relativeTransform, float width, float height) {
            String qualifiedRef = qualifyRef(reference);
            elements.put(qualifiedRef, new ElementData.PlayerData(identifier, relativeTransform, width, height));
            return new ElementHandle(qualifiedRef);
        }

        /**
         * Adds a clickable/hoverable button element.
         */
        public ElementHandle addButton(String reference, String textureId, MenuTransform relativeTransform, int width, int height, ButtonListener listener) {
            String qualifiedRef = qualifyRef(reference);
            elements.put(qualifiedRef, new ElementData.ButtonData(textureId, relativeTransform, width, height, listener));
            buttonListeners.put(qualifiedRef, listener);
            return new ElementHandle(qualifiedRef);
        }

        /**
         * Adds a text component element using MiniMessage format.
         */
        public ElementHandle addText(String reference, String text, MenuTransform relativeTransform, float scale) {
            String qualifiedRef = qualifyRef(reference);
            elements.put(qualifiedRef, new ElementData.TextData(text, relativeTransform, scale));
            return new ElementHandle(qualifiedRef);
        }
    }

    /**
     * Handle returned by builder methods. Call {@link #tickable()} to mark the element
     * for per-tick dirty checking.
     */
    public class ElementHandle {
        private final String qualifiedRef;

        ElementHandle(String qualifiedRef) {
            this.qualifiedRef = qualifiedRef;
        }

        /**
         * Marks this element as tickable — it will be monitored each tick and
         * re-sent to the client only when its data changes.
         */
        public ElementHandle tickable() {
            tickableRefs.add(qualifiedRef);
            return this;
        }
    }
}
