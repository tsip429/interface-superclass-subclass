package taManager;

/* Tsipora Stone
 * 114110213
 * 0108
 * I pledge on my honor that I have not given or received
 * any unauthorized assistance on this assignment*/

// class extends TA and has methods for undergrad TAs
public class UndergradTA extends TA {
	
	public static TAManager.TAType whichType=TAManager.TAType.UNDERGRADUATE;
	
	public UndergradTA(double hourlyWage, String firstName, String lastName){
		super(hourlyWage, firstName, lastName);
	}

	// overridden method returns the type of the TA - UNDERGRADUATE or GRADUATE
	@Override
	public TAManager.TAType getType() {
		return whichType;
	}
	
	// overridden method returns the pay of the grad TA
	@Override
	public double getPay(){
		return (officeHours + .5 * projects) * salary;
	}

}
