/**
 * Holds the information for uses of different types of soil
 * @author sunil
 *
 */
public enum SoilType {
	//This enum helps change the type of soil

	CLAY("Clay"),
	SANDY("Sandy"),
	SILTY("Silty"),
	PEATY("Peaty"),
	CHALKY("Chalky"),
	LOAMY("Loamy");
	
	
	private String name = null;
	
	/**
	 * Constructor for the soiltype
	 * @param soil the name of the soiltype
	 */
	private SoilType(String soil){
		name = soil;
	}
	
	/**
	 * Returns the type of soil
	 * @return - a string representing the type of soil
	 * @author sunil
	 */
	public String getSoil() {
		return name;
	}


}
