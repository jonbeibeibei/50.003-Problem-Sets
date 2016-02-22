import java.io.*;
import java.net.*;

public class EchoClient {
    public static void main(String[] args) throws Exception {
        //String hostName = "localhost";
        String hostName = "localhost";
        int portNumber = 4321;

        Socket echoSocket = new Socket(hostName, portNumber);
        PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
        BufferedReader in =
                new BufferedReader(
                    new InputStreamReader(echoSocket.getInputStream()));
        BufferedReader stdIn =
                new BufferedReader(
                    new InputStreamReader(System.in));
        String userInput;
        do {
            userInput = stdIn.readLine();
            out.println(userInput);
            out.flush();
            long time1 = System.currentTimeMillis();
            System.out.println("time is " + time1);
            System.out.println("Husband says: " + in.readLine());
        } while (!userInput.equals("Bye"));

        echoSocket.close();
        in.close();
        out.close();
        stdIn.close();
    }
}
