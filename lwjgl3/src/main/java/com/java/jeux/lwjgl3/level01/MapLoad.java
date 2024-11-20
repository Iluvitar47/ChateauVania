package com.java.jeux.lwjgl3.level01;

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

public class MapLoad {
    private TiledMap map;
    private OrthogonalTiledMapRenderer mapRenderer;
    private OrthographicCamera camera;
    private ShapeRenderer shapeRenderer;
    public List<Rectangle> deathZone;

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

    public void render() {
        camera.update();
        mapRenderer.setView(camera);
        mapRenderer.render();
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.RED);
        for (Rectangle rect : getGroundObjects()) {
            shapeRenderer.rect(rect.x, rect.y, rect.width, rect.height);
        }
        shapeRenderer.setColor(Color.BLUE);
        for (Rectangle rect : deathZone) {
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
        shapeRenderer.dispose();
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
