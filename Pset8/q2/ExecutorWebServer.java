
import java.io.*;
import java.math.BigInteger;
import java.net.*;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/* Performance comparison
 * The performance of the FixedThreadPool performs better than the sequential server and better than the task-per-thread server when there are many client threads
 */
public class ExecutorWebServer {
	private static final int NTHREADS = 100;
	private static final Executor exec = Executors.newFixedThreadPool(NTHREADS);

  public static void main(String[] args) throws Exception {
		ServerSocket socket = new ServerSocket(4321, 1000);

		while (true) {
			final Socket connection = socket.accept();
      System.out.println("Connected");
			Runnable task = new Runnable () {
				public void run() {
					handleRequest(connection);
				}
			};

			exec.execute(task);
		}
  }

	private static void handleRequest (Socket connection) {

    try{
     BufferedReader in = new BufferedReader(new InputStreamReader
          (connection.getInputStream()));
      PrintWriter out = new PrintWriter(connection.getOutputStream(),true);

      BigInteger num = new BigInteger(in.readLine());
      System.out.println(num);
      BigInteger factor = factor(num);
      out.println(factor);
      System.out.println("factor is " + factor);

    }
    catch(IOException e){
      System.out.println("Exception caught");
    }
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
