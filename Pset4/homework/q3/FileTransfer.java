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
        (new Thread(new ClientRunnable(clientSocket, in, out, fileout))).start();
        i++;

            } catch(IOException e){
        e.printStackTrace();
      }
    }
  }

  public static class ClientRunnable implements Runnable{

    private Socket clientSocket;
    private BufferedReader in;
    private PrintWriter out;
    private PrintWriter fileout;

    public ClientRunnable(Socket clientSocket, BufferedReader in, PrintWriter out, PrintWriter fileout){
      this.clientSocket = clientSocket;
      this.in = in;
      this.out = out;
      this.fileout = fileout;
    }

    public void run(){
      try{
        String line;
        while((line = in.readLine())!=null){
          out.println("Received");
          System.out.println("Received " + line);
          fileout.println(line);
        }

        System.out.println("File transfer finished");

        fileout.close();
        in.close();
        out.close();
        clientSocket.close();
      }
      catch(IOException e){
        e.printStackTrace();
      }
    }
  }
}
