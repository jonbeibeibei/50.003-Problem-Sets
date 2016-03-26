package Week8;

import java.util.concurrent.atomic.AtomicInteger;

//is this class thread-safe?
public class Range {
  // It's in fact no longer necessary to use AtomicInteger
  // If we just use a normal int, with the first two
  // methods synchronized and the last not, we need
  // to make sure it's a volatile int for memory visibility
  // across threads.
  // If however we are updating float types, the reading
  // operation is not atomic, so the last method should be
  // synchronized
	public final AtomicInteger lower = new AtomicInteger(0);
	public final AtomicInteger upper = new AtomicInteger(0);
	//invariant: lower <= upper

  // synchronize these methods so condition
  // checking and setting do not interleave
	public synchronized void setLower(int i) {
		if (i > upper.get()) {
			throw new IllegalArgumentException ("Can't set lower to " + i + " > upper");
		}

		lower.set(i);
	}

	public synchronized void setUpper(int i) {
		if (i < lower.get()) {
			throw new IllegalArgumentException ("Can't set upper to " + i + " < lower");
		}

		upper.set(i);
	}

  // Is is necessary to synchronize this method?
  // There is no reason to enforce upper.get()
  // should be executed strictly before the condition
  // is checked. (ie. the conditions are checked automatically)
  // It is safer to make it synchronized, but
  // if the strict before-after relation is not necessary
  // it can be ommitted for more efficiency
	public boolean isInRange(int i) {
		return (i >= lower.get()) && i <= upper.get();
	}
}
