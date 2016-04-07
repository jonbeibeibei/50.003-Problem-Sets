import java.util.concurrent.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestThreadPoolSample{
  @Test
  public void testPoolExpansion() throws InterruptedException {
      int maxPoolSize = 10;
      ExecutorService exec = Executors.newFixedThreadPool(maxPoolSize);

      int testNumTasks = 100;
      int numThreads = 0;
      for (int i = 0; i < testNumTasks; i++){
        exec.execute(new Runnable(){
          public void run(){
            System.out.println("Running " );
          }
        });
        numThreads = ((ThreadPoolExecutor) exec).getActiveCount();
        assertTrue(numThreads <= maxPoolSize);
      }
      exec.shutdownNow();
  }
}
