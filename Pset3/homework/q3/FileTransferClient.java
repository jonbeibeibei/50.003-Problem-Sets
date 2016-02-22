import java.io.*;
import java.net.*;

class FileTransferClient{

  public static final int PORTNUM = 4321;
  public static final String HOSTNAME = "localhost";

  public static void main (String[] args){

    Socket clientSocket = null;
    BufferedReader filein = null;
    PrintWriter out = null;
    BufferedReader in = null;

    String filename = args[0];

    try{
      clientSocket = new Socket(HOSTNAME,PORTNUM);
      clientSocket.setSoTimeout(2000);
      filein = new BufferedReader(new FileReader(filename));
      out = new PrintWriter(clientSocket.
          getOutputStream(), true);
      in = new BufferedReader(new InputStreamReader(
            clientSocket.getInputStream()
            ));
    } catch(IOException e){
      e.printStackTrace();
    }

    try{
      String line;
      while((line = filein.readLine())!=null){
        System.out.println(line);
        out.println(line);
        try{
          in.readLine(); //blocks till time out
        }catch (java.net.SocketTimeoutException e){
          System.out.println("Retransmitting");
          out.println(line);
        }
      }

      out.close();
      in.close();
      filein.close();
      clientSocket.close();
    }catch(IOException e){
      e.printStackTrace();
    }

  }

}
