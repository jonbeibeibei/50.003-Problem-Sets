//Represents each Observer that is monitoring changes in the subject
// AmericanStockObserver only pulls american stock prices from the subject
// SingaporeStockObserver only pulls sg stock prices from the subject


public class SingaporeStockObserver implements Observer{

  private double sphPrice;
  private double singpostPrice;
  private double superPrice;

  private static int observerIDTracker = 0;

  private int observerID;

  private StockGrabber stockGrabber;

  public SingaporeStockObserver(StockGrabber stockGrabber){
    this.stockGrabber = stockGrabber;

    observerID = ++ observerIDTracker;

    System.out.println("New observer "+ observerID);

    stockGrabber.register(this);
  }

  public void update(){
    this.sphPrice = stockGrabber.getSPHPrice();
    this.singpostPrice = stockGrabber.getSingPostPrice();
    this.superPrice = stockGrabber.getSuperPrice();

    printThePrices();
  }


  public void printThePrices(){

    System.out.println(observerID + "\nSPH: " + sphPrice + "\nSingPost: " + singpostPrice + "\nSuper Group: " + superPrice+ "\n");

  }


}


