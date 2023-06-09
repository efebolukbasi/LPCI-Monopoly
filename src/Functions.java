import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
public class Functions {

  // constants
    public static final int NAME_CAP = 10; // Name cap length
    private static final int TILE_COUNT = 32;
    private static final int BOARD_SIZE = 1080; // Board size in pixels
    private static final int OUTER_MARGIN = BOARD_SIZE / 20; // Outer margin size in pixels
    private static final int INNER_MARGIN = BOARD_SIZE / 40; // Inner margin size in pixels
    private static final Color[] TILE_COLORS = {Color.BLUE, Color.YELLOW}; // Tile colors (blue and gold)

    private static int[] playerPositions;
    private static String[] playerNames;
    private static JLabel[] playerLabels;
    private static JButton[] rollButtons;
    private static boolean[] hasRolled;
    private static JLabel currentPlayerLabel;
    private static int tileSize;
    private static JLabel[] playerImageLabels;
    private static int numPlayers;
    public static void main(String[] args) {


    }

    public static void gameSettings(){

        Scanner in = new Scanner(System.in);


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
            } while (!validName || playerName.length() > Functions.NAME_CAP);

            playerNames[i] = playerName;
        }
    }



                                                         // intro menu

    public static void IntroMenu(){// intro menu screen
    ////
    //Add Start Menu//
    JFrame startFrame = new JFrame("Welcome To LPCI Monopoly");
    JPanel startPanel = new JPanel();
    startPanel.setBackground(Color.decode("#4bd183"));

    startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    startFrame.setSize(400, 400);
    startPanel.setSize(400,400);
    startPanel.setLayout(null);

    JButton startButton = new JButton("Start");

    startButton.setBounds(20,10,85, 30);

    //startButton1.setLocation(0,100);


    JButton exitButton = new JButton("Exit");//exit game button

    exitButton.setBounds(280,10,85, 30);

    JButton creditsButton = new JButton("Credits");// credits button

    creditsButton.setBounds(150,10,85, 30);

        JButton backButton = new JButton("Back");// back button from credits

        backButton.setBounds(20,10,85, 30);


    startButton.addActionListener(new ActionListener(){// button add action listener
        public void actionPerformed(ActionEvent e){
            //
            startPanel.removeAll();  // Remove all components from the panel
            startPanel.revalidate(); // Revalidate the panel to update the layout
            startPanel.repaint();    // Repaint the panel to reflect the changes
           gameBoard();


            startFrame.dispose();
            // System.out.print(mode);
        }
    });// ends here

    creditsButton.addActionListener(new ActionListener(){// credits button
        public void actionPerformed(ActionEvent e){
            //
            startPanel.removeAll();  // Remove all components from the panel
            startPanel.revalidate(); // Revalidate the panel to update the layout
            startPanel.repaint();    // Repaint the panel to reflect the changes
            JLabel credits = new JLabel("Credits");
            credits.setFont(new Font("Arial", Font.BOLD, 20)); // Set font size to 20
            credits.setBounds(150,20,80,20);
            credits.setOpaque(false);// make background Transparent
            startPanel.add(credits);
            ////----
            JLabel creditNames = new JLabel("Creators:Oliver.S & Efe.B");// game credits
            creditNames.setFont(new Font("Arial", Font.BOLD, 20)); // Set font size to 20
            creditNames.setBounds(15,-50,400,300);
            creditNames.setOpaque(false);// make background Transparent
            startPanel.add(creditNames);

            JLabel userThank = new JLabel("Thanks for Playing");// thanks for playing message
            userThank.setFont(new Font("Arial", Font.BOLD, 40)); // Set font size to 40
            userThank.setBounds(10,20,400,300);
            userThank.setOpaque(false);// make background Transparent

            startPanel.add(userThank);// add to starting panel




            startPanel.add(backButton);// back to menu button
            backButton.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    startFrame.dispose();// removes window
                    IntroMenu();//recall start menu function
                }
            });
        }
    });// ends here


    exitButton.addActionListener(new ActionListener(){// exit button action listener
        public void actionPerformed(ActionEvent e){
            //
            System.exit(914);// user pressed exit


        }
    });// ends here

    ImageIcon startImageIcon = new ImageIcon("src/Images/PantherLogo.png"); // Logo Picture
    JLabel startImageLabel = new JLabel(startImageIcon);// assign ImageIcon to a JLabel, to be able to paste to screen.

    startImageLabel.setBounds(0,30,403,331);



    startFrame.add(startPanel);// add the panel into the frame

    startPanel.add(startButton);
    startPanel.add(creditsButton);
    startPanel.add(exitButton);
    startPanel.add(startImageLabel);
    //frame settings
    startFrame.setResizable(false);// Prevent User from changing the window size
    startFrame.setVisible(true);


}

                                                // main game


    public static void gameBoard(){
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

        imageLabel.setSize(600, 645);
        imageLabel.setLocation(BOARD_SIZE / 5, BOARD_SIZE / 5);
        tilePanel.add(imageLabel);

        ImageIcon chestIcon = new ImageIcon(("src/Images/ChestCard.png"));
        JLabel chestLabel = new JLabel(chestIcon); // Making image a J label
        chestLabel.setSize(350, 450);
        chestLabel.setLocation(350, 57);

        tilePanel.add(chestLabel);


        //////////////

        // Create the tiles
        JButton[] tiles = new JButton[TILE_COUNT];
        tileSize = (BOARD_SIZE - 2 * OUTER_MARGIN - 8 * INNER_MARGIN) / 9;
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
////


        // Player Logo's
        ImageIcon[] playerIcons = {
                new ImageIcon("src/Images/p1.png"),
                new ImageIcon("src/Images/p2.png"),
                new ImageIcon("src/Images/p3.png"),
                new ImageIcon("src/Images/p4.png")
        };

        // Create JLabels for each player and display at tile number 1 with a border
        playerImageLabels = new JLabel[numPlayers];
        for (int j = 0; j < numPlayers; j++) {
            Image scaledImage = playerIcons[j].getImage().getScaledInstance(tileSize - 25, tileSize - 25, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);
            playerImageLabels[j] = new JLabel(scaledIcon);
            playerImageLabels[j].setSize(tileSize - 25, tileSize - 25);

            int x = OUTER_MARGIN + INNER_MARGIN + (tileSize - 50 - playerImageLabels[j].getWidth()) / 2; // Calculate x-coordinate
            int y = OUTER_MARGIN + INNER_MARGIN + (tileSize - 50 - playerImageLabels[j].getHeight()) / 2; // Calculate y-coordinate

            playerImageLabels[j].setLocation(x, y);
            tilePanel.add(playerImageLabels[j], 0);
        }

        // Player info
        String[] playerMoney = { "$1200", "$1200", "$1200", "$1200" };

        // POSITIONS
        playerPositions = new int[numPlayers];
        Arrays.fill(playerPositions, 0);
        playerLabels = new JLabel[numPlayers];
        rollButtons = new JButton[numPlayers];
        hasRolled = new boolean[numPlayers];

        //FONTS FOR TEXT
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

                        int diceRoll = rollDice();
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

        // Legend
        JLabel[] playerLegendLabels = new JLabel[numPlayers];
        for (int j = 0; j < numPlayers; j++) {
            // Scale and set the logo
            Image scaledImage = playerIcons[j].getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);

            // Create a new label with the player's name and their logo
            playerLegendLabels[j] = new JLabel(scaledIcon);
            playerLegendLabels[j].setText(" = " + playerNames[j]);
            playerLegendLabels[j].setFont(new Font("SansSerif", Font.BOLD, 15));

            // Set the location of the label
            int x = 450; // Match this with the x-coordinate of the CurrentTurn label - could set to variable if wanted to move them together
            int y = 775 + j * 30; // Starts below the CurrentTurn label, adjust as needed

            playerLegendLabels[j].setBounds(x, y, 120, 30); // Adjust the size if necessary

            // Add the label to the panel
            tilePanel.add(playerLegendLabels[j]);
        }





        ///


        // Set the frame size and visibility
        tilePanel.setBackground(Color.decode("#ad66d9"));// set background color (pastel violet)
        frame.setResizable(false);// Prevent User from changing the window size
        frame.setSize(BOARD_SIZE, BOARD_SIZE);// set board size
        frame.setVisible(true);
    }// end of board game function




