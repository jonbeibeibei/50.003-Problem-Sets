public class BadThreads{

  static String message;

  private static class CorrectorThread extends Thread{

    public void run(){
      try{
        sleep(1000);
      } catch(InterruptedException e){}

      System.out.println("Running");
      message = "Mares do eat oats.";
    }
  }

  public static void main (String args[])
    throws InterruptedException{

    (new CorrectorThread()).start();
    System.out.println("Setting message in main");
    message = "Mares do not eat oats.";
    Thread.sleep(2000);
    System.out.println(message);
  }
}
