package Week10;

import java.io.*;
import java.math.BigInteger;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class MultipleBatchClient {
    public static void main(String[] args) throws Exception {
    	int numberOfClients = 10000;
    	//BigInteger n = new BigInteger("1127451830576035879");
    	BigInteger n = new BigInteger("4294967297");
    	List<BigInteger> list = new ArrayList<BigInteger>();
    	list.add(n);
    	list.add(n);
    	list.add(n);
    	System.out.println (Runtime.getRuntime().availableProcessors());
        for (int i = 0; i < numberOfClients; i++) {
        	new Thread(new BatchClient(list)).start();
        }
    }
}

class BatchClient implements Runnable {
	private final List<BigInteger> n;

	public BatchClient (List<BigInteger> n) {
		this.n = n;
	}

	public void run() {
		String hostName = "192.168.50.14";
        int portNumber = 4321;

        try {
        	long startTime = System.currentTimeMillis();
        	Socket socket = new Socket(hostName, portNumber);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        	BufferedReader in =
                    new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
        	out.println(n.toString());
        	out.flush();
            System.out.println("Spent time: " + (System.currentTimeMillis() - startTime));
            out.close();
            in.close();
            socket.close();
        }
        catch (Exception e) {
        }
	}
}
