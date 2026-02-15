package gg.lode.lecternapi.api.cutscene;

import gg.lode.lecternapi.api.menu.MenuTransform;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.Consumer;

public final class Cutscene {

    private final String id;
    private final List<CutsceneAction> actions;
    private final @Nullable CameraPath cameraPath;
    private final int totalDurationTicks;
    private final boolean loop;
    private final Map<Integer, Runnable> callbacks;
    private final @Nullable Runnable onComplete;

    private Cutscene(String id, List<CutsceneAction> actions, @Nullable CameraPath cameraPath,
                     int totalDurationTicks, boolean loop,
                     Map<Integer, Runnable> callbacks, @Nullable Runnable onComplete) {
        this.id = id;
        this.actions = Collections.unmodifiableList(actions);
        this.cameraPath = cameraPath;
        this.totalDurationTicks = totalDurationTicks;
        this.loop = loop;
        this.callbacks = Collections.unmodifiableMap(callbacks);
        this.onComplete = onComplete;
    }

    public String getId() { return id; }
    public List<CutsceneAction> getActions() { return actions; }
    public @Nullable CameraPath getCameraPath() { return cameraPath; }
    public int getTotalDurationTicks() { return totalDurationTicks; }
    public boolean isLoop() { return loop; }
    public Map<Integer, Runnable> getCallbacks() { return callbacks; }
    public @Nullable Runnable getOnComplete() { return onComplete; }

    public static Builder builder(String id) {
        return new Builder(id);
    }

    public static final class Builder {
        private final String id;
        private int cursor = 0;
        private final List<CutsceneAction> actions = new ArrayList<>();
        private CameraPath cameraPath;
        private boolean loop = false;
        private int nextCallbackId = 0;
        private final Map<Integer, Runnable> callbacks = new HashMap<>();
        private Runnable onComplete;

        private Builder(String id) {
            this.id = id;
        }

        public Builder then(int ticks) {
            cursor += ticks;
            return this;
        }

        public Builder at(int tick) {
            cursor = tick;
            return this;
        }

        public Builder showTexture(String ref, String textureId, MenuTransform transform, int width, int height) {
            actions.add(new CutsceneAction.ShowTexture(cursor, ref, textureId, transform, width, height));
            return this;
        }

        public Builder hideTexture(String ref) {
            actions.add(new CutsceneAction.HideTexture(cursor, ref));
            return this;
        }

        public Builder showText(String ref, String text, MenuTransform transform, float scale) {
            actions.add(new CutsceneAction.ShowText(cursor, ref, text, transform, scale));
            return this;
        }

        public Builder hideText(String ref) {
            actions.add(new CutsceneAction.HideText(cursor, ref));
            return this;
        }

        public Builder showHead(String ref, UUID headUuid, MenuTransform transform, int width, int height) {
            actions.add(new CutsceneAction.ShowHead(cursor, ref, headUuid, transform, width, height));
            return this;
        }

        public Builder hideHead(String ref) {
            actions.add(new CutsceneAction.HideHead(cursor, ref));
            return this;
        }

        public Builder showPlayer(String ref, String identifier, MenuTransform transform, int width, int height) {
            actions.add(new CutsceneAction.ShowPlayer(cursor, ref, identifier, transform, width, height));
            return this;
        }

        public Builder hidePlayer(String ref) {
            actions.add(new CutsceneAction.HidePlayer(cursor, ref));
            return this;
        }

        public Builder letterbox(boolean enabled) {
            actions.add(new CutsceneAction.SetLetterbox(cursor, enabled));
            return this;
        }

        public Builder hideHud(boolean hidden) {
            actions.add(new CutsceneAction.SetHudHidden(cursor, hidden));
            return this;
        }

        public Builder flash(int r, int g, int b, int a, int layer, float durIn, float durStay, float durOut) {
            actions.add(new CutsceneAction.Flash(cursor, r, g, b, a, layer, durIn, durStay, durOut));
            return this;
        }

