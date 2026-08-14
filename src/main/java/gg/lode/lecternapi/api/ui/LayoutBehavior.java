package gg.lode.lecternapi.api.ui;

/**
 * How a layout behaves once it is more than a picture: when it opens, when it closes, and what the
 * player can still do while it is up.
 * <p>
 * A {@link HudLayout} on its own is geometry and animation — the server decides when to play it.
 * A layout authored in the UI editor can instead carry its own rules, and this is them: the command
 * that opens it, whether it opens on join, whether taking a hit closes it. That turns a layout from
 * something a plugin has to drive into something a server owner can drop in a folder and use.
 * <p>
 * Read off the {@code behavior} block of an exported layout by
 * {@code LayoutPageSerializer}, so a server owner never writes one of these by hand — though a
 * plugin may, to open a page under rules of its own.
 *
 * <pre>{@code
 * LayoutPage page = api.getLayoutManager().getPage("stats");
 * if (page.getBehavior().getMode() == Mode.GUI) {
 *     api.getLayoutManager().open(player, "stats");
 * }
 * }</pre>
 */
public class LayoutBehavior {

    /**
     * Whether the layout sits over gameplay or takes the screen.
     * <p>
     * {@link #HUD} draws on top of the world and the player keeps playing. {@link #GUI} opens a
     * screen: the mouse becomes a cursor, movement stops, and elements can be clicked — which is
     * what makes {@link LayoutAction} triggers other than open and close reachable at all.
     */
    public enum Mode {
        HUD,
        GUI;

        /** Wire identifier sent to the client. */
        public String getId() {
            return name().toLowerCase(java.util.Locale.ROOT);
        }

        /** Resolves a mode by wire id; unknown names fall back to {@link #HUD}. */
        public static Mode byId(String id) {
            if (id == null) return HUD;
            return "gui".equalsIgnoreCase(id.trim()) ? GUI : HUD;
        }
    }

    /** The defaults an exported layout omits: a plain HUD overlay that nothing closes for you. */
    public static final LayoutBehavior DEFAULT = new LayoutBehavior();

    private Mode mode = Mode.HUD;
    private boolean openOnJoin;
    private int openDelayTicks;
    private String command = "";
    private boolean closeOnDeath;
    private boolean closeOnDamage;
    private boolean keepOpen;
    private boolean closeReversed;
    private boolean moveWhileClosing;
    private boolean displayHotbar = true;
    private boolean displayHand = true;
    private boolean hudOnClose;
    private boolean hideHud;
    private boolean topmost;
    private final java.util.Set<String> hiddenHudElements = new java.util.LinkedHashSet<>();
    private float cursorSize = 10f;
    private float cursorSpeed = 1f;

    public LayoutBehavior mode(Mode mode) {
        this.mode = mode == null ? Mode.HUD : mode;
        return this;
    }

    /** Opens the layout for a player as they join. */
    public LayoutBehavior openOnJoin(boolean openOnJoin) {
        this.openOnJoin = openOnJoin;
        return this;
    }

    /**
     * Ticks to wait after join before opening.
     * <p>
     * Worth setting for anything that reads player state: on the join tick a player's position,
     * inventory and scoreboard are still settling, so a layout opened immediately can show values
     * that are correct for a moment and then wrong.
     */
    public LayoutBehavior openDelayTicks(int ticks) {
        this.openDelayTicks = Math.max(0, ticks);
        return this;
    }

    /** The command that opens this layout, without its leading slash. Empty registers nothing. */
    public LayoutBehavior command(String command) {
        this.command = command == null ? "" : command.trim();
        return this;
    }

    public LayoutBehavior closeOnDeath(boolean closeOnDeath) {
        this.closeOnDeath = closeOnDeath;
        return this;
    }

    public LayoutBehavior closeOnDamage(boolean closeOnDamage) {
        this.closeOnDamage = closeOnDamage;
        return this;
    }

    /** Keeps this layout up when another one opens, instead of being replaced by it. */
    public LayoutBehavior keepOpen(boolean keepOpen) {
        this.keepOpen = keepOpen;
        return this;
    }

    /** Plays the open animation backwards on close, rather than cutting to nothing. */
    public LayoutBehavior closeReversed(boolean closeReversed) {
        this.closeReversed = closeReversed;
        return this;
    }

    /** Lets the player move while a GUI layout's closing animation plays. */
    public LayoutBehavior moveWhileClosing(boolean moveWhileClosing) {
        this.moveWhileClosing = moveWhileClosing;
        return this;
    }

    public LayoutBehavior displayHotbar(boolean displayHotbar) {
        this.displayHotbar = displayHotbar;
        return this;
    }

