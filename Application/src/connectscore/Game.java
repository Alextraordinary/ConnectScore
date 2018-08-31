/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectscore;

import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Random;
import javafx.scene.paint.Color;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author Alex Guo
 * Date: August 21, 2018
 * Description: This code controls the game JFrame form and adds functionality to it.
 */
public class Game extends javax.swing.JFrame {

    /**
     * Creates new form PlayerVsComputer
     */
    JButton[] topButtons = new JButton[7];//Creates a JButton array that stores the top buttons (the ones the user clicks to drop their token).
    JButton[][] boxes = new JButton[6][7];//Creates a JButton array that stores the grid buttons (the spots where the tokens go).
    int[][] tokens = new int[6][7];/*Creates an integer array that corresponds with the grid buttons array that will be used to get the state of the grid
    buttons (0 = unfilled, 1 = red token, 2 = yellow token).*/

    ImageIcon empty = new ImageIcon("empty.jpg");
    ImageIcon redToken = new ImageIcon("redtoken.jpg");
    ImageIcon yellowToken = new ImageIcon("yellowtoken.jpg");
    ImageIcon redToken_Highlight = new ImageIcon("redtoken_highlight.jpg");
    ImageIcon yellowToken_Highlight = new ImageIcon("yellowtoken_highlight.jpg");
    /*All of the image icons that will be used are declared, including the red and yellow tokens and their highlighted counterparts (used to show when a player
    has won, and the empty image icon.*/

    boolean bolIsPlayer1 = true;/*This boolean will be used to check whose turn it is. If it's true, it's player 1's turn (by default, player 1 goes first
    in the first game).*/
    String strPlayer1Color = "red";//Sets player 1's colour as "red".

    int intPlayer1Wins = 0;
    int intPlayer2Wins = 0;
    int ties = 0;
    //These counters store the number of times player 1 has won, player 2 has won, and the number of ties respectively.
    boolean bolGameOver = false;//This boolean detects whether or not a game has been won or tied.
    boolean bolRobotMode = false;// true - Player1 id the user and  Player2 is Robot; flase - both players are persons
    int intDifficulty = 0;//Indicates difficulty level of AI (0 for nice, 1 for average, 2 for tough).
    Robot robotPlayer;//Creates a new "Robot" object (see Robot.java).
    
    public Game() {
        initComponents();
        
        //Assigns the topButtons array their respective buttons on the form.
        topButtons[0] = btnColumn1;
        topButtons[1] = btnColumn2;
        topButtons[2] = btnColumn3;
        topButtons[3] = btnColumn4;
        topButtons[4] = btnColumn5;
        topButtons[5] = btnColumn6;
        topButtons[6] = btnColumn7;
        //Each index in the boxes[][] 2D array is assigned corresponding buttons on the form column by column.
        //First column.
        boxes[0][0] = btn1_1;
        boxes[1][0] = btn2_1;
        boxes[2][0] = btn3_1;
        boxes[3][0] = btn4_1;
        boxes[4][0] = btn5_1;
        boxes[5][0] = btn6_1;
        //Second column.
        boxes[0][1] = btn1_2;
        boxes[1][1] = btn2_2;
        boxes[2][1] = btn3_2;
        boxes[3][1] = btn4_2;
        boxes[4][1] = btn5_2;
        boxes[5][1] = btn6_2;
        //Third column.
        boxes[0][2] = btn1_3;
        boxes[1][2] = btn2_3;
        boxes[2][2] = btn3_3;
        boxes[3][2] = btn4_3;
        boxes[4][2] = btn5_3;
        boxes[5][2] = btn6_3;
        //Fourth column.
        boxes[0][3] = btn1_4;
        boxes[1][3] = btn2_4;
        boxes[2][3] = btn3_4;
        boxes[3][3] = btn4_4;
        boxes[4][3] = btn5_4;
        boxes[5][3] = btn6_4;
        //Fifth column.
        boxes[0][4] = btn1_5;
        boxes[1][4] = btn2_5;
        boxes[2][4] = btn3_5;
        boxes[3][4] = btn4_5;
        boxes[4][4] = btn5_5;
        boxes[5][4] = btn6_5;
        //Sixth column.
        boxes[0][5] = btn1_6;
        boxes[1][5] = btn2_6;
        boxes[2][5] = btn3_6;
        boxes[3][5] = btn4_6;
        boxes[4][5] = btn5_6;
        boxes[5][5] = btn6_6;
        //Seventh column.
        boxes[0][6] = btn1_7;
        boxes[1][6] = btn2_7;
        boxes[2][6] = btn3_7;
        boxes[3][6] = btn4_7;
        boxes[4][6] = btn5_7;
        boxes[5][6] = btn6_7;
        
        lblPlayer1.setForeground(java.awt.Color.red);/*Sets the text colour of the player 1 label (the one that says player 1's name with a red token next to 
        it) as red. This will be used to highlight whose turn it is.*/
    }

