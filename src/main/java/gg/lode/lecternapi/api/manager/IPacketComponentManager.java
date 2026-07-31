package gg.lode.lecternapi.api.manager;

import gg.lode.lecternapi.api.component.PacketComponent;
import gg.lode.lecternapi.api.ui.HudAnimation;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

/**
 * Manages the lifecycle of {@link PacketComponent} instances — showing, hiding,
 * and ticking components for individual players.
 */
public interface IPacketComponentManager {

    /**
     * Shows a component to the player, sending all element ADD packets.
     * If a component with the same ID is already shown, it is hidden first.
     */
    void show(Player player, PacketComponent component);

    /**
     * Hides a specific component by ID, sending all element REMOVE packets.
     */
    void hide(Player player, String componentId);

    /**
     * Hides all components for the player.
     */
    void hideAll(Player player);

    /**
     * Returns the active component with the given ID for the player, or null.
     */
    @Nullable
    PacketComponent getComponent(Player player, String componentId);

    /**
     * Shows a component with a group animation: the whole component — every texture, head,
     * button and text element — fades in as one unit, holds for the animation's duration
     * (forever when 0), and fades out at its end.
     */
    default void show(Player player, PacketComponent component, HudAnimation animation) {
        show(player, component);
    }

    /** Hides a component by fading its whole group out over {@code fadeOutMs}, then removing it. */
    default void hide(Player player, String componentId, int fadeOutMs) {
        hide(player, componentId);
    }

    /** Hides a component with a full exit animation — fade and scale out together. */
    default void hide(Player player, String componentId, HudAnimation exit) {
        hide(player, componentId, exit.getFadeOut());
    }
}