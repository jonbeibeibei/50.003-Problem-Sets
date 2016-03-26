import java.util.*;
import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;

class Main{

  public static void main (String[] args){
    Map<String,Integer> syncMap = Collections.synchronizedMap(
        new HashMap<String,Integer>()
        );
    Map<String,Integer> concurrentMap = new ConcurrentHashMap<String,Integer>();

    WorkerThread[] synchThreads = new WorkerThread[5];
    for(int i = 0; i< 5; i++){
      synchThreads[i] = new WorkerThread(syncMap);
    }

    WorkerThread[] concurrentThreads = new WorkerThread[5];
    for(int i = 0; i <5; i++){
      concurrentThreads[i] = new WorkerThread(concurrentMap);
    }

    long start = System.currentTimeMillis();
    for(int i = 0; i < 5; i++){
      synchThreads[i].start();
    }
    for(int i =0; i < 5; i++){
      try{
        synchThreads[i].join();
      }catch(InterruptedException e)
      {
        System.out.println("Interrupted");
      }
    }
    System.out.println("Syncmap threads took " + (System.currentTimeMillis() - start) + "ms");

    start = System.currentTimeMillis();
    for(int i = 0; i < 5; i++){
      concurrentThreads[i].start();
    }
    for(int i =0; i < 5; i++){
      try{
        concurrentThreads[i].join();
      }
      catch(InterruptedException e){
        System.out.println("Interrupted");
      }


    }
      System.out.println("Concurrent map thread took " + (System.currentTimeMillis() - start) + "ms");
  }
}
