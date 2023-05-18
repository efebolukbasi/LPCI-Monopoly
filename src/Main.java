import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        boolean gameMode = true; // Main Game While Loop Condition

        System.out.print("How many players: ");
        int numPlayers = in.nextInt();

        System.out.print("Select Game Length:\n1.Short\n2.Medium\n3.Long\nEnter: ");
        int gameLength = in.nextInt();
        while(gameLength <=0 || gameLength >=4){
            System.out.print("Select Game Length:\n1.Short\n2.Medium\n3.Long\nEnter: ");
            gameLength = in.nextInt();
        }


while (gameMode){



}

    }
}