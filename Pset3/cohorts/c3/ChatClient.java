import java.io.*;
import java.net.*;

class ChatClient{
  public static void main(String[] args) throws Exception{

    String hostname = "localhost";
    int portNumber = 4321;

    Socket socket = new Socket(hostname, portNumber);
    PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
    BufferedReader in = new BufferedReader(
        new InputStreamReader(socket.getInputStream())
        );
    BufferedReader stdIn = new BufferedReader(
        new InputStreamReader(System.in)
        );

    String userInput;
    do{
      userInput = stdIn.readLine();
      out.println(userInput);
      out.flush();
      System.out.println("Server says " + in.readLine());
    }while(!userInput.equals("Bye"));

    in.close();
    out.close();
    stdIn.close();

  }
}
