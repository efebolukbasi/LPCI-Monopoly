import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;
import javax.swing.*;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;



public class Main {
    private static final int TILE_COUNT = 30;
    private static final int WINNING_TILE = TILE_COUNT / 2;
    public static boolean user1Turn = true;
    private static JButton rollButton;
    public static JLabel turnLabel;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Random rand = new Random();

        // Board Tiles Values
        int[] boardTileValues = new int[30];
        for (int i = 0; i < boardTileValues.length; i++) {
            int randomValue = rand.nextInt(-1, 2) ;
            boardTileValues[i] = randomValue;
        }

        // Number of Players Loop (Makes sure it is between 2-4)

        Functions.blz_intro(); // intro function

        // Swing GUI
        // Create the main frame
        JFrame frame = new JFrame("Blitz Krieg");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        // Create the tile panel
        JButton[] tiles = new JButton[TILE_COUNT];
        JPanel tilePanel = new JPanel(new FlowLayout());
        for (int i = 0; i < TILE_COUNT; i++) {
            tiles[i] = new JButton(String.valueOf(boardTileValues[i]));
            tiles[i].setBackground(Color.WHITE);
            tiles[i].setEnabled(false);
            tilePanel.add(tiles[i]);
        }

        // Create the pick a card button
        rollButton = new JButton("Pick Card");
        rollButton.addActionListener(e -> {
            String message;

            if (user1Turn) {
                message = "User 1 drew a " +  (Arrays.toString(Functions.generateRandomDeck(1))); // Generate a random card for user1 when pressed button
                user1Turn = false;
                Functions.updateTurnLabel(turnLabel);
                rollButton.setEnabled(true);
            } else {
                message = "User 2 drew a " + (Arrays.toString(Functions.generateRandomDeck(1))); // Generate a random card for user2 when pressed button
                user1Turn = true;
                Functions.updateTurnLabel(turnLabel);
                rollButton.setEnabled(true);
            }

            JOptionPane.showMessageDialog(frame, message);
        });

        // Create the turn label
        turnLabel = new JLabel("Turn: User 1");

        // Bind "W" key to roll dice for User 1
        rollButton.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0), "PickUser1");
        rollButton.getActionMap().put("PickUser1", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (user1Turn) {
                    rollButton.setEnabled(true);
                    rollButton.doClick();
                }
            }
        });

        // Bind "Y" key to roll dice for User 2
        rollButton.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_Y, 0), "PickUser2");
        rollButton.getActionMap().put("PickUser2", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!user1Turn) {
                    rollButton.setEnabled(true);
                    rollButton.doClick();
                }
            }
        });

        // Add the components to the frame
        frame.add(tilePanel);
        frame.add(rollButton);
        frame.add(turnLabel);

        // Set the winning tile in the middle
        JButton winningTile = tiles[WINNING_TILE];
        winningTile.setText("WINNER");
        winningTile.setBackground(Color.YELLOW);

        // Set the frame size and visibility
        frame.setSize(1500, 250);
        frame.setVisible(true);


        Functions.blz_exit();
    }

}