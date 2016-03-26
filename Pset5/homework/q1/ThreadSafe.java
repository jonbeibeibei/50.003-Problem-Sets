public class ThreadSafe{

  public static void main(String[] args){
    //We need to use a StringBuffer object because
    //they are mutable while a String is not
    //and all the threads need to change the same
    //object
    StringBuffer sb = new StringBuffer("A");
    CharThread thread1 = new CharThread(sb);
    CharThread thread2 = new CharThread(sb);
    CharThread thread3 = new CharThread(sb);

    thread1.start();
    thread2.start();
    thread3.start();

  }

}

class CharThread extends Thread{

  private StringBuffer sb;

  public CharThread(StringBuffer sb){
    this.sb = sb;
  }

  public void run(){
    synchronized(sb){
      for(int i =0; i< 100; i++)
        System.out.print(sb);

      char current = sb.charAt(0);
      //Addition of int to char results in an int
      //unless the compound assignment operator is used
      char next = (char) (current + 1);
      sb.setCharAt(0, next);

    }
  }
}
