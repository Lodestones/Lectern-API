package gg.lode.lecternapi.api.cutscene;

/**
 * Lightweight position record — avoids forcing a Bukkit Vector import in the API.
 */
public record Vec(double x, double y, double z) {}
