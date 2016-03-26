public class Main{
  public static final int NUMTHREADS = 5;
  public static void main(String[] args){
    SynchronizedAccount synAcc = new SynchronizedAccount(0);
    BankThread[] threads = new BankThread[NUMTHREADS];

    for (int i =0; i< NUMTHREADS; i++){
      threads[i] = new BankThread(synAcc,i);
      threads[i].start();
    }

    for(int i =0; i<NUMTHREADS; i++){
      try{
        threads[i].join();
      }catch(InterruptedException e){
        e.printStackTrace();
      }
    }


    System.out.println("Final balance is " + synAcc.checkBalance());
    assert synAcc.checkBalance() == 0;
  }

}

class BankThread extends Thread{

  private SynchronizedAccount synAcc;
  private int id;

  public BankThread(SynchronizedAccount synAcc, int id){
    this.synAcc = synAcc;
    this.id = id;
  }

  public void run(){
    synAcc.deposit(7000);
    System.out.println("Thread " + id+" Balance is " +synAcc.checkBalance());
    try{
      Thread.sleep(1000);
    }catch(InterruptedException e){
      e.printStackTrace();
    }
    synAcc.withdraw(7000);
    System.out.println("Thread " + id+" Balance is " +synAcc.checkBalance());
  }
}
