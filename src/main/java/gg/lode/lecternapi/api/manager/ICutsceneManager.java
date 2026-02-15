package gg.lode.lecternapi.api.manager;

import gg.lode.lecternapi.api.cutscene.Cutscene;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

public interface ICutsceneManager {
    void play(Player player, Cutscene cutscene);

    /**
     * Plays a cutscene from a JSON file stored in the Lectern plugin's cutscenes folder.
     *
     * @param player the target player
     * @param fileName the JSON file name (e.g. "intro.json")
     * @throws IllegalArgumentException if the file does not exist or cannot be parsed
     */
    void playFile(Player player, String fileName);

    void pause(Player player);
    void resume(Player player);
    void stop(Player player);
    void seekTo(Player player, int tick);
    boolean isPlaying(Player player);
    @Nullable String getActiveCutsceneId(Player player);
}
