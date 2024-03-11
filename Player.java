package Pokemon_Game;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Player {
    private String name;
    private int score;
    private Pokemon[] pokemons;

    // Constructor

    public Player(String n, int s, Pokemon[] p) {
        name = n;
        score = s;
        pokemons = p;
    }

    // Setters and getters

    public void setName(String s) {
        name = s;
    }

    public String getName() {
        return name;
    }

    public void setScore(int s) {
        score = s;
    }

    public int getScore() {
        return score;
    }

    public Pokemon[] getPokemons() {
        return pokemons;
    }

    // toString

    @Override
    public String toString() {
        return name + ": " + score;
    }

    // Other methods

    public void addPokemon(Pokemon p) {
        pokemons = Arrays.copyOf(pokemons, pokemons.length + 1);
        pokemons[pokemons.length - 1] = p;
        System.out.println(p.getNickname() + " has been added to your party!\n");
    }

    // Save player information to a text file
    public void saveToFile(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            // Write the player's name and score
            writer.write(name + ", " + score);
            writer.newLine();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Display leaderboard of scores
    public void displayTopScores(String fileName) {
        List<Player> players = new ArrayList<>();

        // Read scores from file
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", ");
                if (parts.length >= 2) {
                    String playerName = parts[0];
                    int playerScore = Integer.parseInt(parts[1]);
                    players.add(new Player(playerName, playerScore, null));
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
            return;
        }

        // Sort players based on score in descending order
        players.sort((p1, p2) -> Integer.compare(p2.getScore(), p1.getScore()));

        // Display the top 5 scores
        System.out.println("\n-----Leaderboard-----");
        int count = 0;
        for (Player p : players) {
            System.out.println(p);
            count++;
            if (count == 5) {
                break;
            }
        }
    }
}