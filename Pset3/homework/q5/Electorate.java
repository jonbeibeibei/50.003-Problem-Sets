import java.io.*;
import java.net.*;

class Electorate{

  public static final int PORTNUM = 4321;
  public static final String HOSTNAME = "localhost";

  public static int countA = 0;
  public static int countB = 0;

  public static void main(String[] args){

    Socket clientSocket = null;
    BufferedReader stdIn = null;
    BufferedReader in = null;
    PrintWriter out = null;

    try{
      clientSocket = new Socket(HOSTNAME,PORTNUM);
      stdIn = new BufferedReader(new InputStreamReader(
            System.in
            ));
      in = new BufferedReader(new InputStreamReader(
            clientSocket.getInputStream()
            ));
      out = new PrintWriter(clientSocket.getOutputStream(), true);

      System.out.println("Start voting!");
      String vote = stdIn.readLine();
      while(!vote.equals("A") && !vote.equals("B")){
        System.out.println("Please enter a valid candidate name");
        vote = stdIn.readLine();
      }
      out.println(vote);


      while((vote = in.readLine()) != null){
        System.out.println("Counting votes...");
        if(vote.equals("A"))
          countA++;
        else if(vote.equals("B"))
          countB++;
      }

    }catch (IOException e){
      if( e instanceof SocketException){
        if(countA >countB)
          System.out.println("The winner is A");
        else if (countB >countA)
          System.out.println("The winner is B");
        else
          System.out.println("It's a tie!");

      }else
        e.printStackTrace();
    }




  }

}

