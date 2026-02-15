package gg.lode.lecternapi.api.cutscene;

public record CameraWaypoint(double x, double y, double z,
                              float yaw, float pitch, float roll, int tick) {}
