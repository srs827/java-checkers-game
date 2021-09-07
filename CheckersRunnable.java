/**
* Samantha Sudhoff 
* Pd. 4
* APCSA Final Project -- Checkers 
* 5/25/21
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

/** 
* Main class that runs full program 
* Extends JPanel 
*/
public class CheckersRunnable extends JPanel { 
   /**
   * Screensize dimension
   */
   private static Dimension screensize;
   
   /**
   * JPanel that is the panel selected when mouse clicked
   */
   private JPanel selectedPanel = null;
   
   /**
   * The original row of moving piece
   */
   private int fromRow;
   
   /**
   * First color for grid
   */
   private Color pink;
   
   
   /**
   * Second grid color 
   */
   private Color offWhite;
   
   /**
   * The original column of moving piece
   */
   private int fromCol;
   
   /**
   * The occupant currently being used
   */
   private int p1Occ;
   
   /**
   * The occupant currently being used
   */
   private int p2Occ;
   
   /**
   * boolean that contains whether a specific piece is captured
   */
   private boolean isCapture;
   
   /**
   * a 2D array of JPanels for the grid pattern of tiles 
   */
   private JPanel[][] panels = null;
   
   /**
   * A boolean containing whether the music play is completed even though the music doesn't work 0_0
   */
   
   
    
      /**
      * @param args is the arguments for main method
      * Main method, creates new game with new frame 
      */
   public static void main(String[] args) { 
      SwingUtilities.invokeLater(
         new Runnable() {
            public void run() {
               Game g = new Game();
               g.addTiles();
               g.startGame();
               
               JFrame frame = new JFrame("Checkers");
               CheckersRunnable content = new CheckersRunnable(g);
               frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
               frame.getContentPane().add(new CheckersRunnable(g));
               screensize = Toolkit.getDefaultToolkit().getScreenSize();
               frame.setLocation((screensize.width - frame.getWidth())/2,(screensize.height - frame.getHeight())/2);
               frame.pack();
               frame.setLocationRelativeTo(null); // centers frame
               frame.setSize(screensize);
               frame.setVisible(true);
               
            
            }
         });
         
   } 
   
   
  /** 
  * @param Game g is the current game
  * CheckersRunnable sets up the grid layout for the checkers game using a 2D array of JPanels
  */
   public CheckersRunnable(Game g) {
      pink = new Color(250, 212, 245);
      offWhite = new Color(255, 247, 230);
      panels = new JPanel[8][8];
      setLayout(new GridLayout(8,8));
      setBackground(Color.WHITE);
      for (int r = 0; r < 8; r++) {
         for (int c = 0; c < 8; c++) { 
            JPanel panel = new JPanel();
            panels[r][c] = panel;
            String name = r + ", " + c;
            panel.setName(name);
            
            if (r%2 == c%2) { 
               panel.setBackground(pink);
               panel.add(new JLabel(new InvisibleIcon()));
               
               
            } 
            else { 
               panel.setBackground(offWhite);
               panel.add(new JLabel(new InvisibleIcon()));
               
            } 
            if (g.getTile(r,c).isOccupied()) {
             
               if(g.getTile(r,c).getOccupant().getPlayer().getName().equals("p1")) { 
                  // find a way to make this its own method, too repetitive 
                  panel.removeAll();
                  panel.add(new JLabel(new P1PieceIcon()));
                  panel.revalidate();
                  panel.repaint();
               } 
               else if(g.getTile(r,c).getOccupant().getPlayer().getName().equals("p2")) {
                  panel.removeAll();
                  panel.add(new JLabel(new P2PieceIcon()));
                  panel.revalidate();
                  panel.repaint();
               } 
            } 
            panel.setPreferredSize(new Dimension(60,60));
            
            add(panel);
         } 
      } 
      
      /**
      * adds a mouse listener
      */
      addMouseListener(
         new MouseAdapter() {
            
            @Override
            /**
            * @param MouseEvent e which is a mouse event 
            * operates action when mouse pressed 
            */
            public void mousePressed(MouseEvent e) {
               
             
               JPanel panel = (JPanel) getComponentAt(e.getPoint());
               if (panel == null || panel == CheckersRunnable.this) {
                  return;
               }
               // if there is a panel that was previously selected 
               if (selectedPanel != null) {
                  
                  
                  int row =  (selectedPanel.getY()) / selectedPanel.getHeight();
                  int col =  (selectedPanel.getX()) / selectedPanel.getWidth();
                 
                  
                  resetFrame(g);
                                
                      
                  selectedPanel.add(new JLabel(new InvisibleIcon()));
                  selectedPanel.revalidate();
                  selectedPanel.repaint();
                      
               } 
                  
               selectedPanel = panel;
            
               
               int row =  (selectedPanel.getY()) / selectedPanel.getHeight();
               int col =  (selectedPanel.getX()) / selectedPanel.getWidth();
               
               if (g.getTile(row,col).isHighlighted()) { 
                 
                  g.executeMove(fromRow,row,fromCol,col);
                  
                  int capRow = (fromRow + row)/2;
                  int capCol = (fromCol + col)/2;
                
                  if (g.getTile(capRow,capCol).isCaptured()) { 
                      
                     g.capturePiece(capRow, capCol, g.getTile(capRow,capCol).getOccupant().getPlayer());
                     panels[capRow][capCol].removeAll();
                     panels[capRow][capCol].add(new JLabel(new InvisibleIcon()));
                     panels[capRow][capCol].revalidate();
                     panels[capRow][capCol].repaint();
                     
                     
                  } 
                  
                  resetFrame(g);
                  selectedPanel.revalidate();
                  selectedPanel.repaint();
                  clearHighlights(g);
                  clearCaptures(g);
                                    
                  
               } 
                
               else { 
                  if(row % 2 == 0 && col % 2 == 0) { 
                     selectedPanel.setBackground(new Color(156, 121, 151));
                     
                  }
                  else {
                     selectedPanel.setBackground(new Color(186, 181, 169));
                    
                  } 
                  selectedPanel.revalidate();
                  selectedPanel.repaint();
                  
                  if (g.getTile(row,col).isOccupied()) { 
                     findMoves(g, row,col); 
                     
                     if(findMoves(g,row,col)) { 
                        findMoves(g,row,col);
                     }
                     
                     
                  }
               } 
            }
         });
   
   
      
   }
   
