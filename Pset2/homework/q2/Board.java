import java.util.*;

public class Board{

  private static Board instance;

  private String[][] board;

  private Board(){
    board = new String[3][3];
    reset();
  }

  public static synchronized Board getInstance(){
    if(instance == null)
      instance = new Board();
    return instance;
  }

  public String toString(){
    String retString = "";
    retString += "\n-----------\n";
    for(String[] row: board){
      for(String square: row){
        retString += square +"|";
      }
      retString = retString.substring(0, retString.length() -1);

      retString += "\n-----------\n";
    }

    return retString;
  }

  public void reset(){
    for(int i =0; i <3; i++){
      for(int j =0 ; j<3; j++){
        board[i][j] = "   ";
      }
    }
  }

  public boolean setPiece(String token, int row, int col){
    if (board[row][col].equals("   ")){
      board[row][col] = " " + token + " ";
      return true;
    }

    return false;
  }

  public String checkWin(){
    //check rows
    for(int row = 0; row < 3; row++){
      boolean winX = true;
      boolean winO = true;
      for(int col = 0; col < 3; col++){
        if(board[row][col].equals(" X ")){
          winO = false;
        }
        else if(board[row][col].equals(" O ")){
          winX = false;
        }
        else{
          winX = false;
          winO = false;
        }
      }


      if(winO)
        return "O";
      else if(winX)
        return "X";
    }

    //check columns
    for(int col = 0; col < 3; col++){
      boolean winX = true;
      boolean winO = true;
      for(int row = 0; row < 3; row++){
        if(board[row][col].equals(" X ")){
          winO = false;
        }
        else if (board[row][col].equals(" O ")){
          winX = false;
        }
        else{
          winX = false;
          winO = false;
        }
      }

      if(winO)
        return "O";
      else if(winX)
        return "X";
    }

    //check diagonals
    boolean winX = true;
    boolean winO = true;
    for(int i = 0; i< 3; i++){
      if(board[i][i].equals(" X "))
        winO = false;
      else if(board[i][i].equals(" X "))
        winX = false;
      else{
        winO = false;
        winX = false;
      }
    }
    if(winO)
      return "O";
    else if(winX)
      return "X";

    winX = true;
    winO = true;

    for(int i =0; i< 3; i++){
      if(board[i][2-i].equals(" X "))
        winO = false;
      else if (board[i][2-i].equals(" O "))
        winX = false;
      else{
        winO = false;
        winX = false;
      }
    }

    if(winO)
      return "O";
    else if(winX)
      return "X";
    else
      return null;
  }

  public String checkEnd(){
    String win = checkWin();
    if(win != null)
    {
      return win;
    }

    for(String[] row: board)
    {
      for(String val: row){
        if(val=="   ")
          return null;
      }
    }

    return "tie";

  }

}
