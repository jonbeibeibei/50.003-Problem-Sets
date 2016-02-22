import java.io.*;
import java.net.*;
import java.math.BigInteger;

class FactorPrimeClient{

  public static void main(String[] args) throws Exception{

    String hostname = "localhost";
    int portnumber = 4321;
    Socket socket = new Socket(hostname, portnumber);
    System.out.println("connected");

    PrintWriter out = new PrintWriter(socket.getOutputStream(),
        true);
    BufferedReader in = new BufferedReader(
        new InputStreamReader(socket.getInputStream()));

    BigInteger num = new BigInteger(in.readLine());
    BigInteger start = new BigInteger(in.readLine());
    BigInteger stop = new BigInteger(in.readLine());
    System.out.println(num);
    System.out.println(start);
    System.out.println(stop);

    for(BigInteger i = start; i.compareTo(stop) == -1; 
        i = i.add(BigInteger.ONE)){
      if(num.mod(i) == BigInteger.ZERO){
        System.out.println(i);
        out.println(i);
        out.println(num.divide(i));
        out.flush();
        break;
      }

    }

  }
}

