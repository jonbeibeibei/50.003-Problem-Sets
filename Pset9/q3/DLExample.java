package Week11;

import java.util.HashSet;
import java.util.Set;

public class DLExample {

}

// DEADLOCK SCENARIO:
// If one thread is executing taxi.setLocation()
// on a certain taxi object, while another thread
// is executing dispatcher.getImage() on the dispatcher
// object that is a member of the tax object,
// the first thread will lock on the taxi object
// then the second thread may run and lock on the
// dispatcher object, and the two threads will be in
// deadlock as the first is waiting for the lock on
// the dispatcher while the second is waiting for the
// lock on the taxi
class Taxi {
  private Point location, destination;
  private final Dispatcher dispatcher;

  public Taxi(Dispatcher dispatcher) {
      this.dispatcher = dispatcher;
  }

	public synchronized Point getLocation() {
        return location;
    }

  public synchronized void setLocation(Point location) {
      this.location = location;
      if (location.equals(destination))
          dispatcher.notifyAvailable(this); // locks on dispatcher object
  }

  public synchronized Point getDestination() {
      return destination;
  }
}

class Dispatcher {
  private final Set<Taxi> taxis;
  private final Set<Taxi> availableTaxis;

  public Dispatcher() {
      taxis = new HashSet<Taxi>();
      availableTaxis = new HashSet<Taxi>();
  }

  public synchronized void notifyAvailable(Taxi taxi) {
      availableTaxis.add(taxi);
  }

  public synchronized Image getImage() {
      Image image = new Image();
      for (Taxi t : taxis)
          image.drawMarker(t.getLocation()); // locks on Taxi object t
      return image;
  }
}

class Image {
  public void drawMarker(Point p) {
  }
}

class Point {

}

