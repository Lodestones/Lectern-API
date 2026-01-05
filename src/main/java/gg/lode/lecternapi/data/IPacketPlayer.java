package gg.lode.lecternapi.data;

import gg.lode.lecternapi.ILecternAPI;
import gg.lode.lecternapi.packetmenu.text.HorizontalAlignment;
import gg.lode.lecternapi.packetmenu.text.TextAlignment;
import gg.lode.lecternapi.packetmenu.text.VerticalAlignment;
import net.kyori.adventure.text.Component;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;

/**
 * The packet player is used to manage the player's packet data.
 * You can get the player using the {@link ILecternAPI#getPlayer(UUID)} or
 * {@link ILecternAPI#getPlayer(Player)} method.
 * <p>
 * For more information about how to use the packet player, you can find our
 * documentation at:
 * <a href="https://lode.gg/docs/lectern/features/packet-player">...</a>
 */
public interface IPacketPlayer {

    /**
     * Gets all loaded mods of the player.
     * 
     * @return All loaded mods of the player
     */
    List<String> getMods();

    /**
     * Gets all loaded resourcepacks of the player.
     * 
     * @return All loaded resourcepacks of the player
     */
    List<String> getResourcePacks();

    /**
     * Shows a letter box
     * 
     * @param shouldShowLetterBox Whether the letterbox is visible or not.
     */
    void shouldShowLetterBox(boolean shouldShowLetterBox);

    /**
     * Renders an entity
     * 
     * @param uniqueId     The unique id of the entity
     * @param packetAction The action to perform on the entity (ADD, REMOVE)
     */
    void renderEntity(UUID uniqueId, PacketAction packetAction);

    /**
     * Resets all rendered entities
     * Meaning any entities that were force (un)rendered will be reset to their
     * default state
     */
    void resetRenderedEntities();

    /**
     * Renders the hand of the player
     * 
     * @param shouldRender Whether the hand is visible or not.
     */
    void renderHand(boolean shouldRender);

    /**
     * Plays a global sound
     * 
     * @param sound  The sound to play
     * @param volume The volume of the sound
     * @param pitch  The pitch of the sound
     */
    void playGlobalSound(String sound, float volume, float pitch);

    /**
     * Flashes the screen with a color
     * 
     * @param r            The red color of the flash (0-255)
     * @param g            The green color of the flash (0-255)
     * @param b            The blue color of the flash (0-255)
     * @param alpha        The alpha of the flash (0-1)
     * @param layer        The layer of the flash
     * @param durationIn   The duration of the flash in (in ticks)
     * @param durationStay The duration of the flash stay (in ticks)
     * @param durationOut  The duration of the flash out (in ticks)
     */
    void flash(int r, int g, int b, float alpha, int layer, float durationIn, float durationStay, float durationOut);

    /**
     * Disables the disconnect button for the player
     * 
     * @param shouldDisableDisconnect Whether the disconnect is disabled or not.
     */
    void shouldDisableDisconnect(boolean shouldDisableDisconnect);

    /**
     * Disables the disconnect button for the player
     * 
     * @param shouldDisableDisconnect Whether the disconnect is disabled or not.
     * @param text                    The text to replace the disconnect button
     *                                with.
     */
    void shouldDisableDisconnect(boolean shouldDisableDisconnect, Component text);

    /**
     * Blocks any incoming sound of the player
     * This does not block any sounds that are already playing
     * 
     * @param shouldBlockSound Whether the sound is blocked or not.
     */
    void shouldBlockSound(boolean shouldBlockSound);

    /**
     * Adds a ping on the player's screen
     * This ping is persistent per world
     * 
     * @param id       The id of the ping
     * @param title    The title of the ping
     * @param location The location of the ping
     */
    void addPing(String id, String title, Location location);

    /**
     * Removes a ping from the player's screen
     *
     * @param id The id of the ping
     */
    void removePing(String id);

