/**
 * Represents different levels of sunlight
 * @author sunil
 *
 */
public enum Sunlight {
	//helps set sunlight level

	SHADE("Shade"),
	PARTIALSUN("Partial Sun"),
	FULLSUN("Full Sun");
	
	private String name = null;
	
	/**
	 * Constructor for the sunlight
	 * @param sunlight the name of the sunlight level
	 */
	private Sunlight(String sunlight){
		name = sunlight;
	}
	
	/**
	 * Returns the sunlight
	 * @return - a string representing the type of sunlight
	 * @author sunil
	 */
	public String getName() {
		return name;
	}
}
