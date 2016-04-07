import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.Vector;


public class FirstExample {
  // Suppose two threads call the methods and pass
  // the same list object. If the methods are allowed
  // to interleave, one thread may read the wrong
  // list size and access an invalid index
  // so synchronize them on the list object
  // This works because the Vector objects own
  // synchronization policy is to synchronize
  // on the 'this' object (ie. it supports
  // client-side locking)
	public static Object getLast(Vector list) {
    synchronized(list){
	       int lastIndex = list.size()-1;
	       return list.get(lastIndex);
    }
	}

	public static synchronized void deleteLast(Vector list) {
    synchronized(list){
	      int lastIndex = list.size()-1;
	      list.remove(lastIndex);
    }
	}

	public static boolean contains(Vector list, Object obj) {
    synchronized(list){
      for (int i = 0; i < list.size(); i++) {
        if (list.get(i).equals(obj)) {
          return true;
        }
      }

      return false;

    }
  }
}
