public class Main {
    public static void main(String[] args) {

        if (!Functions.gameOver){
            Functions.IntroMenu();
        }

        do{
            if (Functions.gameOver){
                System.out.println("Game Over"); // Doesnt Print
            }
        }while (!Functions.gameOver);

        //Functions.diceMiniGame();
    }}