    /**
     * Clears all pings from the player's screen
     */
    void clearAllPings();

    /**
     * Forces an Iris shader!
     * <p>
     * assets/{namespace}/shaders/
     *
     * @param shaderPath The shader path to use
     */
    void forceShader(String shaderPath);

    /**
     * Stops the force shader
     */
    void stopForceShader();

    void forcePack(String pack);

    void stopForcePack();

    /**
     * Shakes the screen of the player
     *
     * @param duration  The duration of the shake (in ticks)
     * @param intensity The intensity of the shake (0-1)
     */
    void shakeScreen(int duration, float intensity);

    /**
     * Stops the screen shake of the player
     */
    void stopScreenShake();

    /**
     * Disables a keybind for the player
     * 
     * @param keybind The keybind to disable
     */
    void disableKeybind(Keybind keybind);

    /**
     * Enables a keybind for the player
     * 
     * @param keybind The keybind to enable
     */
    void enableKeybind(Keybind keybind);

    /**
     * Clears all disabled keybinds for the player
     */
    void clearDisabledKeybinds();

    /**
     * Inverts the keybinds for the player
     * 
     * @param shouldInvert Whether the keybinds are inverted or not.
     */
    void shouldInvertKey(boolean shouldInvert);

    /**
     * Moves the camera to a location
     * 
     * @param location The location to move the camera to
     */
    void moveCamera(Location location);

    /**
     * Replaces all leashes with a visual chain
     * 
     * @param shouldVisualChain Whether the visual chain is visible or not.
     */
    void setVisualChain(boolean shouldVisualChain);

    /**
     * Moves the camera to a location with a roll
     * 
     * @param location The location to move the camera to
     * @param roll     The roll of the camera
     */
    void moveCamera(Location location, float roll);

    /**
     * Moves the camera to a location with a yaw, pitch, and roll
     * 
     * @param x     The x coordinate of the location (in blocks)
     * @param y     The y coordinate of the location (in blocks)
     * @param z     The z coordinate of the location (in blocks)
     * @param yaw   The yaw of the camera (in degrees) (0-360)
     * @param pitch The pitch of the camera (in degrees) (0-360)
     * @param roll  The roll of the camera (in degrees) (0-360)
     */
    void moveCamera(float x, float y, float z, float yaw, float pitch, float roll);

    /**
     * Moves the camera to a location with a yaw and pitch
     * 
     * @param x     The x coordinate of the location (in blocks)
     * @param y     The y coordinate of the location (in blocks)
     * @param z     The z coordinate of the location (in blocks)
     * @param yaw   The yaw of the camera (in degrees) (0-360)
     * @param pitch The pitch of the camera (in degrees) (0-360)
     */
    void moveCamera(float x, float y, float z, float yaw, float pitch);

    /**
     * Moves the camera to a location
     * 
     * @param x The x coordinate of the location (in blocks)
     * @param y The y coordinate of the location (in blocks)
     * @param z The z coordinate of the location (in blocks)
     */
    void moveCamera(float x, float y, float z);

    /**
     * Resets the camera to the default position
     */
    void resetCamera();

    /**
     * Forces the fov of the player's camera
     * 
     * @param fov The fov of the camera
     */
    void forceFov(int fov);

    /**
     * Resets the fov of the player's camera
     */
    void resetFov();

    /**
     * Shows or hides the hud of the player
     * 
     * @param shouldHideHud Whether the hud is visible or not.
     */
    void shouldShowHud(boolean shouldHideHud);

    /**
     * Renders a custom tooltip for the player to see.
     * Remember that the tooltip only shows if a player's mouse cursor is visible.
     * 
     * @param components The components to render
     */
    void renderCustomTooltip(List<Component> components);

    /**
     * Resets the custom tooltip for the player to see.
     */
    void resetCustomTooltip();

