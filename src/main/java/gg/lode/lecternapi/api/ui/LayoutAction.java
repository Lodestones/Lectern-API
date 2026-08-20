package gg.lode.lecternapi.api.ui;

import java.util.Locale;

/**
 * Something the server does when a layout element is interacted with.
 * <p>
 * Actions are the reason a layout can be a menu rather than a display. An element names itself with
 * a reference; an action binds a trigger on that reference to an effect — run a command, play a
 * sound, open another layout. The client reports the interaction, the server decides what it means,
 * which is the only arrangement that keeps a clicked button from being something a client can
 * simply claim to have run.
 * <p>
 * An action with an empty {@link #element()} belongs to the layout itself rather than to any one
 * element, which is how {@link Trigger#OPEN} and {@link Trigger#CLOSE} are usually written.
 *
 * @param element the element reference this fires for, or empty for the layout as a whole
 * @param trigger what has to happen for it to fire
 * @param type    what to do
 * @param value   the argument for {@code type} — a command, a sound key, a destination
 */
public record LayoutAction(String element, Trigger trigger, Type type, String value) {

    /** What has to happen for an action to fire. */
    public enum Trigger {
        /** Left click, in GUI mode. */
        CLICK,
        /** Right click, in GUI mode. */
        RIGHT_CLICK,
        /** The cursor entering the element, in GUI mode. */
        HOVER,
        /**
         * The cursor leaving the element, in GUI mode.
         *
         * <p>The counterpart to {@link #HOVER}. Without it a server can tell that a pointer arrived
         * but never that it left, so anything switched on by a hover had no signal to switch it
         * back off again.
         */
        HOVER_END,
        /** The layout being opened. Fires server-side; no client interaction needed. */
        OPEN,
        /** The layout being closed, for any reason. Fires server-side. */
        CLOSE;

        public String getId() {
            return name().toLowerCase(Locale.ROOT);
        }

        /** Resolves a trigger by wire id, or null if unknown. */
        public static Trigger byId(String id) {
            if (id == null) return null;
            String normalized = id.trim().toUpperCase(Locale.ROOT);
            for (Trigger trigger : values()) {
                if (trigger.name().equals(normalized)) return trigger;
            }
            return null;
        }

        /**
         * Whether this trigger needs the client to report it.
         * <p>
         * Open and close are decided by the server, so they work regardless of what the client
         * supports; the pointer triggers only fire on a client that sends interactions back.
         */
        public boolean requiresClient() {
            return this == CLICK || this == RIGHT_CLICK || this == HOVER || this == HOVER_END;
        }
    }

    /** What an action does. */
    public enum Type {
        /** Runs {@code value} as the player, as if they typed it. */
        COMMAND,
        /** Runs {@code value} from console. */
        CONSOLE,
        /** Sends {@code value} to the player as a MiniMessage component. */
        MESSAGE,
        /** Plays {@code value} as a sound key at the player. */
        SOUND,
        /** Teleports the player. {@code value} is {@code world x y z} or {@code world x y z yaw pitch}. */
        TELEPORT,
        /** Opens another layout by id. */
        REDIRECT,
        /** Closes the layout. */
        CLOSE;

        public String getId() {
            return name().toLowerCase(Locale.ROOT);
        }

        /** Resolves a type by wire id, or null if unknown. */
        public static Type byId(String id) {
            if (id == null) return null;
            String normalized = id.trim().toUpperCase(Locale.ROOT);
            for (Type type : values()) {
                if (type.name().equals(normalized)) return type;
            }
            return null;
        }
    }

    public LayoutAction {
        element = element == null ? "" : element.trim();
        trigger = trigger == null ? Trigger.CLICK : trigger;
        type = type == null ? Type.COMMAND : type;
        value = value == null ? "" : value;
    }

    /** Whether this action belongs to the layout rather than to a single element. */
    public boolean isLayoutScoped() {
        return element.isEmpty();
    }
}
