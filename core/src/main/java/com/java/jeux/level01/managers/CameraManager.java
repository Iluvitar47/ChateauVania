package com.java.jeux.level01.managers;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;

public class CameraManager {
    private OrthographicCamera camera;
    private float mapWidth;
    private float mapHeight;
    private float targetZoom;

    public CameraManager(OrthographicCamera camera, float mapWidth, float mapHeight) {
        this.camera = camera;
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        this.targetZoom = 1.0f;
    }
    public void setZoom(float zoom) {
        this.targetZoom = zoom;
    }

    public void update(Vector2 playerPosition) {
        camera.zoom = targetZoom;
        float halfScreenWidth = (camera.viewportWidth / 2) * camera.zoom;
        float halfScreenHeight = (camera.viewportHeight / 2) * camera.zoom;
        float cameraX = Math.max(halfScreenWidth, Math.min(playerPosition.x, mapWidth - halfScreenWidth));
        float cameraY = Math.max(halfScreenHeight, Math.min(playerPosition.y, mapHeight - halfScreenHeight));
        camera.position.set(cameraX, cameraY, 0);
        camera.update();
    }
}

