class Main{

  private static  Board board = Board.getInstance();
  private static IO io = IO.getInstance();

  private static Player playerX = new Player("X");
  private static Player playerO = new Player("O");

  public static void main(String[] args){

    boolean playing = true;
    boolean turn = true;
    String yes = "y";
    String no = "n";

    io.printOutput("Let the game begin!", true);
    while(playing){

      while(true){
        turn = playTurn(playerX);
        if(!turn)
          break;
        turn = playTurn(playerO);
        if(!turn)
          break;
      }

      if(yes.contains(io.getConfirmation().toLowerCase())){
        io.printOutput("Let the game begin!", true);
        board.reset();
      }
      else{
        io.printOutput("Hope you had fun!");
        io.close();
        playing = false;
      }

    }

  }

  private static boolean playTurn(Player player){
    boolean valid = false;
    io.printOutput(player.toString() + "'s turn", true);
    while(true){
      try{
        while(!valid)
        {
          int row = io.getRow();
          int col = io.getCol();
          valid = player.playPiece(board, row, col);
          if(!valid)
            io.printOutput("That space is already occupied!", true); }
        break;
      }
      catch(ArrayIndexOutOfBoundsException e){
        io.printOutput("Those coordinates are out of range!", true);
      }
    }

    //print board
    io.printOutput(board.toString());
    //check end game
    String end = board.checkEnd();
    if(end != null){
      if(end == "tie")
        io.printOutput("It's a tie!",true);
      else
        io.printOutput("Player " + end + " wins!",true);
      return false;
    }

    return true;
  }
}
