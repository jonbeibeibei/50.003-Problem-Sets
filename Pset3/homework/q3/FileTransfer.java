import java.util.*;
import java.io.*;
import java.net.*;

class FileTransfer{

  public static final int PORTNUM = 4321;
  public static final String FILE = "ftpout";

  public static void main(String[] args){

    ServerSocket serverSocket = null;

    try{
      serverSocket = new ServerSocket(PORTNUM);
    } catch (IOException e){
      e.printStackTrace();
    }

    Socket clientSocket = null;
    BufferedReader in = null;
    PrintWriter out = null;
    PrintWriter fileout = null;
    int i = 1;

    while(true){
      try{
        System.out.println("... expecting connection ...");
        clientSocket = serverSocket.accept();
        in = new BufferedReader(new InputStreamReader(
              clientSocket.getInputStream()
              ));
        out = new PrintWriter(clientSocket.getOutputStream(),true);
        fileout = new PrintWriter(new FileWriter(
                new File(FILE+i+".txt")),true
                );
        i++;

        String line;
        while((line = in.readLine())!=null){
          out.println("Received");
          System.out.println("Received " + line);
          fileout.println(line);
        }

        System.out.println("File transfer finished");

        fileout.close();
        clientSocket.close();
        in.close();
        out.close();

      } catch(IOException e){
        e.printStackTrace();
      }
    }
  }
}
