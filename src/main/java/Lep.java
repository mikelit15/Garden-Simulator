/**
 * Holds the information for the leps
 * 
 * @author Mike
 */
public class Lep

{
	public String nameScientific;
	public String nameCommon;

	/**
	 * Constructor for a lep object
	 * 
	 * @param nameCommon - the common name of the lep
	 * @param nameScientific - the scientific name of the lep
	 * @author Mike
	 */
	public Lep(String nameCommon, String nameScientific) 
	{
		this.nameScientific = nameScientific;
		this.nameCommon = nameCommon;
	}
	
	/**
	 * Second constructor for a lep object
	 * 
	 * @param nameCommon - the common name of the lep
	 * @author Mike
	 */
	public Lep(String nameCommon)
	{
		this.nameCommon = nameCommon;
	}

	/**
	 * Getter for the scientific name of the lep
	 * 
	 * @return String - the scientific name of the lep
	 * @author Mike
	 */
	public String getName()
	{
		return nameScientific;
	}
	
	/**
	 * Getter for the common name of the lep
	 * 
	 * @return String - the common name of the lep
	 * @author Mike
	 */
	public String getCommonName()
	{
		return nameCommon;
	}
}	