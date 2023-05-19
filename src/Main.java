import java.util.Arrays;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
         final int G_COINS =8; // each player has 8 coins


while (true){
    blz_intro();


    // exit while loop
    blz_exit();

   }

}


//                                      --------------- functions----------------

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

    public static void blz_intro(){
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





    }
}