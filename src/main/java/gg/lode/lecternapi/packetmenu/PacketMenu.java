package gg.lode.lecternapi.packetmenu;

import gg.lode.lecternapi.data.IPacketPlayer;
import gg.lode.lecternapi.LecternAPI;
import gg.lode.lecternapi.event.PacketMenuCloseEvent;
import gg.lode.lecternapi.event.PacketMenuOpenEvent;
import gg.lode.lecternapi.event.PacketMenuReplacedEvent;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public abstract class PacketMenu {
    private final String baseId;
    private final Player player;
    private final IPacketPlayer packetPlayer;

    private MenuElements.MasterCompound masterCompound;

    public PacketMenu(String baseId, Player player) {
        this.baseId = baseId;
        this.player = player;
        this.packetPlayer = Objects.requireNonNull(LecternAPI.getApi().getPlayer(player));
    }

    public String baseId() {
        return baseId;
    }

    public Player player() {
        return player;
    }

    public IPacketPlayer packetPlayer() {
        return packetPlayer;
    }

    public MenuElements.MasterCompound masterCompound() {
        return masterCompound;
    }

    protected abstract ElementsBuilder getElementsBuilder();

    protected void init() {
        if (this.masterCompound != null) throw new IllegalStateException("PacketMenu already initialized.");

        this.masterCompound = new MenuElements.MasterCompound(this, getElementsBuilder().build());
    }

    public final void open() {
        if (this.masterCompound == null) init();

        LecternAPI.getApi().getPacketMenuManager().registerAndOpen(this);
    }

    public final void close() {
        LecternAPI.getApi().getPacketMenuManager().unregisterAndClose(this);
    }

    /**
     * null -> no background and no cursor
     * <p>
     * 0.0f to 1.0f -> cursor, with background blur
     */
    public @Nullable Float backgroundBlur() {
        return 1.0f;
    }

    public void onReplaced(PacketMenuReplacedEvent info) {
    }

    public void onReplacing(PacketMenuReplacedEvent info) {
    }

    public void onOpened(PacketMenuOpenEvent event) {
    }

    public void onClosed(PacketMenuCloseEvent event) {
    }
}
