import java.util.Map;
import java.util.concurrent.ConcurrentHashMap<K,V>

//is this class thread-safe?
public class Tracker {
	//@guarded by 'this'
	private final Map<String, MutablePoint> locations;

  //pre-condition: argument locations is not null
  //post-condition: this.locations is not null
	public Tracker(Map<String, MutablePoint> locations) {
    // copy locations into a new object
    this.locations = copyLocation(locations);
	}

  //pre-condition: locations is not null
  //post-condition: locations is unchanged and returned
	public synchronized Map<String, MutablePoint> getLocations () {
    // return a copy
		return copyLocation(locations);
	}

  //pre-condition: id is not null
  //post-condition: locations is unchanged
	public synchronized MutablePoint getLocation (String id) {
    // clone the object
		MutablePoint loc = locations.get(id).clone();
		return loc;
	}

  //pre-condition: id is not null
  //post-condition: location specified by id is updated
	public synchronized void setLocation (String id, int x, int y) {
		MutablePoint loc = locations.get(id);

    //check on precondition
		if (loc == null) {
			throw new IllegalArgumentException ("No such ID: " + id);
		}

		loc.x = x;
		loc.y = y;
	}


  private copyLocation(Map<String,MutablePoint> locations){
    Map<String, MutablePoint> mLocations = new Map<String, MutablePoint>();
    for(Map.Entry<String,MutablePoint> entry: locations.entrySet()){
      mLocations.put(entry.getKey(), entry.getValue().clone());
    }

    return mLocations;
  }

	//this class is not thread-safe (why?) and keep it unmodified.
  //public variables can be set freely, in an unsynchronized manner
	class MutablePoint {
		public int x, y;

		public MutablePoint (MutablePoint p) {
			this.x = p.x;
			this.y = p.y;
		}
	}
}
