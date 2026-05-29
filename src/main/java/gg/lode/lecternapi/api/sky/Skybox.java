package gg.lode.lecternapi.api.sky;

/**
 * OptiFine-style custom skybox driven by Lectern. Server pushes a config; the
 * Fabric client renders a textured cube during the sky pass — analogous to the
 * Skyboxify mod (single layer, no biome / height filters).
 * <p>
 * The texture must be a 3:2 OptiFine cube atlas (e.g. 3072×2048). UV layout
 * (matches OptiFine + Skyboxify):
 * <pre>
 *   +--------+--------+--------+
 *   | BOTTOM |  TOP   |  EAST  |   row 0 (v = 0.0–0.5)
 *   +--------+--------+--------+
 *   | SOUTH  |  WEST  |  NORTH |   row 1 (v = 0.5–1.0)
 *   +--------+--------+--------+
 * </pre>
 * Fade times are stored as raw Minecraft daytime ticks (0–23999, where 0 =
 * sunrise / 06:00, 6000 = noon, 12000 = sunset, 18000 = midnight). Server can
 * compute these from OptiFine "HH:MM" via {@link #ticksFromClockTime(int, int)}.
 *
 * @param id            unique skybox id (lifecycle key — re-sending updates in place)
 * @param texturePath   resource path to the 3:2 atlas
 * @param startFadeIn   daytime ticks when the skybox begins fading in
 * @param endFadeIn     daytime ticks when the skybox reaches full alpha
 * @param startFadeOut  daytime ticks when the skybox begins fading out
 * @param endFadeOut    daytime ticks when the skybox is fully invisible
 * @param blend         blend mode (controls how the skybox composites with vanilla sky)
 * @param rotate        if true, the skybox rotates with the celestial bodies
 * @param axisX         rotation axis X component (Y axis is typical — 0,1,0)
 * @param axisY         rotation axis Y component
 * @param axisZ         rotation axis Z component
 */
public record Skybox(
        String id,
        String texturePath,
        int startFadeIn,
        int endFadeIn,
        int startFadeOut,
        int endFadeOut,
        Blend blend,
        boolean rotate,
        float axisX,
        float axisY,
        float axisZ
) {

    public enum Blend {
        /** Source × 1 + dest × 1. Brightens. */
        ADD("add"),
        /** Source × dst + dest × 0. Darkens. */
        MULTIPLY("multiply"),
        /** Standard alpha-over composite. */
        NORMAL("normal");

        private final String value;

        Blend(String value) { this.value = value; }

        public String value() { return value; }

        public static Blend fromString(String s) {
            for (Blend b : values()) if (b.value.equalsIgnoreCase(s)) return b;
            return ADD;
        }
    }

    /** Convert OptiFine HH:MM clock time → Minecraft daytime ticks. */
    public static int ticksFromClockTime(int hour, int minute) {
        int totalMinutes = (hour * 60 + minute) % 1440;
        long ticks = Math.round(totalMinutes / 1440.0 * 24000.0) - 6000;
        ticks = ((ticks % 24000) + 24000) % 24000;
        return (int) ticks;
    }

    /** Always-visible skybox (no fade), with ADD blend + Y-axis rotation. */
    public static Skybox always(String id, String texturePath) {
        return new Skybox(id, texturePath, 0, 0, 24000, 24000, Blend.ADD, true, 0F, 1F, 0F);
    }

    /** Night skybox between dusk (19:00) and dawn (5:25), ADD blend, Y rotation. */
    public static Skybox night(String id, String texturePath) {
        return new Skybox(id, texturePath,
                ticksFromClockTime(19, 0),
                ticksFromClockTime(19, 30),
                ticksFromClockTime(5, 0),
                ticksFromClockTime(5, 25),
                Blend.ADD, true,
                0F, 1F, 0F);
    }
}