   /**
   * @param Game g is current game
   * Clears highlighted tiles
   */
   public void clearHighlights(Game g) {
      for (int r = 0; r < 8; r++) { 
         for (int c = 0; c < 8; c++) { 
            g.getTile(r,c).setIsHighlighted(false);
         }
      } 
   } 
   
   /**
   * @param Game g which is the game
   * @param int row which is the row of highlighted tile
   * @param int col which is the column of highlighted tile
   * Highlights specified tile to signify it is an available move
   */
   public void highlight(Game g, int row, int col) {
      panels[row][col].setBackground(new Color(250, 202, 90));
      panels[row][col].revalidate();
      panels[row][col].repaint();
      g.getTile(row,col).setIsHighlighted(true);
   } 
   
   /**
   * @param Game g which is the current game
   * @param int row which is the row to be captured
   * @param int col which is the column to be captured
   * Captures a piece 
   */
   public void capture(Game g, int row, int col) { 
      g.getTile(row,col).setIsCaptured(true);
      
      
   } 
   
   /**
   * @param Game g which is the current game
   * Clears captures 
   */
   public void clearCaptures(Game g) {
      for (int r = 0; r < 8; r++) { 
         for (int c = 0; c < 8; c++) { 
            g.getTile(r,c).setIsCaptured(false);
         }
      }
   } 
   
