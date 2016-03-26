import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

// if we want factor to be executed once and only once
// for each number, we can use FutureTask
public class CacheV2 {
  // Store a Future object instead of the actual factors
	private final ConcurrentHashMap<Integer, Future<List<Integer>>> results = new ConcurrentHashMap<Integer, Future<List<Integer>>>(); //the last factors must be the factors of the last number

	public List<Integer> service (final int input) throws Exception {
		Future<List<Integer>> f = results.get(input);

		if (f == null) {
			Callable<List<Integer>> eval = new Callable<List<Integer>>() {
				public List<Integer> call () throws InterruptedException {
					return factor(input);
				}
			};

			FutureTask<List<Integer>> ft = new FutureTask<List<Integer>>(eval);
			f = ft;
      // put the number and task in the map
      // before actually running the task
      // which shortens the gap between checking the
      // map and adding the key-value pair
      // (but there is still a gap that can present problems)
      // so how do we make sure that factor(a) is only
      // executed once and only once for any a?
			results.put(input, ft);
			ft.run();
		}

    // if the task has already been executed before
    // and another thread comes in and calls f.get()
    // on the same task, the computation has already been
    // complete, and it returns straightaway
		return f.get();
	j}

	public List<Integer> factor(int n) {
		List<Integer> factors = new ArrayList<Integer>();
		for (int i = 2; i <= n; i++) {
			while (n % i == 0) {
		        factors.add(i);
		        n /= i;
		    }
		}

		return factors;
	}
}
