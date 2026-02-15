package gg.lode.lecternapi.api.component;

import gg.lode.lecternapi.api.menu.ButtonListener;
import gg.lode.lecternapi.api.menu.MenuTransform;

/**
 * Sealed interface representing the data for a single element within a {@link PacketComponent}.
 * <p>
 * Each variant is a record, so equality checks are automatic — this is used by the tick loop
 * to detect which tickable elements have changed and need re-sending.
 */
public sealed interface ElementData permits
        ElementData.TextureData,
        ElementData.HeadData,
        ElementData.PlayerData,
        ElementData.ButtonData,
        ElementData.TextData {

    /**
     * Returns the relative transform (position within the component).
     */
    MenuTransform relativeTransform();

    record TextureData(String textureId, MenuTransform relativeTransform, float width, float height) implements ElementData {}

    record HeadData(String headUuid, MenuTransform relativeTransform, float width, float height) implements ElementData {}

    record PlayerData(String identifier, MenuTransform relativeTransform, float width, float height) implements ElementData {}

    record ButtonData(String textureId, MenuTransform relativeTransform, int width, int height, ButtonListener listener) implements ElementData {}

    record TextData(String text, MenuTransform relativeTransform, float scale) implements ElementData {}
}
