/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectscore;

import java.util.ArrayList;
import javax.swing.JButton;

/**
 *
 * @author Alex Guo
 * Date: August 21, 2018
 * Description: A class that controls the AI's behaviour when playing player vs computer mode.
 */
public class Robot {
    
    String name;
    int level = 1;
    //The name of the robot and the difficulty level is declared.
    public Robot(String name,  int level){//The robot class has parameters for a name and a difficulty level.
        this.name = name;
        this.level = level;
        //The variables are assigned the values.
    }
    
    public void play(int[][] tokens, JButton[] topButtons ){//This method allows the AI to play based on what difficulty level is selected.
        if (level == 1)
            niceAI(tokens, topButtons);//If the difficulty level is one, the "nice" AI is set to play.
        else if(level == 2)
            averageAI(tokens, topButtons);//If the difficulty level is two, the "average" AI is set to play.
        
       else
           toughAI(tokens, topButtons);//If the difficulty level is three, the "tough" AI is set to play.       
    }
    
    void niceAI(int[][] tokens, JButton[] topButtons ) {/*This controls the "nice" AI and allows it to click the column buttons to play. The nice AI will simply
        choose a random column button to click without considering anything else.*/
        
        ArrayList<JButton> enabledColumnButtons =new ArrayList<JButton>();/*This arraylist stores which column buttons are available for the computer to press
        so that it doesn't press a disabled button.*/
        
        for(int i = 0; i <= 6; i++){//This checks the very top row to see if it's empty.
            if(tokens[0][i] == 0)//If the column is empty.
                enabledColumnButtons.add(topButtons[i]);//This means that the button is available to press, so it's added to the arraylist.
        }
       
        int intColumn = (int) (Math.random() * enabledColumnButtons.size());/*This generates a random integer from 0 to the number of enabled column buttons
        minus one which will determine which button the AI clicks.*/
   
        enabledColumnButtons.get(intColumn).doClick();//This makes the AI click the button.
    }
    
    
     void averageAI(int[][] tokens, JButton[] topButtons ) {/*This method controls the "average" AI. This AI now has the ability to detect when the user is about to 
         win and has the ability to block them. If no such case is detected, it plays randomly (uses the niceAI method)*/
        boolean bolBlocked=false;//This boolean determines whether or not the user has been blocked.
        
        for(int i = 0;i <= 6; i++) {
            int top=5;
            for(int j=0; j<=5;j++){/*Here the loop checks for the top available row. This is done to make sure that the AI ignore when the user has three in a row if
                the three tokens are stacked vertcally to fill up a column, since there's no way the user can place another token there to win.*/
                if (tokens[j][i] > 0){
                    top=j-1; 
                    break;
                }    
            }
            
            if (top >=0 && getNumberOfThrees(top, i, tokens, 1) >= 1) {//If the user has at least 1 three in a row.
               topButtons[i].doClick();//Block the user.
               bolBlocked = true;//Set blocked as true.
               break;
            }
        }
           
        if(bolBlocked ==false)
            niceAI(tokens, topButtons );//If the user isn't about to win, the niceAI method is called(the AI plays randomly).
    }
    