    /**
     * Sets a custom cape for the player
     * For resourcepack development, place any textures inside
     * "assets/lodestone/textures/"
     * 
     * @param uniqueId    The unique id of the player
     * @param texturePath The path to the texture
     */
    void setCustomCape(UUID uniqueId, String texturePath);

    /**
     * Removes a custom cape for the player
     * 
     * @param uniqueId The unique id of the player
     */
    void clearCustomCape(UUID uniqueId);

    /**
     * Clears all custom capes for the player
     */
    void clearCustomCapes();

    /**
     * Sets a custom skin for the player
     * For resourcepack development, place any textures inside
     * "assets/lodestone/textures/"
     *
     * @param uniqueId    The unique id of the player
     * @param texturePath The path to the texture
     */
    void setCustomSkin(UUID uniqueId, String texturePath);

    void setCustomSkinByName(UUID uniqueId, String username);

    /**
     * Resets a custom skin for the player
     * 
     * @param uniqueId The unique id of the player
     */
    void resetCustomSkin(UUID uniqueId);

    /**
     * Clears all custom skins for the player
     */
    void clearCustomSkins();

    /**
     * Sets a custom name tag for the player
     * Use adventure text components to set custom name tags.
     * 
     * @param uniqueId The unique id of the player
     * @param name     The name of the name tag
     */
    void setCustomNameTag(UUID uniqueId, String name);

    /**
     * Resets a custom name tag for the player
     * 
     * @param uniqueId The unique id of the player
     */
    void resetCustomNameTag(UUID uniqueId);

    /**
     * Resets all custom name tags for the player
     */
    void resetAllCustomNameTags();

    /**
     * Sets a custom name tag color for the player
     * 
     * @param uniqueId The unique id of the player
     * @param red      The red color of the name tag (0-255)
     * @param green    The green color of the name tag (0-255)
     * @param blue     The blue color of the name tag (0-255)
     */
    void setCustomNameTagColor(UUID uniqueId, int red, int green, int blue);

    /**
     * Sets a custom name tag color for the player
     * 
     * @param uniqueId The unique id of the player
     * @param rgb      The rgb color of the name tag (0-16777215)
     */
    void setCustomNameTagColor(UUID uniqueId, int rgb);

    /**
     * Resets a custom name tag color for the player
     * 
     * @param uniqueId The unique id of the player
     */
    void resetCustomNameTagColor(UUID uniqueId);

    /**
     * Resets all custom name tag colors for the player
     */
    void resetAllCustomNameTagColors();

    /**
     * Plays an emote for the player
     * For resourcepack development, place any emotes inside
     * "assets/lodestone/player_animations/"
     * 
     * For more information about how to create emotes, you can find our documentation at:
     * https://lode.gg/docs/lectern/features/emotes
     * 
     * @param uniqueId The unique id of the player
     * @param emoteId  The id of the emote
     */
    void playEmote(UUID uniqueId, String emoteId);

    /**
     * Plays an emote for the player
     * 
     * @param uniqueId The unique id of the player
     * @param emoteId  The id of the emote
     * @param showHands Whether to show the player's hands
     */
    void playEmote(UUID uniqueId, String emoteId, boolean showHands);

    /**
     * Plays an emote for the player
     * 
     * @param uniqueId The unique id of the player
     * @param emoteId  The id of the emote
     * @param showHands Whether to show the player's hands
     * @param priority The priority of the emote
     */
    void playEmote(UUID uniqueId, String emoteId, boolean showHands, int priority);

    /**
     * Stops an emote for the player
     * 
     * @param uniqueId The unique id of the player
     * @param emoteId  The id of the emote
     */
    void stopEmote(UUID uniqueId, String emoteId);

    /**
     * Stops all emotes for the player
     * 
     * @param uniqueId The unique id of the player
     */
    void stopAllEmotes(UUID uniqueId);

    /**
     * Stops all emotes for the player
     */
    void stopAllEmotes();

    /**
     * Sets the fps of the player
     * 
     * @param fps The fps of the player
     */
    void setFps(float fps);

