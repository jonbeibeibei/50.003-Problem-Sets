import java.math.BigInteger;

class FactorThread{

  public static final int NUM_THREADS = 4;
  public static final BigInteger SEMIPRIME = new BigInteger("4294967297");

  public static boolean finished = false;
  public static BigInteger[] result = new BigInteger[2];
  public static Object lock = new Object();

  public static void main(String[] args){
    FactorThread mainThread = new FactorThread();
    FactoringThread[] threads = new FactoringThread[NUM_THREADS];

    BigInteger numThreads = BigInteger.valueOf(NUM_THREADS);

    for(int i = 0; i<NUM_THREADS; i++){
      BigInteger step = BigInteger.valueOf(i);
      BigInteger lower;
      if(i==0)
        lower = BigInteger.valueOf(2);
      else
        lower = step.multiply(SEMIPRIME.divide(numThreads));
      BigInteger upper = (step.add(BigInteger.ONE)).multiply(SEMIPRIME.divide(numThreads));
      threads[i] = mainThread.new FactoringThread(lower, upper, SEMIPRIME);
      threads[i].start();
    }

    mainThread.checkFlag(threads);



  }

  public void checkFlag(FactoringThread[] threads){
    synchronized(lock){
      while(!finished){
        try{
          lock.wait();
        }catch(InterruptedException e){
          e.printStackTrace();
        }
      }
      System.out.format("Factors are %d and %d\n", result[0], result[1]);
      for(int i = 0; i < NUM_THREADS; i++){
        threads[i].interrupt();
        System.out.println("thread " + i + " is interrupted? " + threads[i].isInterrupted());
      }

    }
  }

  public void setFlag(){
    synchronized(lock){
      finished = true;
      lock.notify();
    }
  }

  public class FactoringThread extends Thread{

    private BigInteger lower;
    private BigInteger upper;
    private BigInteger semiprime;

    public FactoringThread(BigInteger lower, BigInteger upper, BigInteger semiprime){
      this.lower = lower;
      this.upper = upper;
      this.semiprime = semiprime;
    }

    public void run(){
      for(BigInteger i = lower; i.compareTo(upper) == -1; i = i.add(BigInteger.ONE)){
        if(isInterrupted()){
          System.out.println("Thread interrupted");
          return;
        }
        if(semiprime.mod(i) == BigInteger.ZERO){
          result[0] = i;
          result[1] = semiprime.divide(i);
          setFlag();
        }
      }
    }
  }
}
