import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);



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

        do {
            System.out.print("Select Game Length:\n1.Short\n2.Medium\n3.Long\nEnter: ");
            gameLength = in.nextInt();
        }while(gameLength <=0 || gameLength >=4);


    }

}