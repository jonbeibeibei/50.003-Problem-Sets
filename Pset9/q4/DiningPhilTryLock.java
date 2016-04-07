import java.util.Random;
import java.util.concurrent.locks.*;

public class DiningPhilTryLock {
	private static int N = 5;

	public static void main (String[] args) throws Exception {
		Philosopher[] phils = new Philosopher[N];
		Fork[] forks = new Fork[N];

		for (int i = 0; i < N; i++) {
			forks[i] = new Fork(i);
		}

		for (int i = 0; i < N; i++) {
			phils[i] = new Philosopher (i, forks[i], forks[(i+N-1)%N]);
			phils[i].start();
		}
	}
}

class Philosopher extends Thread {
	private final int index;
	private final Fork left;
	private final Fork right;

	public Philosopher (int index, Fork left, Fork right) {
		this.index = index;
		this.left = left;
		this.right = right;
	}

	public void run() {
		Random randomGenerator = new Random();
		try {
			while (true) {
				//Thread.sleep(randomGenerator.nextInt(100)); //not sleeping but thinking
				System.out.println("Phil " + index + " finishes thinking.");
				boolean leftPicked = left.pickup();

        if(leftPicked)
          System.out.println("Phil " + index + " picks up left fork.");
        else
          System.out.println("Phil " + index + " can't pick up left fork." );
				boolean rightPicked = right.pickup();
        if (rightPicked)
          System.out.println("Phil " + index + " picks up right fork.");
        else{
          System.out.println("Phil " + index + " can't pick up right fork");
          left.putdown();
        }

				Thread.sleep(randomGenerator.nextInt(100)); //eating
				System.out.println("Phil " + index + " finishes eating.");
				left.putdown();
				System.out.println("Phil " + index + " puts down left fork.");
				right.putdown();
				System.out.println("Phil " + index + " puts down right fork.");
			}
		} catch (InterruptedException e) {
			System.out.println("Don't disturb me while I am sleeping, well, thinking.");
		}
	}
}

class Fork {
	private final int index;
	private boolean isAvailable = true;
  public Lock lock = new ReentrantLock();

	public Fork (int index) {
		this.index = index;
	}

  // return true if pickup succeeds
	public boolean pickup () throws InterruptedException {
    if(lock.tryLock() && isAvailable){
      isAvailable = false;
      lock.unlock();
      return true;

    }
    return false;
  }

	public boolean putdown() throws InterruptedException {
    if(lock.tryLock() && !isAvailable){
      isAvailable = true;
      lock.unlock();
      return true;
    }
    return false;
  }

	public String toString () {
		if (isAvailable) {
			return "Fork " + index + " is available.";
		}
		else {
			return "Fork " + index + " is NOT available.";
		}
	}
}
