import java.util.*;
import java.util.concurrent.*;

public class CacheFinal{

  private final ConcurrentHashMap<Integer, Future<List<Integer>>> results = new ConcurrentHashMap<Integer, Future<List<Integer>>> ();

  public List<Integer> service(final int input) throws Exception{

    Future<List<Integer>> f = results.get(input);

    if(f == null){
      Callable<List<Integer>> eval = new Callable<List<Integer>>(){
        public List<Integer> call() throws InterruptedException{
          return factor(input);
        }
      };

      FutureTask<List<Integer>> ft = new FutureTask<List<Integer>>(eval);
      f = ft;

      if(results.putIfAbsent(input, ft) == null){
      // if the input key was not previously in the map
      // the task will be added and run
        ft.run();
      }
      else{
        // the input key and task was already added into the map
        // by a previous thread
        f = results.get(input);
      }

    }

    return f.get();

  }

  public List<Integer> factor(int input){
    List<Integer> factors = new ArrayList<Integer>();
    for(int i = 2; i <= input; i++ ){
      while(input % i ==0 ){
        factors.add(i);
        input /= i;
      }
    }

    return factors;
  }

}
