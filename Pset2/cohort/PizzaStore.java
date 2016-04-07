
public class PizzaStore {

	public Pizza orderPizza (String type) {
		Pizza pizza = null;
    PizzaFactory pizzaFactory = new PizzaFactory();

    pizza = pizzaFactory.makePizza(type);

		pizza.prepare();
		pizza.bake();
		pizza.cut();
		pizza.box();

		return pizza;
	}
}

class PizzaFactory{
  public Pizza makePizza(String PizzaType){
    switch(PizzaType){
      case "pepperoni":
        return new PepperoniPizza();
      case "cheese":
        return new CheesePizza();
      case "greek":
        return new GreekPizza();
      default:
        return null;
    }
  }

}
abstract class Pizza {

	public void prepare() {
    System.out.println("Preparing");
	}

	public void box() {
    System.out.println("Boxing");
	}

	public void cut() {
    System.out.println("Cutting");
	}

	public void bake() {
    System.out.println("Baking");
	}
}

class CheesePizza extends Pizza {}
class GreekPizza extends Pizza {}
class PepperoniPizza extends Pizza {}

