import java.util.HashSet;
import java.util.Set;

//this class is thread-safe.
public class PersonSet {
	//@guarded by "this"
	private final Set<Person> mySet = new HashSet<Person>();
	//note that HashSet is not thread-safe!

	public synchronized void addPerson(Person p) {
    // This is escaping its scope!
    // Since anyone can maintain a reference to the Person
    // object and modify it as it wishes
		mySet.add(p);
	}

	public synchronized boolean containsPerson (Person p) {
		return mySet.contains(p);
	}

	class Person {
	}
}
