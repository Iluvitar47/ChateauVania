package com.java.jeux.level01;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;
import java.util.List;

/**
 * The `Leve01MapLoader` class is responsible for loading and rendering the map for level 1.
 */
public class Leve01MapLoader {
    private TiledMap map;
    private OrthogonalTiledMapRenderer mapRenderer;
    private OrthographicCamera camera;
    private ShapeRenderer shapeRenderer;
    public List<Rectangle> deathZone;

    /**
     * Initializes the map loader, loading the map and setting up the camera and shape renderer.
     */
    public void create() {
        map = new TmxMapLoader().load("Maps/Level_1.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(map);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();
        shapeRenderer = new ShapeRenderer();

        deathZone = getDeathZoneObjects();
    }

    /**
     * Renders the map.
     */
    public void render() {
        camera.update();
        mapRenderer.setView(camera);
        mapRenderer.render();
    }

    /**
     * Gets the camera used for rendering the map.
     *
     * @return the camera
     */
    public OrthographicCamera getCamera() {
        return camera;
    }

    /**
     * Disposes of the resources used by the map loader.
     */
    public void dispose() {
        map.dispose();
        mapRenderer.dispose();
        shapeRenderer.dispose();
    }

    /**
     * Gets the width of the map in pixels.
     *
     * @return the width of the map
     */
    public int getMapWidth() {
        int width = map.getProperties().get("width", Integer.class);
        int tileWidth = map.getProperties().get("tilewidth", Integer.class);
        return width * tileWidth;
    }

    /**
     * Gets the height of the map in pixels.
     *
     * @return the height of the map
     */
    public int getMapHeight() {
        int height = map.getProperties().get("height", Integer.class);
        int tileHeight = map.getProperties().get("tileheight", Integer.class);
        return height * tileHeight;
    }

    /**
     * Gets the ground objects from the map.
     *
     * @return a list of rectangles representing the ground objects
     */
    public List<Rectangle> getGroundObjects() {
        List<Rectangle> groundObjects = new ArrayList<>();
        if (map.getLayers().get("platform") != null) {
            MapObjects objects = map.getLayers().get("platform").getObjects();
            for (MapObject object : objects) {
                if (object instanceof PolygonMapObject) {
                    Polygon polygon = ((PolygonMapObject) object).getPolygon();
                    Rectangle boundingRectangle = polygon.getBoundingRectangle();
                    groundObjects.add(boundingRectangle);
                }
            }
        } else {
            Gdx.app.log("MapTest", "platform layer not found");
        }
        return groundObjects;
    }

    /**
     * Gets the solid objects from the map.
     *
     * @return a list of rectangles representing the solid objects
     */
    public List<Rectangle> getSolidObjects() {
        List<Rectangle> solidObjects = new ArrayList<>();
        if (map.getLayers().get("SolidObjects") != null) {
            MapObjects objects = map.getLayers().get("SolidObjects").getObjects();
            for (MapObject object : objects) {
                if (object instanceof PolygonMapObject) {
                    Polygon polygon = ((PolygonMapObject) object).getPolygon();
                    Rectangle boundingRectangle = polygon.getBoundingRectangle();
                    solidObjects.add(boundingRectangle);
                }
            }
        } else {
            Gdx.app.log("MapTest", "SolidObjects layer not found");
        }
        return solidObjects;
    }

    /**
     * Gets the death zone objects from the map.
     *
     * @return a list of rectangles representing the death zone objects
     */
    public List<Rectangle> getDeathZoneObjects() {
        List<Rectangle> deathZoneObjects = new ArrayList<>();
        if (map.getLayers().get("DeathZone") != null) {
            MapObjects objects = map.getLayers().get("DeathZone").getObjects();
            for (MapObject object : objects) {
                if (object instanceof PolygonMapObject) {
                    Polygon polygon = ((PolygonMapObject) object).getPolygon();
                    Rectangle boundingRectangle = polygon.getBoundingRectangle();
                    deathZoneObjects.add(boundingRectangle);
                }
            }
        } else {
            Gdx.app.log("MapTest", "DeathZone layer not found");
        }
        return deathZoneObjects;
    }
}
