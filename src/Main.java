public class Main {
    public static void main(String[] args) {

        Functions.IntroMenu();
        while (true) {
            if ( Functions.gameOver ) {
                Functions.showGameOverPopup();
                break;
            }
        }
    }
}
