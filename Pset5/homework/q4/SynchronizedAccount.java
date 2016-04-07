class SynchronizedAccount{

  private Object depositLock = new Object();
  private Object withdrawLock = new Object();

  private int balance;

  public SynchronizedAccount(int balance){
    this.balance = balance;
  }

  public int checkBalance(){
    return balance;
  }

  public void deposit(int amount){
    synchronized(depositLock){
      balance += amount;
    }
  }

  public void withdraw(int amount){
    synchronized(withdrawLock){
      if(balance >= amount){
        balance -= amount;
      }
      else{
        System.out.println("There is not enough money in your account to withdraw specified amount");
      }
    }
  }
}
