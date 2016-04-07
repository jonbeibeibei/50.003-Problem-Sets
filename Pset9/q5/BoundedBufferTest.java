import org.junit.TestCase;
//this class is thread safe
public class BoundedBufferTest<E> extends TestCase {
	private static final long LOCKUP_DETECT_TIMEOUT = 1000;

	public void testIsEmptyWhenConstructued () {
		BoundedBuffer<Integer> bb = new BoundedBuffer<Integer>(10);
		assertTrue(bb.isEmpty());
		assertFalse(bb.isFull());
	}

	public void testIsFullAfterPuts () throws InterruptedException {
		BoundedBuffer<Integer> bb = new BoundedBuffer<Integer>(10);
		for (int i = 0; i < 10; i++) {
			bb.put(i);
		}

		assertTrue(bb.isFull());
		assertFalse(bb.isEmpty());
	}

	public void testTakeBlocksWhenEmpty () {
		final BoundedBuffer<Integer> bb = new BoundedBuffer<Integer>(10);
		Thread taker = new Thread() {
			public void run() {
				try {
					int unused = bb.take();
					fail();
				} catch (InterruptedException success) {} //if interrupted, the exception is caught here
			}
		};

		try {
			taker.start();
			Thread.sleep(LOCKUP_DETECT_TIMEOUT);
			taker.interrupt();
			taker.join(LOCKUP_DETECT_TIMEOUT);
			assertFalse(taker.isAlive()); //the taker should not be alive for some time
		} catch (Exception unexpected) {
			fail(); //it fails for other exceptions
		}
	}

  public void testPutBlocksWhenFull(){
    final BoundedBuffer<Integer> bb = new BoundedBuffer<Integer>(10);
    Thread putter = new Thread(){
      public void run(){
        try{
          bb.put(1);
          fail();
        } catch( InterruptedException success){}
        // interrupted while blocking
        //
      }
    };
        try {
          putter.start();
          Thread.sleep(LOCKUP_DETECT_TIMEOUT);
          putter.interrupt();
          putter.join(LOCKUP_DETECT_TIMEOUT);
          assertFalse(putter.isAlive());
        }catch (Exception unexpected){
          fail();
        }
  }

  public void testIsEmptyAfterTake() throws InterruptedException{
    final BoundedBuffer<Integer> bb = new BoundedBuffer<Integer>(10);
    for(int i = 0; i< 10; i++){
      bb.put(i);
    }

    for(int i = 0; i< 10; i++){
      bb.take();
    }

    assertTrue(bb.isEmpty());
    assertFalse(bb.isFull());

  }
}
