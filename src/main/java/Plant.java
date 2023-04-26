import java.util.HashSet;

/**
 * Holds the information for any given plant in the model
 * @author sunil
 *
 */
public class Plant 
{
	public String nameScientific;
	public String nameCommon;
	public SoilType perfferedSoil;
	public Sunlight perfferedSun;
	public Moisture perfferedMoist;
	public int numLeps;
	public double price;
	public int radius;
	public double x = 100;
	public double y = 200;
	boolean woody;
	public HashSet<Lep> leps = new HashSet<Lep>();
	
	/**
	 * Object representing the data behind each plant
	 * @param nameTechnical - the scientific name of the plant
	 * @param nameCommon - the common name of the plant
	 * @param lepNumber - the number of leps the plant supports
	 * @param price - the price per plant
	 * @param radius - the root spread of the plant
	 * @param perfferedSoil - the soil in which to plant the plant
	 * @param perfferedSun - the type of sunlight for the plant
	 * @param perfferedMoist - the preffered moisture level of the plant
	 * @param leps - the HashSet of leps that each plant can host
	 * @author sunil
	 */
	public Plant(String nameCommon, String nameScientific,  int radius, int numLeps, boolean woody, SoilType perfferedSoil, Sunlight perfferedSun, Moisture perfferedMoist, HashSet<Lep> leps) 
	{
		this.nameScientific = nameScientific;
		this.nameCommon = nameCommon;
		this.numLeps = numLeps;
		this.woody = woody;
		this.radius = radius;
		this.perfferedSoil = perfferedSoil;
		this.perfferedSun = perfferedSun;
		this.perfferedMoist=perfferedMoist;
		this.leps = leps;
		if (woody) {
			this.price = 20.00;
		} else {
			this.price = 6.00;
		}
	}
	
	/**
	 * second constructor for the plant data object
	 * @param nameTechnical - the scientific name of the plant
	 * @param price - the price per plant
	 * @param leps - the number of leps the plant supports
	 * @author sunil
	 */
	public Plant(String nameScientific, double price, int numLeps) 
	{
		this.nameScientific = nameScientific;
		this.price = price;
		this.numLeps = numLeps;
	}
	
	/**
	 * third constructor for the plant data object
	 * @param nameScientific - the scientific name of the plant
	 * @param price - the price per plant
	 * @author sunil
	 */
	public Plant(String nameScientific, int price) 
	{
		this.nameScientific = nameScientific;
		this.price = price;
	}
	
	public Plant(String nameCommon) 
	{
		this.nameCommon = nameCommon;
	}
	
	/**
	 * getter for the scientific name of the plant
	 * @return string representing the scientific name of the plant
	 * @author sunil
	 */
	public String getName()
	{
		return nameScientific;
	}
	
	/**
	 * Getter for the common name of the plant
	 * 
	 * @return String - the common name of the plant
	 * @author Mike
	 */
	public String getCommonName()
	{
		return nameCommon;
	}
	
	/**
	 * Getter for the HashSet of leps that each plant can host
	 * 
	 * @return HashSet<Lep> - the set of leps that each plant can host
	 * @author Mike
	 */
	public HashSet<Lep> getLeps()
	{
		return this.leps;
	}
	
	/**
	 * getter for the price of the plant
	 * @return int representing price of the plant
	 */
	public double getPrice() {return this.price;}
	
	/**
	 * getter for the leps supported by the plant
	 * @return int representing the leps supported by the plant
	 * @author sunil
	 */
	public int getNumLeps() {return this.numLeps;}
	
	/**
	 * getter for the soil the plant needs
	 * @return soiltype enum representing the proper soil
	 * @author sunil
	 */
	public SoilType getSoil() {
		return this.perfferedSoil;
	}
	
	/**
	 * getter for the light the plant needs
	 * @return sunlight enum representing the proper amount of light
	 * @author sunil
	 */
	public Sunlight getSun() {
		return this.perfferedSun;
	}
	
	/**
	 * getter for the moisture the plant needs
	 * @return moisture enum representing the proper amount of moisture
	 * @author sunil
	 */
	public Moisture getMoisture() {
		return this.perfferedMoist;
	}
}