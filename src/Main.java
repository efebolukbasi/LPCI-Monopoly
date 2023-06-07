import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main {
    private static final int TILE_COUNT = 32;
    private static final int BOARD_SIZE = 1080; // Board size in pixels
    private static final int OUTER_MARGIN = BOARD_SIZE / 20; // Outer margin size in pixels
    private static final int INNER_MARGIN = BOARD_SIZE / 40; // Inner margin size in pixels
    private static final Color[] TILE_COLORS = {Color.BLUE, Color.YELLOW}; // Tile colors (blue and gold)

    public static int userTurn =1;
    public static boolean extraPlayers;

    public static boolean mode = false;

    public static void main(String[] args) {
        // Create the main frame
        JFrame frame = new JFrame("Monopoly Board");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null); // Use absolute layout

        // Create the tile panel
        JPanel tilePanel = new JPanel();
        tilePanel.setLayout(null); // Use absolute layout
        tilePanel.setBounds(0, 0, BOARD_SIZE, BOARD_SIZE);
        frame.add(tilePanel);

                                                        // Images //
        // logo in screen
        // Create a JLabel to display the image
        ImageIcon imageIcon = new ImageIcon("src/Images/PantherLogo.png"); // Logo Picture
        JLabel imageLabel = new JLabel(imageIcon);// assign ImageIcon to a JLabel, to be able to paste to screen.

        imageLabel.setSize(600,645);
        imageLabel.setLocation(BOARD_SIZE/5,BOARD_SIZE/5);
        tilePanel.add(imageLabel);

ImageIcon chestIcon = new ImageIcon(("src/Images/ChestCard.png"));
JLabel chestLabel = new JLabel(chestIcon); // Making image a J label
        chestLabel.setSize(350,450);
        chestLabel.setLocation(350,57);

        tilePanel.add(chestLabel);


        //////////////

        // Create the tiles
        JButton[] tiles = new JButton[TILE_COUNT];
        int tileSize = (BOARD_SIZE - 2 * OUTER_MARGIN - 8 * INNER_MARGIN) / 9;
        int row = 0;
        int col = 0;
        int colorIndex = 0; // Index for tile colors
        int i = 0;
        while (i < TILE_COUNT - 1) {
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

        // Add the additional tile on the left side
        tiles[TILE_COUNT - 1] = new JButton(String.valueOf(TILE_COUNT));
        tiles[TILE_COUNT - 1].setBounds(OUTER_MARGIN, OUTER_MARGIN + tileSize + INNER_MARGIN, tileSize, tileSize);
        tiles[TILE_COUNT - 1].setHorizontalAlignment(SwingConstants.RIGHT); // Set number alignment to right
        tiles[TILE_COUNT - 1].setVerticalAlignment(SwingConstants.TOP); // Set number alignment to top
        tiles[TILE_COUNT - 1].setEnabled(false); // Disable the additional tile button
        tiles[TILE_COUNT - 1].setBackground(TILE_COLORS[colorIndex]); // Set tile color
        tilePanel.add(tiles[TILE_COUNT - 1]);


                                                //Game Top Bar Menu//
            JFrame MenuFrame = new JFrame("Game Menu");
       // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      //  frame.setSize(400, 300);

            JMenuBar menuBar = new JMenuBar();
            JMenu menu = new JMenu("GameInfo");

            JMenuItem menuItem1 = new JMenuItem("Credits");
            JMenuItem menuItem2 = new JMenuItem("Don't Click");
        menu.add(menuItem1);
        menu.add(menuItem2);

        menuBar.add(menu);
        frame.setJMenuBar(menuBar);

        Functions.IntroMenu();// call menu function



                                                    ///




        // Set the frame size and visibility
        tilePanel.setBackground(Color.decode("#ad66d9"));// set background color (pastel violet)
        frame.setResizable(false);// Prevent User from changing the window size
        frame.setSize(BOARD_SIZE, BOARD_SIZE);// set board size
        frame.setVisible(true);
    }


}

