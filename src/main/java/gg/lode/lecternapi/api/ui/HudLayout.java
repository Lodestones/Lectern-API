package gg.lode.lecternapi.api.ui;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * An editor-authored HUD screen and its animation, sent as the JSON the Lectern HUD editor
 * exports.
 * <p>
 * The layout travels as its own document rather than as a series of element calls, which is what
 * lets a UI be retimed or restyled without either side being rebuilt: the editor exports the
 * file, the server ships it, the client plays it. Layouts are cached on the client by id, so a
 * repeat showing costs only the id.
 * <p>
 * Text in a layout may carry {@code %tokens%}. Values the client knows — position, health, FPS —
 * it fills in itself; anything else comes from {@link #variable(String, String)}. That is also
 * how <b>PlaceholderAPI</b> reaches a layout: PAPI runs on the server against a real player, so
 * the server expands the placeholder and pushes the result under the same name, and a layout
 * written against PAPI tokens works unchanged.
 *
 * <pre>{@code
 * api.getHUDManager().playLayout(player, new HudLayout("objective_intro", json)
 *         .variable("objective", "Capture the relay")
 *         .variable("score", "3 / 5"));
 * }</pre>
 */
public class HudLayout {

    private final String id;
    private final String json;
    private final Map<String, String> variables = new LinkedHashMap<>();

    /**
     * @param id   the layout's key, used to replay or stop it later
     * @param json the exported layout; null or empty replays one the client already has cached
     */
    public HudLayout(String id, String json) {
        this.id = id;
        this.json = json == null ? "" : json;
    }

    /** Replays a layout the client has already been sent, by id alone. */
    public HudLayout(String id) {
        this(id, "");
    }

    /**
     * Sets a value for {@code %name%} in the layout's text.
     * <p>
     * Variables are global to the client rather than scoped to one layout, so a value pushed
     * once shows in every layout using that token — which is what makes a scoreboard-style
     * screen a set of variables rather than a re-send.
     */
    public HudLayout variable(String name, String value) {
        if (name != null) variables.put(name, value == null ? "" : value);
        return this;
    }

    public HudLayout variables(Map<String, String> values) {
        if (values != null) values.forEach(this::variable);
        return this;
    }

    public String getId() { return id; }
    public String getJson() { return json; }
    public Map<String, String> getVariables() { return variables; }

    /** Whether the layout carries a definition, as opposed to replaying a cached one. */
    public boolean hasDefinition() {
        return !json.isEmpty();
    }
}