    public void setTheme(String strTheme) {//This method sets the theme of the game (what the pieces look like).
        if (strTheme.equals("Sports")) {//If the string parameter is "Sports".
            redToken = new ImageIcon("basketball.jpg");
            yellowToken = new ImageIcon("soccer.jpg");
            redToken_Highlight = new ImageIcon("basketball_highlight.jpg");
            yellowToken_Highlight = new ImageIcon("soccer_highlight.jpg");
            //The "redToken" and "yellowToken" are changed to a basketball and soccer ball respectively.
        } else if (strTheme.equals("Money")) {//If the string parameter is "Money".
            redToken = new ImageIcon("coin1.jpg");
            yellowToken = new ImageIcon("coin2.jpg");
            redToken_Highlight = new ImageIcon("coin1_highlight.jpg");
            yellowToken_Highlight = new ImageIcon("coin2_highlight.jpg");
            //The "redToken" and "yellowToken" are changed to an American penny and dime respectively.
        }

        lblPlayer1.setIcon(redToken);//Sets the icon for the player 1 label as a red token.
        lblPlayer2.setIcon(yellowToken);//Sets the icon for the player 2 label as a yellow token.
    }

    public void setPlayer1(String strName, String strColor) {//This method sets player 1's name and token colour.
        strPlayer1Color = strColor;
        lblPlayer1.setText(strName);
        //The player's name is set on the player 1 label. By default, player 1 is always red.

        if (strColor.equalsIgnoreCase("red")) {//If player 1's colour is red.
            lblPlayer1.setIcon(redToken);//Display a red token.
        } else {
            lblPlayer1.setIcon(yellowToken);//Otherwise, display a yellow token.
        }
    }

