import javax.swing.*;
import java.util.Random;

public class Functions {
    public static void main(String[] args) {

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
    public static void updateTurnLabel(JLabel label) {
        if (Main.user1Turn) {
            label.setText("Turn: User 1");
        } else {
            label.setText("Turn: User 2");
        }
    }
}
