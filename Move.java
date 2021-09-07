/**
* Move class
*/
public class Move {
   
   /**
   * @param Tile t1 which is first tile
   * @param Tile t2 which is second tile to be moved to
   * Move constructor to create a move
   */
   public Move(Tile t1, Tile t2) {
      Piece p = t1.getOccupant();
      
      t2.setOccupant(p);
      t1.setOccupant(null);
    
   } 

} 