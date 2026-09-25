package gg.lode.lecternapi.api.manager;

import gg.lode.lecternapi.api.data.FlashlightMode;
import org.bukkit.entity.Player;

/**
 * Turns a flashlight on, changing only what you name.
 *
 * <p>The shape of the beam belongs to the client. It draws every beam in the room itself, per pixel,
 * and the shape is a judgement about how a corridor feels that can only be made while looking at
 * one: it is tuned in game and those values ship with the mod. A server that sends its own numbers
 * on every update pulls the beam back out of that shape, and does it differently on every server.
 *
 * <p>So nothing here is sent unless it is set. What you leave alone keeps whatever the player's
 * client was tuned to, and what you set is taken as a deliberate override for a moment you have
 * staged: stretching the beam while somebody falls, draining the colour out of it as a light dies.
 *
 * <pre>{@code
 * // Just switch it on, at whatever the client looks best at.
 * api.getEnvironmentManager().flashlight(player).send();
 *
 * // Reach much further for the length of a fall, leaving everything else alone.
 * api.getEnvironmentManager().flashlight(player).range(175.0f).send();
 *
 * // A failing lamp: flickering, dim and cold.
 * api.getEnvironmentManager().flashlight(player)
 *         .mode(FlashlightMode.FLICKER)
 *         .strength(0.6f)
 *         .colour(180, 200, 255)
 *         .send();
 * }</pre>
 */
public final class FlashlightBuilder {

    /**
     * What an unset value is sent as. Non-positive tells the client this server is not asking, so it
     * keeps its own. It is a sentinel rather than a null Float because the packet is a fixed run of
     * floats, and every one of these has to be on the wire whether or not it means anything.
     */
    private static final float UNSET = -1.0f;

    private final IEnvironmentManager environment;
    private final Player player;

    private FlashlightMode mode = FlashlightMode.ON;
    private float range = UNSET;
    private float strength = UNSET;
    private float volumetric = UNSET;
    private int red = -1;
    private int green = -1;
    private int blue = -1;

    public FlashlightBuilder(IEnvironmentManager environment, Player player) {
        this.environment = environment;
        this.player = player;
    }

    /** Steady, flickering, or out while the darkness stays. Defaults to {@link FlashlightMode#ON}. */
    public FlashlightBuilder mode(FlashlightMode mode) {
        this.mode = mode;
        return this;
    }

    /** Flickering, for a lamp that is failing. */
    public FlashlightBuilder flicker() {
        return mode(FlashlightMode.FLICKER);
    }

    /**
     * Puts the beam out while leaving the dark in place, which is a flashlight someone is holding
     * switched off rather than one they never had.
     */
    public FlashlightBuilder disabled() {
        return mode(FlashlightMode.DISABLED);
    }

    /** How far the beam reaches before it fades out, in blocks. */
    public FlashlightBuilder range(float range) {
        this.range = range;
        return this;
    }

    /** Overall power of the beam. */
    public FlashlightBuilder strength(float strength) {
        this.strength = strength;
        return this;
    }

    /** How visible the beam is hanging in the air. 0 is clean air, and is therefore not an override. */
    public FlashlightBuilder volumetric(float volumetric) {
        this.volumetric = volumetric;
        return this;
    }

    /** The colour of the light, each component 0-255. */
    public FlashlightBuilder colour(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        return this;
    }

    /** As {@link #colour(int, int, int)}. */
    public FlashlightBuilder color(int red, int green, int blue) {
        return colour(red, green, blue);
    }

    /** Sends it. Nothing reaches the player until this is called. */
    public void send() {
        environment.setFlashlight(player, mode, range, strength, volumetric, red, green, blue);
    }
}
