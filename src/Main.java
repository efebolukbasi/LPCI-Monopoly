import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import java.util.Random;
import java.util.Arrays;

public class Main {
    private static final int TILE_COUNT = 32;
    private static final int BOARD_SIZE = 1080; // Board size in pixels
    private static final int OUTER_MARGIN = BOARD_SIZE / 20; // Outer margin size in pixels
    private static final int INNER_MARGIN = BOARD_SIZE / 40; // Inner margin size in pixels
    private static final Color[] TILE_COLORS = { Color.BLUE, Color.YELLOW }; // Tile colors (blue and gold)
    private static final int NAME_CAP = 10; // Name cap length

    private static int[] playerPositions;
    private static String[] playerNames;
    private static JLabel[] playerLabels;
    private static JButton[] rollButtons;
    private static boolean[] hasRolled;
    private static JLabel currentPlayerLabel;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int numPlayers;
        do {
            System.out.print("How many players: ");
            numPlayers = in.nextInt();
        } while (numPlayers < 2 || numPlayers > 4);

        in.nextLine(); // Consume the newline character

        playerNames = new String[numPlayers];

        for (int i = 0; i < numPlayers; i++) {
            String playerName;
            boolean validName;

            do {
                System.out.print("Enter name for Player " + (i + 1) + ": ");
                playerName = in.nextLine();

                validName = true;
                for (int j = 0; j < i; j++) {
                    if (playerName.equalsIgnoreCase(playerNames[j])) {
                        validName = false;
                        System.out.println("Name already taken. Please enter a different name.");
                        break;
                    }
                }
            } while (!validName || playerName.length() > NAME_CAP);

            playerNames[i] = playerName;
        }

        // Create the main frame
        JFrame frame = new JFrame("Monopoly Board");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null); // Use absolute layout

        // Create the tile panel
        JPanel tilePanel = new JPanel();
        tilePanel.setLayout(null); // Use absolute layout
        tilePanel.setBackground(Color.decode("#AD66D9"));
        tilePanel.setBounds(0, 0, BOARD_SIZE, BOARD_SIZE);
        frame.add(tilePanel);

        // Images
        // logo in screen
        // Create a JLabel to display the image
        ImageIcon imageIcon = new ImageIcon("src/Images/PantherLogo.png"); // Logo Picture
        JLabel imageLabel = new JLabel(imageIcon);// assign ImageIcon to a JLabel, to be able to paste to screen.
        imageLabel.setSize(600, 645);
        imageLabel.setLocation(BOARD_SIZE / 5, BOARD_SIZE / 5);
        tilePanel.add(imageLabel);

        ImageIcon chestIcon = new ImageIcon(("src/Images/ChestCard.png"));
        JLabel chestLabel = new JLabel(chestIcon); // Making image a J label
        chestLabel.setSize(350, 450);
        chestLabel.setLocation(350, 57);
        tilePanel.add(chestLabel);

        // Create the tiles
        JButton[] tiles = new JButton[TILE_COUNT];
        int tileSize = (BOARD_SIZE - 2 * OUTER_MARGIN - 8 * INNER_MARGIN) / 9;
        int row = 0;
        int col = 0;
        int colorIndex = 0; // Index for tile colors
        int i = 0;
        while (i < TILE_COUNT) {
            if (row == 0 || row == 8 || col == 0 || col == 8) {
                tiles[i] = new JButton(String.valueOf(i + 1));
                tiles[i].setBounds(OUTER_MARGIN + col * (tileSize + INNER_MARGIN),
                        OUTER_MARGIN + row * (tileSize + INNER_MARGIN), tileSize, tileSize);
                tiles[i].setHorizontalAlignment(SwingConstants.RIGHT); // Set number alignment to right
                tiles[i].setVerticalAlignment(SwingConstants.TOP); // Set number alignment to top
                tiles[i].setEnabled(false); // Disable the tile button
                tiles[i].setBackground(TILE_COLORS[colorIndex]); // Set tile color
                tilePanel.add(tiles[i]);
                i++;
            }
            if (col < 8 && row == 0) {
                col++;
            } else if (row < 8 && col == 8) {
                row++;
            } else if (col > 0 && row == 8) {
                col--;
            } else if (col == 0 && row > 1) {
                row--;
            }
            colorIndex = (colorIndex + 1) % TILE_COLORS.length; // Update tile color index
        }

        // Player info
        playerPositions = new int[numPlayers];
        Arrays.fill(playerPositions, 0);
        playerLabels = new JLabel[numPlayers];
        rollButtons = new JButton[numPlayers];
        hasRolled = new boolean[numPlayers];
        String[] playerMoney = { "$1200", "$1200", "$1200", "$1200" };
        Font playerNameFont = new Font("SansSerif", Font.BOLD, 16);
        Font tileFont = new Font("SansSerif", Font.BOLD, 13);

        for (int j = 0; j < numPlayers; j++) {
            // Player Information Text
            playerLabels[j] = new JLabel(playerNames[j] + ": " + playerMoney[j]);
            playerLabels[j].setBounds(220 + 480 * (j % 2), 715 + 110 * (j / 2), 150, 30);
            playerLabels[j].setForeground(Color.BLACK);
            playerLabels[j].setFont(playerNameFont);
            tilePanel.add(playerLabels[j]);

            // Roll Button
            rollButtons[j] = new JButton("Roll");
            rollButtons[j].setBounds(220 + 480 * (j % 2), 755 + 110 * (j / 2), 80, 30);
            rollButtons[j].setFont(tileFont);
            final int currentPlayerIndex = j;
            int finalNumPlayers = numPlayers;
            rollButtons[j].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!hasRolled[currentPlayerIndex]) {
                        hasRolled[currentPlayerIndex] = true;
                        rollButtons[currentPlayerIndex].setEnabled(false);

                        int diceRoll = Functions.rollDice();
                        updatePlayerPosition(currentPlayerIndex, diceRoll);
                        JOptionPane.showMessageDialog(frame,
                                playerNames[currentPlayerIndex] + " rolled a " + diceRoll + ".");

                        currentPlayerLabel.setText("Current Turn: " + playerNames[(currentPlayerIndex + 1)
                                % finalNumPlayers]);

                        // Check if all players have rolled
                        boolean allPlayersRolled = true;
                        for (boolean rolled : hasRolled) {
                            if (!rolled) {
                                allPlayersRolled = false;
                                break;
                            }
                        }

                        // Enable roll buttons for all players if all have rolled
                        if (allPlayersRolled) {
                            for (JButton button : rollButtons) {
                                button.setEnabled(true);
                            }
                            Arrays.fill(hasRolled, false);
                        }
                    }
                }
            });
            tilePanel.add(rollButtons[j]);
        }

        // Current Player Label
        currentPlayerLabel = new JLabel("Current Turn: " + playerNames[0]);
        currentPlayerLabel.setBounds(450, 715, 200, 30);
        currentPlayerLabel.setForeground(Color.BLACK);
        currentPlayerLabel.setFont(playerNameFont);
        tilePanel.add(currentPlayerLabel);

        // Set frame size and make it visible
        frame.setSize(BOARD_SIZE, BOARD_SIZE);
        frame.setVisible(true);
    }

    private static void updatePlayerPosition(int playerIndex, int diceRoll) {
        playerPositions[playerIndex] = (playerPositions[playerIndex] + diceRoll) % TILE_COUNT;
        playerLabels[playerIndex].setText(playerNames[playerIndex] + ": " + "$1200");
    }
}