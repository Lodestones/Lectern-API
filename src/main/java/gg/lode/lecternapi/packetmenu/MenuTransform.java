package gg.lode.lecternapi.packetmenu;

import gg.lode.lecternapi.packetmenu.text.HorizontalAlignment;
import gg.lode.lecternapi.packetmenu.text.VerticalAlignment;
import org.bukkit.configuration.ConfigurationSection;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.Objects;

public record MenuTransform(int x, int y, int layer, float scale, float alpha,
                            @Nullable HorizontalAlignment horizontalAlignment,
                            @Nullable VerticalAlignment verticalAlignment) {
    public static final MenuTransform DEFAULT = new MenuTransform(0, 0, 1, 1f, 1f, null, null);

    public MenuTransform(int x, int y) {
        this(x, y, DEFAULT.layer, DEFAULT.scale, DEFAULT.alpha, DEFAULT.horizontalAlignment, DEFAULT.verticalAlignment);
    }

    public MenuTransform(int x, int y, int layer) {
        this(x, y, layer, DEFAULT.scale, DEFAULT.alpha, DEFAULT.horizontalAlignment, DEFAULT.verticalAlignment);
    }
    
    public MenuTransform withAlignments(HorizontalAlignment horizontalAlignment, VerticalAlignment verticalAlignment) {
        return new MenuTransform(x, y, layer, scale, alpha, horizontalAlignment, verticalAlignment);
    }

    public MenuTransform withTranslation(int x, int y) {
        return new MenuTransform(x, y, this.layer, this.scale, this.alpha, this.horizontalAlignment, this.verticalAlignment);
    }

    public MenuTransform withLayer(int layer) {
        return new MenuTransform(this.x, this.y, layer, this.scale, this.alpha, this.horizontalAlignment, this.verticalAlignment);
    }

    public MenuTransform withScale(float scale) {
        return new MenuTransform(this.x, this.y, this.layer, scale, this.alpha, this.horizontalAlignment, this.verticalAlignment);
    }

    public MenuTransform withAlpha(float alpha) {
        return new MenuTransform(this.x, this.y, this.layer, this.scale, alpha, this.horizontalAlignment, this.verticalAlignment);
    }

    public MenuTransform combine(MenuTransform parent) {
        return new MenuTransform(
                (int) (this.x + this.scale * parent.x),
                (int) (this.y + this.scale * parent.y),
                this.layer + parent.layer,
                this.scale * parent.scale,
                this.alpha * parent.alpha,
                this.horizontalAlignment == null ? parent.horizontalAlignment : this.horizontalAlignment,
                this.verticalAlignment == null ? parent.verticalAlignment : this.verticalAlignment
        );
    }

    @Override
    public @NotNull HorizontalAlignment horizontalAlignment() {
        return Objects.requireNonNullElse(horizontalAlignment, HorizontalAlignment.CENTER);
    }

    @Override
    public @NotNull VerticalAlignment verticalAlignment() {
        return Objects.requireNonNullElse(verticalAlignment, VerticalAlignment.CENTER);
    }

    public static MenuTransform deserialize(ConfigurationSection raw) {
        return new MenuTransform(
                raw.getInt("x", DEFAULT.x),
                raw.getInt("y", DEFAULT.y),
                raw.getInt("layer", DEFAULT.layer),
                (float) raw.getDouble("scale", DEFAULT.scale),
                (float) raw.getDouble("alpha", DEFAULT.alpha),
                raw.get("horizontal_alignment") instanceof String s1 ? HorizontalAlignment.valueOf(s1) : null,
                raw.get("vertical_alignment") instanceof String s1 ? VerticalAlignment.valueOf(s1) : null
        );
    }
}
