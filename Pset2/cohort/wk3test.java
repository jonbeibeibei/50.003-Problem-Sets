import java.util.*;

class wk3test{
  public static void main(String args[])
  {
    Animal doggy = new Dog();
    doggy.digHole();
    //Dog animal = new Animal(); throws an errror

    //Pizza Store: Factory Design Pattern
    PizzaStore pizzaStore = new PizzaStore();
    pizzaStore.orderPizza("greek");

    //Robot Game: Strategy Design Pattern
    RobotGame.main(new String[0]);

    //Pizza Topping: Decorator Design Pattern
    DecoratorDemo.main(new String[0]);

    //Employee info: Visitor Design Pattern
    VisitorPatternImplemented.main(new String[0]);

  }

  public static class Animal{
    public void digHole(){
      System.out.println("Digging animal");

    }
  }

  public static class Dog extends Animal{
    public void digHole(){
      System.out.println("Digging");
    }
  }
}
