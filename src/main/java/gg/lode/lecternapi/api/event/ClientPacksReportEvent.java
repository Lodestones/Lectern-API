package gg.lode.lecternapi.api.event;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Fired when the Lectern client reports its installed resource packs to the server.
 */
public class ClientPacksReportEvent extends LecternClientEvent {

    private static final HandlerList HANDLERS = new HandlerList();

    private final List<String> enabledPacks;
    private final List<String> disabledPacks;

    public ClientPacksReportEvent(Player player, List<String> enabledPacks, List<String> disabledPacks) {
        super(player, "CLIENT_PACKS");
        this.enabledPacks = enabledPacks;
        this.disabledPacks = disabledPacks;
    }

    public List<String> getEnabledPacks() {
        return enabledPacks;
    }

    public List<String> getDisabledPacks() {
        return disabledPacks;
    }

    /**
     * Returns all packs (enabled + disabled).
     */
    public List<String> getPacks() {
        List<String> all = new ArrayList<>(enabledPacks.size() + disabledPacks.size());
        all.addAll(enabledPacks);
        all.addAll(disabledPacks);
        return Collections.unmodifiableList(all);
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
