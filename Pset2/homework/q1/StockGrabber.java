import java.util.ArrayList;

//Uses the Subject interface to update all Observers

public class StockGrabber implements iSubject {

	private ArrayList<Observer> observers;
	private double ibmPrice;
	private double aaplPrice;
	private double googPrice;
  private double sphPrice;
  private double singpostPrice;
  private double superPrice;

	public StockGrabber(){

		// Creates an ArrayList to hold all observers

		observers = new ArrayList<Observer>();
	}

	public void register(Observer newObserver) {

		// Adds a new observer to the ArrayList

		observers.add(newObserver);

	}

	public void unregister(Observer deleteObserver) {

		// Get the index of the observer to delete

		int observerIndex = observers.indexOf(deleteObserver);

		// Print out message (Have to increment index to match)

		System.out.println("Observer " + (observerIndex+1) + " deleted");

		// Removes observer from the ArrayList

		observers.remove(observerIndex);

	}

	public void notifyObserver() {

		// Cycle through all observers and notifies them of
		// price changes

		for(Observer observer : observers){

			observer.update();

		}
	}

	// Getters (for pull model) and setters

  public double getIBMPrice(){
    return ibmPrice;
  }

	public void setIBMPrice(double newIBMPrice){

		this.ibmPrice = newIBMPrice;

		notifyObserver();

	}


  public double getAAPLPrice(){
    return aaplPrice;
  }

	public void setAAPLPrice(double newAAPLPrice){

		this.aaplPrice = newAAPLPrice;

		notifyObserver();

	}

  public double getGOOGPrice(){
    return googPrice;
  }

	public void setGOOGPrice(double newGOOGPrice){

		this.googPrice = newGOOGPrice;

		notifyObserver();

	}

  public double getSPHPrice(){
    return sphPrice;
  }

	public void setSPHPrice(double newPrice){

		this.sphPrice = newPrice;

		notifyObserver();

	}


  public double getSingPostPrice(){
    return singpostPrice;
  }

	public void setSingPostPrice(double newPrice){

		this.singpostPrice = newPrice;

		notifyObserver();

	}

  public double getSuperPrice(){
    return superPrice;
  }

	public void setSuperPrice(double newPrice){

		this.superPrice = newPrice;

		notifyObserver();

	}



}
