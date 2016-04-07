package Week8;

public class MyStack {
	private final int maxSize;
  // putting a final in front of a reference type
  // only guarantees that the reference always
  // points to the same memory address
  // but the contents of the object pointed to
  // are not guaranteed to remain the same
	private long[] stackArray; // guarded by 'this'
	private int top; // invariant: top >= -1 and top < stachArray.length

  //pre-condition: s > 0
  //post-condition: maxSize == s, top == -1, stackArray != null
	public MyStack(int s) {
		maxSize = s;
	    stackArray = new long[maxSize];
	    top = -1;
	}

  //pre-condition: top < maxSize -1
  //post-condition: j is added as the last element to the stack
	public synchronized void push(long j) {
    // wait on precondition
    while(isFull()){
      try{
        wait();
      }catch(InterruptedException){
        System.out.println("Interrupted while waiting");
      }
    }

		stackArray[++top] = j;
    notifyAll();
	}

  //pre-condition: top >= 0
  //post-condition: top element is removed
	public synchronized long pop() {
    // wait on precondition
    while(isEmpty()){
      try{
        wait();
      }catch(InterruptedException){
        System.out.println("Interrupted while waiting");
      }
    }

    long retVal = stackArray[top--];
    notifyAll();
    return retval;
	}


  //pre-condition: top >= 0
  //post_condition: elements are unchanged; top element is returned
	public synchronized long peek() {
	    return stackArray[top];
	}

  //pre-condition: true
  //post-condition:  elements are unchanged; returns true iff stack is empty
	public synchronized boolean isEmpty() {
		return (top == -1);
	}

  //pre-condition: true
  //post-condition: elements are unchanged; returns true iff stack is full
	public synchronized boolean isFull() {
		return (top == maxSize - 1);
	}
}
