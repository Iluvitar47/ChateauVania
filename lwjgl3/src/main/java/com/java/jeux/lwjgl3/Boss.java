package com.java.jeux.lwjgl3;

enum SpecialMove {
    FIREBALL,
    LIGHTNING_STRIKE
}

public class Boss extends Enemies {
    private float fly;
    private SpecialMove specialMove;

    public Boss(AIType aiType, String name, int health, int damage, int lives, float fly, SpecialMove initialMove) {
        super(aiType, name, health, damage, lives);
        this.fly = fly;
        this.specialMove = initialMove;
    }

    @Override
    public void behaviourAI() {
        if (aiType == AIType.BOSS) {
            System.out.println(getName() + " utilise son pouvoir spécial : " + specialMove);
            useSpecialMove();
        }
    }

    // Méthode pour utiliser le sort spécial
    public void useSpecialMove() {
        switch (specialMove) {
            case FIREBALL:
                System.out.println(getName() + " lance une boule de feu !");
                break;
            case LIGHTNING_STRIKE:
                System.out.println(getName() + " frappe avec un éclair !");
                break;
        }
    }

    // Méthode pour alterner entre les sorts spéciaux
    public void toggleSpecialMove() {
        if (specialMove == SpecialMove.FIREBALL) {
            specialMove = SpecialMove.LIGHTNING_STRIKE;
        } else {
            specialMove = SpecialMove.FIREBALL;
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

    public float getFly() {
        return fly;
    }

    public void setFly(float fly) {
        this.fly = fly;
    }

    public SpecialMove getSpecialMove() {
        return specialMove;
    }

    public void setSpecialMove(SpecialMove specialMove) {
        this.specialMove = specialMove;
    }
}
