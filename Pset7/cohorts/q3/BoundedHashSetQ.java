package Week9;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;

public class BoundedHashSetQ<T> {
  private static final int MAX_AVAILABLE = 10;
	private final Set<T> set;
  private final Semaphore empty = new Semaphore(MAX_AVAILABLE, true);

	public BoundedHashSetQ (int bound) {
		this.set = Collections.synchronizedSet(new HashSet<T>());
	}

	public boolean add(T o) throws InterruptedException {
    boolean status;
    empty.acquire();
    try{
      status =  set.add(o);
      // make sure to check whether add is successful
      // if it is not successful,
      // amend the value of the semaphore
      if(!status)
        empty.release();
    }
    catch (Exception e){
      empty.release();
      return false;
    }
    return status;
	}

	public boolean remove (Object o) {
    boolean status;
    try{
      status  = set.remove(o);
      if(status)
        empty.release();
    }
    catch (Exception e){
      return false;
    }
    return status;
	}
}
