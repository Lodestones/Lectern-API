package gg.lode.lecternapi.packetmenu;

import gg.lode.lecternapi.packetmenu.text.TextAlignment;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.*;

public class ElementsBuilder implements PacketMenuElement.ContextIdProvider {
    MenuTransform transform;

    final List<PacketMenuElement> elements;
    final List<MenuElements> internal;
    final List<Runnable> closeActions;
    final Map<String, List<ButtonListener>> anyButtonListeners;
    final Map<String, ButtonListener> buttonListeners;
    final Map<String, ReplacementConfig> replacementPointers;

    private int incrementalReservedId = 0;

    public ElementsBuilder(MenuTransform transform, List<PacketMenuElement> elements) {
        this.transform = transform;

        this.elements = elements;
        this.internal = new ArrayList<>();
        this.closeActions = new ArrayList<>();
        this.anyButtonListeners = new HashMap<>();
        this.buttonListeners = new HashMap<>();
        this.replacementPointers = new HashMap<>();
    }

    public ElementsBuilder(MenuTransform transform) {
        this(transform, new ArrayList<>());
    }

    public ElementsBuilder() {
        this(MenuTransform.DEFAULT);
    }

    public MenuTransform transform() {
        return transform;
    }

    @Override
    public String getNewElementId() {
        return String.valueOf(this.incrementalReservedId++);
    }

    public ElementsBuilder setTransform(MenuTransform transform) {
        this.transform = transform;
        return this;
    }

    public ElementsBuilder setTransform(int x, int y) {
        return this.setTransform(new MenuTransform(x, y));
    }

    public ElementsBuilder addCloseAction(Runnable onClose) {
        this.closeActions.add(onClose);
        return this;
    }

    public ElementsBuilder listenButton(String buttonId, ButtonListener listener) {
        this.anyButtonListeners.computeIfAbsent(buttonId, (x) -> new ArrayList<>()).add(listener);
        return this;
    }

    public ElementsBuilder listenButtons(ButtonListener listener, String... buttonIds) {
        for (var buttonId : buttonIds)
            this.anyButtonListeners.computeIfAbsent(buttonId, (x) -> new ArrayList<>()).add(listener);
        return this;
    }

    public ElementsBuilder addReplacement(
            String pointerId,
            ReplacementConfig replacement
    ) {
        if (this.replacementPointers.put(pointerId, Objects.requireNonNull(replacement)) != null)
            throw new IllegalStateException("Duplicated replacement pointer id: " + pointerId);
        return this;
    }

    public ElementsBuilder addElement(@NotNull InsertionElement element) {
        if (element instanceof PacketMenuElement.Button.Insertion buttonInsertion) {
            if (this.buttonListeners.put(buttonInsertion.button().id(), buttonInsertion.listener()) != null)
                throw new IllegalStateException("Duplicated button id: " + buttonInsertion.button().id());
            return this.addElement(buttonInsertion.button());
        } else if (element instanceof PacketMenuElement.Button button) {
            this.buttonListeners.computeIfAbsent(button.id(), (x) -> (origin) -> {
            });
            this.elements.add(button);
        } else if (element instanceof PacketMenuElement.Pointer pointer &&
                pointer.base() instanceof PacketMenuElement.Button button) {
            this.buttonListeners.computeIfAbsent(pointer.id() + button.id(), (x) -> (origin) -> {
            });
            this.elements.add(pointer);
        } else if (element instanceof PacketMenuElement menuElement) {
            this.elements.add(menuElement);
        } else if (element instanceof MenuElements innerBuilder) {
            this.internal.add(innerBuilder);
        } else {
            throw new IllegalArgumentException("Invalid insertion: " + element);
        }

        return this;
    }

    public ElementsBuilder addInner(@NotNull MenuElements inner) {
        return this.addElement(inner);
    }

    public ElementsBuilder addComponent(MenuTransform transform, float size, net.kyori.adventure.text.Component component, @Nullable TextAlignment alignment) {
        return this.addElement(new PacketMenuElement.Component(transform, getNewElementId(), size, component, alignment));
    }

    public ElementsBuilder addComponent(MenuTransform transform, float size, net.kyori.adventure.text.Component component) {
        return this.addComponent(transform, size, component, null);
    }

    public ElementsBuilder addComponent(int x, int y, float size, net.kyori.adventure.text.Component component) {
        return this.addComponent(new MenuTransform(x, y), size, component);
    }

    public ElementsBuilder addComponent(int x, int y, net.kyori.adventure.text.Component component) {
        return this.addComponent(x, y, 1f, component);
    }

    public ElementsBuilder addComponent(int x, int y, String component) {
        return this.addComponent(x, y, MiniMessage.miniMessage().deserialize(component));
    }

    public ElementsBuilder addTexture(MenuTransform transform, int width, int height, String texturePath) {
        return this.addElement(new PacketMenuElement.Texture(transform, getNewElementId(), width, height, texturePath));
    }

    public ElementsBuilder addTexture(int x, int y, int width, int height, String texturePath) {
        return this.addTexture(new MenuTransform(x, y), width, height, texturePath);
    }

    public ElementsBuilder addButton(MenuTransform transform, @Nullable String id, int width, int height, String texturePath, ButtonListener listener) {
        if (id == null) id = getNewElementId();
        return this.addElement(new PacketMenuElement.Button.Insertion(
                new PacketMenuElement.Button(transform, id, width, height, texturePath),
                listener
        ));
    }

    public ElementsBuilder addButton(MenuTransform transform, @Nullable String id, int width, int height, String texturePath) {
        return this.addButton(transform, id, width, height, texturePath, origin -> {
        });
    }

    public ElementsBuilder addButton(MenuTransform transform, int width, int height, String texturePath, ButtonListener listener) {
        return this.addButton(transform, null, width, height, texturePath, listener);
    }

    public ElementsBuilder addButton(int x, int y, int width, int height, String texturePath, ButtonListener listener) {
        return this.addButton(new MenuTransform(x, y), width, height, texturePath, listener);
    }

    public ElementsBuilder addPointer(MenuTransform transform, String id, PacketMenuElement base) {
        return this.addElement(new PacketMenuElement.Pointer(transform, id, base));
    }

    public ElementsBuilder addPointer(String id, PacketMenuElement base) {
        return this.addElement(new PacketMenuElement.Pointer(MenuTransform.DEFAULT, id, base));
    }

    public ElementsBuilder addTooltip(List<Component> lines) {
        return this.addElement(new PacketMenuElement.Tooltip(lines));
    }

    public MenuElements build() {
        return new MenuElements(this);
    }

    public interface ReplacementConfig {
        PacketMenuElement transform(PacketMenuElement current);
    }
}