    public void setPlayer2(String strName, String strColor) {//This method sets player 2's name and token colour.
        lblPlayer2.setText(strName);
        //Player 2's name is displayed on the label.
        if (strColor.equalsIgnoreCase("red")) {//If player 2's colour is red.
            lblPlayer2.setIcon(redToken);//Display a red token.

        } else {
            lblPlayer2.setIcon(yellowToken);//Display a yellow token.
        }
    }

    
    public void setRobotMode(boolean bolRobotMode, int intLevel ){//This method sets the game in robot or AI mode.
       this.bolRobotMode = bolRobotMode;//Gets the boolean for robo mode (true or false).
       if (bolRobotMode==true){//If robot mode is true.
           robotPlayer = new Robot("Robot " + intLevel,  intLevel);//Player 2 is set as the robot (see this method under Robot.java).
       }
        
    }
    public void close() {//This method is used to close this GUI once a certain button is pressed.
        WindowEvent winClosingEvent = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlBackground = new javax.swing.JPanel();
        btnMainMenu = new javax.swing.JButton();
        btnPlayAgain = new javax.swing.JButton();
        btnResetWins = new javax.swing.JButton();
        btnColumn1 = new javax.swing.JButton();
        btnColumn2 = new javax.swing.JButton();
        btnColumn3 = new javax.swing.JButton();
        btnColumn4 = new javax.swing.JButton();
        btnColumn5 = new javax.swing.JButton();
        btnColumn6 = new javax.swing.JButton();
        btnColumn7 = new javax.swing.JButton();
        btn1_2 = new javax.swing.JButton();
        btn1_1 = new javax.swing.JButton();
        btn2_2 = new javax.swing.JButton();
        btn3_1 = new javax.swing.JButton();
        btn1_3 = new javax.swing.JButton();
        btn2_1 = new javax.swing.JButton();
        btn1_4 = new javax.swing.JButton();
        btn1_5 = new javax.swing.JButton();
        btn1_6 = new javax.swing.JButton();
        btn1_7 = new javax.swing.JButton();
        btn2_4 = new javax.swing.JButton();
        btn4_1 = new javax.swing.JButton();
        btn5_1 = new javax.swing.JButton();
        btn6_1 = new javax.swing.JButton();
        btn2_3 = new javax.swing.JButton();
        btn2_5 = new javax.swing.JButton();
        btn2_6 = new javax.swing.JButton();
        btn3_2 = new javax.swing.JButton();
        btn3_3 = new javax.swing.JButton();
        btn2_7 = new javax.swing.JButton();
        btn4_7 = new javax.swing.JButton();
        btn3_4 = new javax.swing.JButton();
        btn3_6 = new javax.swing.JButton();
        btn3_7 = new javax.swing.JButton();
        btn4_2 = new javax.swing.JButton();
        btn5_2 = new javax.swing.JButton();
        btn6_4 = new javax.swing.JButton();
        btn4_3 = new javax.swing.JButton();
        btn5_3 = new javax.swing.JButton();
        btn6_3 = new javax.swing.JButton();
        btn4_4 = new javax.swing.JButton();
        btn5_4 = new javax.swing.JButton();
        btn3_5 = new javax.swing.JButton();
        btn4_5 = new javax.swing.JButton();
        btn4_6 = new javax.swing.JButton();
        btn5_7 = new javax.swing.JButton();
        btn5_6 = new javax.swing.JButton();
        btn6_7 = new javax.swing.JButton();
        btn6_6 = new javax.swing.JButton();
        btn5_5 = new javax.swing.JButton();
        btn6_5 = new javax.swing.JButton();
        btn6_2 = new javax.swing.JButton();
        lblPlayer2 = new javax.swing.JLabel();
        lblPlayer1 = new javax.swing.JLabel();
        lblWinner = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtareaWins = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                GameWindowClosed(evt);
            }
        });

        pnlBackground.setBackground(new java.awt.Color(204, 204, 255));

        btnMainMenu.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btnMainMenu.setText("Main Menu");
        btnMainMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMainMenuActionPerformed(evt);
            }
        });

        btnPlayAgain.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btnPlayAgain.setText("Play Again");
        btnPlayAgain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlayAgainActionPerformed(evt);
            }
        });

        btnResetWins.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btnResetWins.setText("Reset Wins");
        btnResetWins.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetWinsActionPerformed(evt);
            }
        });

        btnColumn1.setText("↓");
        btnColumn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnColumn1ActionPerformed(evt);
            }
        });

        btnColumn2.setText("↓");
        btnColumn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnColumn2ActionPerformed(evt);
            }
        });

        btnColumn3.setText("↓");
        btnColumn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnColumn3ActionPerformed(evt);
            }
        });

        btnColumn4.setText("↓");
        btnColumn4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnColumn4ActionPerformed(evt);
            }
        });

        btnColumn5.setText("↓");
        btnColumn5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnColumn5ActionPerformed(evt);
            }
        });

        btnColumn6.setText("↓");
        btnColumn6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnColumn6ActionPerformed(evt);
            }
        });

        btnColumn7.setText("↓");
        btnColumn7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnColumn7ActionPerformed(evt);
            }
        });

        btn1_2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/connectscore/empty.jpg"))); // NOI18N
        btn1_2.setEnabled(false);

        btn1_1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/connectscore/empty.jpg"))); // NOI18N
        btn1_1.setEnabled(false);

        btn2_2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/connectscore/empty.jpg"))); // NOI18N
        btn2_2.setEnabled(false);

        btn3_1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/connectscore/empty.jpg"))); // NOI18N
        btn3_1.setEnabled(false);

        btn1_3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/connectscore/empty.jpg"))); // NOI18N
        btn1_3.setEnabled(false);

        btn2_1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/connectscore/empty.jpg"))); // NOI18N
        btn2_1.setEnabled(false);

        btn1_4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/connectscore/empty.jpg"))); // NOI18N
        btn1_4.setEnabled(false);

        btn1_5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/connectscore/empty.jpg"))); // NOI18N
        btn1_5.setEnabled(false);

        btn1_6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/connectscore/empty.jpg"))); // NOI18N
        btn1_6.setEnabled(false);

        btn1_7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/connectscore/empty.jpg"))); // NOI18N
        btn1_7.setEnabled(false);

        btn2_4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/connectscore/empty.jpg"))); // NOI18N
        btn2_4.setEnabled(false);

        btn4_1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/connectscore/empty.jpg"))); // NOI18N
        btn4_1.setEnabled(false);

        btn5_1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/connectscore/empty.jpg"))); // NOI18N
        btn5_1.setEnabled(false);

        btn6_1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/connectscore/empty.jpg"))); // NOI18N
        btn6_1.setEnabled(false);

        btn2_3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/connectscore/empty.jpg"))); // NOI18N
        btn2_3.setEnabled(false);

        btn2_5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/connectscore/empty.jpg"))); // NOI18N
        btn2_5.setEnabled(false);

        btn2_6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/connectscore/empty.jpg"))); // NOI18N
        btn2_6.setEnabled(false);

        btn3_2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/connectscore/empty.jpg"))); // NOI18N
        btn3_2.setEnabled(false);

        btn3_3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/connectscore/empty.jpg"))); // NOI18N
        btn3_3.setEnabled(false);

        btn2_7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/connectscore/empty.jpg"))); // NOI18N
        btn2_7.setEnabled(false);

        btn4_7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/connectscore/empty.jpg"))); // NOI18N
        btn4_7.setEnabled(false);

        btn3_4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/connectscore/empty.jpg"))); // NOI18N
        btn3_4.setEnabled(false);

        btn3_6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/connectscore/empty.jpg"))); // NOI18N
        btn3_6.setEnabled(false);

        btn3_7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/connectscore/empty.jpg"))); // NOI18N
        btn3_7.setEnabled(false);

        btn4_2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/connectscore/empty.jpg"))); // NOI18N
        btn4_2.setEnabled(false);

        btn5_2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/connectscore/empty.jpg"))); // NOI18N
        btn5_2.setEnabled(false);

        btn6_4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/connectscore/empty.jpg"))); // NOI18N
        btn6_4.setEnabled(false);

        btn4_3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/connectscore/empty.jpg"))); // NOI18N
        btn4_3.setEnabled(false);

        btn5_3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/connectscore/empty.jpg"))); // NOI18N
        btn5_3.setEnabled(false);

        btn6_3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/connectscore/empty.jpg"))); // NOI18N
        btn6_3.setEnabled(false);

        btn4_4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/connectscore/empty.jpg"))); // NOI18N
        btn4_4.setEnabled(false);

        btn5_4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/connectscore/empty.jpg"))); // NOI18N
        btn5_4.setEnabled(false);
        btn5_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn5_4ActionPerformed(evt);
            }
        });

        btn3_5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/connectscore/empty.jpg"))); // NOI18N
        btn3_5.setEnabled(false);

        btn4_5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/connectscore/empty.jpg"))); // NOI18N
        btn4_5.setEnabled(false);

        btn4_6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/connectscore/empty.jpg"))); // NOI18N
        btn4_6.setEnabled(false);

        btn5_7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/connectscore/empty.jpg"))); // NOI18N
        btn5_7.setEnabled(false);

        btn5_6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/connectscore/empty.jpg"))); // NOI18N
        btn5_6.setEnabled(false);

        btn6_7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/connectscore/empty.jpg"))); // NOI18N
        btn6_7.setEnabled(false);

        btn6_6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/connectscore/empty.jpg"))); // NOI18N
        btn6_6.setEnabled(false);

        btn5_5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/connectscore/empty.jpg"))); // NOI18N
        btn5_5.setEnabled(false);

        btn6_5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/connectscore/empty.jpg"))); // NOI18N
        btn6_5.setEnabled(false);

        btn6_2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/connectscore/empty.jpg"))); // NOI18N
        btn6_2.setEnabled(false);

        lblPlayer2.setFont(new java.awt.Font("MV Boli", 0, 48)); // NOI18N
        lblPlayer2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/connectscore/redtoken.jpg"))); // NOI18N
        lblPlayer2.setText("Player2");

        lblPlayer1.setFont(new java.awt.Font("MV Boli", 0, 48)); // NOI18N
        lblPlayer1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/connectscore/redtoken.jpg"))); // NOI18N
        lblPlayer1.setText("Player1");

        lblWinner.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        lblWinner.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        txtareaWins.setEditable(false);
        txtareaWins.setColumns(20);
        txtareaWins.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        txtareaWins.setLineWrap(true);
        txtareaWins.setRows(5);
        txtareaWins.setWrapStyleWord(true);
        jScrollPane1.setViewportView(txtareaWins);

        javax.swing.GroupLayout pnlBackgroundLayout = new javax.swing.GroupLayout(pnlBackground);
        pnlBackground.setLayout(pnlBackgroundLayout);
        pnlBackgroundLayout.setHorizontalGroup(
            pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBackgroundLayout.createSequentialGroup()
                .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlBackgroundLayout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlBackgroundLayout.createSequentialGroup()
                                .addComponent(btn5_1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(119, 119, 119)
                                .addComponent(btn5_3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlBackgroundLayout.createSequentialGroup()
                                .addComponent(btn6_1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(119, 119, 119)
                                .addComponent(btn6_3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlBackgroundLayout.createSequentialGroup()
                                .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlBackgroundLayout.createSequentialGroup()
                                        .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(btn1_1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                            .addComponent(btnColumn1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btn2_1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(pnlBackgroundLayout.createSequentialGroup()
                                                .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(pnlBackgroundLayout.createSequentialGroup()
                                                        .addComponent(btn1_2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(btn1_3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(pnlBackgroundLayout.createSequentialGroup()
                                                        .addComponent(btn2_2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(btn2_3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(pnlBackgroundLayout.createSequentialGroup()
                                                        .addComponent(btn3_2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(btn3_3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(pnlBackgroundLayout.createSequentialGroup()
                                                        .addComponent(btn4_2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(btn4_3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGap(13, 13, 13)
                                                .addComponent(btn1_4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(btn1_5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btn1_6, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btn1_7, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(pnlBackgroundLayout.createSequentialGroup()
                                                .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                    .addGroup(pnlBackgroundLayout.createSequentialGroup()
                                                        .addGap(0, 0, Short.MAX_VALUE)
                                                        .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                            .addGroup(pnlBackgroundLayout.createSequentialGroup()
                                                                .addComponent(btn2_4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(btn2_5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                            .addGroup(pnlBackgroundLayout.createSequentialGroup()
                                                                .addComponent(btn3_4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(btn3_5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(btn2_6, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(btn3_6, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                    .addGroup(pnlBackgroundLayout.createSequentialGroup()
                                                        .addComponent(btn5_2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(btn5_4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(btn5_5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(btn5_6, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlBackgroundLayout.createSequentialGroup()
                                                        .addComponent(btn6_2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(119, 119, 119)
                                                        .addComponent(btn6_4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(btn6_5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(btn6_6, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlBackgroundLayout.createSequentialGroup()
                                                        .addGap(219, 219, 219)
                                                        .addComponent(btn4_4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(13, 13, 13)
                                                        .addComponent(btn4_5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(btn4_6, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(btn3_7, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(btn2_7, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(btn4_7, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(btn5_7, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(btn6_7, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(pnlBackgroundLayout.createSequentialGroup()
                                                .addComponent(btnColumn2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnColumn3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(btnColumn4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(12, 12, 12)
                                                .addComponent(btnColumn5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnColumn6, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnColumn7, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addComponent(btn3_1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btn4_1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                                .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBackgroundLayout.createSequentialGroup()
                                        .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(btnPlayAgain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnMainMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnResetWins, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE))
                                        .addGap(21, 21, 21))))))
                    .addGroup(pnlBackgroundLayout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(lblPlayer1)
                        .addGap(18, 18, 18)
                        .addComponent(lblWinner, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblPlayer2)))
                .addGap(96, 96, 96))
        );
        pnlBackgroundLayout.setVerticalGroup(
            pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBackgroundLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblWinner, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblPlayer2, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(lblPlayer1)))
                .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlBackgroundLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                            .addComponent(btnColumn6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnColumn7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnColumn5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnColumn4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnColumn3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnColumn2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnColumn1))
                        .addGap(3, 3, 3)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlBackgroundLayout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn1_2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn1_1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn1_3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn1_4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn1_5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn1_6, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn1_7, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn2_2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn2_1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn2_4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn2_3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn2_5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn2_6, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn2_7, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn3_1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn3_3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn3_7, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn3_6, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn3_4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn3_2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn3_5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(pnlBackgroundLayout.createSequentialGroup()
                                .addComponent(btn4_2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn5_2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlBackgroundLayout.createSequentialGroup()
                                .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btnMainMenu)
                                    .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btn4_1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btn4_7, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btn4_3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btn4_4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btn4_5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btn4_6, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btn5_1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btn5_3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btn5_7, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btn5_6, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btn5_5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btn5_4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBackgroundLayout.createSequentialGroup()
                                        .addComponent(btnPlayAgain)
                                        .addGap(25, 25, 25)))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btn6_1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn6_3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btn6_6, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn6_5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn6_2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn6_4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn6_7, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnResetWins))
                .addGap(608, 608, 608))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlBackground, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlBackground, javax.swing.GroupLayout.PREFERRED_SIZE, 859, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn5_4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn5_4ActionPerformed
        // TODO add your handling code here
    }//GEN-LAST:event_btn5_4ActionPerformed

    private void btnColumn7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnColumn7ActionPerformed
        columnClick(6, btnColumn7);//Calls the columnClick method which adds functionality to the column buttons. The same is done for all 7 columns.
    }//GEN-LAST:event_btnColumn7ActionPerformed

    private void btnColumn6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnColumn6ActionPerformed
        columnClick(5, btnColumn6);
    }//GEN-LAST:event_btnColumn6ActionPerformed

    private void btnColumn5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnColumn5ActionPerformed
        columnClick(4, btnColumn5);
    }//GEN-LAST:event_btnColumn5ActionPerformed

    private void btnColumn4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnColumn4ActionPerformed
        columnClick(3, btnColumn4);
    }//GEN-LAST:event_btnColumn4ActionPerformed

    private void btnColumn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnColumn3ActionPerformed
        columnClick(2, btnColumn3);
    }//GEN-LAST:event_btnColumn3ActionPerformed

    private void btnColumn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnColumn2ActionPerformed
        columnClick(1, btnColumn2);
    }//GEN-LAST:event_btnColumn2ActionPerformed

    private void btnColumn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnColumn1ActionPerformed
        columnClick(0, btnColumn1);

    }//GEN-LAST:event_btnColumn1ActionPerformed

    private void btnMainMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMainMenuActionPerformed

        Game objForm1 = new Game();
        objForm1.setVisible(false);
        close();//Closes the Game form.
    }//GEN-LAST:event_btnMainMenuActionPerformed

    private void GameWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_GameWindowClosed
        //This event controls what happens if the user closes the Game form.
        MainMenu objForm1 = new MainMenu();
        objForm1.setVisible(true);//Main menu is set visible.
        Game objForm2 = new Game();
        objForm2.setVisible(false);//Game form is closed.

    }//GEN-LAST:event_GameWindowClosed

    private void btnPlayAgainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlayAgainActionPerformed
        //If the user wants to play again after a game is over.
        bolGameOver = false;//Reset the game over boolean to false.
        lblWinner.setText("");
        btnColumn1.setEnabled(true);
        btnColumn2.setEnabled(true);
        btnColumn3.setEnabled(true);
        btnColumn4.setEnabled(true);
        btnColumn5.setEnabled(true);
        btnColumn6.setEnabled(true);
        btnColumn7.setEnabled(true);
        //Re-enable all of the top column buttons.

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 6; j++) {//These 2 for loops go through the 2D array of the connect four board and resets them to empty.
                tokens[j][i] = 0;//Set the values as 0, indicating they are empty.
                boxes[j][i].setIcon(empty);//Reset their icons as the empty icon.
                boxes[j][i].setEnabled(false);//Disable the buttons.
            }
        }
        if ((intPlayer1Wins + intPlayer2Wins + ties)%2 == 0)//This if statement is to determine which player is going first.
            bolIsPlayer1 = true;//In game 1, player 1 always goes first, so the sum of the wins and ties (AKA number of games played) willl be even.
        else//If the number of games played is odd.
            bolIsPlayer1 = false;//It's player 2's turn.
        
         if(bolRobotMode  == true  &&  bolIsPlayer1 == false) {//If the mode is robot mode and it's player 2's turn.
            robotPlayer.play(tokens, topButtons);//The AI starts with a move since they are player 2 (see Robot.java).
          }

    }//GEN-LAST:event_btnPlayAgainActionPerformed

    private void btnResetWinsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetWinsActionPerformed
        //If the user wants to reset all of the statistics.
        intPlayer1Wins = 0;
        intPlayer2Wins = 0;
        ties = 0;
        //Win and ties counters are reset to 0.
        String strPlayer1Name = lblPlayer1.getText();
        String strPlayer2Name = lblPlayer2.getText();
        txtareaWins.setText(strPlayer1Name + " Wins: " + intPlayer1Wins + "\n\n" + strPlayer2Name + " Wins: " + intPlayer2Wins + "\n\nTies: " + ties);
        //The information is updated in the text areathat displays the statistics.
    }//GEN-LAST:event_btnResetWinsActionPerformed

    void columnClick(int column, JButton btnColumn) {/*This method adds functionality to the column buttons that add the tokens. It also adds the proper physics
        to the tokens, making sure they drop to the bottom.*/           
            int top = -1;/*This declares the top row, which is the row that the token will go (note that the very top row on the board has index number 0, and
            the very bottom row has index number 5).*/
            for (int i = 5; i >= 0; i--) {//This for loop checks for the first available spot in the row.
                if (tokens[i][column] == 0) {//If the square is empty.
                    top = i;//The top row is set to i.
                    break;//End the loop.
                }
            }
            if (top >= 0) {//If there is an empty row.
                //Checks which player's turn it is and sets the icon to a red or yellow token and gives the square a value of 1 (for red) and 2 (for yellow).
                if (bolIsPlayer1 == true) {
                    boxes[top][column].setIcon(getPlayerToken());
                    boxes[top][column].setEnabled(true);
                    tokens[top][column] = 1;
                } else {
                    boxes[top][column].setIcon(getPlayerToken());
                    boxes[top][column].setEnabled(true);
                    tokens[top][column] = 2;
                }
            }
            if (top == 0) {//If the top is equal to 0 (meaning that the column has been ocmpletely filled up).
                btnColumn.setEnabled(false);//Disable the colum button so the user can't drop a token into an already full column.
            }

            checkWinner(top, column);//Checks for a winner (see the method).
            if(bolGameOver == false){//If the game isn't over yet.
                bolIsPlayer1 = !bolIsPlayer1;//Change turns.
                if(bolRobotMode  == true && bolIsPlayer1 == false ){//If robot mode is on and it's the robot's turn.
                        robotPlayer.play(tokens, topButtons);//Make the robot click (see Robot.java).
                }
            
            }

    }

    void checkWinner(int lastMoveRow, int lastMoveCol) {//This method checks for a winner.
        if (bolGameOver == false && checkVertical(lastMoveRow, lastMoveCol) == true) {//If there is a vertical connect four.
            bolGameOver = true;
        }
        if (bolGameOver == false && checkHorizontal(lastMoveRow, lastMoveCol) == true) {//If there is a horizontal connect four.
            bolGameOver = true;
        }
        if (bolGameOver == false && checkDiagonalUpper(lastMoveRow, lastMoveCol) == true) {//If there is an upper diagonal connect four (going this way ↗ or ↙).
            bolGameOver = true;
        }
        if (bolGameOver == false && checkDiagonalLower(lastMoveRow, lastMoveCol) == true) {//If there is a lower diagonal connect four (going this way ↘ or ↖).
            bolGameOver = true;
        }
        //The game is set to over is a winner is detected.

        //If there's a tie (no winner and the board is filled up).
        if (bolGameOver == false && tokens[0][0] > 0 && tokens[0][1] > 0 && tokens[0][2] > 0
                && tokens[0][3] > 0 && tokens[0][4] > 0 && tokens[0][5] > 0 && tokens[0][6] > 0) {
            //The if statement checks if the board is filled up by looking at the very top row (if that row is filled up, it means the rest of the board is too).
            ties++;//Number of ties increased by 1.
            bolGameOver = true;//Sets game as over.
            lblWinner.setText("It's a tie!");//Display a message saying that it's a tie.
            String strPlayer1Name = lblPlayer1.getText();
            String strPlayer2Name = lblPlayer2.getText();
            txtareaWins.setText(strPlayer1Name + " Wins: " + intPlayer1Wins + "\n\n" + strPlayer2Name + " Wins: " + intPlayer2Wins + "\n\nTies: " + ties);
            //Update the text area with the win and tie counters.

        }
    }

    //All of the check methods check the square where the last token was placed. They check neighbouring squares to see if they match.
    boolean checkDiagonalUpper(int lastMoveRow, int lastMoveCol) {//This method checks for a diagonal connect four going ↗ or ↙.
        int playerTokenIndicator = tokens[lastMoveRow][lastMoveCol];/*This gets the value of the token that was last placed original token, red or yellow) 
        which will tell which player won*/
        int intCount = 0;//This counts how many of the same token are connected to the original token.
        int intLeft = 0;//This counts how many spaces right the check moves.
        int intRight = 0;//This counts how far left the check moves.
        //intLeft and intRight will be used to highlight the icons when connect 4 is achieved to show how it was won.
        //Here we check for tokens going from left to right, down to up (in this direction ↗).
        for (int i = 0; lastMoveRow - i >= 0 && lastMoveCol + i <= 6; i++) {
            if (tokens[lastMoveRow - i][lastMoveCol + i] == playerTokenIndicator) {//If the token matches the original token.
                intCount++;//Number of tokens matched increases by one.
                intRight = i;//Number of positions shifted right.
                //Note: The intCount and intRight includes the original token (since i starts at 0 in the loop).
            } else {//If the token doesn't match.
                break;//Stop counting.
            }
        }

        //Here we check for tokens going from right to left, up to down (in this direction ↙). 
        for (int i = 1; lastMoveRow + i <= 5 && lastMoveCol - i >= 0; i++) {/*Same as checking the diagonal ↗, except the for loop is adjusted. Also, i
            starts at 1 this time, since starting at 0 would mean double counting the original token.*/
            if (tokens[lastMoveRow + i][lastMoveCol - i] == playerTokenIndicator) {
                intCount++;
                intLeft = -i;//Number of positions shifted left.
            } else {
                break;
            }
        }
        if (intCount >= 4) {//If there's a connect four (four of the same token consecutively).
            setWinner();//Set the winner.
 
            for (int i = intLeft; i <= intRight; i++) {
                if (bolIsPlayer1 == true) { //If it was player 1's turn.
                    boxes[lastMoveRow - i][lastMoveCol + i].setIcon(redToken_Highlight);//Highlight the winning tokens red.
                } else {//If player 2 won.
                    boxes[lastMoveRow - i][lastMoveCol + i].setIcon(yellowToken_Highlight);//Highlight the winnins tokens yellow.
                }
            }

            return true;//Return true if a connect 4 was formed.
        }
        return false;
    }
    //Please see the commenting for checkDiagonalUpper (this works the same, the loops are tweaked to change the direction).
    boolean checkDiagonalLower(int lastMoveRow, int lastMoveCol) {//This method checks for a connect four diagonal going ↖ and ↘.
        int playerTokenIndicator = tokens[lastMoveRow][lastMoveCol];
        int intCount = 0;
        int intLeft = 0;
        int intRight = 0;
        //All of the variables are declared like in the checkDiagonalUpper method.

        for (int i = 0; lastMoveRow + i <= 5 && lastMoveCol + i <= 6; i++) {//This loop checks from left to right, up to down (this direction ↘).
            if (tokens[lastMoveRow + i][lastMoveCol + i] == playerTokenIndicator) {
                intCount++;
                intRight = i;
            } else {
                break;
            }
        }

        for (int i = 1; lastMoveRow - i >= 0 && lastMoveCol - i >= 0; i++) {//This loop checks from right to left, down to up (this direction ↖).
            if (tokens[lastMoveRow - i][lastMoveCol - i] == playerTokenIndicator) {
                intCount++;
                intLeft = -i;
            } else {
                break;
            }
        }
        if (intCount >= 4) {//If there's a connect four.
            setWinner();//Set the winner.

            for (int i = intLeft; i <= intRight; i++) {//Highlight the winning tokens.
                if (bolIsPlayer1 == true) {
                    boxes[lastMoveRow + i][lastMoveCol + i].setIcon(redToken_Highlight);
                } else {
                    boxes[lastMoveRow + i][lastMoveCol + i].setIcon(yellowToken_Highlight);
                }
            }

            return true;
        }
        return false;
    }

    boolean checkHorizontal(int lastMoveRow, int lastMoveCol) {//This method checks for any horixontal connect fours left of and right of the original token.
        int playerTokenIndicator = tokens[lastMoveRow][lastMoveCol];
        int intCount = 0;
        int intLeft = lastMoveCol;
        int intRight = lastMoveCol;
        //All of the variables are declared like in the checkDiagonalUpper method.

        for (int i = lastMoveCol; i <= 6; i++) {//This loop checks from left to right.
            if (tokens[lastMoveRow][i] == playerTokenIndicator) {
                intCount++;
                intRight = i;
            } else {
                break;
            }
        }

        for (int i = lastMoveCol - 1; i >= 0; i--) {//This loop checks from right to left.
            if (tokens[lastMoveRow][i] == playerTokenIndicator) {
                intCount++;
                intLeft = i;
            } else {
                break;
            }
        }
        if (intCount >= 4) {//If there's a connect four.
            setWinner();//Set the winner.

            for (int i = intLeft; i <= intRight; i++) {//Highlight the winning tokens.
                if (bolIsPlayer1 == true) {
                    boxes[lastMoveRow][i].setIcon(redToken_Highlight);
                } else {
                    boxes[lastMoveRow][i].setIcon(yellowToken_Highlight);
                }
            }
            return true;
        }
        return false;

    }

    boolean checkVertical(int lastMoveRow, int lastMoveCol) {/*This method checks for any vertical connect fours below the original token (note there's no need to
        check above the original token, since it's the token that was last placed, so there are no tokens above it.*/
        int playerTokenIndicator = tokens[lastMoveRow][lastMoveCol];
        int intCount = 0;
  
        for (int i = lastMoveRow; i <= 5; i++) {//This loop checks below the original token for any identical tokens.
            if (tokens[i][lastMoveCol] == playerTokenIndicator) {
                intCount++;
            } else {
                break;
            }
        }

        if (intCount >= 4) {//If there's a connect four.
            setWinner();//Set the winner.
            for (int i = lastMoveRow; i <= lastMoveRow + 3; i++) {//Highlight the winning tokens.
                if (bolIsPlayer1 == true) {
                    boxes[i][lastMoveCol].setIcon(redToken_Highlight);
                } else {
                    boxes[i][lastMoveCol].setIcon(yellowToken_Highlight);
                }
            }
            return true;
        }
        return false;
    }

    void setWinner() {//This method sets the winner of a game.
        String strPlayer1Name = lblPlayer1.getText();
        String strPlayer2Name = lblPlayer2.getText();
        if (bolIsPlayer1 == true) {//If it's player 1'
            intPlayer1Wins++;
            lblWinner.setText(strPlayer1Name + " wins!");
        } else {
            intPlayer2Wins++;
            lblWinner.setText(strPlayer2Name + " wins!");
        }

        //Disable all of the column buttons to stop play.
        btnColumn1.setEnabled(false);
        btnColumn2.setEnabled(false);
        btnColumn3.setEnabled(false);
        btnColumn4.setEnabled(false);
        btnColumn5.setEnabled(false);
        btnColumn6.setEnabled(false);
        btnColumn7.setEnabled(false);

        txtareaWins.setText(strPlayer1Name + " Wins: " + intPlayer1Wins + "\n\n" + strPlayer2Name + " Wins: " + intPlayer2Wins + "\n\nTies: " + ties);
        //Update the text area with the win counter.
    }

    ImageIcon getPlayerToken() {//This method gets which token the player has and displays whose turn it is to move.
        if (bolIsPlayer1) {//If it's player 1's turn.
            lblPlayer2.setForeground(java.awt.Color.red);
            lblPlayer1.setForeground(java.awt.Color.black);
            //When the button is pressed, it will become player 2's turn. Highlight player 2's name as red and turn player 1's name back to black.
            if (strPlayer1Color.equalsIgnoreCase("red")) {//If player 1's colour is red.
                return redToken;//Player 1 gets the red token.
            } else {
                return yellowToken;//Player 1 gets the yellow token.
            }

        } else {//If it's player 2's turn.
            lblPlayer1.setForeground(java.awt.Color.red);
            lblPlayer2.setForeground(java.awt.Color.black);
            //When the button is pressed, it will become player 1's turn. Highlight player 1's name as red and turn player 2's name back to black.
            if (strPlayer1Color.equalsIgnoreCase("red")) {//If player 2's colour is red.
                return yellowToken;//Player 2 gets the red token.
            } else {
                return redToken;//Player 2 gets the yellow token.
            }
        }
    }
    

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Game().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn1_1;
    private javax.swing.JButton btn1_2;
    private javax.swing.JButton btn1_3;
    private javax.swing.JButton btn1_4;
    private javax.swing.JButton btn1_5;
    private javax.swing.JButton btn1_6;
    private javax.swing.JButton btn1_7;
    private javax.swing.JButton btn2_1;
    private javax.swing.JButton btn2_2;
    private javax.swing.JButton btn2_3;
    private javax.swing.JButton btn2_4;
    private javax.swing.JButton btn2_5;
    private javax.swing.JButton btn2_6;
    private javax.swing.JButton btn2_7;
    private javax.swing.JButton btn3_1;
    private javax.swing.JButton btn3_2;
    private javax.swing.JButton btn3_3;
    private javax.swing.JButton btn3_4;
    private javax.swing.JButton btn3_5;
    private javax.swing.JButton btn3_6;
    private javax.swing.JButton btn3_7;
    private javax.swing.JButton btn4_1;
    private javax.swing.JButton btn4_2;
    private javax.swing.JButton btn4_3;
    private javax.swing.JButton btn4_4;
    private javax.swing.JButton btn4_5;
    private javax.swing.JButton btn4_6;
    private javax.swing.JButton btn4_7;
    private javax.swing.JButton btn5_1;
    private javax.swing.JButton btn5_2;
    private javax.swing.JButton btn5_3;
    private javax.swing.JButton btn5_4;
    private javax.swing.JButton btn5_5;
    private javax.swing.JButton btn5_6;
    private javax.swing.JButton btn5_7;
    private javax.swing.JButton btn6_1;
    private javax.swing.JButton btn6_2;
    private javax.swing.JButton btn6_3;
    private javax.swing.JButton btn6_4;
    private javax.swing.JButton btn6_5;
    private javax.swing.JButton btn6_6;
    private javax.swing.JButton btn6_7;
    private javax.swing.JButton btnColumn1;
    private javax.swing.JButton btnColumn2;
    private javax.swing.JButton btnColumn3;
    private javax.swing.JButton btnColumn4;
    private javax.swing.JButton btnColumn5;
    private javax.swing.JButton btnColumn6;
    private javax.swing.JButton btnColumn7;
    private javax.swing.JButton btnMainMenu;
    private javax.swing.JButton btnPlayAgain;
    private javax.swing.JButton btnResetWins;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblPlayer1;
    private javax.swing.JLabel lblPlayer2;
    private javax.swing.JLabel lblWinner;
    private javax.swing.JPanel pnlBackground;
    private javax.swing.JTextArea txtareaWins;
    // End of variables declaration//GEN-END:variables
}
