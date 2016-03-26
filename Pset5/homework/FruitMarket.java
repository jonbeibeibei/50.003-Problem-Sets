import java.util.Random;

public class FruitMarket{

  public static final int NUMFARMERS = 6;
  public static final int NUMCONS = 10;

  public static void main(String[] args){
    PineappleBuffer pinBuff = new PineappleBuffer(20);
    //cuz everyone prefers pineapples to apples dum dum dum
    AppleBuffer  appleBuff = new AppleBuffer(10);
    FarmerThread[] farmers = new FarmerThread[NUMFARMERS];
    ConsumerThread[] consumers = new ConsumerThread[NUMCONS];


    System.out.println(pinBuff);
    System.out.println(appleBuff);

    System.out.println("Market opening...");

    //produce a total of NUMFARMERS pineapples
    //and NUMFARMERS apples
    for(int i =0; i< NUMFARMERS; i++){

      if(i%2==0)
        farmers[i] = new FarmerThread((FruitBuffer)pinBuff);
      else
        farmers[i] = new FarmerThread(appleBuff);

      farmers[i].start();
    }

    //consume a toal of NUMCONS/2 pineapples
    //and NUMCONS/2 apples
    for(int i =0;i < NUMCONS; i++){
      if(i%2==0)
        consumers[i] = new ConsumerThread(pinBuff);
      else
        consumers[i] = new ConsumerThread(appleBuff);

      consumers[i].start();
    }

    for(int i =0; i <NUMFARMERS; i++){
      try{
        farmers[i].join();
      }catch(InterruptedException e){
        System.out.println("Interrupted while joining");
      }
    }

    for(int i =0; i < NUMCONS; i++){
      try{
        consumers[i].join();
      }catch(InterruptedException e){
        System.out.println("Interrupted while joining");
      }
    }

    System.out.println(pinBuff);
    System.out.println(appleBuff);
  }
}

class FruitBuffer{

  protected  int SIZE;
  protected int count = 0;
  protected  Object[] fruits;

  public FruitBuffer(int SIZE){
    this.SIZE = SIZE;
    fruits = new Object[SIZE];
  }

  public synchronized void produce(){
    while(count == SIZE){
      try{
        wait();
      }catch(InterruptedException e){
        System.out.println("Interrupted while waiting");
      }
    }

    fruits[count] = new Object();
    count++;
    //Since we might have multiple producer and
    //consumer threads accessing the same buffer
    notifyAll();
  }

  public synchronized Object consume(){
    while(count == 0){
      try{
        wait();
      }catch(InterruptedException e){
        System.out.println("Interrupted while waiting");
      }
    }

    count--;
    //Since we might have multiple producer and
    //consumer threads accessing the same buffer
    notifyAll();
    return fruits[count];
  }


}

class PineappleBuffer extends FruitBuffer{

  public PineappleBuffer(int SIZE){
    super(SIZE);
  }

  public String toString(){
    return "Number of pineapples on the market " + count;
  }

}


class AppleBuffer extends FruitBuffer{

  public AppleBuffer(int SIZE){
    super(SIZE);
  }
  public String toString(){
    return "Number of apples on the market " + count;
  }
}


class FarmerThread extends Thread{

  private FruitBuffer fruitBuff;
  public FarmerThread(FruitBuffer fruitBuff){
    this.fruitBuff = fruitBuff;
  }

  public void run(){
    Random random = new Random();
    try{
      Thread.sleep(random.nextInt(1000));
    }catch(InterruptedException e){
      System.out.println("Sleep interrupted");
    }

    fruitBuff.produce();
    fruitBuff.produce();

  }
}

class ConsumerThread extends Thread{

  private FruitBuffer fruitBuff;

  public ConsumerThread(FruitBuffer fruitBuff){
    this.fruitBuff = fruitBuff;
  }

  public void run(){
    Random random = new Random();
    try{
      Thread.sleep(random.nextInt(500));
    } catch(InterruptedException e){
      System.out.println("Sleep interrupted");
    }


    fruitBuff.consume();
  }
}
