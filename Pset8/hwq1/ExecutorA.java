import java.io.*;
import java.math.BigInteger;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledThreadPoolExecutor;

public class ExecutorA{
	private static final ExecutorService exec = new ScheduledThreadPoolExecutor (100);

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

  public static void stop() {
    System.out.println("Shutting down");
    exec.shutdownNow();
  }

	protected static void handleRequest(Socket connection) {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			PrintWriter out = new PrintWriter(connection.getOutputStream(), true);
      String received = in.readLine();
      //System.out.println(received);
      if(received.equals("STOP"))
        stop();
      else{
        BigInteger number = new BigInteger(received);
        BigInteger result = factor(number);
        //System.out.println("sending results: " + String.valueOf(result));
        out.println(result);
      }

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