    /**
     * Resets the fps of the player
     */
    void resetFps();

    /**
     * Sets the motion blur of the player
     * 
     * @param motionBlur The motion blur of the player
     */
    void setMotionBlur(float motionBlur);

    /**
     * Resets the motion blur of the player
     */
    void resetMotionBlur();

    /**
     * Registers a custom keybind for the player
     * 
     * @param keybindId The id of the keybind
     * @param translationKey The translation key of the keybind
     * @param category The category of the keybind
     * @param defaultKeyCode The default key code of the keybind
     */
    void registerCustomKeybind(String keybindId, String translationKey, String category, int defaultKeyCode);

    /**
     * Registers a custom keybind for the player
     * 
     * @param keybindId The id of the keybind
     * @param translationKey The translation key of the keybind
     * @param category The category of the keybind
     */
    void registerCustomKeybind(String keybindId, String translationKey, String category);

    /**
     * Unregisters a custom keybind for the player
     * 
     * @param keybindId The id of the keybind
     */
    void unregisterCustomKeybind(String keybindId);

    /**
     * Unregisters all custom keybinds for the player
     */
    void unregisterAllCustomKeybinds();

    /**
     * Adds a xray block for the player
     * 
     * @param material The material of the xray block
     * @param r The red color of the xray block
     * @param g The green color of the xray block
     * @param b The blue color of the xray block
     */
    void addXrayBlock(Material material, int r, int g, int b);

    /**
     * Removes a xray block for the player
     * 
     * @param material The material of the xray block
     */
    void removeXrayBlock(Material material);

    /**
     * Starts xraying for the player
     * 
     * @param range The range of the xray
     */
    void startXraying(int range);

    /**
     * Stops xraying for the player
     */
    void stopXraying();

    /**
     * Adds a xray block and starts xraying for the player
     * 
     * @param material The material of the xray block
     * @param r The red color of the xray block
     * @param g The green color of the xray block
     * @param b The blue color of the xray block
     * @param range The range of the xray
     */
    void addAndStartXraying(Material material, int r, int g, int b, int range);

    /**
     * Removes a xray block and stops xraying for the player
     * 
     * @param material The material of the xray block
     */
    void removeAndStopXraying(Material material);

    /**
     * Sets the moon color for the player
     * 
     * @param r The red color of the moon (0-255)
     * @param g The green color of the moon (0-255)
     * @param b The blue color of the moon (0-255)
     * @param alpha The alpha of the moon (0-1)
     */
    void setMoonColor(int r, int g, int b, float alpha);

    /**
     * Sets the fog color for the player
     * 
     * @param r The red color of the fog (0-255)
     * @param g The green color of the fog (0-255)
     * @param b The blue color of the fog (0-255)
     * @param alpha The alpha of the fog (0-1)
     */
    void setFogColor(int r, int g, int b, float alpha);

    /**
     * Sets the sky color for the player
     * 
     * @param r The red color of the sky (0-255)
     * @param g The green color of the sky (0-255)
     * @param b The blue color of the sky (0-255)
     * @param alpha The alpha of the sky (0-1)
     */
    void setSkyColor(int r, int g, int b, float alpha);

    /**
     * Sets the dense fog for the player
     */
    void showDenseFog(float start, float end, float alpha);

    void resetDenseFog();


    /**
     * Resets the moon color for the player
     */
    void resetMoonColor();

    /**
     * Resets the fog color for the player
     */
    void resetFogColor();

    /**
     * Resets the sky color for the player
     */
    void resetSkyColor();

    /**
     * Sets the true darkness for the player
     * 
     * @param shouldDoTrueDarkness Whether the true darkness is enabled or not.
     */
    void shouldDoTrueDarkness(boolean shouldDoTrueDarkness);

    /**
     * Sets the shoulder surf for the player
     * 
     * @param shouldShoulderSurf Whether the shoulder surf is enabled or not.
     */
    void shouldShoulderSurf(boolean shouldShoulderSurf);