    public LayoutBehavior displayHand(boolean displayHand) {
        this.displayHand = displayHand;
        return this;
    }

    /**
     * Draws the layout above Minecraft's own HUD, including chat, the scoreboard and the tab list.
     * <p>
     * Off by default, and deliberately so: Lectern draws before the vanilla HUD precisely so a
     * badge can sit behind the scoreboard rather than over the top of it. A layout that is the
     * whole screen for a moment — a cutscene, a title card — wants the opposite, and this is it.
     */
    public LayoutBehavior topmost(boolean topmost) {
        this.topmost = topmost;
        return this;
    }

    /**
     * Hides the vanilla HUD for as long as the layout is up, the way F1 does.
     * <p>
     * The layout itself keeps drawing — this suppresses Minecraft's own interface, not Lectern's,
     * so a cutscene or a full-screen page can own the view without the player's hearts and hotbar
     * showing through it. Nothing is toggled on the player's settings: the moment the layout stops,
     * the HUD is back exactly as they left it.
     */
    public LayoutBehavior hideHud(boolean hideHud) {
        this.hideHud = hideHud;
        return this;
    }

    /**
     * Hides individual vanilla HUD elements rather than all of them.
     * <p>
     * Recognised names are {@code hotbar}, {@code hearts}, {@code hunger}, {@code armor},
     * {@code oxygen} and {@code experience} — anything else is ignored rather than rejected, so a
     * layout naming an element a newer client knows about still loads here.
     */
    public LayoutBehavior hideHudElements(java.util.Collection<String> elements) {
        hiddenHudElements.clear();
        if (elements != null) {
            for (String element : elements) {
                if (element != null && !element.isBlank()) {
                    hiddenHudElements.add(element.trim().toLowerCase(java.util.Locale.ROOT));
                }
            }
        }
        return this;
    }

    /** Leaves the layout drawing as a HUD overlay once the GUI screen closes. */
    public LayoutBehavior hudOnClose(boolean hudOnClose) {
        this.hudOnClose = hudOnClose;
        return this;
    }

    /** Cursor size in GUI mode. 10 is the recommended default. */
    public LayoutBehavior cursorSize(float cursorSize) {
        this.cursorSize = Math.max(1f, cursorSize);
        return this;
    }

    /** Cursor speed multiplier in GUI mode. */
    public LayoutBehavior cursorSpeed(float cursorSpeed) {
        this.cursorSpeed = Math.max(0.1f, cursorSpeed);
        return this;
    }

    public Mode getMode() { return mode; }
    public boolean isOpenOnJoin() { return openOnJoin; }
    public int getOpenDelayTicks() { return openDelayTicks; }
    public String getCommand() { return command; }
    public boolean isCloseOnDeath() { return closeOnDeath; }
    public boolean isCloseOnDamage() { return closeOnDamage; }
    public boolean isKeepOpen() { return keepOpen; }
    public boolean isCloseReversed() { return closeReversed; }
    public boolean isMoveWhileClosing() { return moveWhileClosing; }
    public boolean isDisplayHotbar() { return displayHotbar; }
    public boolean isDisplayHand() { return displayHand; }
    public boolean isHudOnClose() { return hudOnClose; }
    public boolean isHideHud() { return hideHud; }
    public boolean isTopmost() { return topmost; }
    /** Unmodifiable; edit through {@link #hideHudElements(java.util.Collection)}. */
    public java.util.Set<String> getHiddenHudElements() {
        return java.util.Collections.unmodifiableSet(hiddenHudElements);
    }
    public float getCursorSize() { return cursorSize; }
    public float getCursorSpeed() { return cursorSpeed; }

    /** Whether this layout registers a command of its own. */
    public boolean hasCommand() {
        return !command.isEmpty();
    }

    /** An independent copy, so a page's stored behaviour cannot be edited through a handed-out reference. */
    public LayoutBehavior copy() {
        return new LayoutBehavior()
                .mode(mode)
                .openOnJoin(openOnJoin)
                .openDelayTicks(openDelayTicks)
                .command(command)
                .closeOnDeath(closeOnDeath)
                .closeOnDamage(closeOnDamage)
                .keepOpen(keepOpen)
                .closeReversed(closeReversed)
                .moveWhileClosing(moveWhileClosing)
                .displayHotbar(displayHotbar)
                .displayHand(displayHand)
                .hudOnClose(hudOnClose)
                .hideHud(hideHud)
                .topmost(topmost)
                .hideHudElements(hiddenHudElements)
                .cursorSize(cursorSize)
                .cursorSpeed(cursorSpeed);
    }
}
