package com.java.jeux.lwjgl3.Level1;

import java.awt.Point;
import java.util.List;

public abstract class Maps {
    protected int width;
    protected int height;
    protected String backgroundImagePath;
    protected List<Platform> platforms;
    protected List<Enemy> enemies;
    protected List<Items> items;
    protected Point spawnPoint;
    protected Point goalPoint;
    protected List<Obstacle> obstacles;
    protected Tile[][] tiles;
    protected float gravity = 9.8f;
    protected String musicPath;
    protected List<Checkpoint> checkPoints;

    public Maps(int width, int height, String backgroundImagePath) {
        this.width = width;
        this.height = height;
        this.backgroundImagePath = backgroundImagePath;
    }

    // Méthode abstraite pour charger la carte
    public abstract void loadMap();

    // Méthode pour rendre la carte
    public void renderMap() {
        System.out.println("Rendu de la carte avec l'image de fond: " + backgroundImagePath);
        // Logique de rendu ici
    }

    // Méthode pour ajouter un ennemi
    public void addEnemy(Enemy enemy) {
        enemies.add(enemy);
    }

    // Méthode pour ajouter un item
    public void addItem(Items item) {
        items.add(item);
    }

    // Méthode pour définir le point de spawn
    public void setSpawnPoint(Point spawnPoint) {
        this.spawnPoint = spawnPoint;
    }

    // Méthode pour vérifier les collisions
    public boolean checkCollision(Character character) {
        // Logique de collision ici
        return false;
    }

    // Méthode pour mettre à jour la carte
    public void update() {
        System.out.println("Mise à jour de la carte.");
        // Logique de mise à jour ici
    }

    // Méthode pour réinitialiser la carte
    public void resetMap() {
        System.out.println("Réinitialisation de la carte.");
        // Logique de réinitialisation ici
    }

    // Méthode pour jouer la musique de fond
    public void playMusic() {
        System.out.println("Lecture de la musique: " + musicPath);
        // Logique de lecture de la musique ici
    }

    // Méthode pour arrêter la musique de fond
    public void stopMusic() {
        System.out.println("Arrêt de la musique.");
        // Logique d'arrêt de la musique ici
    }

    // Méthode pour ajouter un checkpoint
    public void addCheckpoint(Checkpoint checkpoint) {
        checkPoints.add(checkpoint);
    }

    // Méthode pour charger un checkpoint
    public void loadCheckpoint() {
        System.out.println("Chargement du checkpoint.");
        // Logique de chargement du checkpoint ici
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getBackgroundImagePath() {
        return backgroundImagePath;
    }

    public void setBackgroundImagePath(String backgroundImagePath) {
        this.backgroundImagePath = backgroundImagePath;
    }

    public List<Platform> getPlatforms() {
        return platforms;
    }

    public void setPlatforms(List<Platform> platforms) {
        this.platforms = platforms;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public void setEnemies(List<Enemy> enemies) {
        this.enemies = enemies;
    }

    public List<Items> getItems() {
        return items;
    }

    public void setItems(List<Items> items) {
        this.items = items;
    }

    public Point getSpawnPoint() {
        return spawnPoint;
    }

    public Point getGoalPoint() {
        return goalPoint;
    }

    public void setGoalPoint(Point goalPoint) {
        this.goalPoint = goalPoint;
    }

    public List<Obstacle> getObstacles() {
        return obstacles;
    }

    public void setObstacles(List<Obstacle> obstacles) {
        this.obstacles = obstacles;
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    public void setTiles(Tile[][] tiles) {
        this.tiles = tiles;
    }

    public float getGravity() {
        return gravity;
    }

    public void setGravity(float gravity) {
        this.gravity = gravity;
    }

    public String getMusicPath() {
        return musicPath;
    }

    public void setMusicPath(String musicPath) {
        this.musicPath = musicPath;
    }

    public List<Checkpoint> getCheckPoints() {
        return checkPoints;
    }

    public void setCheckPoints(List<Checkpoint> checkPoints) {
        this.checkPoints = checkPoints;
    }

    //  Classe Platform
    public static class Platform {
        private final int x, y;
        private final int width, height;

        public Platform(int x, int y, int width, int height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }

        public int getX() { return x; }
        public int getY() { return y; }
        public int getWidth() { return width; }
        public int getHeight() { return height; }
    }

    //  Classe Enemy
    public static class Enemy {
        private final int health;
        private final int damage;

        public Enemy(int health, int damage) {
            this.health = health;
            this.damage = damage;
        }

        public int getHealth() { return health; }
        public int getDamage() { return damage; }
    }

    //  Classe Obstacle
    public static class Obstacle {
        private final int x, y;
        private final int width, height;

        public Obstacle(int x, int y, int width, int height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }

        public int getX() { return x; }
        public int getY() { return y; }
        public int getWidth() { return width; }
        public int getHeight() { return height; }
    }

    //  Classe Tile
    public static class Tile {
        private final int x, y;
        private final String type;

        public Tile(int x, int y, String type) {
            this.x = x;
            this.y = y;
            this.type = type;
        }

        public int getX() { return x; }
        public int getY() { return y; }
        public String getType() { return type; }
    }

    //  Classe Checkpoint
    public static class Checkpoint {
        private final int x, y;
        private final String name;

        public Checkpoint(int x, int y, String name) {
            this.x = x;
            this.y = y;
            this.name = name;
        }

        public int getX() { return x; }
        public int getY() { return y; }
        public String getName() { return name; }
    }
}
