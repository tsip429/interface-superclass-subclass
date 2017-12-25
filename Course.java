package taManager;

import taManager.TAManager.TAType;

/* Tsipora Stone
 * 114110213
 * 0108
 * I pledge on my honor that I have not given or received
 * any unauthorized assistance on this assignment*/

//class implements TAManager and contains methods about the courses
public class Course implements TAManager {
	private String department;
	private int numCourse;
	private int maxTas;
	private int numUndergrad;
	private int numGrad;
	TA[] TAs;

	// constructor for Course
	public Course(String department, int courseNum, int maxNumTas) {
		this.department = department;
		numCourse = courseNum;
		maxTas = maxNumTas;
		TAs = new TA[maxNumTas];
		numUndergrad = 0;
		numGrad = 0;
	}

	// returns maxTas
	public int getMaxTas() {
		return maxTas;
	}

	// returns the course name and number
	public String getCourseName() {
		return department + " " + numCourse;
	}

	// returns the numUndergrad
	public int getNumUndergrad() {
		return numUndergrad;
	}

	// returns the numGrad
	public int getNumGrad() {
		return numGrad;
	}

	// adds an UndergraduateTA to the TAs array - a field of Course objects
	public boolean hireUndergraduateTA(String firstName, String lastName, 
			double hourlySalary) {
		TA newUndergradTA = new UndergradTA(hourlySalary, firstName, lastName);
		// makes sure that first and last name are not null and hourlySalary is > 0
		if (!(nullCheck(firstName, lastName)) && 
				(!(emptyString(firstName, lastName)) && hourlySalary > 0)) {
			for (int j = 0; j < TAs.length; j++) {
				// checks that TAs at that position isn't null 
				//and if the first and last
				// names are equal
				// to others in the array
				if (TAs[j] != null && 
						newUndergradTA.getFirstName().equals(TAs[j].getFirstName())
						&& newUndergradTA.getLastName().equals(TAs[j].getLastName())) {
					System.out.println("same name");
					return false;
				}
			}
			// checks if the number of TAs is less than the limit of TAs and if it is
			// adds the new Undergrad object
			if (numUndergrad + numGrad < maxTas) {
				TAs[numUndergrad + numGrad] = newUndergradTA;
				numUndergrad++;
				return true;
			}
			// checks if the department is CMSC and if it is increases the maxTas and
			// creates a new array of that size with all the TAs copied to it
			else if (department.equals("CMSC")) {
				maxTas++;
				TA[] newTAs = new TA[maxTas];
				for (int i = 0; i < numUndergrad + numGrad; i++) {
					newTAs[i] = TAs[i];
				}
				// sets the old array to the new array and adds the Undergrad object to
				// the array
				TAs = newTAs;
				TAs[numUndergrad + numGrad] = newUndergradTA;
				numUndergrad++;
				return true;
			} else {
				return false;
			}

		}

		return false;
	}

	// adds a GraduateTA to the TAs array - a field of Course objects
	public boolean hireGraduateTA(String firstName, String lastName, 
			double yearlySalary) {
		TA newGradTA = new GradTA(yearlySalary, firstName, lastName);
		if (!(nullCheck(firstName, lastName)) && 
				(!(emptyString(firstName, lastName)) && yearlySalary > 0)) {
			for (int j = 0; j < TAs.length; j++) {
				// checks that TAs at that position isn't null 
				//and if the first and last
				// names are equal
				// to others in the array
				if (TAs[j] != null && 
						newGradTA.getFirstName().equals(TAs[j].getFirstName())
						&& newGradTA.getLastName().equals(TAs[j].getLastName())) {
					return false;
				}
			}
			// checks if the number of TAs is less than the limit of TAs and if it is
			// adds the new Grad object
			if (numUndergrad + numGrad < maxTas) {
				TAs[numUndergrad + numGrad] = newGradTA;
				numGrad++;
				return true;
			} 
			// checks if the department is CMSC and if it is increases the maxTas and
			// creates a new array of that size with all the TAs copied to it 
			else if (department.equals("CMSC")) {
				maxTas++;
				TA[] newTAs = new TA[maxTas];
				for (int i = 0; i < numUndergrad + numGrad; i++) {
					newTAs[i] = TAs[i];
				}
				// sets the old array to the new array and adds the Undergrad object to
				// the array
				TAs = newTAs;
				TAs[numUndergrad + numGrad] = newGradTA;
				numGrad++;
				return true;
			} else {
				return false;
			}

		}

		return false;
	}

