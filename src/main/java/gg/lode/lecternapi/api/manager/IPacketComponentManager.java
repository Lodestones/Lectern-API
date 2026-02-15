package gg.lode.lecternapi.api.manager;

import gg.lode.lecternapi.api.component.PacketComponent;

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
}
