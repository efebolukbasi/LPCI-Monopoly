import java.util.Scanner;
import java.util.Random;


public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Random rand = new Random();

        // Board Tiles Values
        int[] boardTileValues = new int[30];
        for (int i = 0; i < boardTileValues.length; i++) {
            int randomValue = rand.nextInt(7) - 3;
            boardTileValues[i] = randomValue;
        }

        // Number of Players Loop (Makes sure it is between 2-4)
        int numPlayers;
        do{
            System.out.print("Enter number of players (Between 2-4): ");
            numPlayers = in.nextInt();
        } while (numPlayers > 4 || numPlayers < 2);

    }
    public static String[] generateRandomDeck(int numCards) {
        String[] deck = new String[numCards];
        String[] suits = {"Spades", "Hearts", "Diamonds", "Clubs"};
        String[] ranks = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};

        Random rand = new Random();

        for (int i = 0; i < numCards; i++) {
            int suitIndex = rand.nextInt(suits.length);
            int rankIndex = rand.nextInt(ranks.length);
            deck[i] = ranks[rankIndex] + " of " + suits[suitIndex];
        }

        return deck;
    }
}