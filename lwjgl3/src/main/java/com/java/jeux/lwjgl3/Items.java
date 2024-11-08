package com.java.jeux.lwjgl3;

public abstract class Items {
    protected Sprite sprite;
    protected String effect;
    protected int x;
    protected int y;

    public Items(Sprite sprite, String effect, int x, int y) {
        this.sprite = sprite;
        this.effect = effect;
        this.x = x;
        this.y = y;
    }

    // Méthode abstraite à implémenter par les sous-classes (pour appliquer l'effet de l'item au personnage)
    public abstract void applyEffect(Character character);

    // Méthode pour rendre l'item visible dans le jeu (par exemple, afficher son sprite)
    public void render() {
        System.out.println("Rendre l'item à la position: (" + x + ", " + y + ")");
        sprite.loadImage();  // Charge l'image du sprite
    }

    // Méthode pour définir la position de l'item
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getEffect() {
        return effect;
    }

    // Classe interne Sprite
    public static class Sprite {
        private String imagePath;
        private final int width;
        private final int height;

        public Sprite(String imagePath, int width, int height) {
            this.imagePath = imagePath;
            this.width = width;
            this.height = height;
        }

        // Méthode pour charger l'image (peut être étendue selon les besoins)
        public void loadImage() {
            System.out.println("Chargement de l'image: " + imagePath);
        }

        // Méthodes pour obtenir les dimensions de l'image
        public int getWidth() {
            return width;
        }

        public int getHeight() {
            return height;
        }

        public String getImagePath() {
            return imagePath;
        }

        public void setImagePath(String imagePath) {
            this.imagePath = imagePath;
        }
    }
}
