import java.util.ArrayList;

/**
* Game class
*/ 
public class Game {

   /**
   * 2D array of game tiles
   */
   private Tile[][] tiles;
   
   /**
   * first player
   */
   private Player p1;
   
   /**
   * second player
   */
   private Player p2;
   
   /**
   * number of players in game
   */
   private int numPlayers = 0;
   
   /**
   * boolean that states whether it is p1's turn
   */
   private boolean p1Turn;
   
   /**
   * boolean that states whether the game is in progress 
   */
   private boolean isInProgress;
   
   /**
   * Game constructor 
   */ 
   public Game() {
      tiles = new Tile[8][8];
      p1 = new HumanPlayer("p1");
      p2 = new HumanPlayer("p2");
      isInProgress = true;
   } 
   
   /**
   * @return boolean whether game is in progress
   * checks if game is in progress
   */
   public boolean inProgress() {
      return isInProgress;
   } 
   
   /**
   * @param newPlayer is a new player to be added
   * adds player
   */
   public void addPlayer(Player newPlayer) { 
   
      if (numPlayers == 0) { 
         p1.setGame(this);
      } 
      else if (numPlayers == 1) {
         p2.setGame(this);
      } 
      
   } 
   
   /**
   * adds a new tile
   */
   public void addTiles() {
      for (int r = 0; r < 8; r++) {
         for (int c = 0; c < 8; c++) {
            tiles[r][c] = new Tile(r,c);
         }
      }
   } 
   
   /**
   * @param int row which is the row of the piece to be captured
   * @param int col which is the column of the piece to be captured
   * @param Player capturee which is the player of the captured piece 
   * captures a piece
   */
   public void capturePiece(int row, int col, Player capturee) { 
      
      capturee.removePiece(tiles[row][col].getOccupant());
      tiles[row][col].setOccupant(null);
   } 
   
   /**
   * @param int row which is the row of the tile
   * @param int col which is the column of the tile
   * gets a tile based on row and column 
   */
   public Tile getTile(int row, int col) { 
      return tiles[row][col];
   } 
   
   /**
   * @param int fromRow which is the row before move
   * @param int toRow which is the row after move
   * @param int fromCol which is the column before move
   * @param int toCol which is the column after move 
   * Executes a move where a piece moves from one tile to another
   */
   public void executeMove(int fromRow, int toRow, int fromCol, int toCol) {
     
      Piece p = new Piece(tiles[fromRow][fromCol].getOccupant().getPlayer(),tiles[toRow][toCol],tiles[fromRow][fromCol].getOccupant().getDirection(), tiles[fromRow][fromCol].getOccupant().isKinged());
      tiles[toRow][toCol].setOccupant(p);
      tiles[fromRow][fromCol].setOccupant(null);
      
      if(tiles[toRow][toCol].getOccupant().getPlayer().getName().equals("p1") && toRow == 7 ||tiles[toRow][toCol].getOccupant().getPlayer().getName().equals("p2") && toRow == 0 ) { 
         tiles[toRow][toCol].getOccupant().setKing();
         tiles[toRow][toCol].getOccupant().setDirection(0);
       
      } 
      
      switchTurns();
      
   } 
   
   /**
   * @param int fromRow which is the first row to check
   * @param int toRow which is the second row to check
   * @param int fromCol which is the first column to check
   * @param int toCol which is the second column to check
   * Checks whether a move is legal based on if intended tile to move to is occupied and the current tile is occupied
   */ 
   public boolean isLegalMove(int fromRow, int toRow, int fromCol, int toCol) {
      if(!(getTile(fromRow,fromCol).isOccupied())) {
         return false;
      }
      else if(getTile(toRow,toCol).isOccupied()) {
         return false;
      } 
      
      return true;
   } 
   
   /**
   * Starts a new game with p1, p2, and a set of pieces for each player
   */
   public void startGame() {
      p1 = new HumanPlayer("p1");
      p2 = new HumanPlayer("p2");
      p1Turn = true; 
      ArrayList<Piece> p1Pieces = p1.getPieces();
      ArrayList<Piece> p2Pieces = p2.getPieces();
      p1Pieces.clear();
      p2Pieces.clear();
      isInProgress = true;
      for (int r = 0; r < 3; r++) {
         for (int c = 0; c < 8; c++) { 
            if (r % 2 == c % 2) { 
               p1Pieces.add(new Piece(p1, tiles[r][c], -1, false));
               tiles[r][c].setOccupant(p1Pieces.get(p1Pieces.size() -1));
            }
         }
      } 
      for (int r = tiles.length - 1; r > tiles.length - 4; r--) {
         for (int c = 0; c < 8; c++) {
            if (r % 2 == c % 2) { 
               p2Pieces.add(new Piece(p2, tiles[r][c], 1, false)); 
               tiles[r][c].setOccupant(p2Pieces.get(p2Pieces.size() -1));
            }
         }
      } 
     
     
   } 
   
   /**
   * Switches turn from one player to other
   */
   public void switchTurns() {
      if(p1Turn) { 
         p1Turn = false;
      } 
      else { 
         p1Turn = true;
      } 
   } 
   
   /**
   * @return boolean if it is player 1's turn or not
   * Gets player turn 
   */
   public boolean getTurn() { 
      return p1Turn; 
   } 
}