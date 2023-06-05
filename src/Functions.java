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
    public static void updateTurnLabel(JLabel label, int turn) {
        if (turn == 1) {
            label.setText("Turn: User 1");
        }else if(turn ==2) {
            label.setText("Turn: User 2");
        } else if (Main.extraPlayers && turn==3) {
            label.setText("Turn: User 3");
        } else if (Main.extraPlayers && turn ==4) {
            label.setText("Turn: User 4");
        }
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

    public static int[] blz_intro() {// before the game starts (user enters their game parameters)

        Scanner in = new Scanner(System.in);
        int[] numPlayers;
        do {
            System.out.print("\n\nHow many players(2,4): ");// an array of how many players we have
            numPlayers = new int[in.nextInt()];

        } while (numPlayers.length ==3 || (numPlayers.length <= 1 || numPlayers.length >= 5)); // only even numbers

        if(numPlayers.length >3) Main.extraPlayers = true;

        int[] blz_PlayerCoins = new int[numPlayers.length]; // an array that stores the amount of coins users have

        // giving each player 8 coins to start
        Arrays.fill(blz_PlayerCoins, 8);
        // System.out.print(Arrays.toString(blz_PlayerCoins));
        return numPlayers;
    }


    /*
    public static int blz_getGameLength(){
        Scanner in = new Scanner(System.in);
        final int blz_Short = 16;
        final int blz_Med = 30;
        final int blz_Long = 40;
        int gameLength;
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
     */

}