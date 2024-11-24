package com.java.jeux.level01;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.java.jeux.AbstractLevel;
import com.java.jeux.GlobalSettings;
import com.java.jeux.level01.character.Enemy;
import com.java.jeux.level01.managers.AttackManager;
import com.java.jeux.level01.managers.CameraManager;
import com.java.jeux.level01.managers.GravityManager;
import com.java.jeux.level01.managers.SolidObjectsManager;
import com.java.jeux.level01.character.Player;
import com.java.jeux.level01.character.Ennemies.Gorgon_1;

import java.util.ArrayList;
import java.util.List;

public class Level01Screen extends AbstractLevel {
    private SpriteBatch batch;
    private Player player;
    private List<Enemy> enemies;
    private Leve01MapLoader leve01MapLoader;
    private ShapeRenderer shapeRenderer;
    private GravityManager gravityManager;
    private CameraManager cameraManager;
    private OrthographicCamera camera;
    private SolidObjectsManager solidObjectsManager;
    private AttackManager attackManager;
    private Viewport viewport;
    private SpriteBatch HUDbatch = new SpriteBatch();

    public Level01Screen(Player player){
        leve01MapLoader = new Leve01MapLoader();
        leve01MapLoader.create();
        this.viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), leve01MapLoader.getCamera());

        List<Rectangle> solidObjects = leve01MapLoader.getSolidObjects();
        solidObjectsManager = new SolidObjectsManager(solidObjects);

        camera = leve01MapLoader.getCamera();
        cameraManager = new CameraManager(camera, leve01MapLoader.getMapWidth(), leve01MapLoader.getMapHeight());
        cameraManager.setZoom(0.5f);

        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();

        this.player = player;

        enemies = new ArrayList<>();
        Gorgon_1 gorgon_1 = new Gorgon_1(100, 50, 4, 2, player);
        gorgon_1.create();
        enemies.add(gorgon_1);

        // Gorgon_1 gorgon_2 = new Gorgon_1(7000, 500, 4, 2);
        // gorgon_2.create();
        // enemies.add(gorgon_2);

        // Gorgon_1 gorgon_3 = new Gorgon_1(13000, 500, 4, 2);
        // gorgon_3.create();
        // enemies.add(gorgon_3);

        // Gorgon_1 gorgon_4 = new Gorgon_1(14000, 500, 4, 2);
        // gorgon_4.create();
        // enemies.add(gorgon_4);

        // Gorgon_2 gorgon_5 = new Gorgon_2(5600, 100, 4, 2);
        // gorgon_5.create();
        // enemies.add(gorgon_5);

        // Gorgon_2 gorgon_6 = new Gorgon_2(10500, 500, 4, 2);
        // gorgon_6.create();
        // enemies.add(gorgon_6);

        // Gorgon_2 gorgon_7 = new Gorgon_2(17000, 500, 4, 2);
        // gorgon_7.create();
        // enemies.add(gorgon_7);

        // Gorgon_3 gorgon_8 = new Gorgon_3(25500, 500, 10, 3);
        // gorgon_8.create();
        // enemies.add(gorgon_8);

        gravityManager = new GravityManager(leve01MapLoader.getGroundObjects());
        attackManager = new AttackManager();
    }

    @Override
    public void show() {

    }

    @Override
    public void renderLevel(float deltaTime) {
        solidObjectsManager.applyCollision(player);
        gravityManager.applyGravity(player, deltaTime);
        for (Enemy enemy : enemies) {
            gravityManager.applyGravity(enemy, deltaTime);
            solidObjectsManager.applyCollision(enemy);
        }

        cameraManager.update(new Vector2(player.getPosition().x + player.getWeightBetweenHitBoxAndSprite() + player.getHitBox().width, player.getPosition().y));

        ScreenUtils.clear(Color.BLACK);
//        Gdx.gl.glClearColor(0, 0, 0, 1);
//        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        leve01MapLoader.render();


        if (GlobalSettings.getShowDebugHitboxes()) {
            shapeRenderer.setProjectionMatrix(camera.combined);
            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
            shapeRenderer.setColor(Color.BLUE);
            for (Rectangle rect : leve01MapLoader.getSolidObjects()) {
                shapeRenderer.rect(rect.x, rect.y, rect.width, rect.height);
            }
            shapeRenderer.end();

            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
            shapeRenderer.setColor(Color.BROWN);
            for (Rectangle rect : leve01MapLoader.getDeathZoneObjects()) {
                shapeRenderer.rect(rect.x, rect.y, rect.width, rect.height);
            }
            shapeRenderer.end();

            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
            shapeRenderer.setColor(Color.GREEN);
            Rectangle playerBounds = player.getHitBox();
            shapeRenderer.rect(playerBounds.x, playerBounds.y, playerBounds.width, playerBounds.height);

            shapeRenderer.setColor(Color.BLUE);
            for (Enemy enemy : enemies) {
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
            for (Rectangle ground : leve01MapLoader.getGroundObjects()) {
                shapeRenderer.rect(ground.x, ground.y, ground.width, ground.height);
            }
            shapeRenderer.end();

        }



        player.update(deltaTime);
        for (Enemy enemy : enemies) {
            enemy.update(deltaTime);
        }


        attackManager.checkPlayerAttacks(player, enemies);
        attackManager.checkEnemyAttacks(player, enemies);
        attackManager.resetPlayerHit();

        for (Rectangle deathZone : leve01MapLoader.getDeathZoneObjects()) {
            if (player.getHitBox().overlaps(deathZone)) {
                player.die();
            }

            for (Enemy enemy : enemies) {
                if (enemy.getHitBox().overlaps(deathZone)) {
                    enemy.die();
                }
            }
        }

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        player.render(batch);
        for (Enemy enemy : enemies) {
            enemy.render(batch);
        }
        batch.end();

        HUDbatch.begin();
        // Render hearts on top of the sprite batch.
        HUDbatch.end();
    }

    @Override
    public void resize(int width, int height) {
        this.viewport.update(width, height, false);
    }

    @Override
    public void pause() {
        // Never called bc not android
    }

    @Override
    public void resume() {
        // Never called bc not android
    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        leve01MapLoader.dispose();
        batch.dispose();
        shapeRenderer.dispose();
        player.dispose();
        for (Enemy enemy : enemies) {
            enemy.dispose();
        }
    }

    public Player getPlayer() {
        return player;
    }
}
