public class BadThreads{

  static String message;

  private static class CorrectorThread extends Thread{

    public void run(){
      try{
        sleep(1000);
      } catch(InterruptedException e){}

      System.out.println("Running");
      // Key Statement 1
      message = "Mares do eat oats.";
    }
  }

  public static void main (String args[])
    throws InterruptedException{

    (new CorrectorThread()).start();
    System.out.println("Setting message in main");
    message = "Mares do not eat oats.";
    Thread.sleep(2000);
    // Key Statement 2
    System.out.println(message);
  }
}

//On my machine the program always prints "Mares do eat oats."
//The invocation to sleep the main thread for 2 seconds before
//executing Key Statement 2 ensures most of the time
//(but doesn't guarantee) that Key Statement 1 is
//executed before Key Statement 2. However the change to message
//may not always be visible (in memory) to the main thread
//thus this doesn't guarantee that "Mares do eat oats."
//is printed everytime.
//
//The best solution would be to call join on the CorrectorThread
//before Key Statement 2, which ensures a happen-before relationship
//on the two statements
