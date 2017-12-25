package taManager;
//creates courses in the University and returns them
public class University {

//creates either CMSC courses or NonCMSC courses depending on the arguments
	public static Course createCourse(String department, int courseNumber, int maxNumTAs) {
		Course course = null;
		//if department isn't null and the courseNumber and maxNumTAs are greater than 0
		//checks if the department is CMSC or anything else
		if ((!(department==null)) && !(department.length() == 0) 
				&& courseNumber > 0 && maxNumTAs > 0) {
				course = new Course(department, courseNumber, maxNumTAs);
		}
		return course;
	}
}
