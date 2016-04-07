public class homeworkTest{
  public static void main (String args[]){
    //Testing Observer Pattern with pull model
    StockGrabber stockGrabber = new StockGrabber();
    Observer ameObserver = new AmericanStockObserver(stockGrabber);
    Observer sgObserver = new SingaporeStockObserver(stockGrabber);

    stockGrabber.setIBMPrice(100);
    stockGrabber.setSPHPrice(200);
  }
}
