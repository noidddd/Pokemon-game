package Pokemon_Game;

public enum Move {
    // Constants
    TACKLE("Tackle", Type.Normal, 40),
    SCRATCH("Scratch", Type.Normal, 40),
    QUICK_ATTACK("Quick Attack", Type.Normal, 40),
    BITE("Bite", Type.Normal, 60),
    EMBER("Ember", Type.Fire, 40),
    VINE_WHIP("Vine Whip", Type.Grass, 45),
    WATER_GUN("Water Gun", Type.Water, 40),
    BUG_BITE("Bug Bite", Type.Bug, 60),
    POISON_STING("Poison Sting", Type.Poison, 15),
    GUST("Gust", Type.Flying, 40),
    PECK("Peck", Type.Flying, 35),
    THUNDER_SHOCK("Thunder Shock", Type.Electric, 40);

    private String name;
    private Type type;
    private int damage;

    // Constructor
    Move(String n, Type t, int d) {
        name = n;
        type = t;
        damage = d;
    }

    // Getters

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    public int getDamage() {
        return damage;
    }

    // toString

    @Override
    public String toString() {
        return name + ": deals " + type + " damage";
    }
}
