package com.java.jeux.lwjgl3.Level1;

public class CharactersSprites {
    private String hurt;
    private String attack;
    private String idle;
    private String jump;
    private String run;

    private String attackSound;
    private String deathSound;
    private String hitSound;


    public CharactersSprites(String hurt, String attack, String idle, String jump, String run,
                            String attackSound, String deathSound, String hitSound) {
        this.hurt = hurt;
        this.attack = attack;
        this.idle = idle;
        this.jump = jump;
        this.run = run;
        this.attackSound = attackSound;
        this.deathSound = deathSound;
        this.hitSound = hitSound;
    }

    // Méthode pour charger un sprite spécifique basé sur le type (par exemple "hurt", "attack")
    public void loadSprite(String type) {
        switch (type) {
            case "hurt":
                System.out.println("Chargement du sprite pour 'hurt': " + hurt);
                break;
            case "attack":
                System.out.println("Chargement du sprite pour 'attack': " + attack);
                break;
            case "idle":
                System.out.println("Chargement du sprite pour 'idle': " + idle);
                break;
            case "jump":
                System.out.println("Chargement du sprite pour 'jump': " + jump);
                break;
            case "run":
                System.out.println("Chargement du sprite pour 'run': " + run);
                break;
            default:
                System.out.println("Type de sprite inconnu: " + type);
                break;
        }
    }

    // Méthode pour jouer un son spécifique basé sur le type (par exemple "attack", "death")
    public void playSound(String type) {
        switch (type) {
            case "attack":
                System.out.println("Jouer le son de l'attaque: " + attackSound);
                break;
            case "death":
                System.out.println("Jouer le son de la mort: " + deathSound);
                break;
            case "hit":
                System.out.println("Jouer le son du coup reçu: " + hitSound);
                break;
            default:
                System.out.println("Type de son inconnu: " + type);
                break;
        }
    }

    // Méthode pour obtenir le sprite d'un type spécifique
    public String getSprite(String type) {
        switch (type) {
            case "hurt":
                return hurt;
            case "attack":
                return attack;
            case "idle":
                return idle;
            case "jump":
                return jump;
            case "run":
                return run;
            default:
                return null;
        }
    }

    // Méthode pour obtenir le son d'un type spécifique
    public String getSound(String type) {
        switch (type) {
            case "attack":
                return attackSound;
            case "death":
                return deathSound;
            case "hit":
                return hitSound;
            default:
                return null;
        }
    }

    // Méthode pour précharger les ressources (sprites et sons)
    public void preloadRessources() {
        System.out.println("Préchargement des ressources:");
        System.out.println("Sprites: " + hurt + ", " + attack + ", " + idle + ", " + jump + ", " + run);
        System.out.println("Sons: " + attackSound + ", " + deathSound + ", " + hitSound);
    }

    // Méthode pour mettre à jour un sprite spécifique
    public void updateSprite(String type, String newSprite) {
        switch (type) {
            case "hurt":
                this.hurt = newSprite;
                break;
            case "attack":
                this.attack = newSprite;
                break;
            case "idle":
                this.idle = newSprite;
                break;
            case "jump":
                this.jump = newSprite;
                break;
            case "run":
                this.run = newSprite;
                break;
            default:
                System.out.println("Type de sprite inconnu pour mise à jour: " + type);
                break;
        }
    }

    // Méthode pour mettre à jour un son spécifique
    public void updateSound(String type, String newSound) {
        switch (type) {
            case "attack":
                this.attackSound = newSound;
                break;
            case "death":
                this.deathSound = newSound;
                break;
            case "hit":
                this.hitSound = newSound;
                break;
            default:
                System.out.println("Type de son inconnu pour mise à jour: " + type);
                break;
        }
    }

    // Getters et setters
    public String getHurt() {
        return hurt;
    }

    public void setHurt(String hurt) {
        this.hurt = hurt;
    }

    public String getAttack() {
        return attack;
    }

    public void setAttack(String attack) {
        this.attack = attack;
    }

    public String getIdle() {
        return idle;
    }

    public void setIdle(String idle) {
        this.idle = idle;
    }

    public String getJump() {
        return jump;
    }

    public void setJump(String jump) {
        this.jump = jump;
    }

    public String getRun() {
        return run;
    }

    public void setRun(String run) {
        this.run = run;
    }

    public String getAttackSound() {
        return attackSound;
    }

    public void setAttackSound(String attackSound) {
        this.attackSound = attackSound;
    }

    public String getDeathSound() {
        return deathSound;
    }

    public void setDeathSound(String deathSound) {
        this.deathSound = deathSound;
    }

    public String getHitSound() {
        return hitSound;
    }

    public void setHitSound(String hitSound) {
        this.hitSound = hitSound;
    }
}
