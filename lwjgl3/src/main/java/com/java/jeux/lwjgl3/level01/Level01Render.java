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
    private AttackManager attackManager;

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

        player = new Player(64, 50, 10, 2);
        player.create();

        enemies = new ArrayList<>();
        Gorgon_1 gorgon_1 = new Gorgon_1(4000, 500, 4, 2);
        gorgon_1.create();
        enemies.add(gorgon_1);

        Gorgon_1 gorgon_2 = new Gorgon_1(7000, 500, 4, 2);
        gorgon_2.create();
        enemies.add(gorgon_2);

        Gorgon_1 gorgon_3 = new Gorgon_1(13000, 500, 4, 2);
        gorgon_3.create();
        enemies.add(gorgon_3);

        Gorgon_1 gorgon_4 = new Gorgon_1(14000, 500, 4, 2);
        gorgon_4.create();
        enemies.add(gorgon_4);

        Gorgon_2 gorgon_5 = new Gorgon_2(5600, 100, 4, 2);
        gorgon_5.create();
        enemies.add(gorgon_5);

        Gorgon_2 gorgon_6 = new Gorgon_2(10500, 500, 4, 2);
        gorgon_6.create();
        enemies.add(gorgon_6);

        Gorgon_2 gorgon_7 = new Gorgon_2(17000, 500, 4, 2);
        gorgon_7.create();
        enemies.add(gorgon_7);

        Gorgon_3 gorgon_8 = new Gorgon_3(25500, 500, 10, 3);
        gorgon_8.create();
        enemies.add(gorgon_8);

        gravityTest = new GravityTest(mapLoad.getGroundObjects());
        attackManager = new AttackManager();
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

        cameraController.update(new Vector2(player.getPosition().x + player.getWeightBetweenHitBoxAndSprite() + player.getHitBox().width, player.getPosition().y), Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

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


        attackManager.checkPlayerAttacks(player, enemies);

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        player.render(batch);
        for (Ennemies enemy : enemies) {
            enemy.render(batch);
        }
        batch.end();
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
