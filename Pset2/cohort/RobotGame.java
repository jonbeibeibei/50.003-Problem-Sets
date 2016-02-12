class Robot {
	String name;
  IBehaviour behaviour;

	public Robot (String name)
	{
		this.name = name;
	}

	public void behave ()
	{
    behaviour.moveCommand();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setBehavior(String type) {
    type = type.toLowerCase();
    switch(type)
    {
      case "aggresive":
        behaviour = new Aggresive();
        break;
      case "defensive":
        behaviour = new Defensive();
        break;
      case "normal":
        behaviour = new Normal();
        break;
      default:
        behaviour = new Normal();
    }
	}
}

class Aggresive implements IBehaviour{
  public int moveCommand(){
    System.out.println("Moving aggresively");
    return 1;
  }
}

class Defensive implements IBehaviour{
  public int moveCommand(){
    System.out.println("Moving defensively");
    return 2;
  }
}

class Normal implements IBehaviour{
  public int moveCommand(){
    System.out.println("Moving normally");
    return 3;
  }

}

interface IBehaviour {
	public int moveCommand();
}

public class RobotGame {

	public static void main(String[] args) {

		Robot r1 = new Robot("Big Robot");
		Robot r2 = new Robot("George v.2.1");
		Robot r3 = new Robot("R2");

		r1.setBehavior("Normal");
		r2.setBehavior("Normal");
		r3.setBehavior("Normal");

		r1.behave();
		r2.behave();
		r3.behave();

		//change the behaviors of each robot.
		r1.setBehavior("aggresive");
		r2.setBehavior("defensive");
		r3.setBehavior("norm");

		r1.behave();
		r2.behave();
		r3.behave();
	}
}
