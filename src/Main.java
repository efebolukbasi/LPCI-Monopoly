import java.awt.*;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.Scanner;
import java.util.Random;
public class Main {
    private static final int TILE_COUNT = 32;
    private static final int BOARD_SIZE = 1080; // Board size in pixels
    private static final int OUTER_MARGIN = BOARD_SIZE / 20; // Outer margin size in pixels
    private static final int INNER_MARGIN = BOARD_SIZE / 40; // Inner margin size in pixels
    private static final Color[] TILE_COLORS = {Color.BLUE, Color.YELLOW}; // Tile colors (blue and gold)


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Random rand = new Random();

        int numPlayers;

        do {
            System.out.print("How many players: ");
            numPlayers = in.nextInt();

        } while (numPlayers <= 1 || numPlayers >= 5);

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

        imageLabel.setSize(600,645);
        imageLabel.setLocation(BOARD_SIZE/5,BOARD_SIZE/5);
        tilePanel.add(imageLabel);

        ImageIcon chestIcon = new ImageIcon(("src/Images/ChestCard.png"));
        JLabel chestLabel = new JLabel(chestIcon); // Making image a J label
        chestLabel.setSize(350,450);
        chestLabel.setLocation(350,57);

        tilePanel.add(chestLabel);

        // COORDINATE FINDER
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        JLabel label = new JLabel("Mouse coordinates: ");
        frame.add(label);
        frame.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseMoved(MouseEvent e) {
                // Get the coordinates of the mouse cursor
                int x = e.getX();
                int y = e.getY();

                // Display the coordinates in the console
                System.out.println("Mouse coordinates: (" + x + ", " + y + ")");
            }
            @Override
            public void mouseDragged(MouseEvent e) {
                // Not used in this example
            }
        });

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

        // Set the frame size and visibility
        frame.setSize(BOARD_SIZE, BOARD_SIZE);
        frame.setVisible(true);
        frame.setResizable(false);
    }


}
