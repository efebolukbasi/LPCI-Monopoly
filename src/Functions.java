import javax.swing.*;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Functions {

    public static void main(String[] args) {


    }
    public static String[] generateRandomDeck(int numCards) {
        String[] deck = new String[numCards];
        String[] suits = {"Spades", "Hearts", "Diamonds", "Clubs"};
        String[] ranks = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King","Ace"};

        Random rand = new Random();

        for (int i = 0; i < numCards; i++) {
            int suitIndex = rand.nextInt(suits.length);
            int rankIndex = rand.nextInt(ranks.length);
            deck[i] = ranks[rankIndex] + " of " + suits[suitIndex];
        }

        return deck;
    }

    public static void blz_exit(){
        Scanner in = new Scanner(System.in);
        String exit;
        do {
            System.out.print("Play Again (y/n): ");
            exit = in.next().toLowerCase();
        }while (!exit.equals("n") && !exit.equals("y"));
        if(exit.equals("n")) {
            System.out.println("Game Over");
            System.exit(0);


        }
    }

    public static int blz_intro(){
        final int blz_Short = 16;
        final int blz_Med = 30;
        final int blz_Long = 40;
        Scanner in = new Scanner(System.in);
        int numPlayers;
        do {
            System.out.print("\n\nHow many players: ");
            numPlayers = in.nextInt();

        }while(numPlayers <=1 || numPlayers >= 5);
        int gameLength;

        int[] blz_PlayerCoins = new int[numPlayers]; // an array that stores the amount of coins users have

        // giving each player 8 coins to start
        Arrays.fill(blz_PlayerCoins, 8);
        // System.out.print(Arrays.toString(blz_PlayerCoins));


        do {
            System.out.print("Select Game Length:\n1.Short\n2.Medium\n3.Long\nEnter: ");// come back to this feature
            gameLength = in.nextInt();
        }while(gameLength <=0 || gameLength >=4);

        if(gameLength == 1){
            gameLength = blz_Short;

        } else if (gameLength==2) {
            gameLength = blz_Med;

        }else{
            gameLength = blz_Long;
        }


        return gameLength;

    }

    public static int rollDice() {
        Random rand = new Random();

        return rand.nextInt(1,7);
    }
}
