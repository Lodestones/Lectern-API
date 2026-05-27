package gg.lode.lecternapi.api.event;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * Fired when a client integrity report has been received from a client.
 * The report is a JSON string containing class hashes, code source validation,
 * JVM arguments, thread info, and classloader info.
 */
public class ClientIntegrityReportEvent extends LecternClientEvent {

    private static final HandlerList HANDLERS = new HandlerList();

    private final String reportJson;

    public ClientIntegrityReportEvent(Player player, String reportJson) {
        super(player, "INTEGRITY_REPORT_END");
        this.reportJson = reportJson;
    }

    /**
     * Gets the raw JSON string of the integrity report.
     *
     * @return the report JSON
     */
    public String getReportJson() {
        return reportJson;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
