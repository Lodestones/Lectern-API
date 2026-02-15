package gg.lode.lecternapi.api.cutscene;

import java.util.Collections;
import java.util.List;

public final class CameraPath {

    private final List<CameraWaypoint> waypoints;
    private final CameraInterpolation interpolation;

    public CameraPath(List<CameraWaypoint> waypoints, CameraInterpolation interpolation) {
        this.waypoints = Collections.unmodifiableList(waypoints);
        this.interpolation = interpolation;
    }

    public List<CameraWaypoint> getWaypoints() {
        return waypoints;
    }

    public CameraInterpolation getInterpolation() {
        return interpolation;
    }
}
