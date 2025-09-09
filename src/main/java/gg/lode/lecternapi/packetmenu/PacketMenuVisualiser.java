package gg.lode.lecternapi.packetmenu;

public interface PacketMenuVisualiser {
    MenuTransform transform();

    PendingHide show(String baseId, PacketMenu menu, MenuTransform transform);

    interface PendingHide {
        void hide();
    }
}
