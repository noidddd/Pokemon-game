package Pokemon_Game;

public enum Species {
    // Constants
    CHARMANDER("Charmander", Type.Fire, 39, 52, 43, 65, new Move[]{Move.SCRATCH, Move.EMBER}),
    BULBASAUR("Bulbasaur", Type.Grass, 45, 49, 49, 45, new Move[]{Move.TACKLE, Move.VINE_WHIP}),
    SQUIRTLE("Squirtle", Type.Water, 44, 48, 65, 43, new Move[]{Move.TACKLE, Move.WATER_GUN}),
    CATERPIE("Caterpie", Type.Bug, 45, 30, 35, 45, new Move[]{Move.TACKLE, Move.BUG_BITE}),
    WEEDLE("Weedle", Type.Poison, 40, 35, 30, 50, new Move[]{Move.TACKLE, Move.POISON_STING}),
    PIDGEY("Pidgey", Type.Flying, 40, 45, 40, 56, new Move[]{Move.TACKLE, Move.GUST}),
    RATTATA("Rattata", Type.Normal, 30, 56, 35, 72, new Move[]{Move.TACKLE, Move.QUICK_ATTACK}),
    SPEAROW("Spearow", Type.Flying, 40, 60, 30, 70, new Move[]{Move.SCRATCH, Move.PECK}),
    EKANS("Ekans", Type.Poison, 35, 60, 44, 55, new Move[]{Move.BITE, Move.POISON_STING}),
    PIKACHU("Pikachu", Type.Electric, 35, 55, 40, 90, new Move[]{Move.QUICK_ATTACK, Move.THUNDER_SHOCK});

    private String name;
    private Type type;
    private int health;
    private int attack;
    private int defense;
    private int speed;
    private Move[] moves;

    // Constructor

    Species(String n, Type t, int h, int a, int d, int s, Move[] m) {
        name = n;
        type = t;
        health = h;
        attack = a;
        defense = d;
        speed = s;
        moves = m;
    }

    // Setters and getters

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    public void setHealth(int h) {
        health = h;
    }

    public int getHealth() {
        return health;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public int getSpeed() {
        return speed;
    }

    public Move[] getMoves() {
        return moves;
    }

    // toString

    @Override
    public String toString() {
        return name + ": " + type + " Type";
    }
}