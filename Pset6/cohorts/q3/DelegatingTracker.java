package Week8;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap; //this is thread-safe!
import java.util.concurrent.ConcurrentMap; //this is thread-safe!

// THE SPECIFICATION:
// anyone else with a handle on a Point object
// is allowed to modify it, just as long as it
// modifies x and y together
//
//is this class thread-safea (based on the above specification)?
public class DelegatingTracker {
	private final ConcurrentMap<String, Point> locations;

	public DelegatingTracker(Map<String, Point> locations) {
    // copies location into a new object
    // is this a deep copy?
    // no it is not
    // so the caller still has references to
    // all the Point objects
		this.locations = new ConcurrentHashMap<String, Point>(locations);
	}

	public synchronized Map<String, Point> getLocations () {
    // unmodifiable map presents a view on the map object
    // so it may change if the original map changes
    // but it does not allow others with a handle on it
    // to change the original map
    // so is it actually necessary to create clone
    // locations before wrapping it in unmodifiableMap?
		return Collections.unmodifiableMap(new HashMap<String, Point>(locations));
	}

	public synchronized Point getLocation (String id) {
		return locations.get(id);
	}

	public synchronized void setLocation (String id, int x, int y) {
		if (!locations.containsKey(id)) {
			throw new IllegalArgumentException ("No such ID: " + id);
		}

		locations.get(id).set(x, y);
	}

	//is this class not thread-safe? yes
	//is a Point object mutable? yes it is
	class Point {
		private int x, y;

		private Point (int[] a) {
			this(a[0], a[1]);
		}

		public Point (Point p) {
			this(p.get());
		}

		public Point (int x, int y) {
			this.x = x;
			this.y = y;
		}

		public synchronized int[] get() {
			return new int[] {x, y};
		}

		public synchronized void set(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
