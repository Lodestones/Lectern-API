package gg.lode.lecternapi.event;

import gg.lode.bookshelfapi.api.event.BaseEvent;
import gg.lode.lecternapi.packetmenu.PacketMenu;
import org.jetbrains.annotations.Nullable;

public class PacketMenuCloseEvent extends BaseEvent {
    private final PacketMenu menu;
    private final @Nullable PacketMenu newMenu;

    public PacketMenuCloseEvent(PacketMenu menu, @Nullable PacketMenu newMenu) {
        this.menu = menu;
        this.newMenu = newMenu;
    }

    public PacketMenu getMenu() {
        return menu;
    }

    public @Nullable PacketMenu newMenu() {
        return newMenu;
    }
}
