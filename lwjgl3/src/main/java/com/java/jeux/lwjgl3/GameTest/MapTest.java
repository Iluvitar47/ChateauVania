package com.java.jeux.lwjgl3.GameTest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class MapTest {
    private TiledMap map;
    private OrthogonalTiledMapRenderer mapRenderer;
    private OrthographicCamera camera;
    private ShapeRenderer shapeRenderer;

    public void create() {
        map = new TmxMapLoader().load("Maps/MapTest.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(map);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();

        // Initialisation du ShapeRenderer
        shapeRenderer = new ShapeRenderer();
    }

    public void render() {
        camera.update();
        mapRenderer.setView(camera);
        mapRenderer.render();

        // Dessine les rectangles du calque "Sol" en rouge pour le débogage
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.RED);

        for (Rectangle rect : getGroundObjects()) {
            shapeRenderer.rect(rect.x, rect.y, rect.width, rect.height);
        }

        shapeRenderer.end();
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public void dispose() {
        map.dispose();
        mapRenderer.dispose();
        shapeRenderer.dispose(); // Libère les ressources du ShapeRenderer
    }

    public int getMapWidth() {
        int width = map.getProperties().get("width", Integer.class);
        int tileWidth = map.getProperties().get("tilewidth", Integer.class);
        return width * tileWidth;
    }

    public int getMapHeight() {
        int height = map.getProperties().get("height", Integer.class);
        int tileHeight = map.getProperties().get("tileheight", Integer.class);
        return height * tileHeight;
    }

    public List<Rectangle> getGroundObjects() {
        List<Rectangle> groundObjects = new ArrayList<>();
        if (map.getLayers().get("Sol") != null) {
            MapObjects objects = map.getLayers().get("Sol").getObjects();
            for (MapObject object : objects) {
                if (object instanceof RectangleMapObject) {
                    groundObjects.add(((RectangleMapObject) object).getRectangle());
                }
            }
        } else {
            Gdx.app.log("MapTest", "Le calque 'Sol' est introuvable.");
        }
        return groundObjects;
    }
}
