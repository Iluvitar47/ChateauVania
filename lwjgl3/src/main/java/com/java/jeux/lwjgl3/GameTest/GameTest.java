package com.java.jeux.lwjgl3.GameTest;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class GameTest extends ApplicationAdapter {
    private SpriteBatch batch;
    private TestPlayer player;
    private TestEnemie enemy;
    private MapTest mapTest;
    private ShapeRenderer shapeRenderer;
    private GravityTest gravityTest;

    @Override
    public void create() {

        mapTest = new MapTest();
        mapTest.create();

        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();


        player = new TestPlayer(200, 36);
        player.create();

        enemy = new TestEnemie(800, 800);
        enemy.create();

        gravityTest = new GravityTest(mapTest.getGroundObjects());
    }

    @Override
    public void render() {
        float deltaTime = Gdx.graphics.getDeltaTime();
        gravityTest.applyGravity(player, deltaTime);
        gravityTest.applyGravity(enemy, deltaTime);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        mapTest.render();


        shapeRenderer.setProjectionMatrix(mapTest.getCamera().combined);


        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.RED);
        for (Rectangle ground : mapTest.getGroundObjects()) {
            shapeRenderer.rect(ground.x, ground.y, ground.width, ground.height);
        }
        shapeRenderer.end();


        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.YELLOW);
        Rectangle gravityHitbox = player.getGravityHitbox();
        shapeRenderer.rect(gravityHitbox.x, gravityHitbox.y, gravityHitbox.width, gravityHitbox.height);
        shapeRenderer.end();



        player.update(deltaTime);
        enemy.update(deltaTime);
        if (!enemy.isDead()) {
            checkCollision();
        }


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
        mapTest.dispose();
        batch.dispose();
        shapeRenderer.dispose();
        player.dispose();
        enemy.dispose();
    }
}