public static void diceMiniGame(){

        JFrame diceFrame = new JFrame("Mr.Reeds Dice House");
        JPanel dicePanel = new JPanel();
        dicePanel.setBackground(Color.decode("#361521"));
    diceFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    diceFrame.setSize(400, 400);
    dicePanel.setSize(400,400);
    dicePanel.setLayout(null);


// mini game title
    JLabel diceName = new JLabel("Mr.Reid's Dice House");// game credits
    diceName.setFont(new Font("Arial", Font.BOLD, 30)); // Set font size to 20
    diceName.setForeground(Color.white);// set text color white
    diceName.setBounds(40,-70,400,200);// set text location


    diceName.setOpaque(false);// make background Transparent
    dicePanel.add(diceName);

    diceFrame.add(dicePanel);// add the panel into the frame
    diceFrame.setResizable(false);// Prevent User from changing the window size
    diceFrame.setVisible(true);

    }// end of dice game function

    public static int rollDice() {
        Random rand = new Random();

        return rand.nextInt(1,7);
    }


    private static void updatePlayerPosition(int playerIndex, int diceRoll) {
        int currentPlayerPosition = playerPositions[playerIndex];
        int newPlayerPosition = (currentPlayerPosition + diceRoll) % TILE_COUNT;
        playerPositions[playerIndex] = newPlayerPosition;

        // Calculate new row and column
        int newRow, newCol;
        if (newPlayerPosition < 9) {
            // Top row, moving right
            newRow = 0;
            newCol = newPlayerPosition;
        } else if (newPlayerPosition < 17) {
            // Right column, moving down
            newRow = newPlayerPosition - 8;
            newCol = 8;
        } else if (newPlayerPosition < 25) {
            // Bottom row, moving left
            newRow = 8;
            newCol = 24 - newPlayerPosition;
        } else {
            // Left column, moving up
            newRow = 32 - newPlayerPosition;
            newCol = 0;
        }

        // Calculate the new location of the player's image label
        int x = OUTER_MARGIN + INNER_MARGIN + (tileSize - 25 - playerImageLabels[playerIndex].getWidth()) / 2 + newCol * (tileSize + INNER_MARGIN);
        int y = OUTER_MARGIN + INNER_MARGIN + (tileSize - 25 - playerImageLabels[playerIndex].getHeight()) / 2 + newRow * (tileSize + INNER_MARGIN);

        playerImageLabels[playerIndex].setLocation(x, y);
        playerLabels[playerIndex].setText(playerNames[playerIndex] + ": " + "$1200");
    }



}// end of Functions class