/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectscore;

import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;

/**
 *
 * @author Alex Guo
 * Date: August 22, 21, 2018
 * Description: The main menu GUI of the game that contains the options to select the mode, change the theme, view a tutorial, or quit the game.
 */
public class MainMenu extends javax.swing.JFrame {

    /**
     * Creates new form MainMenu
     */
    public MainMenu() {
        initComponents();
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        pnlBackground = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();
        btnStart = new javax.swing.JButton();
        btnHelp = new javax.swing.JButton();
        btnQuit = new javax.swing.JButton();
        cmbTheme = new javax.swing.JComboBox<>();
        lblTheme = new javax.swing.JLabel();
        lblYellowToken2 = new javax.swing.JLabel();
        lblYellowToken = new javax.swing.JLabel();
        lblRedToken2 = new javax.swing.JLabel();
        lblRedToken = new javax.swing.JLabel();
        btnPlayVsPlay = new javax.swing.JRadioButton();
        btnPlayVsCPU = new javax.swing.JRadioButton();
        lblPlayer1Name = new javax.swing.JLabel();
        lblPlayer2Name = new javax.swing.JLabel();
        txtPlayer1Name = new javax.swing.JTextField();
        txtPlayer2Name = new javax.swing.JTextField();
        lblPlayerName = new javax.swing.JLabel();
        lblDifficulty = new javax.swing.JLabel();
        cmbDifficulty = new javax.swing.JComboBox<>();
        txtPlayerName = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        pnlBackground.setBackground(new java.awt.Color(204, 204, 255));

        lblTitle.setFont(new java.awt.Font("MV Boli", 0, 48)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(51, 51, 51));
        lblTitle.setText("<html>Connect Score<br><font size = \"5\">Made by: Alex Guo</font></html>");

        btnStart.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btnStart.setText("Start Playing");
        btnStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartActionPerformed(evt);
            }
        });

        btnHelp.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btnHelp.setText("Help");
        btnHelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHelpActionPerformed(evt);
            }
        });

        btnQuit.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btnQuit.setText("Quit");
        btnQuit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuitActionPerformed(evt);
            }
        });

        cmbTheme.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        cmbTheme.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Classic", "Money", "Sports" }));
        cmbTheme.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbThemeActionPerformed(evt);
            }
        });

        lblTheme.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblTheme.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTheme.setText("Theme:");

        lblYellowToken2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/connectscore/yellowtoken.jpg"))); // NOI18N

        lblYellowToken.setIcon(new javax.swing.ImageIcon(getClass().getResource("/connectscore/yellowtoken.jpg"))); // NOI18N

        lblRedToken2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/connectscore/redtoken.jpg"))); // NOI18N

        lblRedToken.setIcon(new javax.swing.ImageIcon(getClass().getResource("/connectscore/redtoken.jpg"))); // NOI18N

        buttonGroup1.add(btnPlayVsPlay);
        btnPlayVsPlay.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btnPlayVsPlay.setSelected(true);
        btnPlayVsPlay.setText("Player vs Player");
        btnPlayVsPlay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlayVsPlayActionPerformed(evt);
            }
        });

        buttonGroup1.add(btnPlayVsCPU);
        btnPlayVsCPU.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btnPlayVsCPU.setText("Player vs Computer");
        btnPlayVsCPU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlayVsCPUActionPerformed(evt);
            }
        });

        lblPlayer1Name.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblPlayer1Name.setText("Player 1's Name:");

        lblPlayer2Name.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblPlayer2Name.setText("Player 2's Name:");

        txtPlayer1Name.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtPlayer1Name.setText("Player 1");

        txtPlayer2Name.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtPlayer2Name.setText("Player 2");

        lblPlayerName.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblPlayerName.setText("Player's Name:");
        lblPlayerName.setEnabled(false);

        lblDifficulty.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblDifficulty.setText("Computer Difficulty:");
        lblDifficulty.setEnabled(false);

        cmbDifficulty.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cmbDifficulty.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nice", "Average", "Tough" }));
        cmbDifficulty.setEnabled(false);

        txtPlayerName.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtPlayerName.setText("Player");
        txtPlayerName.setEnabled(false);
        txtPlayerName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPlayerNameActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlBackgroundLayout = new javax.swing.GroupLayout(pnlBackground);
        pnlBackground.setLayout(pnlBackgroundLayout);
        pnlBackgroundLayout.setHorizontalGroup(
            pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBackgroundLayout.createSequentialGroup()
                .addGap(160, 160, 160)
                .addComponent(lblTheme)
                .addGap(18, 18, 18)
                .addComponent(cmbTheme, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(pnlBackgroundLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlBackgroundLayout.createSequentialGroup()
                        .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pnlBackgroundLayout.createSequentialGroup()
                                .addComponent(lblPlayer2Name)
                                .addGap(18, 18, 18)
                                .addComponent(txtPlayer2Name))
                            .addGroup(pnlBackgroundLayout.createSequentialGroup()
                                .addComponent(lblPlayer1Name)
                                .addGap(18, 18, 18)
                                .addComponent(txtPlayer1Name, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(35, 35, 35)
                        .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlBackgroundLayout.createSequentialGroup()
                                .addComponent(lblPlayerName)
                                .addGap(42, 42, 42)
                                .addComponent(txtPlayerName, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlBackgroundLayout.createSequentialGroup()
                                .addComponent(lblDifficulty)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbDifficulty, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlBackgroundLayout.createSequentialGroup()
                                .addComponent(lblRedToken2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(51, 51, 51)
                                .addComponent(lblYellowToken, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(42, Short.MAX_VALUE))
                    .addGroup(pnlBackgroundLayout.createSequentialGroup()
                        .addComponent(btnPlayVsPlay)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnPlayVsCPU)
                        .addGap(54, 54, 54))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBackgroundLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblRedToken, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(lblYellowToken2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(378, 378, 378))
            .addGroup(pnlBackgroundLayout.createSequentialGroup()
                .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlBackgroundLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnStart, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnHelp, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(btnQuit, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlBackgroundLayout.createSequentialGroup()
                        .addGap(171, 171, 171)
                        .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlBackgroundLayout.setVerticalGroup(
            pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBackgroundLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblRedToken, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblYellowToken2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblRedToken2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblYellowToken, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPlayVsPlay)
                    .addComponent(btnPlayVsCPU))
                .addGap(38, 38, 38)
                .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPlayer1Name)
                    .addComponent(txtPlayer1Name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPlayerName)
                    .addComponent(txtPlayerName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPlayer2Name)
                    .addComponent(txtPlayer2Name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDifficulty)
                    .addComponent(cmbDifficulty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 103, Short.MAX_VALUE)
                .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbTheme, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTheme))
                .addGap(68, 68, 68)
                .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnHelp)
                    .addComponent(btnStart)
                    .addComponent(btnQuit))
                .addGap(51, 51, 51))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlBackground, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlBackground, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnQuitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitActionPerformed
        System.exit(0);//Exit the application.
    }//GEN-LAST:event_btnQuitActionPerformed

    private void btnHelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHelpActionPerformed
        //This button event causes the main menu form to close and opens the tutorial form.
        MainMenu objForm1 = new MainMenu();//Creates the main menu form.
        objForm1.setVisible(false);//Sets it as not visible.
        Tutorial objForm2 = new Tutorial();//Creates the tutorial object form.
        objForm2.setVisible(true);//Sets it as visible.
        close();//Close the main menu form.
    }//GEN-LAST:event_btnHelpActionPerformed

    private void btnStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartActionPerformed
        MainMenu objForm1 = new MainMenu();//Creates the main menu form.
        objForm1.setVisible(false);//Sets it as not visible.
        Game objForm2 = new Game();//This creates the game object form.
        String strTheme = cmbTheme.getSelectedItem().toString();//This gets which theme the user picked.
        if (btnPlayVsPlay.isSelected() == true) {//If the user delects player vs player mode.
            objForm2.setPlayer1(txtPlayer1Name.getText(), "red");
            objForm2.setPlayer2(txtPlayer2Name.getText(), "yellow");
            /*Calls the setPlayer methods which get the user's names from the text fields and sets their colours. Player 1 is always set to "red" and player 2
            will be set to "yellow". Red goes first in the first game then the turns are switched the next game (see the methods in Game.java for more information.*/
        }
        
        else {//If the player selects player vs computer mode.
            String strLevel = cmbDifficulty.getSelectedItem().toString();//Gets the difficulty of the computer from the combo box.
            int intLevel = 1;//The difficulty is by default set to "nice" (since that is the option that is selected by default).
            if (strLevel.equalsIgnoreCase("Average")) {
                intLevel = 2;//The difficulty is set to average if the user selects it.
            }
            else if (strLevel.equalsIgnoreCase("Tough")){
                intLevel = 3;//The difficulty is set to tough if the user selects it.
            }


            objForm2.setPlayer1(txtPlayerName.getText(), "red");
            objForm2.setPlayer2("Robot-" + strLevel, "yellow");
            //Uses the setPlayer methods, this time with player 1 being the user and player 2 being the robot.
            objForm2.setRobotMode(true, intLevel);
            /*Calls the setRobotMode method which takes a boolean value which decides whether or not to implement the AI and an integer value that determines
            difficulty*/
        }
        objForm2.setTheme(strTheme);//Calls the setTheme method which sets the game's theme based on the user's selection.      
        objForm2.setVisible(true);//Sets the game form as visible.
        close();//Closes the main menu.
    }//GEN-LAST:event_btnStartActionPerformed

    private void cmbThemeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbThemeActionPerformed
        String strTheme = cmbTheme.getSelectedItem().toString();//Gets which theme the user selected from the combo box.
        ImageIcon redToken = new ImageIcon("redtoken.jpg");//Creates the redToken image icon, which is by default a classic red token.
        ImageIcon yellowToken = new ImageIcon("yellowtoken.jpg");//Creates the yellowToken image icon, which is by default a classic yellow token.
        if (strTheme.equals("Sports")) {//If the user selects sports as their theme.
            redToken = new ImageIcon("basketball.jpg");//The "redToken" becomes a basketball.
            yellowToken = new ImageIcon("soccer.jpg");//The "yellowToken" becomes a soccer ball.
        }
        else if (strTheme.equals("Money")) {//If the user selects money are their theme.
            redToken = new ImageIcon("coin1.jpg");//The "redToken" becomes a penny.
            yellowToken = new ImageIcon("coin2.jpg");//The "yellowToken" becomes a dime.
        }
        
        lblRedToken.setIcon(redToken);
        lblRedToken2.setIcon(redToken);
        lblYellowToken.setIcon(yellowToken);
        lblYellowToken2.setIcon(yellowToken);
        //Sets the icons in the main menu to the appropriate images based on the theme.
        
    }//GEN-LAST:event_cmbThemeActionPerformed

    private void btnPlayVsCPUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlayVsCPUActionPerformed
        if (btnPlayVsCPU.isSelected() == true) {//If the player selects the player vs computer radio button.
            lblPlayer1Name.setEnabled(false);
            lblPlayer2Name.setEnabled(false);
            txtPlayer1Name.setEnabled(false);
            txtPlayer2Name.setEnabled(false);
            lblPlayerName.setEnabled(true);
            txtPlayerName.setEnabled(true);
            lblDifficulty.setEnabled(true);
            cmbDifficulty.setEnabled(true);
            //Enable the fields under player vs computer and disable the ones under player vs player.
        }
    }//GEN-LAST:event_btnPlayVsCPUActionPerformed

    private void txtPlayerNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPlayerNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPlayerNameActionPerformed

    private void btnPlayVsPlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlayVsPlayActionPerformed
        if (btnPlayVsPlay.isSelected() == true) {//If the player selects the player vs player radio button.
            lblPlayer1Name.setEnabled(true);
            lblPlayer2Name.setEnabled(true);
            txtPlayer1Name.setEnabled(true);
            txtPlayer2Name.setEnabled(true);
            lblPlayerName.setEnabled(false);
            txtPlayerName.setEnabled(false);
            lblDifficulty.setEnabled(false);
            cmbDifficulty.setEnabled(false);
            //Enable the fields under player vs player and disable the ones under player vs computer.
        }
    }//GEN-LAST:event_btnPlayVsPlayActionPerformed

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
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHelp;
    private javax.swing.JRadioButton btnPlayVsCPU;
    private javax.swing.JRadioButton btnPlayVsPlay;
    private javax.swing.JButton btnQuit;
    private javax.swing.JButton btnStart;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cmbDifficulty;
    private javax.swing.JComboBox<String> cmbTheme;
    private javax.swing.JLabel lblDifficulty;
    private javax.swing.JLabel lblPlayer1Name;
    private javax.swing.JLabel lblPlayer2Name;
    private javax.swing.JLabel lblPlayerName;
    private javax.swing.JLabel lblRedToken;
    private javax.swing.JLabel lblRedToken2;
    private javax.swing.JLabel lblTheme;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblYellowToken;
    private javax.swing.JLabel lblYellowToken2;
    private javax.swing.JPanel pnlBackground;
    private javax.swing.JTextField txtPlayer1Name;
    private javax.swing.JTextField txtPlayer2Name;
    private javax.swing.JTextField txtPlayerName;
    // End of variables declaration//GEN-END:variables
}