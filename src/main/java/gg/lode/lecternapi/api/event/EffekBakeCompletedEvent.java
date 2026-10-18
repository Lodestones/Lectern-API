package gg.lode.lecternapi.api.event;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.UUID;

/**
 * A player's Lectern client finished baking effeks into flipbooks and sent them back, after
 * {@code /lecternp effek_bake} asked it to.
 *
 * <p>The files are resource pack paths, for example
 * {@code assets/lodestone/textures/item/effek/meteor/0.png}, already checked: only frame
 * textures, frame models and item definitions under an {@code effek/} folder, lowercase, PNG or
 * JSON. The manifest describes each flipbook for the server ({@code frames}, {@code size},
 * {@code offset_x}, {@code offset_y}, {@code item_model}) and is what Lectern reads from its
 * {@code vanilla_particles.flipbooks} setting.
 *
 * <p>Nothing is installed by Lectern itself: a listener decides where the bake goes, such as a
 * pull request against the server pack.
 */
public class EffekBakeCompletedEvent extends LecternClientEvent {

    private static final HandlerList HANDLERS = new HandlerList();

    private final String bakeId;
    private final @Nullable UUID requesterId;
    private final String requesterName;
    private final Map<String, byte[]> files;
    private final String manifest;

    public EffekBakeCompletedEvent(Player player, String bakeId, @Nullable UUID requesterId, String requesterName,
                                   Map<String, byte[]> files, String manifest) {
        super(player, "EFFEK_BAKE_COMPLETED");
        this.bakeId = bakeId;
        this.requesterId = requesterId;
        this.requesterName = requesterName;
        this.files = Map.copyOf(files);
        this.manifest = manifest;
    }

    /** Unique per bake; safe to use in a branch name. */
    public String getBakeId() {
        return bakeId;
    }

    /** Who ran the command, or null when it came from the console. */
    public @Nullable UUID getRequesterId() {
        return requesterId;
    }

    public String getRequesterName() {
        return requesterName;
    }

    /** Pack path to file contents, without the manifest. */
    public Map<String, byte[]> getFiles() {
        return files;
    }

    /** The {@code {"version":1,"flipbooks":{...}}} manifest, as JSON. */
    public String getManifest() {
        return manifest;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