        public Builder motionBlur(boolean enabled) {
            actions.add(new CutsceneAction.SetMotionBlur(cursor, enabled));
            return this;
        }

        public Builder playSound(String soundId, float volume, float pitch) {
            actions.add(new CutsceneAction.PlaySound(cursor, soundId, volume, pitch));
            return this;
        }

        public Builder disableInput(boolean disabled) {
            actions.add(new CutsceneAction.SetInputDisabled(cursor, disabled));
            return this;
        }

        public Builder setCamera(double x, double y, double z, float yaw, float pitch, float roll) {
            actions.add(new CutsceneAction.SetCamera(cursor, x, y, z, yaw, pitch, roll));
            return this;
        }

        public Builder releaseCamera() {
            actions.add(new CutsceneAction.ReleaseCamera(cursor));
            return this;
        }

        public Builder setFov(float fov) {
            actions.add(new CutsceneAction.SetFov(cursor, fov));
            return this;
        }

        public Builder resetFov() {
            actions.add(new CutsceneAction.ResetFov(cursor));
            return this;
        }

        public Builder animateComponent(String ref, int durationTicks, int easingType,
                                         float bz0, float bz1, float bz2, float bz3,
                                         float targetX, float targetY, int targetLayer, float targetAlpha,
                                         int targetWidth, int targetHeight, float targetScale) {
            actions.add(new CutsceneAction.AnimateComponent(cursor, ref, durationTicks, easingType,
                    bz0, bz1, bz2, bz3, targetX, targetY, targetLayer, targetAlpha,
                    targetWidth, targetHeight, targetScale));
            return this;
        }

        public Builder callback(Runnable callback) {
            int callbackId = nextCallbackId++;
            callbacks.put(callbackId, callback);
            actions.add(new CutsceneAction.CallbackMarker(cursor, callbackId));
            return this;
        }

        public Builder cameraPath(Consumer<CameraPathBuilder> configurator) {
            CameraPathBuilder pathBuilder = new CameraPathBuilder();
            configurator.accept(pathBuilder);
            this.cameraPath = pathBuilder.build();
            return this;
        }

        public Builder loop(boolean loop) {
            this.loop = loop;
            return this;
        }

        public Builder onComplete(Runnable onComplete) {
            this.onComplete = onComplete;
            return this;
        }

        public Cutscene build() {
            actions.sort(Comparator.comparingInt(CutsceneAction::tick));

            int maxTick = 0;
            for (CutsceneAction action : actions) {
                if (action.tick() > maxTick) maxTick = action.tick();
            }
            if (cameraPath != null) {
                for (CameraWaypoint wp : cameraPath.getWaypoints()) {
                    if (wp.tick() > maxTick) maxTick = wp.tick();
                }
            }

            return new Cutscene(id, new ArrayList<>(actions), cameraPath, maxTick, loop,
                    new HashMap<>(callbacks), onComplete);
        }
    }

    public static final class CameraPathBuilder {
        private final List<CameraWaypoint> waypoints = new ArrayList<>();
        private CameraInterpolation interpolation = CameraInterpolation.CATMULL_ROM;

        public CameraPathBuilder waypoint(Vec pos, float yaw, float pitch, float roll) {
            waypoints.add(new CameraWaypoint(pos.x(), pos.y(), pos.z(), yaw, pitch, roll, 0));
            return this;
        }

        public CameraPathBuilder waypoint(Vec pos, float yaw, float pitch, float roll, int tick) {
            waypoints.add(new CameraWaypoint(pos.x(), pos.y(), pos.z(), yaw, pitch, roll, tick));
            return this;
        }

        public CameraPathBuilder interpolation(CameraInterpolation interpolation) {
            this.interpolation = interpolation;
            return this;
        }

        CameraPath build() {
            waypoints.sort(Comparator.comparingInt(CameraWaypoint::tick));
            return new CameraPath(new ArrayList<>(waypoints), interpolation);
        }
    }
}
