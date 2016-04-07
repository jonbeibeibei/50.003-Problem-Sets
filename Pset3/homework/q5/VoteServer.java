import java.io.*;
import java.net.*;

class VoteServer{

  public static final int NUM_ELECTORATES = 5;
  public static final int PORTNUM = 4321;
  public static final String hostname = "localhost";

  public static void main(String[] args){

    ServerSocket serverSocket = null;
    Socket[] clientSockets = new Socket[NUM_ELECTORATES];
    BufferedReader[] ins = new BufferedReader[NUM_ELECTORATES];
    PrintWriter[] outs = new PrintWriter[NUM_ELECTORATES];

    try{
      serverSocket = new ServerSocket(PORTNUM);
      for(int i = 0; i < NUM_ELECTORATES; i++){
        System.out.println("... expecting connections ...");
        Socket clientSocket = serverSocket.accept();
        clientSockets[i] = clientSocket;
        ins[i] = new BufferedReader(new InputStreamReader(
              clientSocket.getInputStream()
              ));
        outs[i] = new PrintWriter(clientSocket.getOutputStream(), true);
      }

    String vote;
    for(int i =0; i <NUM_ELECTORATES; i++){
      vote = ins[i].readLine();
      System.out.println("Vote is " + vote);
      if(vote.equals("A")){
        for(int j =0; j< NUM_ELECTORATES; j++ ){
          outs[j].println("A");
        }
      }else if (vote.equals("B")){
        for(int j = 0; j < NUM_ELECTORATES; j++){
          outs[j].println("B");
        }
      }
    }

    }
    catch(IOException e){
      e.printStackTrace();
    }
  }
}
