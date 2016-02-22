import java.io.*;
import java.net.*;

class TicTacToe{

  private static final int PORTNUM = 4321;

  private static Board board = Board.getInstance();

   public static void main(String[] args){

    IO ioX = null;
    IO ioO = null;

    Player playerX = new Player("X");
    Player playerO = new Player("O");

    ServerSocket serverSocket = null;
    Socket playerXSocket = null;
    Socket playerOSocket = null;

    try{
      //Create sockets
      serverSocket = new ServerSocket(PORTNUM);
      System.out.println("Connecting to player X ...");
      playerXSocket = serverSocket.accept();
      System.out.println("Connecting to player O ...");
      playerOSocket = serverSocket.accept();

      //Get IO streams
      ioX = new IO(playerXSocket.getInputStream(),
          playerXSocket.getOutputStream());
      ioX.printOutput("You are Player X", true);
      ioO = new IO(playerOSocket.getInputStream(),
          playerOSocket.getOutputStream());
      ioO.printOutput("You are Player O", true);

      boolean playing = true;
      boolean turn = true;
      String yes = "y";
      String no = "n";

      ioX.printOutput("Let the game begin!", true);
      ioO.printOutput("Let the game begin!", true);
      while(playing){

        while(true){
          turn = playTurn(playerX, ioX, ioO);
          if(!turn)
            break;
          turn = playTurn(playerO, ioO, ioX);
          if(!turn)
            break;
        }

        if(yes.contains(ioX.getConfirmation().toLowerCase())
            && yes.contains(ioO.getConfirmation().toUpperCase())){
          ioX.printOutput("Let the game begin!", true);
          ioO.printOutput("Let the game begin!", true);
          board.reset();
        }
        else{
          ioX.printOutput("Hope you had fun!");
          ioO.printOutput("Hope you had fun!");
          playing = false;
        }

    }



    } catch(IOException e){
      e.printStackTrace();
    }

  }

  private static boolean playTurn(Player player, IO io, IO ioOther){
    boolean valid = false;
    io.printOutput(player.toString() + "'s turn", true);
    ioOther.printOutput(player.toString() + "'s turn", true);
    while(true){
      try{
        while(!valid)
        {
          System.out.println("Getting input");
          ioOther.printOutput("Getting input from " + player.toString());
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
    ioOther.printOutput(board.toString());
    //check end game
    String end = board.checkEnd();
    if(end != null){
      if(end == "tie"){
        io.printOutput("It's a tie!",true);
        ioOther.printOutput("It's a tie!",true);
      }
      else{
        io.printOutput("Player " + end + " wins!",true);
        ioOther.printOutput("Player " + end + " wins!",true);
      }
      return false;
    }

    return true;
  }
}
