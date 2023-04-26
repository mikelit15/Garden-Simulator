import java.util.HashSet;

public class saveGarden implements java.io.Serializable {
//add data you want to save
	private static final long serialVersionUID = 1L;
	
	public String budgett;
	public SoilType soilTypeDD;
	public Sunlight sunLightDD;
	public Moisture moistureDD;
	public HashSet<Plant> plantsInGarden;
}
