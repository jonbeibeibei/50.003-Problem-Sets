import java.io.*;
import java.math.BigInteger;
import java.net.*;

public class MultipleClientB {
    public static void main(String[] args) throws Exception {
    	int numberOfClients = 1000; //vary this number here
    	long startTime = System.currentTimeMillis();
    	BigInteger n = new BigInteger("4294967297");
    	//BigInteger n = new BigInteger("239839672845043");
    	Thread[] clients = new Thread[numberOfClients];
    	for (int i = 0; i < numberOfClients; i++) {
        clients[i] = new Thread(new Client(n, false));
        clients[i].start();
      }

      for (int i = 0; i < numberOfClients; i++) {
        clients[i].join();
      }
      System.out.println("Spent time: " + (System.currentTimeMillis() - startTime));
  }
}

class Client implements Runnable {
	private final BigInteger n;
  private boolean stop;

	public Client (BigInteger n, boolean stop) {
		this.n = n;
    this.stop = stop;
	}

	public void run() {
		String hostName = "localhost";
        int portNumber = 4321;

        try {
        	//long startTime = System.currentTimeMillis();
        	Socket socket = new Socket(hostName, portNumber);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        	BufferedReader in =
                    new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
          if(stop){
            System.out.println("Stopping thread");
            out.println("STOP");
          }
          else{

            System.out.println("Starting client thread");
            out.println(n.toString());
            out.flush();
            System.out.println(in.readLine());

                        //System.out.println("Spent time: " + (System.currentTimeMillis() - startTime));
              out.close();
              in.close();
              socket.close();

          }
        }
        catch (Exception e) {
        }
	}
}
