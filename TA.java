package taManager;

/* Tsipora Stone
 * 114110213
 * 0108
 * I pledge on my honor that I have not given or received
 * any unauthorized assistance on this assignment*/

// abstract class TA that contains methods for all TAs
public abstract class TA {
	int officeHours;
	int projects;
	double salary;
	String firstName;
	String lastName;
	TAManager.TAType type;
	
	public TA (double money, String name1, String name2){
		salary = money;
		firstName = name1;
		lastName = name2;
	}
	
	// returns salary 
	public double getSalary(){
		return salary;
	}
	
	// returns first name of TA
	public String getFirstName(){
		return firstName;
	}
	
	// returns last name of TA
	public String getLastName(){
		return lastName;
	}
	
	// returns office hours of TA
	public int getOfficeHours(){
		return officeHours;
	}
	
	// returns number of graded projects of TA
	public int getGradedProjects(){
		return projects;
	}
	
	// sets the office hours of TA to numOfficeHours
	public void setOfficeHours(int numOfficeHours){
		officeHours = numOfficeHours;
	}
	
	// sets the number of graded projects of TA to numProjects
	public void setGradedProjects(int numProjects){
		projects = numProjects;
	}
	
	// abstract method getType that will get the type of the TA
	public abstract TAManager.TAType getType();
	
	// abstract method getPay that will get the pay of the TA
	public abstract double getPay();
}


