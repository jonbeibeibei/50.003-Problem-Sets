import java.io.*;
import java.net.*;
import java.util.*;
import java.math.BigInteger;

class FactorPrimeServerMul{

  public static void main(String[] args) throws Exception{

    ServerSocket serverSocket = new ServerSocket(4321);
    serverSocket.setSoTimeout(5000);
    List<Socket> socketList = new ArrayList<Socket>();
    List<PrintWriter> outList = new ArrayList<PrintWriter>();
    List<BufferedReader> inList = new ArrayList<BufferedReader>();

    while(true){
      try{
        System.out.println("... expecting connection ...");
        Socket clientSocket = serverSocket.accept();
        socketList.add(clientSocket);
        System.out.println(socketList.size() + " clients connected");

        outList.add(new PrintWriter(clientSocket
              .getOutputStream(), true));
        inList.add(new BufferedReader(
              new InputStreamReader(clientSocket
              .getInputStream())));
      }
      catch (java.net.SocketTimeoutException e)
      {
        System.out.println("Timed out");
        break;
      }
    }

    BigInteger num = new BigInteger("4294967297");
    //BigInteger num = new BigInteger("1127451830576035879");
    BigInteger numClients = BigInteger.valueOf(socketList.size());
    BigInteger step = num.divide(numClients);

    for(int i = 0; i< socketList.size(); i++){
      BigInteger start;
      if(i==0)
        start = BigInteger.valueOf(2);
      else
        start = step.multiply(BigInteger.valueOf(i));
      BigInteger stop = start.add(step);
      outList.get(i).println(num);
      outList.get(i).println(start);
      outList.get(i).println(stop);
      outList.get(i).flush();
    }

    String factor1 = null;
    String factor2 = null;

    while(true){
      for(int i =0; i< socketList.size(); i++){
        factor1 = inList.get(i).readLine();
        factor2 = inList.get(i).readLine();
        System.out.println("Factor 1 is " + factor1);
        System.out.println("Factor 2 is " + factor2);

      }
    }

  }
}
