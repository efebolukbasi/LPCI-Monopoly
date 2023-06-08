import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import javax.swing.text.*;
public class Functions {

  // constants
    private static final int TILE_COUNT = 32;
    private static final int BOARD_SIZE = 1080; // Board size in pixels
    private static final int OUTER_MARGIN = BOARD_SIZE / 20; // Outer margin size in pixels
    private static final int INNER_MARGIN = BOARD_SIZE / 40; // Inner margin size in pixels
    private static final Color[] TILE_COLORS = {Color.BLUE, Color.YELLOW}; // Tile colors (blue and gold)

    public static int userTurn = 1;
    public static boolean extraPlayers;

    public static void main(String[] args) {


    }
    public static String[] generateRandomDeck(int numCards) {
        String[] deck = new String[numCards];
        String[] suits = {"Spades", "Hearts", "Diamonds", "Clubs"};
         String[] ranks = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King","Ace"};

        Random rand = new Random();

        for (int i = 0; i < numCards; i++) {
            int suitIndex = rand.nextInt(suits.length);
            int rankIndex = rand.nextInt(ranks.length);
            deck[i] = ranks[rankIndex] + " of " + suits[suitIndex];
        }

        return deck;
    }
    public static void updateTurnLabel(JLabel label, int turn) {
        if (turn == 1) {
            label.setText("Turn: User 1");
        }else if(turn ==2) {
            label.setText("Turn: User 2");
        } else if (extraPlayers && turn==3) {
            label.setText("Turn: User 3");
        } else if (extraPlayers && turn ==4) {
            label.setText("Turn: User 4");
        }
    }
/*
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

    public static int[] blz_intro() {// before the game starts (user enters their game parameters)

        Scanner in = new Scanner(System.in);
        int[] numPlayers;
        do {
            System.out.print("\n\nHow many players(2,4): ");// an array of how many players we have
            numPlayers = new int[in.nextInt()];

        } while (numPlayers.length ==3 || (numPlayers.length <= 1 || numPlayers.length >= 5)); // only even numbers

        if(numPlayers.length >3) extraPlayers = true;

        int[] blz_PlayerCoins = new int[numPlayers.length]; // an array that stores the amount of coins users have

        // giving each player 8 coins to start
        Arrays.fill(blz_PlayerCoins, 8);
        // System.out.print(Arrays.toString(blz_PlayerCoins));
        return numPlayers;
    }
*/



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


        ///


        // Set the frame size and visibility
        tilePanel.setBackground(Color.decode("#ad66d9"));// set background color (pastel violet)
        frame.setResizable(false);// Prevent User from changing the window size
        frame.setSize(BOARD_SIZE, BOARD_SIZE);// set board size
        frame.setVisible(true);
    }

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
    diceName.setForeground(Color.white);
    diceName.setBounds(40,-70,400,200);


    diceName.setOpaque(false);// make background Transparent
    dicePanel.add(diceName);

JLabel pDice1 = new JLabel();
    JLabel pDice2 = new JLabel();
    JLabel pcDice1 = new JLabel();
    JLabel pcDice2 = new JLabel();



    diceFrame.add(dicePanel);// add the panel into the frame
    diceFrame.setResizable(false);// Prevent User from changing the window size
    diceFrame.setVisible(true);

    }



}// end of class