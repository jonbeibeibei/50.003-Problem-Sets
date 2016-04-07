import java.util.Scanner;

public class Counter{

  public static void main(String[] args){

    CounterThread thread = new CounterThread();
    thread.start();

    Scanner scanner = new Scanner(System.in);

    if(scanner.nextInt() == 0){
      thread.interrupt();
    }


  }

}

class CounterThread extends Thread{

  private int count = 0;

  public void run(){
    while(true){
      count++;
      try{
        Thread.sleep(1000);
      }catch(InterruptedException e){
        System.out.println("Thread interrupted");
        return;
      }
      System.out.println("Count is " + count);

    }
  }


}
