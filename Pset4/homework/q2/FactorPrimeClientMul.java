import java.io.*;
import java.util.*;
import java.net.*;
import java.math.BigInteger;

class FactorPrimeClientMul{

  private static volatile boolean found = false;

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

    FactorThread thread = new FactorThread(out,num,start,stop);
    thread.start();

    while(true){
      if(found){
        thread.interrupt();
      }
    }

  }

  public static class FactorThread extends Thread{

    PrintWriter out;
    BigInteger num;
    BigInteger start;
    BigInteger stop;

    public FactorThread(PrintWriter out, BigInteger num, BigInteger start, BigInteger stop){
      this.out = out;
      this.num = num;
      this.start = start;
      this.stop = stop;
    }

    public void run(){
      for(BigInteger i = start; i.compareTo(stop) == -1;
          i = i.add(BigInteger.ONE)){

        if(isInterrupted()){
          System.out.println("I was interrupted!");
          return;
        }

        if(num.mod(i)==BigInteger.ZERO){
          System.out.println("i here is " + i);
          out.println(i);
          out.println(num.divide(i));
          found = true;
        }
      }
    }


  }
}