	// returns the total number of TAs
	public int numTAs() {
		return numUndergrad + numGrad;
	}

	// returns the number of TAs of type whichType - graduate or undergraduate
	public int numTAs(TAType whichType) {
		if (whichType == TAManager.TAType.UNDERGRADUATE) {
			return numUndergrad;
		} else {
			return numGrad;
		}
	}

	// returns the current TA capacity
	public int taCapacity() {
		return TAs.length;
	}

	// increases the field maxTas by numTAsToAdd and creates a new array of that
	// size with copied references to the old array
	public boolean increaseTACapacity(int numTAsToAdd) {
		boolean increased = false;
		if (numTAsToAdd > 0) {
			maxTas += numTAsToAdd;
			TA[] newTAs = new TA[maxTas];
			// adds old items to the new array
			for (int i = 0; i < TAs.length; i++) {
				newTAs[i] = TAs[i];
				increased = true;
			}
			// sets the old array to the new array
			TAs = newTAs;
		}
		return increased;
	}

	// orders the TAs alphabetically according to their names and returns 
	// the names as a String 
	public String getTANames() {
		String names = "";
		if (numTAs() != 0) {
			BubbleSortNames(TAs);
			for (int i = 0; i < numTAs(); i++) {
				// checks if TAs at that position is null and if the next position is 
				// end of the list - puts a comma after each TA
				if (TAs[i] != null && i + 1 != numTAs()) {
					names = names + TAs[i].getFirstName() + " " + TAs[i].getLastName() + ", ";
					// if TAs at that position is not null and the element at that position 
					// is the end of the list - doesn't have a comma at the end
				} else if (TAs[i] != null && i + 1 == numTAs()) {
					names += TAs[i].getFirstName() + " " + TAs[i].getLastName();
				}
			}
		}
		return names;
	}

	// helper method that sorts all of the TA names in alphabetical order
	private void BubbleSortNames(TA[] arr) {
		TA temp;
		for (int i = 0; i < arr.length - 1; i++) {
			for (int j = 1; j < arr.length - i; j++) {
				// checks if either position is null and if the ASCII value of the first is 
				// greater than the second - if it is shifts the old one over one and the new one
				// to the position before it
				if (arr[j - 1] != null && arr[j] != null && 
						arr[j - 1].getLastName().compareTo(arr[j].getLastName()) > 0) {
					temp = arr[j - 1];
					arr[j - 1] = arr[j];
					arr[j] = temp;
					// checks if either position is null and if the ASCII value of the first is 
					// equal to the second - if it is checks the first names to see which is 
					// greater and shifts the elements in the array over
				} else if (arr[j - 1] != null && arr[j] != null
						&& arr[j - 1].getLastName().compareTo(arr[j].getLastName()) == 0) {
					for (int k = 0; k < arr.length - 1; k++) {
						for (int l = 1; l < arr.length - k; l++) {
							if (arr[l - 1] != null && arr[l] != null && arr[l - 1].getFirstName().
									compareTo(arr[l].getFirstName()) > 0) {
								temp = arr[l - 1];
								arr[l - 1] = arr[l];
								arr[l] = temp;
							}
						}
					}
				}
			}
		}
	}

	// adds officeHours for TAs depending on if they are graduate or undergraduate
	public boolean holdOfficeHours(String firstName, String lastName, int numHours) {
		boolean officeHours = false;
		int newOfficeHours = 0;
		// checks if firstName or lastName are null or empty strings and that numHours 
		// is greater than 0
		if (!(nullCheck(firstName, lastName)) && 
				!(emptyString(firstName, lastName)) && numHours > 0) {
			for (int i = 0; i < numTAs(); i++) {
				// checks if TAs is null at that position and if a TA exists in the 
				// array with that name 
				if (TAs[i] != null && TAs[i].getFirstName().equals(firstName) 
						&& TAs[i].getLastName().equals(lastName)) {
					newOfficeHours = TAs[i].getOfficeHours();
					// checks if TAType is GRADUATE or UNDERGRADUATE 
					// if it's GRADUATE adds if it's UNDERGRADUATE checks if the 
					// hours are < 20 then adds numHours
					if (TAs[i].getType().equals(TAManager.TAType.GRADUATE)) {
						newOfficeHours += numHours;
						TAs[i].setOfficeHours(newOfficeHours);
						officeHours = true;
					} else if (TAs[i].getType().equals(TAManager.TAType.UNDERGRADUATE)) {
						if (newOfficeHours + numHours <= 20) {
							newOfficeHours += numHours;
							TAs[i].setOfficeHours(newOfficeHours);
							officeHours = true;

						}
					}
				}
			}
		}
		return officeHours;
	}

