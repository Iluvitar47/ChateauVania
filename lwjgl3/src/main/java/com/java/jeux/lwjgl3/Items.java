package com.java.jeux.lwjgl3;

// Abstract class Items
public abstract class Items {
    // Attributs de la classe Items
    protected Sprite sprite;  // Représente l'élément visuel de l'item
    protected String effect;  // L'effet de l'item (par exemple, "health", "boost", etc.)
    protected int x;          // Position de l'item sur l'axe X
    protected int y;          // Position de l'item sur l'axe Y

    // Constructeur
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
        // Code pour rendre l'item visible dans le jeu
        System.out.println("Rendre l'item à la position: (" + x + ", " + y + ")");
        // Le code de rendu réel dépendra de la logique du moteur de jeu
    }

    // Méthode pour définir la position de l'item
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Méthode pour vérifier si l'item est collecté par le personnage
    public boolean isCollected(Character character) {
        // Cette logique peut vérifier si la position de l'item et du personnage sont assez proches
        if (Math.abs(character.getX() - this.x) < 10 && Math.abs(character.getY() - this.y) < 10) {
            return true;
        }
        return false;
    }

    // Méthode pour retirer l'item du jeu après qu'il ait été collecté
    public void remove() {
        // Code pour supprimer l'item (par exemple, le retirer de la liste des items ou le supprimer de la scène)
        System.out.println("L'item a été supprimé.");
    }

    // Getters et Setters
    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
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
}