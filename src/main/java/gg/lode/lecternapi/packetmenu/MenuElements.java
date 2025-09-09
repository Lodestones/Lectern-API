package gg.lode.lecternapi.packetmenu;

import org.apache.commons.lang3.tuple.Pair;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuElements implements InsertionElement, PacketMenuVisualiser {
    final MenuTransform transform;
    final List<PacketMenuElement> elements;
    final List<MenuElements> internal;
    final List<Runnable> closeActions;
    final Map<String, List<ButtonListener>> anyButtonListeners;
    final Map<String, ButtonListener> buttonListeners;
    final Map<String, ElementsBuilder.ReplacementConfig> replacementPointers;

    MenuElements(ElementsBuilder builder) {
        this.transform = builder.transform;
        this.elements = List.copyOf(builder.elements);
        this.internal = List.copyOf(builder.internal);
        this.closeActions = List.copyOf(builder.closeActions);
        this.anyButtonListeners = Map.copyOf(builder.anyButtonListeners);
        this.buttonListeners = Map.copyOf(builder.buttonListeners);
        this.replacementPointers = Map.copyOf(builder.replacementPointers);
    }

    @Override
    public MenuTransform transform() {
        return transform;
    }

    private void pushIndexes(String baseId, MasterCompound masterCompound) {
        for (var entry : this.anyButtonListeners.entrySet()) {
            masterCompound.anyButtonListeners.computeIfAbsent(
                    entry.getKey(), (x) -> new ArrayList<>()).addAll(entry.getValue()
            );
        }
        for (var entry : this.buttonListeners.entrySet()) {
            if (masterCompound.buttonListeners.put(
                    baseId + entry.getKey(),
                    Pair.of(entry.getKey(), entry.getValue())
            ) != null)
                throw new IllegalStateException("Duplicated button action: " + entry.getKey() + " with base: " + baseId);
        }
        for (var entry : this.replacementPointers.entrySet()) {
            if (masterCompound.indexedReplacementPointers.put(entry.getKey(), entry.getValue()) != null)
                throw new IllegalStateException("Duplicated replacement pointer: " + entry.getKey() + " with base: " + baseId);
        }
        for (var i = 0; i < this.internal.size(); i++) {
            var inner = this.internal.get(i);
            inner.pushIndexes(baseId + "inner" + i, masterCompound);
        }
    }

    @Override
    public PendingHide show(String baseId, PacketMenu menu, MenuTransform transform) {
        var hidingList = new ArrayList<PendingHide>(this.elements.size() + this.internal.size());

        for (var element : this.elements) {
            hidingList.add(element.show(baseId + element.id(), menu, transform.combine(element.transform())));
        }
        for (var i = 0; i < this.internal.size(); i++) {
            var inner = this.internal.get(i);
            hidingList.add(inner.show(baseId + "inner" + i, menu, transform.combine(inner.transform)));
        }

        return () -> hidingList.forEach(PacketMenuVisualiser.PendingHide::hide);
    }


    private void onClose() {
        for (var action : closeActions) {
            action.run();
        }
        for (var inner : internal) {
            inner.onClose();
        }
    }

    public static class MasterCompound {
        private final PacketMenu menu;
        private final MenuElements mainElements;
        private final HashMap<String, Pair<String, ButtonListener>> buttonListeners = new HashMap<>();
        private final HashMap<String, List<ButtonListener>> anyButtonListeners = new HashMap<>();
        private final HashMap<String, ElementsBuilder.ReplacementConfig> indexedReplacementPointers = new HashMap<>();

        private final HashMap<String, List<ButtonListener.PendingUnHover>> pendingUnHover = new HashMap<>();

        MasterCompound(PacketMenu menu, MenuElements mainElements) {
            this.menu = menu;
            this.mainElements = mainElements;

            this.mainElements.pushIndexes(menu.baseId(), this);
        }

        public PendingHide show() {
            var toHide = this.mainElements.show(this.menu.baseId(), this.menu, this.mainElements.transform);

            return () -> {
                for (var hoverList : this.pendingUnHover.values()) {
                    for (var toUnHover : hoverList) {
                        toUnHover.onUnHover();
                    }
                }
                this.pendingUnHover.clear();

                toHide.hide();
                this.mainElements.onClose();
            };
        }

        public void onButtonClick(String buttonId) {
            var action = this.buttonListeners.get(buttonId);
            if (action == null) return;

            var origin = new ButtonListener.Origin.Direct(buttonId, this.menu);

            action.getRight().onClick(origin);
            var listeners = this.anyButtonListeners.get(action.getLeft());
            if (listeners != null) for (var i = 0; i < listeners.size(); i++) {
                var listener = listeners.get(i);
                listener.onClick(new ButtonListener.Origin.AnyFork(origin, i));
            }
        }

        public void onButtonHover(String buttonId, boolean hovering) {
            if (hovering) {
                var action = this.buttonListeners.get(buttonId);
                if (action == null) return;

                var origin = new ButtonListener.Origin.Direct(buttonId, this.menu);
                var hoverList = this.pendingUnHover.computeIfAbsent(buttonId, (x) -> new ArrayList<>());

                hoverList.add(action.getRight().onHover(origin));
                var listeners = this.anyButtonListeners.get(action.getLeft());
                if (listeners != null) for (var i = 0; i < listeners.size(); i++) {
                    var listener = listeners.get(i);
                    hoverList.add(listener.onHover(new ButtonListener.Origin.AnyFork(origin, i)));
                }
            } else {
                var removed = this.pendingUnHover.remove(buttonId);
                if (removed != null) for (var toUnHover : removed) {
                    toUnHover.onUnHover();
                }
            }
        }

        public @Nullable ElementsBuilder.ReplacementConfig replacementOf(String pointerId) {
            return this.indexedReplacementPointers.get(pointerId);
        }
    }
}
