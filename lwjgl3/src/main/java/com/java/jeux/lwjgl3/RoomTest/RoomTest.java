package com.java.jeux.lwjgl3.RoomTest;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class RoomTest extends ApplicationAdapter {
    private SpriteBatch batch;
    private Player player;
    private TestEnemie enemy;
    private RoomTestMapLoad roomTestMapLoad;
    private ShapeRenderer shapeRenderer;
    private GravityTest gravityTest;
    private CameraController cameraController;
    private OrthographicCamera camera;

    @Override
    public void create() {
        roomTestMapLoad = new RoomTestMapLoad();
        roomTestMapLoad.create();

        camera = roomTestMapLoad.getCamera();
        cameraController = new CameraController(camera, roomTestMapLoad.getMapWidth(), roomTestMapLoad.getMapHeight());
        cameraController.setZoom(0.5f);

        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();

        player = new Player(200, 36);
        player.create();

        enemy = new TestEnemie(800, 800);
        enemy.create();

        gravityTest = new GravityTest(roomTestMapLoad.getGroundObjects());
    }

    @Override
    public void render() {
        float deltaTime = Gdx.graphics.getDeltaTime();
        gravityTest.applyGravity(player, deltaTime);
        gravityTest.applyGravity(enemy, deltaTime);

        cameraController.update(new Vector2(player.getX(), player.getY()), Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        roomTestMapLoad.render();

        shapeRenderer.setProjectionMatrix(camera.combined);

        // Dessiner les hitboxes des objets
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.GREEN);
        Rectangle playerBounds = player.getBounds();
        shapeRenderer.rect(playerBounds.x, playerBounds.y, playerBounds.width, playerBounds.height);

        shapeRenderer.setColor(Color.BLUE);
        Rectangle enemyBounds = enemy.getBounds();
        if (!enemy.isDead()) {
            shapeRenderer.rect(enemyBounds.x, enemyBounds.y, enemyBounds.width, enemyBounds.height);
        }
        shapeRenderer.end();

        // Dessiner les hitboxes du sol (existant)
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.RED);
        for (Rectangle ground : roomTestMapLoad.getGroundObjects()) {
            shapeRenderer.rect(ground.x, ground.y, ground.width, ground.height);
        }
        shapeRenderer.end();

        player.update(deltaTime);
        enemy.update(deltaTime);
        if (!enemy.isDead()) {
            checkCollision();
        }

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        player.render(batch);
        enemy.render(batch);
        batch.end();
    }

    private void checkCollision() {
        Rectangle playerBounds = player.getBounds();
        Rectangle enemyBounds = enemy.getBounds();

        if (player.isAttacking() && playerBounds.overlaps(enemyBounds)) {
            enemy.die();
        }
    }

    @Override
    public void dispose() {
        roomTestMapLoad.dispose();
        batch.dispose();
        shapeRenderer.dispose();
        player.dispose();
        enemy.dispose();
    }
}
