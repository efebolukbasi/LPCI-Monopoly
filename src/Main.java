import java.awt.*;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;


public class Main {
    private static final int TILE_COUNT = 30;

    public static int userTurn = 1;
    private static JButton rollButton;
    public static JLabel turnLabel;

    public static boolean extraPlayers = false;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Random rand = new Random();


        int[] numplayers = (Functions.blz_intro()); // intro function
        // int gameLength = Functions.blz_getGameLength();// get game length


        final int WINNING_TILE = TILE_COUNT / 2; // set winning tile in the middle of the board


        // Board Tiles Values
        int[] boardTileValues = new int[TILE_COUNT];
        for (int i = 0; i < boardTileValues.length; i++) {
            int randomValue = rand.nextInt(-1, 2);// random point values for tiles
            boardTileValues[i] = randomValue;
        }

        // Number of Players Loop (Makes sure it is between 2-4)

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
            tiles[i].setBackground(Color.WHITE); // change color of Game tiles
            tiles[i].setEnabled(false);
            tilePanel.add(tiles[i]);
        }

        // Create the turn label
        turnLabel = new JLabel("Turn: User 1");


        if (userTurn == 1) {
            // Create the pick a card button
            rollButton = new JButton("Pick Card");
            rollButton.addActionListener(e -> {
                String message = "";


                message = "User 1 drew a " + (Arrays.toString(Functions.generateRandomDeck(1))); // Generate a random card for user1 when pressed button
                userTurn++;
                Functions.updateTurnLabel(turnLabel, userTurn);
                rollButton.setEnabled(true);


                JOptionPane.showMessageDialog(frame, message);// output button click message
            });

        } else if (userTurn == 2) {
            rollButton = new JButton("Pick Card");
            rollButton.addActionListener(e -> {
                String message = "";


                message = "User 2 drew a " + (Arrays.toString(Functions.generateRandomDeck(1))); // Generate a random card for user1 when pressed button

                Functions.updateTurnLabel(turnLabel, userTurn);
                rollButton.setEnabled(true);
                userTurn++;

                JOptionPane.showMessageDialog(frame, message);// output button click message
                if (!extraPlayers) userTurn = 1; // if there are only 2 players then reset the player turn
            });


        } else if (extraPlayers && userTurn == 3) {
            rollButton = new JButton("Pick Card");
            rollButton.addActionListener(e -> {
                String message = "";


                message = "User 3 drew a " + (Arrays.toString(Functions.generateRandomDeck(1))); // Generate a random card for user1 when pressed button

                Functions.updateTurnLabel(turnLabel, userTurn);
                rollButton.setEnabled(true);
                userTurn++;

                JOptionPane.showMessageDialog(frame, message);// output button click message
            });

        } else if (extraPlayers && userTurn == 4) {
            rollButton = new JButton("Pick Card");
            rollButton.addActionListener(e -> {
                String message = "";


                message = "User 4 drew a " + (Arrays.toString(Functions.generateRandomDeck(1))); // Generate a random card for user1 when pressed button

                Functions.updateTurnLabel(turnLabel, userTurn);
                rollButton.setEnabled(true);


                JOptionPane.showMessageDialog(frame, message);// output button click message
                userTurn = 1;
            });




/*
 int i=0;
        while(i!=5){
            if(i==0){
                // Create the pick a card button
                rollButton = new JButton("Pick Card");
                rollButton.addActionListener(e -> {
                    String message = "";
                    message = "User 1 drew a " + (Arrays.toString(Functions.generateRandomDeck(1))); // Generate a random card for user1 when pressed button
                    Functions.updateTurnLabel(turnLabel);
                    rollButton.setEnabled(true);
                    JOptionPane.showMessageDialog(frame, message);// output button click message
                });
i++;
            } else if (i==1) {
                rollButton = new JButton("Pick Card");
                rollButton.addActionListener(e -> {
                    String message = "";
                    message = "User 2 drew a " + (Arrays.toString(Functions.generateRandomDeck(1))); // Generate a random card for user1 when pressed button
                    Functions.updateTurnLabel(turnLabel);
                    rollButton.setEnabled(true);
                    JOptionPane.showMessageDialog(frame, message);// output button click message
                });
                i++;
            } else if (numplayers.length>3 && i==2) {
                rollButton = new JButton("Pick Card");
                rollButton.addActionListener(e -> {
                    String message = "";
                    message = "User 3 drew a " + (Arrays.toString(Functions.generateRandomDeck(1))); // Generate a random card for user1 when pressed button
                    Functions.updateTurnLabel(turnLabel);
                    rollButton.setEnabled(true);
                    JOptionPane.showMessageDialog(frame, message);// output button click message
                });
                i++;
            } else if (numplayers.length>3 &&i==3) {
                rollButton = new JButton("Pick Card");
                rollButton.addActionListener(e -> {
                    String message = "";
                    message = "User 4 drew a " + (Arrays.toString(Functions.generateRandomDeck(1))); // Generate a random card for user1 when pressed button
                    Functions.updateTurnLabel(turnLabel);
                    rollButton.setEnabled(true);
                    JOptionPane.showMessageDialog(frame, message);// output button click message
                });
                break;
            }
        }
        i=0;
 */


// keybinds for user turns
/*
    if(userTurn ==1){
    // Bind "W" key to pick up card for User 1
    rollButton.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0), "PickUser1");
    rollButton.getActionMap().put("PickUser1", new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
                rollButton.setEnabled(true);
                rollButton.doClick();
            userTurn++;
        }
    });
}
    if(userTurn ==2){
        rollButton.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_Y, 0), "PickUser2");
        rollButton.getActionMap().put("PickUser2", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    rollButton.setEnabled(true);
                    rollButton.doClick();
                userTurn++;
            }
        });
    // add for more players
    }
    if(extraPlayers && userTurn ==3){ // player 3
        rollButton.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_Y, 0), "PickUser3");
        rollButton.getActionMap().put("PickUser3", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rollButton.setEnabled(true);
                rollButton.doClick();
                userTurn++;
            }
        });
    }
    if(extraPlayers && userTurn ==4){// player 4
        rollButton.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_Y, 0), "PickUser4");
        rollButton.getActionMap().put("PickUser4", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rollButton.setEnabled(true);
                rollButton.doClick();
                userTurn=1;
            }
        });
    }
 */


            // Bind "Y" key to roll dice for User 2


            // Add the components to the frame
            frame.add(tilePanel);
            frame.add(rollButton);
            frame.add(turnLabel);

            // Set the winning tile in the middle
            JButton winningTile = tiles[WINNING_TILE];
            winningTile.setText("WINNER");


            // Create a custom JPanel to display the player icon
            JPanel player1Panel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    drawPlayer1Icon(g);
                }
            };

            // Add the player panel to the frame
            frame.getContentPane().add(player1Panel);

            // Display the frame
            frame.setVisible(true);


            // Set the frame size and visibility
            frame.setSize(1500, 450);
            frame.setVisible(true);

            // Functions.blz_exit();// play again or leave
        }
    }
        private static void drawPlayer1Icon(Graphics g){
            // Set the color and size for the player icon
            g.setColor(Color.BLUE);
            int iconSize = 100;

            // Calculate the position for the player icon
            int p1_x = (300 - iconSize) / 2;
            int p1_y = (300 - iconSize) / 2;

            // Draw the player icon as a circle
            g.fillOval(p1_x, p1_y, iconSize, iconSize);
        }



}