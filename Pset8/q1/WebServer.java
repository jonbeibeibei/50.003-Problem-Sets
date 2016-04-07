import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

// Sequential timing:
// 10 clients: 33451ms
// 100 clients: 225456ms
// Thread per task:
// 10 clients: 21494ms
// 100 clients: 136159ms
// Clearly the thread per task server is faster
// than the sequential server
public class WebServer {
	public static void main (String[] args) throws Exception {
		ServerSocket socket = new ServerSocket(4321, 1000);

		long startTime = 0;
		while (true) {
			Socket connection = socket.accept();
			if (startTime == 0) {
				startTime = System.currentTimeMillis();
			}
      Runnable task = new Runnable(){
        public void run(){
          try{
            handleRequest(connection);
          }catch(Exception e){
            e.printStackTrace();
          }
        }
      };

      (new Thread(task)).start();
		}
	}

	private static void handleRequest (Socket connection) throws Exception {

    /* Sequential*/
    BufferedReader in = new BufferedReader(new InputStreamReader
        (connection.getInputStream()));
    PrintWriter out = new PrintWriter(connection.getOutputStream(),true);

    BigInteger num = new BigInteger(in.readLine());
    System.out.println(num);
    BigInteger factor = factor(num);
    out.println(factor);
    System.out.println("factor is " + factor);


	}

	private static BigInteger factor(BigInteger n) {
		BigInteger i = new BigInteger("2");
		BigInteger zero = new BigInteger("0");

		while (i.compareTo(n) < 0) {
			if (n.remainder(i).compareTo(zero) == 0) {
				return i;
			}

			i = i.add(new BigInteger("1"));
		}

		assert(false);
		return null;
	}

}

