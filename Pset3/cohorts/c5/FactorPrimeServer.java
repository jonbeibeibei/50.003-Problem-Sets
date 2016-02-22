import java.io.*;
import java.net.*;
import java.math.BigInteger;

class FactorPrimeServer{

  public static void main(String[] args) throws Exception{
    ServerSocket serverSocket = new ServerSocket(4321);

    int numClients = 3;
    //BigInteger num = new BigInteger("4294967297");
    BigInteger num = new BigInteger("1127451830576035879");
    BigInteger step = num.divide(BigInteger.valueOf(3));
    BufferedReader[] ins = new BufferedReader[numClients];
    // 3 client sockets
    Socket[] clientSockets = new Socket[3];
    for(int i = 0; i< numClients; i++){
      System.out.println("... expecting connection ...");
      clientSockets[i] = serverSocket.accept();

      PrintWriter out = new PrintWriter(clientSockets[i].
          getOutputStream(), true);
      ins[i] = new BufferedReader(
          new InputStreamReader(clientSockets[i].
          getInputStream()));


      BigInteger start = step.multiply(BigInteger.valueOf(i));
      BigInteger stop = step.multiply(BigInteger.valueOf(i+1));

      out.println(num);
      if(i == 0)
        out.println(2);
      else
        out.println(start);
      if(i == numClients-1)
        out.println(num);
      else
        out.println(step.multiply(BigInteger.valueOf(i+1)));

      System.out.println(ins[i].readLine());
      System.out.println(ins[i].readLine());

    }

    while(true){
      System.out.println("in while loop");
      String ans1 = null;
      String ans2 = null;
      for(int i =0; i<numClients; i++){
       // System.out.println(ins[i]);
        ans1 = ins[i].readLine();
        System.out.println("Ans is " + ans1);
        ans2 = ins[i].readLine();
        System.out.println(ans1 + " " + ans2);
        if(ans1 != null && ans2 != null){
          System.out.println("breaking");
          break;
        }
      }
    }

  }

}
