import java.util.Random;

public class BufferFixed {
	public static void main (String[] args) throws Exception {
		Buffer buffer = new Buffer (10);
		MyProducer prod = new MyProducer(buffer);
		prod.start();
		MyConsumer cons = new MyConsumer(buffer);
		cons.start();

    prod.join();
    cons.join();

    System.out.println("Buffer size is " + buffer);

	}
}

class MyProducer extends Thread {
	private Buffer buffer;

	public MyProducer (Buffer buffer) {
		this.buffer = buffer;
	}

	public void run () {
		Random random = new Random();
		try {
			Thread.sleep(random.nextInt(1000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		buffer.addItem(new Object());
	}
}

class MyConsumer extends Thread {
	private Buffer buffer;

	public MyConsumer (Buffer buffer) {
		this.buffer = buffer;
	}

	public void run () {
		Random random = new Random();
		try {
			Thread.sleep(random.nextInt(1000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		buffer.removeItem();
	}
}

class Buffer {
	public int SIZE;
	private Object[] objects;
	private int count = 0;

	public Buffer (int size) {
		SIZE = size;
		objects = new Object[SIZE];
	}

	public synchronized void addItem (Object object) {
    while(count == SIZE){
      try{
        wait();
      }catch(InterruptedException e){
        e.printStackTrace();
      }
    }

    objects[count] = object;
    count++;
    notify();
	}

	public synchronized Object removeItem() {
    while(count == 0){
      try{
        wait();
      } catch(InterruptedException e){
        e.printStackTrace();
      }
    }
    count--;
    notify();
    return objects[count];
	}

  public String toString(){
    return Integer.toString(count);
  }
}
