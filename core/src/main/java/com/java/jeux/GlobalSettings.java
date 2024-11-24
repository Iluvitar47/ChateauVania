package com.java.jeux;

/**
 * The `GlobalSettings` class manages the global settings for the game.
 */
public class GlobalSettings {
    private static float globalVolume = 1.0f;
    private static boolean showDebugHitboxes = false;

    /**
     * Gets the global volume.
     *
     * @return the global volume
     */
    public static float getGlobalVolume() {
        return globalVolume;
    }

    /**
     * Sets the global volume.
     *
     * @param volume the new global volume
     */
    public static void setGlobalVolume(float volume) {
        globalVolume = volume;
    }

    /**
     * Checks if debug hitboxes are shown.
     *
     * @return true if debug hitboxes are shown, false otherwise
     */
    public static boolean getShowDebugHitboxes() {
        return showDebugHitboxes;
    }

    /**
     * Toggles the visibility of debug hitboxes.
     */
    public static void toggleShowDebugHitboxes() {
        showDebugHitboxes = !showDebugHitboxes;
    }
}
