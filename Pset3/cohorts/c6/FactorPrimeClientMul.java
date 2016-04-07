import java.io.*;
import java.util.*;
import java.net.*;
import java.math.BigInteger;

class FactorPrimeClientMul{

  public static void main (String[] args) throws Exception{
    String hostname = "localhost";
    int portnumber = 4321;

    Socket clientSocket = new Socket(hostname,portnumber);

    PrintWriter out = new PrintWriter(clientSocket.
        getOutputStream(),true);
    BufferedReader in = new BufferedReader(
        new InputStreamReader(clientSocket.
          getInputStream())
        );

    BigInteger num = new BigInteger(in.readLine());
    BigInteger start = new BigInteger(in.readLine());
    BigInteger stop = new BigInteger(in.readLine());

    for(BigInteger i = start; i.compareTo(stop) == -1;
        i = i.add(BigInteger.ONE)){
      System.out.println("i is " + i);

      if(num.mod(i)==BigInteger.ZERO){
        out.println(i);
        out.println(num.divide(i));
      }
    }
  }
}
