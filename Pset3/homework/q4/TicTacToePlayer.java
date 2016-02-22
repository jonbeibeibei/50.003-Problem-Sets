import java.io.*;
import java.net.*;

class TicTacToePlayer{

  private static final int PORTNUM = 4321;
  private static final String HOSTNAME = "localhost";
  private static String token;

  public static void main(String[] args){

    Socket clientSocket = null;
    PrintWriter out = null;
    BufferedReader in = null;
    BufferedReader stdIn = null;

    try{
      clientSocket = new Socket(HOSTNAME, PORTNUM);
      out = new PrintWriter(clientSocket.
          getOutputStream(), true);
      in = new BufferedReader(new InputStreamReader(
            clientSocket.getInputStream()
            ));
      stdIn = new BufferedReader(new InputStreamReader(
            System.in
            ));

      String tokenLine = in.readLine();
      System.out.println(tokenLine);
      if(tokenLine.equals("You are Player X")){
        token = "X";
      }else if(tokenLine.equals("You are Player O")){
        token = "O";
      }

      while(true){
        String line = in.readLine();
        System.out.println(line);

        (new Thread(new InputRunnable(stdIn,out))).start();
      }

    } catch (IOException e){
      e.printStackTrace();
      return;
    }
  }

  public static class InputRunnable implements Runnable{

    private BufferedReader stdIn;
    private PrintWriter out;

    public InputRunnable(BufferedReader stdIn, PrintWriter out){
      this.stdIn = stdIn;
      this.out = out;
    }

    public void run(){
      try{
        out.println(stdIn.readLine());
      }catch(IOException e){
        e.printStackTrace();
      }
    }
  }
}