    /**
     * Sets the third person for the player
     * 
     * @param shouldThirdPerson Whether the third person is enabled or not.
     */
    void shouldThirdPerson(boolean shouldThirdPerson);

    /**
     * Renders a player head on the screen
     * 
     * @param textureId The id of the texture
     * @param playerUniqueId The unique id of the player
     * @param x The x coordinate of the player head
     * @param y The y coordinate of the player head
     * @param layer The layer of the player head
     * @param width The width of the player head
     * @param height The height of the player head
     * @param horizontalAlignment The horizontal alignment of the player head
     * @param verticalAlignment The vertical alignment of the player head
     */
    void renderPlayerHead(String textureId, String playerUniqueId, float x, float y, int layer, float width,
            float height, HorizontalAlignment horizontalAlignment, VerticalAlignment verticalAlignment);

    /**
     * Renders a texture on the screen
     * 
     * @param textureId The id of the texture
     * @param texture The texture to render
     * @param x The x coordinate of the texture
     * @param y The y coordinate of the texture
     * @param layer The layer of the texture
     * @param width The width of the texture
     * @param height The height of the texture
     * @param alpha The alpha of the texture
     * @param horizontalAlignment The horizontal alignment of the texture
     * @param verticalAlignment The vertical alignment of the texture
     */
    void renderTexture(String textureId, String texture, float x, float y, int layer, float width, float height,
            float alpha, HorizontalAlignment horizontalAlignment, VerticalAlignment verticalAlignment);

    /**
     * Unrenders a texture from the screen
     * 
     * @param textureId The id of the texture
     */
    void unrenderTexture(String textureId);

    /**
     * Renders a text component on the screen
     * 
     * @param textId The id of the text
     * @param component The component to render
     * @param x The x coordinate of the component
     * @param y The y coordinate of the component
     * @param layer The layer of the component
     * @param size The size of the component
     * @param horizontalAlignment The horizontal alignment of the component
     * @param verticalAlignment The vertical alignment of the component
     */
    void renderComponent(String textId, String component, float x, float y, int layer, float size,
            HorizontalAlignment horizontalAlignment, VerticalAlignment verticalAlignment);

    /**
     * Renders a text component on the screen
     * 
     * @param textId The id of the text
     * @param component The component to render
     * @param x The x coordinate of the component
     * @param y The y coordinate of the component
     * @param layer The layer of the component
     * @param size The size of the component
     * @param horizontalAlignment The horizontal alignment of the component
     * @param verticalAlignment The vertical alignment of the component
     */
    void renderComponent(String textId, Component component, float x, float y, int layer, float size,
            HorizontalAlignment horizontalAlignment, VerticalAlignment verticalAlignment);

    /**
     * Renders a text component on the screen
     * 
     * @param textId The id of the text
     * @param component The component to render
     * @param x The x coordinate of the component
     * @param y The y coordinate of the component
     * @param layer The layer of the component
     * @param size The size of the component
     * @param horizontalAlignment The horizontal alignment of the component
     * @param verticalAlignment The vertical alignment of the component
     * @param textAlignment The text alignment of the component
     */
    void renderComponent(String textId, String component, float x, float y, int layer, float size,
            HorizontalAlignment horizontalAlignment, VerticalAlignment verticalAlignment, TextAlignment textAlignment);

    /**
     * Renders a text component on the screen
     * 
     * @param textId The id of the text
     * @param component The component to render
     * @param x The x coordinate of the component
     * @param y The y coordinate of the component
     * @param layer The layer of the component
     * @param size The size of the component
     * @param horizontalAlignment The horizontal alignment of the component
     * @param verticalAlignment The vertical alignment of the component
     * @param textAlignment The text alignment of the component
     */
    void renderComponent(String textId, Component component, float x, float y, int layer, float size,
            HorizontalAlignment horizontalAlignment, VerticalAlignment verticalAlignment, TextAlignment textAlignment);

