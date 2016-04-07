import java.util.Stack;

@SuppressWarnings("serial")
class SafeStack1<E> extends Stack<E>{
  private final int maxSize = 10;

  public synchronized E pushIfNotFull(E e){
    if (size() < maxSize){
      push(e);
      return e;
    }
    else
      return null;
  }

  public synchronized E popIfNotEmpty(){
    if (empty())
      return null;
    else
      return pop();
  }
}

class SafeStack2<E>{
  private final Stack<E> stack;
  private final int maxSize = 10;

  public SafeStack2(Stack<E> stack){
    this.stack = stack;
    // should this instance be confined
  }

  public synchronized E pushIfNotFull(E e){
    if(this.stack.size() < maxSize){
      this.stack.push(e);
      return e;
    }
    else
      return null;
  }

  public synchronized E push(E e){
    return this.stack.push(e);
  }

  public synchronized E popIfNotEmpty(){
    if(this.stack.empty())
      return null;
    else
      return pop();
  }

  public synchronized E pop(){
    return this.stack.pop();
  }

  public synchronized E peek(){
    return this.stack.peek();
  }

  public synchronized boolean empty(){
    return this.stack.empty();
  }

  public synchronized int search(Object o){
    return this.stack.search(o);
  }

}
