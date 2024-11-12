package com.java.jeux.lwjgl3.RoomTest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class SpriteResourceManager {
    private Map<String, Map<String, Array<TextureRegion>>> spriteResources;

    public SpriteResourceManager() {
        spriteResources = new HashMap<>();
    }

    /**
     * Charge les animations pour un personnage donné.
     * @param directory Le chemin du dossier du personnage.
     * @param animations Un map des animations disponibles, où la clé est le nom de l'animation et la valeur est le nombre de frames.
     * @param method La méthode de chargement ("single" pour découper une image unique, "folder" pour un dossier d'images).
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
            Gdx.app.log("SpriteResourceManager", "Le dossier " + directory + " est introuvable ou n'est pas un dossier.");
        }
    }

    // Méthode pour charger des frames à partir d'une image unique
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

    // Méthode pour charger des frames à partir d'un dossier d'images
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

    // Méthode pour obtenir une animation spécifique pour un personnage
    public Array<TextureRegion> getAnimation(String directory, String animationType) {
        if (spriteResources.containsKey(directory)) {
            Map<String, Array<TextureRegion>> animations = spriteResources.get(directory);
            return animations.getOrDefault(animationType, new Array<>());
        }
        return new Array<>();
    }

    /**
     * Calcule les dimensions de la frame pour déterminer la taille et l'offset de la hitbox.
     * @param frame La frame de l'animation.
     * @param pixmap Le pixmap de la texture.
     * @return Un tableau contenant la largeur, la hauteur et l'offset en X.
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
