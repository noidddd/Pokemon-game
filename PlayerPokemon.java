package Pokemon_Game;

public class PlayerPokemon extends Pokemon {
    
    //Constructor
    public PlayerPokemon(String n, Species s) {
        super(n, s);
    }

    // Methods

    // Select move based on user input
    public Move selectMove() {
        System.out.println("\n----It is " + getNickname() + "'s turn!----");
        // Display options
        for (int i = 0; i < getMoves().length; i++) {
            System.out.println("[" + (i + 1) + "] " + getMoves()[i]);
        }
        System.out.print("Choose your move: ");
        
        // Validate user input
        int choice;
        while (true) {
            try {
                choice = Integer.parseInt(Sc.nextLine());
                if (choice >= 1 && choice <= getMoves().length) {
                    break;
                } else {
                    System.out.print("Please enter a valid number: ");
                }
            } catch (NumberFormatException e) {
                System.out.print("Please enter a valid number: ");
            }
        }
        // Get the chosen move
        Move chosenMove = getMoves()[choice - 1];
        return chosenMove;
    }
    
}
