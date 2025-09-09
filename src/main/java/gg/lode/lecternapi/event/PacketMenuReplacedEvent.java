package gg.lode.lecternapi.event;

import gg.lode.bookshelfapi.api.event.BaseEvent;
import gg.lode.lecternapi.packetmenu.PacketMenu;

public class PacketMenuReplacedEvent extends BaseEvent {
    private final PacketMenu currentMenu;
    private final PacketMenu newMenu;
    private Result result = Result.IGNORE;

    public PacketMenuReplacedEvent(PacketMenu currentMenu, PacketMenu newMenu) {
        this.currentMenu = currentMenu;
        this.newMenu = newMenu;
    }

    public PacketMenu currentMenu() {
        return currentMenu;
    }

    public PacketMenu newMenu() {
        return newMenu;
    }

    public Result result() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public enum Result {
        IGNORE,
        IGNORE_CLOSING,
    }
}
