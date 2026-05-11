package gg.lode.lecternapi.bootstrap;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * Lifecycle contract that the runtime-loaded Lectern implementation fulfils.
 * The public Lectern-Loader jar is a JavaPlugin shim that loads an
 * implementation (via cloud or local jar), instantiates the entry class,
 * and forwards Bukkit lifecycle calls to it. Implementations MUST have a
 * public no-arg constructor.
 */
public interface LecternBootstrap {
    void onLoad(JavaPlugin host);
    void onEnable(JavaPlugin host);
    void onDisable(JavaPlugin host);
}
