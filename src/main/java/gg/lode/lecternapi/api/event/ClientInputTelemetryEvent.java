package gg.lode.lecternapi.api.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Fired when a client with input telemetry enabled uploads a batch of raw
 * input events. Batches arrive roughly once per second while the player is
 * connected and telemetry is on.
 * <p>
 * Each record carries key identity and timing only — never typed text.
 * Anti-cheat consumers are expected to look at distributions across many
 * batches (click interval variance, crosshair-to-click latency) rather than
 * to judge any single event.
 */
public class ClientInputTelemetryEvent extends Event {

    private static final HandlerList HANDLERS = new HandlerList();

    /**
     * A single key or mouse transition.
     *
     * @param mouse           true for a mouse button, false for a keyboard key
     * @param code            GLFW key code, or mouse button index when {@code mouse}
     * @param scancode        platform scancode (0 for mouse)
     * @param pressed         true on press, false on release
     * @param timestampMs     client wall-clock time of the transition
     * @param boundTo         the keybind id this input triggers, or "" if unbound
     * @param onEntity        whether the crosshair was on an entity at the time
     * @param crosshairHeldMs how long that entity had been under the crosshair,
     *                        or -1 when the crosshair was not on an entity
     */
    public record InputRecord(
            boolean mouse,
            int code,
            int scancode,
            boolean pressed,
            long timestampMs,
            String boundTo,
            boolean onEntity,
            long crosshairHeldMs
    ) {
        /** True when this input is bound to nothing in the player's controls. */
        public boolean isUnbound() {
            return boundTo == null || boundTo.isEmpty();
        }
    }

    private final Player player;
    private final List<InputRecord> records;
    private final int droppedRecords;

    public ClientInputTelemetryEvent(Player player, List<InputRecord> records, int droppedRecords) {
        this.player = player;
        this.records = records;
        this.droppedRecords = droppedRecords;
    }

    public Player getPlayer() {
        return player;
    }

    public List<InputRecord> getRecords() {
        return records;
    }

    /**
     * How many events the client discarded because its buffer filled between
     * flushes. A consistently non-zero count means the batch is incomplete and
     * timing statistics computed from it will have gaps.
     */
    public int getDroppedRecords() {
        return droppedRecords;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
