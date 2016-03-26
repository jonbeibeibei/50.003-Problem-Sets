import java.util.concurrent.CountDownLatch;

public class CountDownLatchExercise{
  private static final int NUM_GRADES = 10000;
  private static final String[] grades = new String[NUM_GRADES];
  private static final int NUM_FAILURES = 7;
  private static final CountDownLatch latch = new CountDownLatch(NUM_FAILURES);
  private static final int NUM_THREADS = 10;
  private static final Thread[] threads = new Thread[NUM_THREADS];

  public static void main(String[] args)
  {
    // Initialize array of grades
    for(int i =0; i < grades.length; i++){
      if(Math.random() >= 0.5)
      {
        grades[i] = "T";
      }
      else
        grades[i] = "F";

    }
    //
    // Create and start threads
    int size = NUM_GRADES/NUM_THREADS;
    for(int i = 0; i < NUM_THREADS; i++){
      threads[i] = new CheckGrade(grades,i*size, (i+1)*size, latch, "Thread" + i);
      threads[i].start();
    }
    try{

      latch.await();
      System.out.println("Found " + NUM_FAILURES + " failures");
      for(int i = 0; i < NUM_THREADS; i++)
      {
        threads[i].interrupt();
      }


    }
    catch(InterruptedException e){
      System.out.println("Main thread interrupted");
    }

  }
}

class CheckGrade extends Thread{
  private String[] grades;
  private int startIndex;
  private int endIndex;
  private String name;
  private CountDownLatch latch;

  public CheckGrade(String[] grades, int startIndex, int endIndex , CountDownLatch latch, String name){
    this.grades = grades;
    this.startIndex = startIndex;
    this.endIndex = endIndex;
    this.latch = latch;
    this.name = name;
  }

  public void run(){
    for(int i = startIndex; i < endIndex; i ++){
      if(isInterrupted())
        return;
      if(grades[i].equals("F")){
        System.out.println("Found one");
        latch.countDown();
      }
    }
  }

}
