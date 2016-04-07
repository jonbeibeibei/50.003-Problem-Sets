import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CachedFactorizerThreadSafe{
	public static void main (String[] args) {
		CachedFactorizer factorizer = new CachedFactorizer ();
    //Threads are passed the same factorizer
		Thread tr1 = new Thread (new MyRunnable(factorizer,1));
		Thread tr2 = new Thread (new MyRunnable(factorizer,2));
		tr1.start();
		tr2.start();

		try {
			tr1.join();
			tr2.join();
		}
		catch (Exception e) {
      System.out.println("Problems joining thread");
		}

    System.out.println("Hit ratio is " + factorizer.getCacheHitRatio());
	}
}

class MyRunnable implements Runnable {
	private CachedFactorizer factorizer;
  private static final int count = 1000;
  private List<Integer> factors;
  private int id;

	public MyRunnable (CachedFactorizer factorizer, int id) {
		this.factorizer = factorizer;
    this.id = id;
	}

	public void run () {
		Random random = new Random ();
    for(int i = 0; i <count;i++){
      int randint = random.nextInt(100);
      //System.out.println("Thread " + id + " Number is " + randint);
      factors = factorizer.service(randint);
      //System.out.println("Thread " + id  + "Factors: " + factors.toString());
    }

    System.out.println("Thread " + id + " hit ratio is " + factorizer.getCacheHitRatio());
	}
}

class CachedFactorizer {
	private int lastNumber = 0;
	private List<Integer> lastFactors = new ArrayList<Integer>();
	private long hits = 0;
	private long cacheHits = 0;
  private Object serviceLock = new Object();

	public long getHits () {
		return hits;
	}

	public synchronized double getCacheHitRatio () {
		return (double) cacheHits/ (double) hits;
	}

  //Updating instance variables, should be synchronized
  //Instead of locking the whole method
  //we can just lock the parts where the variables
  //are updated
	public List<Integer> service (int input) {
    List<Integer> factors = null;
    synchronized(serviceLock){
      ++hits;
      if (input == lastNumber) {
        ++cacheHits;
        factors = new ArrayList<Integer>(lastFactors);
      }
    }

    if (factors == null) {
			factors = factor(input);
			lastNumber = input;
			lastFactors = new ArrayList<Integer>(factors);
		}
    //System.out.println("Hits are " + hits);
    //System.out.println("Cache hits are " + cacheHits);

		return factors;

  }

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