	// counts the number of hours the TA with firstName lastName graded
	public int numOfficeHours(String firstName, String lastName) {
		int officeHrs = -1;
		// checks if firstName and lastName are null or empty 
		if (!(nullCheck(firstName, lastName)) && 
				!(emptyString(firstName, lastName))) {
			for (int i = 0; i < TAs.length; i++) {
				// checks that TAs at that position isn't null and that the TA
				// with firstName, lastName exists in the array
				// then gets the office hours of the TA and returns it
				if (TAs[i] != null && TAs[i].getFirstName().equals(firstName)
						&& TAs[i].getLastName().equals(lastName)) {
					officeHrs = TAs[i].getOfficeHours();
				}
			}
		}
		return officeHrs;
	}

	// adds projects to the number of projects the TAs have graded
	public boolean gradeProjects(String firstName, String lastName, int numProjects) {
		boolean graded = false;
		int newGraded = 0;
		// checks that firstName and lastName aren't null or empty and that numProjects
		// is > 0
		if (!(nullCheck(firstName, lastName)) && 
				!(emptyString(firstName, lastName)) && numProjects > 0) {
			for (int i = 0; i < numTAs(); i++) {
				// checks that TAs at that position isn't null and there is a TA that
				// has firstName and lastName in the array
				if (TAs[i] != null && TAs[i].getFirstName().equals(firstName) 
						&& TAs[i].getLastName().equals(lastName)) {
					newGraded = TAs[i].getGradedProjects();
					// checks if TA is GRADUATE or UNDERGRADUATE 
					// if it's GRADUATE just adds the project
					// if it's UNDERGRADUATE checks if their total office hours +
					// projects graded are less than 20 and then adds projects
					if (TAs[i].getType().equals(TAManager.TAType.GRADUATE)) {
						if (numProjects + newGraded <= 150) {
							newGraded += numProjects;
							TAs[i].setGradedProjects(newGraded);
							graded = true;
						}
					} else if (TAs[i].getType().equals(TAManager.TAType.UNDERGRADUATE)) {
						if (.5 * numProjects + numOfficeHours(firstName, lastName) <= 20) {
							newGraded += numProjects;
							TAs[i].setGradedProjects(newGraded);
							graded = true;
						}
					}
				}
			}
		}
		return graded;
	}

	// returns the number of projects the TA with firstName, lastName graded
	public int numProjectsGraded(String firstName, String lastName) {
		int projects = -1;
		// checks that neither name is null or empty
		if (!(nullCheck(firstName, lastName)) 
				&& !(emptyString(firstName, lastName))) {
			for (int i = 0; i < TAs.length; i++) {
				// checks that TAs at that position is not null and that a TA with 
				// firstName, lastName exists in the array
				if (TAs[i] != null && TAs[i].getFirstName().equals(firstName) 
						&& TAs[i].getLastName().equals(lastName)) {
					projects = TAs[i].getGradedProjects();
				}
			}
		}
		return projects;
	}

	//returns the total pay of undergrad and grad TAs
	public double getPay(String firstName, String lastName) {
		double pay = 0.0;
		// checks that names aren't null or empty strings
		if (!(nullCheck(firstName, lastName)) &&
				!(emptyString(firstName, lastName))) {
			for (int i = 0; i < TAs.length; i++) {
				// checks that TAs at that position isn't null and that a TA
				// exists with firstName, lastName in the array 
				// then calls the method getPay() which is overridden in UndergradTA
				// and GradTA
				if (TAs[i] != null && TAs[i].getFirstName().equals(firstName) 
						&& TAs[i].getLastName().equals(lastName)) {
					pay = TAs[i].getPay();
				}
			}
		}
		return pay;
	}

	// helper method checks if firstName or lastName are null
	private boolean nullCheck(String firstName, String lastName) {
		boolean isNull = false;
		if (firstName == null || lastName == null) {
			isNull = true;
		}
		return isNull;
	}

	// helper method checks if firstName or lastName are empty strings
	private boolean emptyString(String firstName, String lastName) {
		boolean isEmpty = false;
		if (firstName.length() == 0 || lastName.length() == 0) {
			isEmpty = true;
		}
		return isEmpty;
	}
}
