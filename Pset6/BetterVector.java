package Week8;

import java.util.Vector;

@SuppressWarnings("serial")
public class BetterVector<E> extends Vector<E> {
	public synchronized boolean putIfAbsent(E x) {
		boolean absent = !contains(x);

		if (absent) {
			add(x);
		}

		return absent;
	}
}
