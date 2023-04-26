/**
 * Used to describe the moisture conditions for plant or garden
 * @author sunil
 *
 */
public enum Moisture {
	//This enum shows the overall moisture of the garden

	DRY("Dry"),
	MOIST("Moist"),
	WET("Wet"),
	FLOODED("Flooded");

	
	private String name = null;
	
	/**
	 * Constructor for moisture
	 * @param moisture the name of the moisture
	 */
	private Moisture(String moisture){
		name = moisture;
	}
	
	/**
	 * gets the current moisture
	 * @return - the amount of moisture (String)
	 * @author sunil
	 */
	public String getName() {
		return name;
	}


}
