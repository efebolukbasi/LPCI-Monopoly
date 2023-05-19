import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        boolean gameMode = true; // Main Game While Loop Condition




while (gameMode){
    blz_intro();
    System.out.print("Game Cycle Over"); // for testing


}

    }


    public static void blz_intro(){
        Scanner in = new Scanner(System.in);
        int numPlayers;
        do {
            System.out.print("How many players: ");
            numPlayers = in.nextInt();

        }while(numPlayers <=1 || numPlayers >= 5);
        int gameLength;

        do {
            System.out.print("Select Game Length:\n1.Short\n2.Medium\n3.Long\nEnter: ");
            gameLength = in.nextInt();
        }while(gameLength <=0 || gameLength >=4);


    }

}