   /**
   * @param Game g
   * Resets the game frame 
   */
   public void resetFrame(Game g) {
      p1Occ = 0;
      p2Occ = 0;
      for (int r = 0; r < 8; r++) { 
         for (int c = 0; c < 8; c++) { 
            
            if (r%2 == c%2) { 
               panels[r][c].removeAll();
               panels[r][c].setBackground(new Color(250, 212, 245));
               panels[r][c].add(new JLabel(new InvisibleIcon()));
               panels[r][c].revalidate();
               panels[r][c].repaint();
               
              
               
            } 
            else { 
               panels[r][c].removeAll();
               panels[r][c].setBackground(new Color(255, 247, 230));
               panels[r][c].add(new JLabel(new InvisibleIcon()));
               panels[r][c].revalidate();
               panels[r][c].repaint();
              
            
            } 
            if (g.getTile(r,c).isOccupied()) { 
               if(g.getTile(r,c).getOccupant().getPlayer().getName().equals("p1")) { 
                  p1Occ++;
                  panels[r][c].removeAll();
                  panels[r][c].add(new JLabel(new P1PieceIcon()));
                  if (g.getTile(r,c).getOccupant().isKinged()) { 
                     panels[r][c].removeAll();
                     panels[r][c].add(new JLabel(new P1PieceIconK()));
                  } 
                  panels[r][c].revalidate();
                  panels[r][c].repaint();
                  
               } 
               else if(g.getTile(r,c).getOccupant().getPlayer().getName().equals("p2")) {
                  p2Occ++;
                  panels[r][c].removeAll();
                  panels[r][c].add(new JLabel(new P2PieceIcon()));
                  if (g.getTile(r,c).getOccupant().isKinged()) { 
                     panels[r][c].removeAll();
                     panels[r][c].add(new JLabel(new P2PieceIconK()));
                  } 
                  panels[r][c].revalidate();
                  panels[r][c].repaint();
               } 
            } 
         }
      }
      
      if (p1Occ == 0 ) {
         System.out.println("P2 Wins");  
         System.exit(1);
      } 
      if (p2Occ == 0) { 
         System.out.println("P1 Wins");
         System.exit(1);
      } 
   }  
   
