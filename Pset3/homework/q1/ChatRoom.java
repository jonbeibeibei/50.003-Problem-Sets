import java.io.*;
import java.net.*;

class ChatRoom{

  static final int PORTNUM = 4321;

  public static void main(String[] args){

    ServerSocket serverSocket  = null;
    Socket clientSocket = null;

    try{
      serverSocket = new ServerSocket(PORTNUM);
    } catch (IOException e){
      e.printStackTrace();
    }

    while(true){
      try{
        System.out.println("... expecting connections ...");
        clientSocket = serverSocket.accept();
      } catch(IOException e){
        e.printStackTrace();
      }

      (new Thread(new ServerRunnable(clientSocket))).start();
    }
  }

  public static class ServerRunnable implements Runnable{

    private Socket clientSocket;

    public ServerRunnable(Socket clientSocket){
      this.clientSocket = clientSocket;
    }

    public void run(){
      System.out.println("Running in thread");
      BufferedReader in = null;

      try{
        in = new BufferedReader(new InputStreamReader(
              clientSocket.getInputStream()
              ));
      } catch (IOException e){
        System.out.println("buffreader error");
        e.printStackTrace();
        return;
      }

      String inputLine;

      try{
        while(true){
          inputLine = in.readLine();
          if(inputLine == null)
            break;
          else
            System.out.println(inputLine);
        }
      } catch (IOException e){
        System.out.println("input line error");
        e.printStackTrace();
      }
    }
  }
}
