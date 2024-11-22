package com.java.jeux;

public class GlobalSettings {
    private static float globalVolume = 1;
    private static boolean showDebugHitboxes = false;

    public static void setGlobalVolume(float volume){
        globalVolume = volume;
    }

    public static float getGlobalVolume(){
        return globalVolume;
    }

    public static boolean getShowDebugHitboxes(){
        return showDebugHitboxes;
    }

    public static void toggleShowDebugHitboxes(){
        showDebugHitboxes = !showDebugHitboxes;
    }
}
