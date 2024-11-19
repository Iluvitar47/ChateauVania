package com.java.jeux.lwjgl3.level01;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.OrthographicCamera;

import java.util.ArrayList;
import java.util.List;

public class Level01Render extends ApplicationAdapter {
    private SpriteBatch batch;
    private Player player;
    private List<Ennemies> enemies;
    private MapLoad mapLoad;
    private ShapeRenderer shapeRenderer;
    private GravityTest gravityTest;
    private CameraController cameraController;
    private OrthographicCamera camera;
    private SolidObjectsManager solidObjectsManager;

    @Override
    public void create() {
        mapLoad = new MapLoad();
        mapLoad.create();

        List<Rectangle> solidObjects = mapLoad.getSolidObjects();
        solidObjectsManager = new SolidObjectsManager(solidObjects);

        camera = mapLoad.getCamera();
        cameraController = new CameraController(camera, mapLoad.getMapWidth(), mapLoad.getMapHeight());
        cameraController.setZoom(0.5f);

        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();

        player = new Player(36, 36);
        player.create();


        enemies = new ArrayList<>();
        Gorgon_1 gorgon_1 = new Gorgon_1(800, 800);
        Gorgon_2 gorgon_2 = new Gorgon_2(900, 800);
        Gorgon_3 gorgon_3 = new Gorgon_3(1000, 800);

        gorgon_1.create();
        gorgon_2.create();
        gorgon_3.create();


        enemies.add(gorgon_1);
        enemies.add(gorgon_2);
        enemies.add(gorgon_3);

        gravityTest = new GravityTest(mapLoad.getGroundObjects());

    }

    @Override
    public void render() {
        float deltaTime = Gdx.graphics.getDeltaTime();

        solidObjectsManager.applyCollision(player);
        gravityTest.applyGravity(player, deltaTime);
        for (Ennemies enemy : enemies) {
            gravityTest.applyGravity(enemy, deltaTime);
            solidObjectsManager.applyCollision(enemy);
        }

        cameraController.update(new Vector2(player.getX(), player.getY()), Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        mapLoad.render();

        shapeRenderer.setProjectionMatrix(camera.combined);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.BLUE);
        for (Rectangle rect : mapLoad.getSolidObjects()) {
            shapeRenderer.rect(rect.x, rect.y, rect.width, rect.height);
        }
        shapeRenderer.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.GREEN);
        Rectangle playerBounds = player.getHitBox();
        shapeRenderer.rect(playerBounds.x, playerBounds.y, playerBounds.width, playerBounds.height);

        shapeRenderer.setColor(Color.BLUE);
        for (Ennemies enemy : enemies) {
            Rectangle enemyBounds = enemy.getHitBox();
            if (!enemy.isDead()) {
                shapeRenderer.rect(enemyBounds.x, enemyBounds.y, enemyBounds.width, enemyBounds.height);
            }
        }
        shapeRenderer.end();

        if (player.isAttacking()) {
            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
            shapeRenderer.setColor(Color.YELLOW);
            for (Rectangle attackBox : player.getAttackBoxes()) {
                shapeRenderer.rect(attackBox.x, attackBox.y, attackBox.width, attackBox.height);
            }
            shapeRenderer.end();
        }

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.RED);
        for (Rectangle ground : mapLoad.getGroundObjects()) {
            shapeRenderer.rect(ground.x, ground.y, ground.width, ground.height);
        }
        shapeRenderer.end();

        player.update(deltaTime);
        for (Ennemies enemy : enemies) {
            enemy.update(deltaTime);
        }


        for (Ennemies enemy : enemies) {
            if (!enemy.isDead()) {
                checkCollision(enemy);
            }
        }

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        player.render(batch);
        for (Ennemies enemy : enemies) {
            enemy.render(batch);
        }
        batch.end();
    }

    private void checkCollision(Ennemies enemy) {
        Rectangle enemyBounds = enemy.getHitBox();

        if (player.isAttacking()) {
            for (Rectangle attackBox : player.getAttackBoxes()) {
                if (attackBox.overlaps(enemyBounds)) {
                    enemy.die();
                    break;
                }
            }
        }
    }


    @Override
    public void dispose() {
        mapLoad.dispose();
        batch.dispose();
        shapeRenderer.dispose();
        player.dispose();
        for (Ennemies enemy : enemies) {
            enemy.dispose();
        }
    }
}
