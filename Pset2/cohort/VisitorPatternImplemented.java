import java.util.ArrayList;

public class VisitorPatternImplemented{

	public static void main(String[] args){
		SUTD oneSUTD = new SUTD ();

		ArrayList<EmployeeVisitable> employees = oneSUTD.getEmployees();

    EmployeeInfoVisitor visitor = new EmployeeInfoVisitor();

		//auditing
		for (EmployeeVisitable employee: employees){
      employee.accept(visitor);
    }
	}
}

class SUTD {
	private ArrayList<EmployeeVisitable> employees;

	public SUTD () {
		employees = new ArrayList<EmployeeVisitable>();
		employees.add(new Professor("Sun Jun", 0));
		employees.add(new AdminStaff("Stacey", 5));
		employees.add(new Student("Allan", 3));
	}

	public ArrayList<EmployeeVisitable> getEmployees () {
		return employees;
	}
}

interface Visitor{
  public void visit(Professor professor);
  public void visit(AdminStaff adminStaff);
  public void visit(Student student);

}

class EmployeeInfoVisitor implements Visitor{
  public void visit(Professor professor){
    System.out.println("Prof: " + professor.getName() + " "+ professor.getNo_of_publications());
  }

  public void visit(AdminStaff adminStaff){
    System.out.println("Admin Staff: " + adminStaff.getName() + " " + adminStaff.getEfficiency());
  }

  public void visit(Student student){
    System.out.println("Student: " + student.getName() + " " + student.getGPA());
  }

}

interface EmployeeVisitable {
  public void accept(Visitor visitor);
}

class Professor implements EmployeeVisitable{
	private String name;
	private int no_of_publications;

	public Professor (String name, int no_of_publications) {
		this.name = name;
		this.no_of_publications = no_of_publications;
	}

	public String getName () {
		return name;
	}

	public int getNo_of_publications() {
		return no_of_publications;
	}

  public void accept(Visitor visitor){
    visitor.visit(this);
  }

}

class AdminStaff implements EmployeeVisitable {
	private String name;
	private float efficiency;

	public AdminStaff (String name, float efficiency) {
		this.name = name;
		this.efficiency = efficiency;
	}

	public String getName() {
		return name;
	}

	public float getEfficiency() {
		return efficiency;
	}

  public void accept(Visitor visitor){
    visitor.visit(this);
  }
}

class Student implements EmployeeVisitable{
	private String name;
	private float GPA;

	public Student (String name, float GPA) {
		this.name = name;
		this.GPA = GPA;
	}

	public String getName() {
		return name;
	}

	public float getGPA() {
		return GPA;
	}

  public void accept(Visitor visitor){
    visitor.visit(this);
  }
}
