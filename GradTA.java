package taManager;

/* Tsipora Stone
 * 114110213
 * 0108
 * I pledge on my honor that I have not given or received
 * any unauthorized assistance on this assignment*/

// class extends TA and contains methods of graduate TAs
public class GradTA extends TA {
	
	private static TAManager.TAType whichType= TAManager.TAType.GRADUATE;
	
	public GradTA(double flatSalary, String firstName, String lastName){
		super(flatSalary, firstName, lastName);
		
	}
	
	// returns the type of the TA - UNDERGRADUATE or GRADUATE
	@Override
	public TAManager.TAType getType() {
		return whichType;
	}
	
	// overrides getPay method in TA class 
	// returns pay of a graduate TA
	@Override
	public double getPay(){
		return salary / 21.0;
	}
	
	
	
	
}
