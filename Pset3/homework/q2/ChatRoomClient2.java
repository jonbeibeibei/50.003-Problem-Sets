import java.io.*;
import java.net.*;

class ChatRoomClient2{

  private static final String HOSTNAME = "localhost";
  private static final int PORTNUM = 4321;

  public static void main(String[] args){

    Socket clientSocket = null;
    PrintWriter out = null;
    BufferedReader stdIn = null;

    try{
      clientSocket = new Socket(HOSTNAME,PORTNUM);
      out = new PrintWriter(clientSocket.
        getOutputStream(),true);
      stdIn = new BufferedReader(
        new InputStreamReader(System.in)
        );
    } catch (IOException e){
      e.printStackTrace();
    }

    String userInput;
    try{
      while(!(userInput = stdIn.readLine()).equals(""))
      {
        out.println(userInput);
        out.flush();
      }

      stdIn.close();
      out.close();
      clientSocket.close();
    }catch( IOException e){
      e.printStackTrace();
    }

  }
}
