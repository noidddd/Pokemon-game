package Pokemon_Game;

import java.util.Random;

public class EnemyPokemon extends Pokemon {
    
    // Constructor
    public EnemyPokemon(String n, Species s) {
        super(n, s);
    }

    // Methods

    // Randomly select move to use
    public Move selectMove() {
        System.out.println("\n----It is " + getNickname() + "'s turn!----");

        Random random = new Random();
        int randomIndex = random.nextInt(getMoves().length);
        Move chosenMove = getMoves()[randomIndex];

        // Get the chosen move
        return chosenMove;
    }
}
