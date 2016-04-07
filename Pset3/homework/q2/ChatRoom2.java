import java.io.*;
import java.net.*;
import java.util.*;

class ChatRoom2{

  static final int PORTNUM = 4321;

  public static void main(String[] args){

    ServerSocket serverSocket  = null;

    try{
      serverSocket = new ServerSocket(PORTNUM);
      serverSocket.setSoTimeout(10000);
    } catch (IOException e){
      e.printStackTrace();
    }

    List<Socket> clientSockets = new ArrayList<Socket>();
    List<BufferedReader> ins = new ArrayList<BufferedReader>();

    while(true){
      try{
        System.out.println("... expecting connections ...");
        Socket clientSocket = serverSocket.accept();
        clientSockets.add(clientSocket);
        ins.add(new BufferedReader(
              new InputStreamReader(clientSocket.getInputStream())
              ));
      } catch(java.net.SocketTimeoutException e){
        System.out.println("Timed out");
        break;
      } catch(IOException e){
        e.printStackTrace();
      }

    }

    for(int i = 0; i < clientSockets.size(); i++ ){
      System.out.println("Client " + i + "'s turn");
      (new Thread(new ServerRunnable(clientSockets.get(i)))).start();
      try{
        while(true){
          String msg = ins.get(i).readLine();
          if(msg != null)
            System.out.println(msg);
        }
      }catch(IOException e){
        if(e instanceof SocketException)
          System.out.println("Closing chat room");
        else
          e.printStackTrace();
      }
    }

  }

  public static class ServerRunnable implements Runnable{

    private Socket clientSocket;

    public ServerRunnable(Socket clientSocket){
      this.clientSocket = clientSocket;
    }

    public void run(){
      long startTime = System.currentTimeMillis();
      long currentTime = startTime;
      while(currentTime - startTime < 5000){
        currentTime = System.currentTimeMillis();
      }
      System.out.println("Closing connection");
      try{
        clientSocket.close();
      }catch(IOException e){
        e.printStackTrace();
      }
    }
  }

}
