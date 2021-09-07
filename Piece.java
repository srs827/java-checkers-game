/**
* Piece class
*/
public class Piece {
   /**
   * Player of piece
   */
   private Player player;
   /**
   * Tile of piece
   */
   private Tile tile;
   /**
   * Direction of piece (-1, 0, or 1) 
   */
   private int direction;
   /**
   * Boolean whether piece is kinged
   */
   private boolean isKing;
   
   /**
   * @param Player player which is the player of the piece
   * @param Tile tile which is the tile of the piece
   * @param int direction which is the direction of the piece
   * @param boolean isKing which is whether the piece is kinged
   * Piece constructor
   */
   public Piece(Player player, Tile tile, int direction, boolean isKing) { 
      this.player = player;
      this.tile = tile;
      this.direction = direction;
      this.isKing = isKing;
      
   } 
   
   /**
   * @param Piece other which is the piece to be captured
   * @return boolean which is whether piece can be captured
   * Checks whether other piece can be captured by this piece 
   */
   public boolean canCapture(Piece other) {
     
      if (other.getPlayer().equals(getPlayer())) {
         return false;
      }
      return true;
   } 
   
   /*
   * @param Tile tile which is the tile to check if move is possible there
   * @return boolean if piece can move to specified tile
   * Checks if a piece can move to specified tile 
   */
   public boolean canMoveTo(Tile tile) {
      if(tile.isOccupied()) { 
         return false;
      } 
      return true;
      
   } 
   
   /**
   * @return int which is the direction
   * Gets piece direction
   */
   public int getDirection() { 
      return direction;
   } 
   
   /**
   * @param int direction which is intended direction
   * Sets piece direction 
   */
   public void setDirection(int direction) { 
      this.direction = direction;
   } 
   
   /**
   * @return Player which is the player of the piece
   * Gets piece's player
   */
   public Player getPlayer() {
      return player;
   } 
   
   /**
   * @return boolean which is whether piece is king
   * Checks whether piece is king 
   */
   public boolean isKinged() {
      if (isKing == true) {
         
         return true;
      } 
      return false;
   } 
   
   /**
   * Sets king to true
   */
   public void setKing() {
      isKing = true;
      direction = 0;
   } 
}