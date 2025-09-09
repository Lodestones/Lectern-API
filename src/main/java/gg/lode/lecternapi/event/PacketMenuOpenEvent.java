package gg.lode.lecternapi.event;

import gg.lode.bookshelfapi.api.event.BaseEvent;
import gg.lode.lecternapi.packetmenu.PacketMenu;
import org.jetbrains.annotations.Nullable;

public class PacketMenuOpenEvent extends BaseEvent {
    private final PacketMenu menu;
    private final @Nullable PacketMenu previousMenu;

    public PacketMenuOpenEvent(PacketMenu menu, @Nullable PacketMenu previousMenu) {
        this.menu = menu;
        this.previousMenu = previousMenu;
    }

    public PacketMenu getMenu() {
        return menu;
    }

    public @Nullable PacketMenu previousMenu() {
        return previousMenu;
    }
}
