package com.java.jeux.lwjgl3;

public class Boss extends Ennemies {
    private float fly;
    private String specialMove;

    public Boss(AIType aiType, String name, int health, int damage, int lives, float fly, String specialMove) {
        super(aiType, name, health, damage, lives);
        this.fly = fly;
        this.specialMove = specialMove;
    }

    @Override
    public void behaviourAI() {
        if (aiType == AIType.BOSS) {
            System.out.println(getName() + " utilise son pouvoir spécial : " + specialMove);
            // Logique spécifique pour le pouvoir spécial ou comportement du boss
        }
    }

    // Méthode pour afficher les informations du boss
    public void displayBossInfo() {
        System.out.println("Boss: " + getName());
        System.out.println("Santé: " + getHealth());
        System.out.println("Dégâts: " + getDamage());
        System.out.println("Vies: " + getLives());
        System.out.println("Capacité de vol: " + fly);
        System.out.println("Mouvement spécial: " + specialMove);
    }

    // Getters et Setters
    public float getFly() {
        return fly;
    }

    public void setFly(float fly) {
        this.fly = fly;
    }

    public String getSpecialMove() {
        return specialMove;
    }

    public void setSpecialMove(String specialMove) {
        this.specialMove = specialMove;
    }
}