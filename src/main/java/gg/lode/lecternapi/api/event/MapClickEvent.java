package gg.lode.lecternapi.api.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * A player clicked a place on their Lectern map.
 *
 * <p>The position is where they clicked <b>in the world</b>, not on their screen — the client turns
 * the pixel back into world coordinates before sending it, so a plugin gets somewhere it can act on
 * without knowing anything about the size of anyone's map or how far it is zoomed.
 *
 * <p>Nothing is drawn as a result. What a click means is the server's decision: a ping others can
 * see, a marker for the team, a waypoint, or nothing at all. Draw it back with
 * {@code IMapManager.update(...)} and it appears on every map showing that id.
 *
 * <pre>{@code
 * @EventHandler
 * public void onMapClick(MapClickEvent event) {
 *     if (!event.isPing()) return;
 *     for (Player teammate : team.of(event.getPlayer())) {
 *         api.getMapManager().update(teammate, event.getMapId(), List.of(
 *                 MapShape.marker("ping:" + event.getPlayer().getName(), event.getX(), event.getZ())
 *                         .icon(iconFor(event.getPingType()))
 *                         .label(event.getPlayer().getName())), 0);
 *     }
 * }
 * }</pre>
 */
public class MapClickEvent extends Event {

    private static final HandlerList HANDLERS = new HandlerList();

    private final Player player;
    private final String mapId;
    private final double x;
    private final double z;
    private final int button;
    private final String pingType;

    public MapClickEvent(Player player, String mapId, double x, double z, int button, String pingType) {
        this.player = player;
        this.mapId = mapId == null ? "default" : mapId;
        this.x = x;
        this.z = z;
        this.button = button;
        this.pingType = pingType == null ? "" : pingType;
    }

    public Player getPlayer() { return player; }

    /** Which map was clicked — the id its overlays are sent under. */
    public String getMapId() { return mapId; }

    /** World x of the click. There is no y: a map is a plan, and the ground is where it lands. */
    public double getX() { return x; }

    public double getZ() { return z; }

    /** 0 for left, 1 for right. */
    public int getButton() { return button; }

    /**
     * The emote wheel slot chosen for a right-click, or empty.
     *
     * <p>Right-clicking opens the wheel so the player says what kind of ping this is, and the slot
     * they pick arrives here. A left click sends no type: it is the quick one, and deciding what
     * that means is the server's business.
     */
    public String getPingType() { return pingType; }

    /** Whether this is a plain left-click ping rather than a typed one. */
    public boolean isPing() { return button == 0; }

    @Override
    public @NotNull HandlerList getHandlers() { return HANDLERS; }

    public static HandlerList getHandlerList() { return HANDLERS; }
}
