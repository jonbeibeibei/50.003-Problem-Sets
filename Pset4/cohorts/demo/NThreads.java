class NThreads{

  //If you want to do it by synchronizing the update
  //of a static variable, uncomment the commented
  // out parts and comment out the complementary parts
  static final int NUM_THREADS = 10000;
  int counter = 0;
  //static int counter = 0;
  //static Object lock = new Object();

  public static void main(String[] args){
    NThreads mainThread = new NThreads();
    mThread[] threads = new mThread[NUM_THREADS];

    for(int i = 0; i < NUM_THREADS; i ++){
      threads[i] = new mThread(mainThread);
      threads[i].start();
    }

    try{

      for(int i = 0; i < NUM_THREADS; i++){
        threads[i].join();
      }
    }catch(InterruptedException e){
      e.printStackTrace();
    }

    System.out.println("Counter value is "+ mainThread.counter);
  }

  public static class mThread extends Thread{

    private NThreads mainThread;

    public mThread(NThreads mainThread){
      this.mainThread = mainThread;
    }

    public void run(){
      synchronized(mainThread){
      //synchronized(lock){
        mainThread.counter++;
      //}
      }
    }
  }
}