   /**
   * @param Game g which is the game
   * @param int row which is the row of the selected piece to calculate moves of
   * @param int col which is the column of the selected piece to calculate moves of
   * Finds the available moves 
   * (Don't question the number of if statements I used just go with it)
   */      
   public boolean findMoves(Game g, int row, int col) {
      boolean captured = false;
      fromRow = row;
      fromCol = col;
      
      
      
      if(g.getTurn() == true && g.getTile(row,col).getOccupant().getDirection() == -1 || g.getTile(row,col).getOccupant().getDirection() == 0) { 
            
         if (col > 0 && row < 7 && g.getTile(row,col).getOccupant().canMoveTo(g.getTile(row+1, col-1))) { 
            highlight(g,row+1,col-1);
               
         } 
         else if (row < 7 && col > 1 && g.getTile(row,col).getOccupant().canMoveTo(g.getTile(row+2, col-2)) && g.getTile(row,col).getOccupant().canCapture(g.getTile(row+1,col-1).getOccupant())) {
            highlight(g,row+2,col-2);
            capture(g,row+1,col-1);
            captured = true;
            if(row < 4 && col > 3 && g.getTile(row,col).getOccupant().canMoveTo(g.getTile(row+4, col - 4)) && g.getTile(row,col).getOccupant().canCapture(g.getTile(row+3,col-3).getOccupant())) { 
               highlight(g, row+4, col-4);
               capture(g,row+3,col-3);
               captured = true;
            } 
            if(row < 7 && col > 3 && g.getTile(row,col).getOccupant().canMoveTo(g.getTile(row, col - 4)) && g.getTile(row,col).getOccupant().canCapture(g.getTile(row+1,col-3).getOccupant())) { 
               highlight(g, row, col-4); 
               capture(g,row+1,col-3);
               captured = true; 
            } 
            if(row < 4 && col > 0 && g.getTile(row,col).getOccupant().canMoveTo(g.getTile(row+4,col)) && g.getTile(row,col).getOccupant().canCapture(g.getTile(row+3,col-1).getOccupant())) { 
               highlight(g,row+4,col);
               capture(g,row+3,col-1);
               captured = true;
            } 
               
               
         } 
         if (row < 7 && col < 7 && g.getTile(row,col).getOccupant().canMoveTo(g.getTile(row+1, col+1))) { 
            highlight(g,row+1,col+1);
               
         } 
         else if (row < 7 && col < 7 && g.getTile(row,col).getOccupant().canMoveTo(g.getTile(row+2, col+2)) && g.getTile(row,col).getOccupant().canCapture(g.getTile(row+1,col+1).getOccupant())) {
            highlight(g,row+2,col+2);
            capture(g,row+1,col+1);
            captured = true;
            if(row < 4 && col < 4 && g.getTile(row,col).getOccupant().canMoveTo(g.getTile(row+4, col + 4)) && g.getTile(row,col).getOccupant().canCapture(g.getTile(row+3,col+3).getOccupant())) { 
               highlight(g, row+4, col+4);
               capture(g,row+3,col+3);
               captured = true;
            } 
            if(row < 7 && col < 4 && g.getTile(row,col).getOccupant().canMoveTo(g.getTile(row, col + 4)) && g.getTile(row,col).getOccupant().canCapture(g.getTile(row+1,col+3).getOccupant())) { 
               highlight(g, row, col+4); 
               capture(g,row+1,col+3);
               captured = true; 
            } 
            if(row < 4 && col < 7 && g.getTile(row,col).getOccupant().canMoveTo(g.getTile(row+4,col)) && g.getTile(row,col).getOccupant().canCapture(g.getTile(row+3,col+1).getOccupant())) { 
               highlight(g,row+4,col);
               capture(g,row+3,col+1);
               captured = true;
            }
               
               
         } 
           
      } 
      
      if(g.getTurn() == false &&  g.getTile(row,col).getOccupant().getDirection() == 1 || g.getTile(row,col).getOccupant().getDirection() == 0) { 
         if (row > 0 && col > 0 && g.getTile(row,col).getOccupant().canMoveTo(g.getTile(row-1, col-1))) { 
            highlight(g,row-1,col-1);
               
         } 
         else if (row > 1 && col > 1 && g.getTile(row,col).getOccupant().canMoveTo(g.getTile(row-2, col-2)) && g.getTile(row,col).getOccupant().canCapture(g.getTile(row-1,col-1).getOccupant())) {
            highlight(g,row-2,col-2);
            capture(g,row-1,col-1);
            captured = true;
            if(row > 3 && col > 3 && g.getTile(row,col).getOccupant().canMoveTo(g.getTile(row-4, col - 4)) && g.getTile(row,col).getOccupant().canCapture(g.getTile(row-3,col-3).getOccupant())) { 
               highlight(g, row-4, col-4);
               capture(g,row-3,col-3);
               captured = true;
            } 
            if(row < 7 && col > 3 && g.getTile(row,col).getOccupant().canMoveTo(g.getTile(row, col - 4)) && g.getTile(row,col).getOccupant().canCapture(g.getTile(row+1,col-3).getOccupant())) { 
               highlight(g, row, col-4); 
               capture(g,row+1,col-3);
               captured = true; 
            } 
            if(row < 4 && col > 0 && g.getTile(row,col).getOccupant().canMoveTo(g.getTile(row+4,col)) && g.getTile(row,col).getOccupant().canCapture(g.getTile(row+3,col-1).getOccupant())) { 
               highlight(g,row+4,col);
               capture(g,row+3,col-1);
               captured = true;
            }
               
         } 
         if (row > 0 && col < 7 && g.getTile(row,col).getOccupant().canMoveTo(g.getTile(row-1, col+1))) { 
            highlight(g,row-1,col+1);
               
         } 
         else if (row > 1 && col < 6 && g.getTile(row,col).getOccupant().canMoveTo(g.getTile(row-2, col+2)) && g.getTile(row,col).getOccupant().canCapture(g.getTile(row-1,col+1).getOccupant())) {
            highlight(g,row-2,col+2);
            capture(g,row-1,col+1);
            captured = true;
            if(row > 3 && col < 4 && g.getTile(row,col).getOccupant().canMoveTo(g.getTile(row-4, col +4)) && g.getTile(row,col).getOccupant().canCapture(g.getTile(row-3,col+3).getOccupant())) { 
               highlight(g, row-4, col+4);
               capture(g,row+3,col-3);
               captured = true;
            } 
            if(row > 0 && col < 4 && g.getTile(row,col).getOccupant().canMoveTo(g.getTile(row, col + 4)) && g.getTile(row,col).getOccupant().canCapture(g.getTile(row-1,col+3).getOccupant())) { 
               highlight(g, row, col+4); 
               capture(g,row-1,col+3);
               captured = true; 
            } 
            if(row > 3 && col < 7 && g.getTile(row,col).getOccupant().canMoveTo(g.getTile(row-4,col)) && g.getTile(row,col).getOccupant().canCapture(g.getTile(row-3,col+1).getOccupant())) { 
               highlight(g,row-4,col);
               capture(g,row-3,col+1);
               captured = true;
            }
                
         } 
         
      }   
      return captured;  
     
   } 
        
   
     

    
      
}