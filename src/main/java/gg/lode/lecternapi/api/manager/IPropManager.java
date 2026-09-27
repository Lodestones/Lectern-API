package gg.lode.lecternapi.api.manager;

import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * Props: a piece of authored geometry hung on an ordinary server-side entity.
 *
 * <p>A prop is not a costume. It has no skeleton, it never moves under its own power, and it does
 * not belong to the entity carrying it — the entity is only somewhere to stand. What it is for is
 * scenery the server can place: a wall, a doorway, a light fitting, or a face at the end of a
 * corridor.
 *
 * <p>It is drawn during the client's entity pass rather than afterwards, which is the whole reason
 * it is lit at all. World light, True Darkness, the flashlight and whatever shader pack is loaded
 * all reach it without any of them being reimplemented; geometry drawn on one of the client's own
 * pipelines carries no lightmap and stands fullbright in a dark room.
 *
 * <p>Both halves of every call exist because props are used two ways. Scenery has to be
 * <b>shown to everybody</b>, or the room stops agreeing with itself and half the server walks
 * through a wall the other half can see. A haunting has to be shown to <b>one player</b>, because
 * the entire effect is that the person beside them says there was nothing there.
 *
 * <p>{@code emissive} decides which of the client's two render paths the geometry takes, and the
 * choice matters more than it sounds. Ordinary props are drawn on the vanilla entity layer, which
 * is what makes them lit at all — world light, True Darkness, the flashlight and any shader pack
 * reach them for free. An emissive prop is drawn on one of the client's own pipelines instead,
 * which carries no lightmap and is not touched by the pass the flashlight darkens the room with,
 * so it keeps its own brightness however dark the room is. A wall wants the first. Something
 * standing at the end of an unlit corridor wants the second, or nobody will ever see it.
 */
public interface IPropManager {

    /**
     * Hangs a prop on an entity for one viewer only.
     *
     * <p>Nobody else is told, including players who can see the same entity. Dropped when that
     * player disconnects, since it was only ever theirs.
     *
     * @param viewer     the only player who will see it
     * @param entity     the entity to hang it on; it supplies the position and the facing
     * @param model      the model's name, without a path or an extension
     * @param scale      multiplier on the authored size, where the kit is one unit per block
     * @param yawOffset  degrees added to the entity's own facing
     * @param yOffset    blocks to lift it off the entity's feet
     * @param hideEntity whether the carrier's own model gives way to the prop
     * @param emissive   whether it keeps its own brightness in a dark room
     */
    default void setProp(Player viewer, UUID entity, String model, float scale,
                         float yawOffset, float yOffset, boolean hideEntity, boolean emissive) {
    }

    /** The same, for everybody on the server and everybody who connects later. Scenery. */
    default void setProp(UUID entity, String model, float scale,
                         float yawOffset, float yOffset, boolean hideEntity, boolean emissive) {
    }

    /** Takes one viewer's prop off an entity, leaving anybody else's alone. */
    default void removeProp(Player viewer, UUID entity) {
    }

    /** Takes the shared prop off an entity, for everybody. */
    default void removeProp(UUID entity) {
    }

    /** Everything this viewer alone was being shown. */
    default void clearProps(Player viewer) {
    }

    /** Every prop on the server, shared and per-viewer alike. */
    default void clearProps() {
    }
}
