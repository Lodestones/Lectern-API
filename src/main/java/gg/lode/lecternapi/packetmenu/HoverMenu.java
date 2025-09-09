package gg.lode.lecternapi.packetmenu;

public class HoverMenu implements ButtonListener {
    private final PacketMenuVisualiser visualiser;

    public HoverMenu(PacketMenuVisualiser visualiser) {
        this.visualiser = visualiser;
    }

    @Override
    public void onClick(Origin origin) {
    }

    @Override
    public PendingUnHover onHover(Origin origin) {
        var toHide = visualiser.show(origin.extractNewId(), origin.menu(), visualiser.transform());
        return toHide::hide;
    }
}