    /**
     * Unrenders a text component from the screen
     * 
     * @param textId The id of the text
     */
    void unrenderComponent(String textId);

    /**
     * Renders a button on the screen
     * 
     * @param buttonId The id of the button
     * @param texture The texture of the button
     * @param x The x coordinate of the button
     * @param y The y coordinate of the button
     * @param width The width of the button
     * @param height The height of the button
     * @param alpha The alpha of the button
     * @param horizontalAlignment The horizontal alignment of the button
     * @param verticalAlignment The vertical alignment of the button
     */
    void renderButton(String buttonId, String texture, float x, float y, float width, float height, float alpha,
            HorizontalAlignment horizontalAlignment, VerticalAlignment verticalAlignment);

    /**
     * Unrenders a button from the screen
     * 
     * @param buttonId The id of the button
     */
    void unrenderButton(String buttonId);

    /**
     * Renders a player head on the screen
     * 
     * @param uniqueId The unique id of the player
     * @param x The x coordinate of the player head
     * @param y The y coordinate of the player head
     * @param layer The layer of the player head
     * @param width The width of the player head
     * @param height The height of the player head
     * @param horizontalAlignment The horizontal alignment of the player head
     * @param verticalAlignment The vertical alignment of the player head
     */
    void renderPlayerHead(UUID uniqueId, float x, float y, int layer, float width, float height,
            HorizontalAlignment horizontalAlignment, VerticalAlignment verticalAlignment);

    /**
     * Unrenders a player head from the screen
     * 
     * @param uniqueId The unique id of the player
     */
    void unrenderPlayerHead(UUID uniqueId);

    /**
     * Shows a menu
     *
     * @param delta The delta of the menu
     */
    void showMenu(float delta);

    /**
     * Hides a menu
     */
    void hideMenu();

    void setSwapHands(UUID targetUniqueId, PacketAction action);

    void clearSwappedHands();

    void setOutlineShader(boolean isActive);

    /**
     * Sets the smooth camera
     *
     * @param shouldSmoothCamera Whether the smooth camera is enabled or not.
     */
    void setSmoothCamera(boolean shouldSmoothCamera);

    /**
     * Disables the hitbox
     * 
     * @param shouldDisableHitbox Whether the hitbox is disabled or not.
     */
    void shouldDisableHitbox(boolean shouldDisableHitbox);

    /**
     * Disables the chunk boundaries
     * 
     * @param shouldDisableChunkBoundaries Whether the chunk boundaries are disabled or not.
     */
    void shouldDisableChunkBoundaries(boolean shouldDisableChunkBoundaries);

    /**
     * Disables the chunk reload
     *
     * @param shouldDisableChunkReload Whether the chunk reload is disabled or not.
     */
    void shouldDisableChunkReload(boolean shouldDisableChunkReload);

    /**
     * Disables advanved f3 keybinds like copy to clipboard, pi charting etc...
     *
     * @param shouldDisableAdvancedF3 Whether to disable the actions or not.
     */
    void shouldDisableAdvancedF3(boolean shouldDisableAdvancedF3);

    /**
     * Shows a freddy jumpscare whenever the player closes minecraft.
     *
     * @param shouldAltF4Jumpscare Whether the player gets an alt f4 jumpscare when they close minecraft or not.
     */
    void shouldAltF4Jumpscare(boolean shouldAltF4Jumpscare);

    /**
     * Adds a second 'experience bar' under the main experience bar.
     * 
     * @param progress The progress of the second bar
     * @param red      The red color of the second bar
     * @param green    The green color of the second bar
     * @param blue     The blue color of the second bar
     * @param alpha    The alpha of the second bar
     */
    void setSecondBar(float progress, int red, int green, int blue, float alpha);

    /**
     * Hides the second bar
     */
    void hideSecondBar();

    /**
     * Plays a camera playback
     * 
     * @param json The json to play
     */
    void playCameraPlayback(String json);

