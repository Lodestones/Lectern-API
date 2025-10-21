package gg.lode.lecternapi.packetmenu;

import gg.lode.lecternapi.packetmenu.text.TextAlignment;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public abstract class PacketMenuElement implements InsertionElement, PacketMenuVisualiser {
    private final MenuTransform transform;
    private final String id;

    public PacketMenuElement(MenuTransform transform, String id) {
        this.transform = transform;
        this.id = id;
    }

    @Override
    public MenuTransform transform() {
        return transform;
    }

    public String id() {
        return id;
    }

    public interface ContextIdProvider {
        String getNewElementId();
    }

    public static abstract class Sized extends PacketMenuElement {
        private final int width;
        private final int height;

        public Sized(MenuTransform transform, String id, int width, int height) {
            super(transform, id);
            this.width = width;
            this.height = height;
        }

        public int width() {
            return width;
        }

        public int height() {
            return height;
        }
    }

    public static class Component extends PacketMenuElement {
        private final float size;
        private final net.kyori.adventure.text.Component component;
        private final @Nullable TextAlignment alignment;

        public Component(MenuTransform transform, String id, float size, net.kyori.adventure.text.Component component, @Nullable TextAlignment alignment) {
            super(transform, id);
            this.size = size;
            this.component = component;
            this.alignment = alignment;
        }

        public net.kyori.adventure.text.Component component() {
            return component;
        }

        public float size() {
            return size;
        }

        public @Nullable TextAlignment alignment() {
            return alignment;
        }

        public Component withTransform(MenuTransform transform) {
            return new Component(transform, this.id(), this.size(), this.component(), this.alignment());
        }

        public Component withComponent(net.kyori.adventure.text.Component component) {
            return new Component(this.transform(), this.id(), this.size(), component, this.alignment());
        }

        public Component withAlignment(@Nullable TextAlignment alignment) {
            return new Component(this.transform(), this.id(), this.size(), this.component(), alignment);
        }

        @Override
        public PacketMenuVisualiser.PendingHide show(String baseId, PacketMenu menu, MenuTransform transform) {
            if (alignment != null) {
                menu.packetPlayer().renderComponent(
                        baseId, this.component,
                        transform.x(), transform.y(), transform.layer(),
                        transform.scale() * this.size(),
                        transform.horizontalAlignment(),
                        transform.verticalAlignment(),
                        alignment
                );
            } else {
                menu.packetPlayer().renderComponent(
                        baseId, this.component,
                        transform.x(), transform.y(), transform.layer(),
                        transform.scale() * this.size(),
                        transform.horizontalAlignment(),
                        transform.verticalAlignment()
                );
            }
            return () -> menu.packetPlayer().unrenderComponent(baseId);
        }
    }

    public static class Texture extends Sized {
        private final String texturePath;

        public Texture(MenuTransform transform, String id, int width, int height, String texturePath) {
            super(transform, id, width, height);
            this.texturePath = texturePath;
        }

        public String texturePath() {
            return texturePath;
        }

        public Texture withTransform(MenuTransform transform) {
            return new Texture(transform, this.id(), this.width(), this.height(), this.texturePath());
        }

        public Texture withTexture(int width, int height, String texturePath) {
            return new Texture(this.transform(), this.id(), width, height, texturePath);
        }

        public Texture withTexture(String texturePath) {
            return withTexture(this.width(), this.height(), texturePath);
        }

        @Override
        public PacketMenuVisualiser.PendingHide show(String baseId, PacketMenu menu, MenuTransform transform) {
            menu.packetPlayer().renderTexture(
                    baseId, this.texturePath,
                    transform.x(), transform.y(), transform.layer(),
                    (int) (transform.scale() * this.width()),
                    (int) (transform.scale() * this.height()),
                    transform.alpha(),
                    transform.horizontalAlignment(),
                    transform.verticalAlignment()
            );
            return () -> menu.packetPlayer().unrenderTexture(baseId);
        }
    }

    public static class Button extends Sized {
        private final String texturePath;

        public Button(MenuTransform transform, String id, int width, int height, String texturePath) {
            super(transform, id, width, height);
            this.texturePath = texturePath;
        }

        public String texturePath() {
            return texturePath;
        }

        public Button withTransform(MenuTransform transform) {
            return new Button(transform, this.id(), this.width(), this.height(), this.texturePath());
        }

        public Button withTexture(int width, int height, String texturePath) {
            return new Button(this.transform(), this.id(), width, height, texturePath);
        }

        public Button withTexture(String texturePath) {
            return withTexture(this.width(), this.height(), texturePath);
        }

        @Override
        public PacketMenuVisualiser.PendingHide show(String baseId, PacketMenu menu, MenuTransform transform) {
            menu.packetPlayer().renderButton(
                    baseId, this.texturePath,
                    transform.x(), transform.y(),
                    (int) (transform.scale() * width()),
                    (int) (transform.scale() * height()),
                    transform.alpha(),
                    transform.horizontalAlignment(),
                    transform.verticalAlignment()
            );
            return () -> menu.packetPlayer().unrenderButton(baseId);
        }

        public record Insertion(Button button, ButtonListener listener) implements InsertionElement {
        }
    }

    public static class Pointer extends PacketMenuElement {
        private final PacketMenuElement base;

        public Pointer(MenuTransform transform, String id, PacketMenuElement base) {
            super(transform, id);
            this.base = base;
        }

        public PacketMenuElement base() {
            return base;
        }

        @Override
        public PacketMenuVisualiser.PendingHide show(String baseId, PacketMenu menu, MenuTransform transform) {
            var inner = menu.masterCompound().replacementOf(this.id());
            transform = transform.combine(this.transform());
            if (inner != null) {
                var transformed = inner.transform(this.base);
                return transformed.show(baseId + transformed.id(), menu, transform.combine(transformed.transform()));
            } else {
                return this.base.show(baseId + this.base.id(), menu, transform.combine(this.base.transform()));
            }
        }
    }

    public static class Tooltip extends PacketMenuElement {
        private final List<net.kyori.adventure.text.Component> lines;

        public Tooltip(List<net.kyori.adventure.text.Component> lines) {
            super(new MenuTransform(0, 0), "tooltip");
            this.lines = lines;
        }

        public List<net.kyori.adventure.text.Component> lines() {
            return lines;
        }

        public Tooltip withTitle(net.kyori.adventure.text.Component title) {
            return new Tooltip(this.lines());
        }

        public Tooltip withDescription(List<net.kyori.adventure.text.Component> description) {
            return new Tooltip(description);
        }

        @Override
        public PacketMenuVisualiser.PendingHide show(String baseId, PacketMenu menu, MenuTransform transform) {
            menu.packetPlayer().renderCustomTooltip(this.lines());

            return () -> menu.packetPlayer().resetCustomTooltip();
        }
    }
}
