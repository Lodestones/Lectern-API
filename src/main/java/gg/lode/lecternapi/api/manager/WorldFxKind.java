package gg.lode.lecternapi.api.manager;

/**
 * What a world effect draws. Every kind is one fullscreen pass that rebuilds each pixel's world
 * position from depth, so it lands on real terrain and costs the same at any size.
 *
 * <p>Each kind reads {@code scale} and the four free parameters its own way, listed per constant.
 * Positions are in world coordinates; a duration of 0 runs until stopped.
 */
public enum WorldFxKind {
    /** A glowing circle on the ground that fills in as the duration runs out. scale: radius. p0: pulse rate. */
    TARGET("target"),
    /** A ring racing out across the terrain. scale: final radius. p0: band width in blocks. */
    SHOCKWAVE("shockwave"),
    /** A burning body falling from the anchor. p0..p2: where it lands, relative to the anchor. scale: head radius. duration: flight time. */
    METEOR("meteor"),
    /** A spinning dust funnel standing on the anchor. scale: height. p0: base radius. p1: top radius. p2: spin. */
    FUNNEL("funnel"),
    /** A vent: glowing rim, heat haze and steam. scale: radius. p0: activity, 1 for a normal vent, above 1 for a blast. */
    VENT("vent"),
    /** Glowing fractures spreading through the ground. scale: radius once fully spread. p0: crack density. */
    FRACTURE("fracture"),
    /** A forked lightning bolt striking the anchor, and the flash where it lands. scale: bolt height. */
    BOLT("bolt"),
    /** Haze closing in round the viewer. scale: how far they can see. p0: how fast it thins with height. */
    FOG("fog"),
    /** A moving wall of water or dust. p0, p1: its heading on x and z. scale: height. p2: depth in blocks. */
    WALL("wall"),
    /** A tint over the whole view. p0: heat shimmer. p1: frost at the edges. p2: saturation, 1 unchanged. */
    GRADE("grade");

    private final String id;

    WorldFxKind(String id) {
        this.id = id;
    }

    /** The name it goes over the wire as. */
    public String id() {
        return id;
    }
}
