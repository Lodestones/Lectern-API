package gg.lode.lecternapi.api.cutscene;

import gg.lode.lecternapi.api.menu.MenuTransform;

import java.util.UUID;

public sealed interface CutsceneAction {
    int tick();

    record ShowTexture(int tick, String ref, String textureId, MenuTransform transform, int width, int height) implements CutsceneAction {}
    record HideTexture(int tick, String ref) implements CutsceneAction {}
    record ShowText(int tick, String ref, String text, MenuTransform transform, float scale) implements CutsceneAction {}
    record HideText(int tick, String ref) implements CutsceneAction {}
    record ShowHead(int tick, String ref, UUID headUuid, MenuTransform transform, int width, int height) implements CutsceneAction {}
    record HideHead(int tick, String ref) implements CutsceneAction {}
    record ShowPlayer(int tick, String ref, String identifier, MenuTransform transform, int width, int height) implements CutsceneAction {}
    record HidePlayer(int tick, String ref) implements CutsceneAction {}
    record SetLetterbox(int tick, boolean enabled) implements CutsceneAction {}
    record SetHudHidden(int tick, boolean hidden) implements CutsceneAction {}
    record Flash(int tick, int r, int g, int b, int a, int layer, float durIn, float durStay, float durOut) implements CutsceneAction {}
    record SetMotionBlur(int tick, boolean enabled) implements CutsceneAction {}
    record PlaySound(int tick, String soundId, float volume, float pitch) implements CutsceneAction {}
    record SetInputDisabled(int tick, boolean disabled) implements CutsceneAction {}
    record SetCamera(int tick, double x, double y, double z, float yaw, float pitch, float roll) implements CutsceneAction {}
    record ReleaseCamera(int tick) implements CutsceneAction {}
    record SetFov(int tick, float fov) implements CutsceneAction {}
    record ResetFov(int tick) implements CutsceneAction {}
    record CallbackMarker(int tick, int callbackId) implements CutsceneAction {}
    record AnimateComponent(int tick, String ref, int durationTicks, int easingType,
                            float bz0, float bz1, float bz2, float bz3,
                            float targetX, float targetY, int targetLayer, float targetAlpha,
                            int targetWidth, int targetHeight, float targetScale) implements CutsceneAction {}
}
