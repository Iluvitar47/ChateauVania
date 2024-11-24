package com.java.jeux.level01.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * The `SpriteResourceManager` class manages the loading and retrieval of sprite resources for characters.
 */
public class SpriteResourceManager {
    private Map<String, Map<String, Array<TextureRegion>>> spriteResources;

    /**
     * Constructs a new `SpriteResourceManager` to initialize the resource map.
     */
    public SpriteResourceManager() {
        spriteResources = new HashMap<>();
    }

    /**
     * Loads the animations for a given character.
     *
     * @param directory  the path to the character's folder
     * @param animations a map of available animations, where the key is the animation name and the value is the number of frames
     * @param method     the loading method ("single" to cut a single image, "folder" for a folder of images)
     */
    public void loadSprites(String directory, Map<String, Integer> animations, String method) {
        File folder = new File(Gdx.files.internal(directory).file().getPath());
        if (folder.exists() && folder.isDirectory()) {
            Map<String, Array<TextureRegion>> animationMap = new HashMap<>();

            for (Map.Entry<String, Integer> entry : animations.entrySet()) {
                String animationType = entry.getKey();
                int frameCount = entry.getValue();

                Array<TextureRegion> frames = method.equals("single")
                    ? loadFromSingleImage(folder, animationType, frameCount)
                    : loadFromFolder(new File(folder, animationType));

                if (frames.size > 0) {
                    animationMap.put(animationType, frames);
                }
            }

            spriteResources.put(directory, animationMap);
        } else {
            Gdx.app.log("SpriteResourceManager", "The folder " + directory + " is not found or is not a folder.");
        }
    }

    /**
     * Loads frames from a single image.
     *
     * @param folder        the folder containing the image
     * @param animationType the type of animation
     * @param frameCount    the number of frames
     * @return an array of texture regions
     */
    private Array<TextureRegion> loadFromSingleImage(File folder, String animationType, int frameCount) {
        Array<TextureRegion> frames = new Array<>();
        File imageFile = new File(folder, animationType + ".png");

        if (imageFile.exists() && imageFile.isFile()) {
            Texture texture = new Texture(Gdx.files.internal(imageFile.getPath()));
            int frameWidth = texture.getWidth() / frameCount;
            int frameHeight = texture.getHeight();

            for (int i = 0; i < frameCount; i++) {
                frames.add(new TextureRegion(texture, i * frameWidth, 0, frameWidth, frameHeight));
            }
        }
        return frames;
    }

    /**
     * Loads frames from a folder of images.
     *
     * @param animationFolder the folder containing the images
     * @return an array of texture regions
     */
    private Array<TextureRegion> loadFromFolder(File animationFolder) {
        Array<TextureRegion> frames = new Array<>();
        if (animationFolder.exists() && animationFolder.isDirectory()) {
            File[] files = animationFolder.listFiles((dir, name) -> name.toLowerCase().endsWith(".png"));
            if (files != null) {
                java.util.Arrays.sort(files);
                for (File file : files) {
                    Texture texture = new Texture(Gdx.files.internal(file.getPath()));
                    frames.add(new TextureRegion(texture));
                }
            }
        }
        return frames;
    }

    /**
     * Gets a specific animation for a character.
     *
     * @param directory     the path to the character's folder
     * @param animationType the type of animation
     * @return an array of texture regions
     */
    public Array<TextureRegion> getAnimation(String directory, String animationType) {
        if (spriteResources.containsKey(directory)) {
            Map<String, Array<TextureRegion>> animations = spriteResources.get(directory);
            return animations.getOrDefault(animationType, new Array<>());
        }
        return new Array<>();
    }

    /**
     * Calculates the frame dimensions to determine the size and offset of the hitbox.
     *
     * @param frame  the animation frame
     * @param pixmap the pixmap of the texture
     * @return an array containing the width, height, and X offset
     */
    public float[] calculateFrameDimensions(TextureRegion frame, Pixmap pixmap) {
        int minX = frame.getRegionWidth();
        int minY = frame.getRegionHeight();
        int maxX = 0;
        int maxY = 0;

        for (int x = 0; x < frame.getRegionWidth(); x++) {
            for (int y = 0; y < frame.getRegionHeight(); y++) {
                int pixel = pixmap.getPixel(frame.getRegionX() + x, frame.getRegionY() + y);
                if ((pixel & 0x000000ff) != 0) {
                    minX = Math.min(minX, x);
                    maxX = Math.max(maxX, x);
                    minY = Math.min(minY, y);
                    maxY = Math.max(maxY, y);
                }
            }
        }

        float width, height, offsetX;

        if (minX < maxX && minY < maxY) {
            width = maxX - minX;
            height = maxY - minY;
            offsetX = (maxX + minX) / 2.0f;
        } else {
            width = frame.getRegionWidth();
            height = frame.getRegionHeight();
            offsetX = frame.getRegionWidth() / 2.0f;
        }

        return new float[]{width, height, offsetX};
    }

    /**
     * Disposes of the resources used by the sprite manager.
     */
    public void dispose() {
        for (Map<String, Array<TextureRegion>> animations : spriteResources.values()) {
            for (Array<TextureRegion> textures : animations.values()) {
                for (TextureRegion region : textures) {
                    region.getTexture().dispose();
                }
            }
        }
    }
}
