package gg.lode.lecternapi.api.ui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A whole page as the UI editor exports it: the layout, the rules it opens and closes under, and
 * what its elements do when they are used.
 * <p>
 * The editor writes one file; this is that file parsed. The {@code json} is kept verbatim rather
 * than re-serialised from a parsed model, because the client is the thing that reads it and the
 * server has no business re-encoding a document it does not need to understand — a page can carry
 * an element type this build has never heard of and still play correctly.
 *
 * <pre>{@code
 * LayoutPage page = api.getLayoutManager().getPage("stats");
 * api.getLayoutManager().open(player, page);
 * }</pre>
 */
public class LayoutPage {

    private final String id;
    private final String json;
    private final LayoutBehavior behavior;
    private final List<LayoutAction> actions;

    public LayoutPage(String id, String json, LayoutBehavior behavior, List<LayoutAction> actions) {
        this.id = id == null || id.isBlank() ? "layout" : id;
        this.json = json == null ? "" : json;
        this.behavior = behavior == null ? LayoutBehavior.DEFAULT.copy() : behavior;
        this.actions = actions == null ? List.of() : List.copyOf(actions);
    }

    public String getId() { return id; }

    /** The layout document, exactly as exported — what the client is sent and plays. */
    public String getJson() { return json; }

    public LayoutBehavior getBehavior() { return behavior; }

    public List<LayoutAction> getActions() { return actions; }

    /** The actions bound to one element reference and trigger, in the order they were authored. */
    public List<LayoutAction> actionsFor(String elementRef, LayoutAction.Trigger trigger) {
        if (actions.isEmpty()) return Collections.emptyList();
        String reference = elementRef == null ? "" : elementRef.trim();
        List<LayoutAction> matches = new ArrayList<>(2);
        for (LayoutAction action : actions) {
            if (action.trigger() == trigger && action.element().equals(reference)) matches.add(action);
        }
        return matches;
    }

    /** Whether the page carries a layout definition, as opposed to naming one the client has cached. */
    public boolean hasDefinition() {
        return !json.isEmpty();
    }

    /** Turns this page into the layout the HUD manager plays. */
    public HudLayout toLayout() {
        return new HudLayout(id, json);
    }
}
