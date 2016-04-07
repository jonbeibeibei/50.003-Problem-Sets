import java.io.*;
import java.math.BigInteger;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class ExecutorB{
  private static final BlockingQueue<Runnable> taskQueue = new ArrayBlockingQueue<Runnable>(10);
	private static final ExecutorService exec = new ThreadPoolExecutor(100, 100, 5, TimeUnit.SECONDS, taskQueue);

  public static void main(String[] args) throws Exception {
    ServerSocket socket = new ServerSocket(4321);
    int connectionCount = 0;

    while (true) {
      try {
        final Socket connection = socket.accept();
        connectionCount += 1;
        Runnable task = new Runnable () {
          public void run() {
              handleRequest(connection);
          }
        };

        exec.execute(task);
        System.out.println("Count is " + connectionCount);
      } catch (RejectedExecutionException e) {
        System.out.println("LOG: task submission is rejected.");
        break;
      }
    }
  }

	protected static void handleRequest(Socket connection) {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			PrintWriter out = new PrintWriter(connection.getOutputStream(), true);
      System.out.println("Waiting for input");
      String received = in.readLine();
      //System.out.println(received);
      BigInteger number = new BigInteger(received);
      BigInteger result = factor(number);
        //System.out.println("sending results: " + String.valueOf(result));
      out.println(result);
			out.flush();
			in.close();
			out.close();
			connection.close();
		}
		catch (Exception e) {
			System.out.println("Something went wrong with the connection");
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
