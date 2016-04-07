class MultiThreadSearch{

  //If synchronized flag checking is not used
  //declare found as a volatile variable
  //since multiple threads are accessing it
  //volatile ensures that its updated value is
  //always written back to main memory (memory visibility)
  //Using synchronized blocks already ensure
  //a memory visibility constraint such that threads
  //which acquire the object lock sequentially
  //will see memory as it has been modified in sequence
  public static boolean found = false;
  public static int index;
  public static Object lock = new Object();

  public static void main (String[] args){

    int num = 3;
    int[] array = {1,2,3,4,5,6,7,8,9,10,11,12,13,14};

    MultiThreadSearch mainThread = new MultiThreadSearch();

    //Object lock

    SearchThread thread1 = mainThread.new SearchThread(1,num, array, 0, array.length/2);
    SearchThread thread2 = mainThread.new SearchThread(2,num, array, array.length/2, array.length);

    System.out.println("Starting threads");
    thread1.start();
    thread2.start();

    System.out.println("Checking found");
    Object lock = new Object();

    mainThread.checkFlag(thread1, thread2);

    /*Busy waiting solution
     * while(true){
      if(found){
        System.out.format("Number found at index %d\n", index);
        thread1.interrupt();
        thread2.interrupt();
        break;
      }
    }*/
  }

  //Synchronized locks the object instance which calls the method
  //So it won't lock static variables
  //Unless the syncrhonized method is itself static
  //Or the class object is itself synchronized
  //Or some other static object is synchronized
  //Another way to do it would be to pass an the instance of the
  //main thread to the other two threads, which will update
  //the instance variable found, in which case we can lock
  //on the cehckFlag operation by synchronizing the method
  public void checkFlag(SearchThread thread1, SearchThread thread2){
    synchronized(lock){
      while(!found){
        try{
          lock.wait();
        } catch( InterruptedException e){
          e.printStackTrace();
        }
      }

      System.out.format("Number found at index %d\n", index);
      thread1.interrupt();
      thread2.interrupt();

    }
  }

  public void setFlag(int i){
    synchronized(lock){
      found = true;
      index = i;
      lock.notify();
    }
  }

  public class SearchThread extends Thread{

    private int id;
    private int num;
    private int[] array;
    private int lower;
    private int upper;

    public SearchThread(int id,int num, int[] array, int lower, int upper){
      this.id = id;
      this.num = num;
      this.array = array;
      this.lower = lower;
      this.upper = upper;
    }

    public void run(){
      for(int i = lower; i < upper; i++){
        System.out.println("Running thread "+id+" at index " +i);
        if(isInterrupted()){
          System.out.println("Thread "+id+" interrupted");
          return;
        }
        if(array[i] == num){
          setFlag(i);
        }
      }
    }
  }
}
