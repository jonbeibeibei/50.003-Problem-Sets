import java.io.*;
import java.net.*;

class ChatServer{

  public static void main (String[] args) throws Exception{

    ServerSocket serverSocket = new ServerSocket(4321);

    int numClients = 5;
    Socket[] clientSockets = new Socket[numClients];

    for(int i =0; i <numClients; i++)
    {
      System.out.println("... expecting connections ...");
      clientSockets[i] = serverSocket.accept();
      PrintWriter out = new PrintWriter(clientSockets[i].
          getOutputStream(), true);
      BufferedReader in = new BufferedReader(
          new InputStreamReader(clientSockets[i].
          getInputStream()));
      BufferedReader stdIn = new BufferedReader(
          new InputStreamReader(System.in)
          );

      String inputLine;
      System.out.println("Opening IO");

      while(!(inputLine = in.readLine()).equals("Bye"))
      {
        System.out.println("Client says " + inputLine);
        out.println(stdIn.readLine());
        out.flush();
      }

      //clientSockets[i].close();
      //out.close();
      //in.close();
      //stdIn.close();

    }
  }
}
