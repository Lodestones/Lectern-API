package gg.lode.lecternapi.manager;

import gg.lode.lecternapi.ILecternAPI;
import gg.lode.lecternapi.packetmenu.*;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import javax.annotation.Nullable;

/**
 * The packet menu manager is used to build custom-made menus for players.
 * Allowing you to render, text components, images, buttons, etc.
 * You can get the manager using the {@link ILecternAPI#getPacketMenuManager()}
 * method.
 * <p>
 * For more information about how to create packet menus, you can find our
 * documentation at:
 * <a href="https://lode.gg/docs/lectern/features/packet-menus">...</a>
 */
public interface IPacketMenuManager {

    /**
     * Gets a template by its id.
     * Templates can be found in the "plugins/Lectern/packet_menu.yml" file.
     * 
     * @param id The id of the template
     * @return The template
     */
    @Nullable
    MenuElements getTemplate(String id);

    /**
     * Gets the open menu for a player.
     * 
     * @param player The player
     * @return The open menu
     */
    @Nullable
    PacketMenu getOpenMenu(Player player);

    /**
     * Registers and opens a menu.
     * 
     * @param menu The menu to register and open
     */
    void registerAndOpen(PacketMenu menu);

    /**
     * Unregisters and closes a menu.
     * 
     * @param menu The menu to unregister and close
     */
    void unregisterAndClose(PacketMenu menu);

    /**
     * Deserializes an insertion element.
     * 
     * @param idProvider The id provider
     * @param raw        The raw element
     * @return The deserialized element
     */
    InsertionElement deserializeInsertion(
            PacketMenuElement.ContextIdProvider idProvider,
            ConfigurationSection raw);

    /**
     * Deserializes elements.
     * 
     * @param raw The raw elements
     * @return The deserialized elements
     */
    ElementsBuilder deserializeElements(ConfigurationSection raw);

}
