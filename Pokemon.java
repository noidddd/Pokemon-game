package Pokemon_Game;

import java.util.Scanner;

public class Pokemon {
    private String nickname;
    private Species species;
    private int health;

    // Constructor
    public Pokemon(String n, Species s) {
        nickname = n;
        species = s;
        health = species.getHealth();
    }

    // Setters and Getters

    public void setNickname(String n) {
        nickname = n;
    }

    public String getNickname() {
        return nickname;
    }

    public Species getSpecies() {
        return species;
    }

    public String getSpeciesName() {
        return species.getName();
    }

    public Type getType() {
        return species.getType();
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int h) {
        health = h;
    }

    public int getAttack() {
        return species.getAttack();
    }

    public int getDefense() {
        return species.getDefense();
    }

    public int getSpeed() {
        return species.getSpeed();
    }

    public Move[] getMoves() {
        return species.getMoves();
    }

    // toString

    @Override
    public String toString() {
        return nickname + " [HP: " + getHealth() + "]";
    }

    // Other methods

    // Reduce health based on damage taken
    public void takeDamage(int dmg) {
        System.out.println(toString() + " takes " + dmg + " damage.");
        setHealth(getHealth() - dmg);
    }

    Scanner Sc = new Scanner(System.in);

    // Select first move by default
    public Move selectMove() {
        System.out.println("\n----It is " + getNickname() + "'s turn!----");
        
        Move chosenMove = getMoves()[0];
        return chosenMove;
    }

    // Check if health has reached 0
    public boolean isDefeated() {
        if(getHealth() < 1) {
            setHealth(0); // Reset health to 0 to avoid negative values
            return true;
        } else {
            return false;
        }
    }
}
