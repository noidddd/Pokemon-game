package Pokemon_Game;

import java.util.Scanner;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class RunGame {
    public static void main(String[] args) {
        System.out.println("\n----------Welcome to Pokemon Ga-Ole!----------\n");
        System.out.println("In Battle and Catch, your team of pokemon will face off against various enemies.\n");
        System.out.println("These are a few Pokemon which you may encounter...");
        System.out.println(spawnPokemon().getSpecies().toString());
        System.out.println(spawnPokemon().getSpecies().toString());
        // Initialise player
        Player player = new Player(inputPlayerName(), 0, new Pokemon[]{});

        // Create starting pokemon
        Pokemon pokemon1 = spawnPokemon();
        System.out.println("\nYou will start with a pokemon of the species " + pokemon1.getSpecies());
        PlayerPokemon finalPokemon1 = new PlayerPokemon(pokemon1.getNickname(), pokemon1.getSpecies());
        finalPokemon1.setNickname(inputPokemonName());
        player.addPokemon(finalPokemon1);

        // Prompt user to pick one of 3 pokemon to catch
        PlayerPokemon pokemon2 = selectPokemon();
        pokemon2.setNickname(inputPokemonName());
        player.addPokemon(pokemon2);

        // Spawn random enemy pokemon
        Pokemon enemy1 = spawnPokemon();
        System.out.println("A wild " + enemy1.getSpeciesName() + " has appeared!");
        Pokemon enemy2 = spawnPokemon();
        System.out.println("A wild " + enemy2.getSpeciesName() + " has appeared!");
        EnemyPokemon finalEnemy1 = new EnemyPokemon(enemy1.getNickname(), enemy1.getSpecies());
        EnemyPokemon finalEnemy2 = new EnemyPokemon(enemy2.getNickname(), enemy2.getSpecies());

        // Run the battle
        runBattleInOrder(player, finalPokemon1, pokemon2, finalEnemy1, finalEnemy2);

        // Save details of player
        player.saveToFile("Scores.txt");

        // Display the top 5 scores
        player.displayTopScores("Scores.txt");
    }

    // Function to return player name
    public static String inputPlayerName() {
        // Take user input for the username
        Scanner Sc = new Scanner(System.in);
        System.out.print("\nEnter your name to begin: ");

        // Validate and read the username
        String name = "";
        while (true) {
            name = Sc.nextLine();

            // Check if the username meets the criteria
            if (isValidName(name)) {
                return name;
            } else {
                System.out.print("Please enter a valid username: ");
            }
        }
    }

    // Function to validate player name
    private static boolean isValidName(String name) {
        // Check if the username contains only letters and numbers
        if (!name.matches("[a-zA-Z0-9]+")) {
            return false;
        }

        // Check if the username is not more than 12 characters long
        return name.length() <= 12;
    }

    // Prompt user to select their Pokemon
    public static PlayerPokemon selectPokemon() {
        System.out.println("\nSelect one new Pokemon to add to your party!");
        System.out.println("[1] " + Species.BULBASAUR.toString());
        System.out.println("[2] " + Species.CHARMANDER.toString());
        System.out.println("[3] " + Species.SQUIRTLE.toString());

        Scanner Sc = new Scanner(System.in);
        System.out.print("Enter your choice: ");
        // Validate user input
        int choice;
        while (true) {
            try {
                choice = Integer.parseInt(Sc.nextLine());
                if (choice >= 1 && choice <= 3) {
                    switch(choice) {
                        case 1:
                            return new PlayerPokemon("", Species.BULBASAUR);
                        case 2:
                            return new PlayerPokemon("", Species.CHARMANDER);
                        case 3:
                            return new PlayerPokemon("", Species.SQUIRTLE);
                    }
                } else {
                    System.out.print("Please enter a valid number: ");
                }
            } catch (NumberFormatException e) {
                System.out.print("Please enter a valid number: ");
            }
        }
    }

    // Prompt user to name their new Pokemon
    public static String inputPokemonName() {
        // Take user input for the name
        Scanner Sc = new Scanner(System.in);
        System.out.print("Enter a name for your pokemon: ");

        // Validate and read the name
        String name = "";
        while (true) {
            name = Sc.nextLine();

            // Check if the name meets the criteria
            if (isValidName(name)) {
                return name;
            } else {
                System.out.print("Please enter a valid name: ");
            }
        }
    }

    // Spawn new Pokemon of a random Species
    public static Pokemon spawnPokemon() {
        // Get list of Species
        Species[] allSpecies = Species.values();
        List<Species> speciesList = Arrays.asList(allSpecies);
        // Select a random Species
        Random rand = new Random();
        Species randSpecies = speciesList.get(rand.nextInt(speciesList.size()));
        return new Pokemon(randSpecies.getName(), randSpecies);
    }

    // Return the final damage value of a move
    public static int getFinalDamage(Pokemon attacking, Move m, Pokemon defending) {
        System.out.println("\n" + attacking + " used " + m.getName() + " on " + defending + "!");

        Type moveType = m.getType();
        Type pType = defending.getType();

        // Calculation for base damage
        int dmg = (m.getDamage() * attacking.getAttack() / defending.getDefense()) / 2;

        // Alter damage amount based on type effectiveness
        if(moveType.isSuperEffective(pType)) {
            System.out.println("It is super effective!");
            return (dmg * 2);
        } else if(moveType.isNotVeryEffective(pType)) {
            System.out.println("It is not very effective");
            return (dmg / 2);
        }
        return dmg;
    }

    // Use move on a defending Pokemon
    public static void useMove(Pokemon attacking, Move move, Pokemon defending) {
        int dmg = getFinalDamage(attacking, move, defending);

        // Generate chances with 5% possibility of occuring
        int chance = new Random().nextInt(99);
        if(chance >= 0 && chance < 5) {
            System.out.println("* CHANCE: Critical Hit - Extra damage! *"); // Critical hit chance
            dmg = (int) (dmg * 1.5); // Damage is multiplied by 1.5
        } else if (chance >= 90 && chance < 95) {
            System.out.println("* CHANCE: Dodge - " + defending.getNickname() + " dodges the attack completely! *");
            dmg = 0; // Pokemon dodges attack and takes no damage
        }

        // Deal damage to defending Pokemon
        defending.takeDamage(dmg);

        // Check if defending Pokemon's health has gone to 0 following the damage
        if(defending.isDefeated() == true) {
            System.out.println("// " + defending.getNickname() + " has been defeated! //");

            // Generate 5% chance for revival
            if(chance >= 50 && chance < 55) {
                System.out.println("* CHANCE: Revival - " + defending.getNickname() + " is revived with half health! *");
                defending.setHealth(defending.getSpecies().getHealth() / 2); // Defeated Pokemon is brought back with 50% health
            }
        }
    }

    // Remove all defeated Pokemon from array
    public static Pokemon[] filterPokemon(Pokemon[] pList) {
        int aliveCount = 0;
        for (Pokemon p : pList) {
            if (p.isDefeated() == false) {
                aliveCount++;
            }
        }

        // Create a new array
        Pokemon[] alivePokemon = new Pokemon[aliveCount];

        // Populate the array
        int index = 0;
        for (Pokemon p : pList) {
            if (p.isDefeated() == false) {
                alivePokemon[index++] = p;
            }
        }
        return alivePokemon;
    }

    // Check if a Pokemon is contained in an array
    public static boolean contained(Pokemon pokemon, Pokemon[] pList) {
        for (Pokemon p : pList) {
            if (p == pokemon) {
                return true;
            }
        }
        return false;
    }
    
    // Make attempt to catch defeated Pokemon
    public static void catchPokemon(Player player, Pokemon pokemon) {
        System.out.println("\nAttempting to catch " + pokemon.getNickname() + "...");
        // Generate a random number between 0 and 99
        int chance = new Random().nextInt(100);

        // There is a 50% chance of catching the pokemon
        if(chance < 50) {
            System.out.println("Catch successful!");
            player.addPokemon(pokemon);
            // Player gets points if Pokemon is successfully caught
            int points = new Random().nextInt(350, 450);
            player.setScore(player.getScore() + points);
        } else {
            System.out.println("Catch failed...");
        }
    }

    // Run the battle between player and computer-controlled Pokemon
    public static void runBattle(Pokemon pFirst, Pokemon pSecond, Pokemon pThird, Pokemon pFourth) {
        
        Pokemon[] firstGroup = {pFirst, pThird};
        Pokemon[] secondGroup = {pSecond, pFourth};

        // Run loop until either group of Pokemon is defeated
        while((!(pFirst.isDefeated()) || !(pThird.isDefeated())) && (!(pSecond.isDefeated()) || !(pFourth.isDefeated()))) {
            if(firstGroup.length > 0 && secondGroup.length > 0) {
                useMove(firstGroup[0], firstGroup[0].selectMove(), secondGroup[0]);
                firstGroup = filterPokemon(firstGroup); // Reset array to ignore defeated Pokemon
                secondGroup = filterPokemon(secondGroup);
            } else {
                break; // Break loop if both Pokemon are defeated
            }
            if(firstGroup.length > 0 && secondGroup.length > 0) {
                useMove(secondGroup[0], secondGroup[0].selectMove(), firstGroup[0]);
                firstGroup = filterPokemon(firstGroup);
                secondGroup = filterPokemon(secondGroup);
            } else {
                break;
            }
        }
    }

    // Get order of action in battle based on speed stat
    public static void runBattleInOrder(Player player, PlayerPokemon p1, PlayerPokemon p2, EnemyPokemon p3, EnemyPokemon p4) {
        System.out.println("\n" + p1.getNickname() + " is fighting " + p3.getNickname());

        int playerSpeedTotal = p1.getSpeed() + p2.getSpeed();
        int enemySpeedTotal = p3.getSpeed() + p4.getSpeed();
        Pokemon[] enemies = {p3, p4};

        // Player or computer goes first based on who has highest total speed between their Pokemon
        if (playerSpeedTotal > enemySpeedTotal || playerSpeedTotal == enemySpeedTotal) {
            runBattle(p1, p3, p2, p4); // Player moves first
        } else {
            runBattle(p3, p1, p4, p2); // Computer moves first
        }

        // Display whether battle is won or lost
        if(p1.isDefeated() && p2.isDefeated()) {
            System.out.println("\nYou have lost the battle...");
        }
        else if(p3.isDefeated() && p4.isDefeated()) {
            System.out.println("\nYou won the battle!");
            // If won, attempt to catch both enemy Pokemon
            catchPokemon(player, p3);
            catchPokemon(player, p4);
        }

        // Increase player score for each defeated enemy pokemon
        for(Pokemon p : enemies) {
            if (p.isDefeated()) {
                int points = new Random().nextInt(450, 550);
                player.setScore(player.getScore() + points);
            }
        }

        // Display player score
        System.out.println("\nYour Score: " + player.getScore());
    }

}
