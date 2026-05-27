package gg.lode.lecternapi.api.event;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * Fired when a player changes a widget value on a config screen.
 * The value type depends on the widget: boolean for toggles, float for sliders,
 * int for selectors (index), String for edit boxes and buttons (reference).
 */
public class ConfigScreenChangeEvent extends LecternClientEvent {

    private static final HandlerList HANDLERS = new HandlerList();

    private final String screenId;
    private final String widgetReference;
    private final Object value;

    public ConfigScreenChangeEvent(Player player, String screenId, String widgetReference, Object value) {
        super(player, "CONFIG_SCREEN_CHANGE");
        this.screenId = screenId;
        this.widgetReference = widgetReference;
        this.value = value;
    }

    public String getScreenId() {
        return screenId;
    }

    public String getWidgetReference() {
        return widgetReference;
    }

    public Object getValue() {
        return value;
    }

    public boolean getValueAsBoolean() {
        return (Boolean) value;
    }

    public float getValueAsFloat() {
        return ((Number) value).floatValue();
    }

    public int getValueAsInt() {
        return ((Number) value).intValue();
    }

    public String getValueAsString() {
        return (String) value;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
