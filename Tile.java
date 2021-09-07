import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
* Tile class
*/
public  class Tile {
   /**
   * Row of tile
   */
   private int row;
   /**
   * Column of tile
   */
   private int col;
   /**
   * Occupant of tile
   */
   private Piece occupant;
   /**
   * Button that corresponds to tile
   */
   private JButton button;
   /**
   * Whether the tile is highlighted
   */
   private boolean isHighlighted;
   /**
   * Whether the tile is captured
   */
   private boolean isCaptured;
   
   /**
   * @param int row which is the row of tile 
   * @param int col which is the column of tile
   * Tile constructor
   */
   public Tile(int row, int col) {
      this.row = row;
      this.col = col;
   }  
   
   /**
   * @return Piece which is the occupant of the tile
   * Returns tile occupant
   */
   public Piece getOccupant() {
      return occupant;
   } 
   
   /**
   * @param boolean isHighlighted which is whether tile should be highlighted
   * Sets the highlight of the tile
   */
   public void setIsHighlighted(boolean isHighlighted) {
      this.isHighlighted = isHighlighted;
   } 
   
   /**
   * @return boolean which is whether tile is highlighted
   * Checks whether tile is highlighted
   */
   public boolean isHighlighted() {
      return isHighlighted;
   } 
   
   /**
   * @param boolean isCaptured which is whether Tile is captured
   * Sets capture to intended setting
   */
   public void setIsCaptured(boolean isCaptured) { 
      this.isCaptured = isCaptured;
   }
   
   /**
   * @return boolean whether Tile is captured
   * Checks whether tile is captured
   */
   public boolean isCaptured() { 
      return isCaptured;
   } 
   
   /**
   * @param newOccupant which is occupant to set to tile
   * Sets occupant of tile to specified piece
   */
   public void setOccupant(Piece newOccupant) {
      occupant = newOccupant;
   } 
   
   /**
   * @param JButton button which is button to be added
   * Adds a JButton for specified tile
   */
   public void addButton(JButton button) {
      this.button = button;
   } 
   
   /**
   * @return boolean which is whether tile is occupied
   * Checks whether tile is occupied
   */
   public boolean isOccupied() {
      if(occupant != null) {
         return true;
      } 
      return false;
   } 
   public int getRow() {
      return row;
   } 
   public int getCol() {
      return col;
   } 
}