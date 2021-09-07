import java.util.ArrayList;
import java.awt.*;

/**
* Player class 
*/
public abstract class Player {
   /**
   * ArrayList of player pieces
   */
   private ArrayList<Piece> pieces;
   /**
   * Current game
   */
   private Game game;
   /**
   * Player direction (at start for pieces)
   */
   private int direction;
   
   /**
   * Name of player
   */
   private String name;
   
   /**
   * @param String name which is player name
   * Player constructor
   */
   public Player(String name) {
      pieces = new ArrayList<Piece>();
      this.name = name;
   } 
   
   /**
   * @param Tile initialPosition which is the tile in which a piece should be added
   * @return Piece which is added piece
   * Adds a piece to specified tile
   */
   public Piece addPiece(Tile initialPosition) {
      Piece piece = new Piece(this, initialPosition, direction, false);
      pieces.add(piece);
      return piece;
   } 
   
   /**
   * @return String which is name of player
   * gets Player name
   */
   public String getName() {
      return name;
   }
   
   /**
   * @return game which is current game
   * Gets current game
   */
   public Game getGame() {
      return new Game();
   } 
   
   /**
   * @param Game game which is the current game
   * Sets the game to intended game
   */
   public void setGame(Game game) {
      this.game = game;
   } 
   
   /**
   * @return ArrayList<Piece> which is the arraylist of player pieces
   * Gets player's list of pieces 
   */
   public ArrayList<Piece> getPieces() {
      return pieces;
   } 
   
   /**
   * @return int which is the number of pieces
   * Gets the player's number of pieces
   */
   public int getNumPieces() {
      int count = 0; 
      for (int i = 0; i < pieces.size(); i++) { 
         if (pieces.get(i) != null) { 
            count++;
         } 
      } 
      return count;
   } 
   
   /**
   * @param Piece remove which is the piece to be removed
   * Removes a piece from player piece list 
   */
   public void removePiece(Piece remove) {
      pieces.remove(remove);
   } 
  
   /**
   * @param int direction which is the direction to be set
   * Sets player direction
   */
   public void setDirection(int direction) {
      this.direction = direction;
   } 
   
   /**
   * @return int which is player direction
   * Gets player direction
   */
   public int getDirection() {
      return direction;
   } 
   
   /**
   * @param piece to be set to king
   * Kings a piece
   */
   public void king(Piece piece) {
      piece.setKing();
   } 
}