    void toughAI(int[][] tokens, JButton[] topButtons ) {/*This method controls the "tough" AI. This AI now has the ability to detect when it has three in a row
        and play the winning move. It can also detect any "double" threes and block them if they're the opponent's move and win if they're its own tokens. In addition,
        the tough AI will avoid placing a token in a way that helps the user win. The tough AI also retains the functions of the average and nice AIs.
        */
        boolean bolPlayed = false;//This boolean detects whether or not the tough AI has made a move.
        
        for(int i = 0;i <= 6; i++) {
            int top = 5;
            for(int j = 0; j <= 5;j++){/*Here the loop checks for the top available row. This is done to make sure that the AI ignore when the user has three in a row if
                the three tokens are stacked vertcally to fill up a column, since there's no way the user can place another token there to win.*/
                if (tokens[j][i]>0){
                    top=j-1; 
                    break;
                }
                    
            }
            if (top >= 0 && getNumberOfThrees(top, i, tokens, 2) >= 1 ) {/*This is the same as blocking the opponent, except the third parameter has value 2, meaning
                that the tough AI is looking for when its own tokens have three in a row so it can win.*/
               topButtons[i].doClick();//Play the winning move.
               bolPlayed=true;//Sets bolPlayed as true.
               break;           
            }
        }
        
        if (bolPlayed == true)//If the AI has succeeded in a play, end the method.
            return;
        
        //Same code as above, except this time the toughAI is blocking the user.
        for(int i = 0; i<= 6; i++) {
            int top = 5;
            for(int j = 0; j <= 5;j++){
                if (tokens[j][i]>0){
                    top = j-1; 
                    break;
                }
                    
            }
            if (top >=0 && getNumberOfThrees(top, i, tokens, 1) >= 1)//This time the computer is detecting when the user has 3 in a row so they can block them. 
            {
               topButtons[i].doClick();
               bolPlayed=true;
               break;
            
            }
        }
        
        if (bolPlayed == true)
            return;
        
        //This allows the computer to detect when it has a double three.
        for(int i = 0;i <= 6; i++) {
            int top = 5;
            for(int j = 0; j<=5;j++){/*Here the loop checks for the top available row. This is done to make sure that the AI ignore when it has three in a row if
                the three tokens are stacked vertcally to fill up a column, since there's no way the AL can place another token there to win.*/
                if (tokens[j][i]>0){
                    top = j-1; 
                    break;
                }
                    
            }
            if (top >=0 && getNumberOfThrees(top, i, tokens, 2) >= 2)//If the AI detects that it has a double three.
            {
               topButtons[i].doClick();//Play the winning move.
               bolPlayed=true;//Set bolPlayed as false.
               break;
            
            }
        }
        
        if (bolPlayed == true)
            return;
        
        /*Virtually the same code as when the computer is detecting a double three for itself, but the parameter in the if statement is adjusted to it is looking
        for any double threes the user might have in order to block them.*/
        for(int i = 0;i <= 6; i++) {
            int top = 5;
            for(int j=0; j<=5;j++){
                if (tokens[j][i]>0){
                    top=j-1; 
                    break;
                }
                    
            }
            if (top>=0 && getNumberOfThrees(top, i, tokens, 1) >= 2) {
               topButtons[i].doClick();
               bolPlayed=true;
               break;
            
            }
        }
        
        if (bolPlayed == true)
            return;
        
        
        /*Play randomly like niceAI, except this time, don't play in a way that will let the user win (like by placing a token that allows the user to place a
        token on top of it to win*/
        ArrayList<Integer> playeableColumns =new ArrayList<Integer>();/*This arraylist stores which column buttons are available for the computer to press
        so that it doesn't press a disabled button.*/
        for(int i = 0;i <= 6 ; i++) {
            int top = 5;
            for(int j = 0; j <= 5;j++){/*Here the loop checks for the top available row. This is done to make sure that the AI ignores when there's three
                in a row since you can't plae a token on a full column.*/
                if (tokens[j][i] > 0){
                    top = j-1; 
                    break;
                }
            }
            if (top >= 1 && getNumberOfThrees(top-1, i, tokens, 1) == 0) {
              playeableColumns.add(i);//If the column is free, add it to the arraylist.
            }
        }
        if(playeableColumns.size() > 0) {
            int intSelectColumn = (int)(Math.random()*playeableColumns.size());/*If there are playeable columns, generate a random number from 0 to the 
            arraylist size subtract one to determine a column to click.*/
        
            topButtons[(int)playeableColumns.get(intSelectColumn)].doClick();//Click the selected column.
            bolPlayed=true;//Set played as true.
        }   
        
        if (bolPlayed == true)
            return;
        
        niceAI(tokens, topButtons);//If the AI doesn't detect anything, play randomly.
       
           
       }
    
      private int getNumberOfThrees(int row, int col, int[][] tokens, int intToken ){//This method gets how many instances of three in a row there are.
          /*The code here is almost exactly the same as the checkHorizontal, checkVertical, checkDiagonalUpper, and check diagonalLower methods in Game.java.
          The difference is that this method returns an integer value instead of a boolean value to determine how many instances of three in a row there are.
          It also checks  if there are three in a row and not four in a row like the previous methods. Finally, there is no need for intRight and intLeft since
          we are not highlighting the tokens.*/
           
           int intCount = 0;//Counts number of tokens in a row.
           int intThreeCount = 0;//Counts number of threes (this is the return value).
           //Check vertically
           for(int  i = row+1; i <= 5; i++) {
               if(tokens[i][col] == intToken)
                   intCount++;
               else 
                   break;
           }
           if (intCount >= 3)
               intThreeCount++;
 
            //Check horizontally.
           intCount = 0;
           for (int i =col+1; i <= 6; i++){
                if(tokens[row][i] == intToken)
                   intCount++;
               else 
                   break;
           }
           for (int i = col-1; i >= 0; i--){
                if(tokens[row][i] == intToken)
                   intCount++;
               else 
                   break;
           }
          if (intCount >= 3)
               intThreeCount++;
           
           //Check diagonally ↗ and ↙.
          intCount = 0; 
          for (int i = 1; row-i >= 0 && col+i <= 6; i++){
                if(tokens[row-i][col+i] == intToken)
                   intCount++;
               else 
                   break;
           }
           for (int i = 1; row+i <= 5 && col-i >= 0;  i++){
               if(tokens[row+i][col-i] == intToken)
                   intCount++;
               else 
                   break;
           }
           if (intCount >= 3)
               intThreeCount++;
          
           //Check diagonally ↖ and ↘.
           intCount = 0;
           for (int i = 1; row+i <= 5 && col+i <= 6; i++){
                if(tokens[row+i][col+i] == intToken)
                   intCount++;
               else 
                   break;
           }
           for (int i = 1; row-i >=0 && col-i >= 0;  i++){
               if(tokens[row-i][col-i] == intToken)
                   intCount++;
               else 
                   break;
           }
           if (intCount >= 3)
               intThreeCount++;
           
           return intThreeCount;//Return the number of instances of three in a row.
       } 
       
      
    
    
}
