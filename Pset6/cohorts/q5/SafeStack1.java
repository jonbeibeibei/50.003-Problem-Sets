import java.util.Stack;

class SafeStack1<E> extends Stack{
  private final int maxSize = 10;

  public synchronized <E> void pushIfNotFull(E e){


  }

  public synchronized <E> E popIfNotEmpty(){
    if empty()
      return null;
    else
      return pop();
  }
}