    /**
     * Sets if the body should follow the camera
     * 
     * @param shouldBodyFollowCam Whether the body should follow the camera or not.
     */
    void setShouldBodyFollowCam(boolean shouldBodyFollowCam);

    /**
     * Emits a sound ping
     * 
     * @param x The x coordinate of the sound ping
     * @param y The y coordinate of the sound ping
     * @param z The z coordinate of the sound ping
     * @param ticksToLive The ticks to live of the sound ping
     * @param textureId The texture id of the sound ping
     */
    void emitSoundPing(double x, double y, double z, int ticksToLive, String textureId);

    /**
     * Mutes the voice chat
     */
    void muteVoiceChat();

    /**
     * Unmutes the voice chat
     */
    void unMuteVoiceChat();

    /**
     * Undeafens the voice chat
     */
    void unDeafenVoiceChat();

    /**
     * Whether if the player can bhop or not.
     * Warning: This is a built-in cheat, only use it in servers that you own. Use it at your own risk.
     * 
     * @param isActive Whether the bhop is active or not.
     * @param forwardOnly Whether the bhop is forward only or not.
     * @param bps The bps of the bhop
     */
    void setBhop(boolean isActive, boolean forwardOnly, double bps);

    /**
     * Adds a model attach
     *
     * @param id The id of the model attach
     * @param playerUniqueId The unique id of the player
     * @param identifier The identifier of the model attach
     * @param customModelData The custom model data of the model attach
     * @param bodyPart The body part of the model attach
     * @param offsetX The offset x of the model attach
     * @param offsetY The offset y of the model attach
     * @param offsetZ The offset z of the model attach
     */
    void addModelAttach(String id, UUID playerUniqueId, String identifier, String itemModel, BodyPart bodyPart,
                        float offsetX, float offsetY, float offsetZ, float scaleX, float scaleY, float scaleZ, float rotationX,
                        float rotationY, float rotationZ);

    /**
     * Removes a model attach
     * 
     * @param id The id of the model attach
     */
    void removeModelAttach(String id);

    /**
     * Clears all model attachments
     */
    void clearModelAttachments();

    /**
     * Adds a item cooldown
     * For the item cooldown to apply per item, you need to add a custom nbt tag to the item.
     * "{"lodestone:cooldown":"test_item"}"
     * 
     * @param tag The tag of the item cooldown
     * @param duration The duration of the item cooldown
     */
    void addItemCooldown(String tag, int duration);

    /**
     * Removes a item cooldown
     * 
     * @param tag The tag of the item cooldown
     */
    void removeItemCooldown(String tag);

    /**
     * Clears all item cooldowns
     */
    void clearItemCooldowns();

    /**
     * Deafens the voice chat
     */
    void deafenVoiceChat();

    /**
     * Forces the voice chat to be deafened
     */
    void forceDeafenVoiceChat();

    /**
     * Forces the voice chat to be undeafened
     */
    void forceUnDeafenVoiceChat();

    /**
     * Forces the voice chat to be muted
     */
    void forceMuteVoiceChat();

    /**
     * Forces the voice chat to be unmuted
     */
    void forceUnMuteVoiceChat();

    /**
     * Tints an entity
     * 
     * @param uniqueId The unique id of the entity
     * @param r The red color of the tint
     * @param g The green color of the tint
     * @param b The blue color of the tint
     * @param a The alpha of the tint
     */
    void tintEntity(UUID uniqueId, int r, int g, int b, float a);

    /**
     * Removes a tint from an entity
     *
     * @param uniqueId The unique id of the entity
     */
    void removeTintFromEntity(UUID uniqueId);

    /**
     * Clears all entity tints
     */
    void clearEntityTints();

    void triggerBackroomsIntroCutscene();

    void setFlashlight(boolean active);

    void renderParticle(String particleId, double x, double y, double z, float rotationX, float rotationY, float rotationZ, float scale);

}
