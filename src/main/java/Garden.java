import java.util.*;

/**
 * Class to store all of the information in each garden
 * @author sunil
 *
 */

public class Garden 
{
	public Moisture moisture;
	InputScreen budgetD;
	public double budget;
	public double budgetUsed;
	public String bud;
	public SoilType soil;
	public Sunlight light;
	public int TotalLepCount;
	public int gardenX;
	public int gardenY;
	public double scale;
	HashMap<String, Plant> thePlants = new HashMap<String, Plant>();
	HashSet<Plant> plantsToAdd = new HashSet<Plant>();
	HashSet<Plant> plantsInGarden = new HashSet<Plant>();
	HashSet<Lep> lepsToAdd = new HashSet<Lep>();
	Model model;
	int plantTotal = 0;
	
	/**
	 * creates the garden and gives it a model
	 * @param model the model part of MVC
	 * @author sunil
	 */
	public Garden(Model model) {
		this.model = model;
	}
	
	/**
	 * updates the info for the garden
	 * @param moisture - moisture enum
	 * @param budget - double representing the remaining budget
	 * @param soil - the type of soil in the garden
	 * @param light - amount of ligt in garden
	 * @author sunil
	 */
	public void updateGarden(Moisture moisture, SoilType soil, Sunlight light) 
	{
		this.moisture = moisture;
		this.soil = soil;
		this.light = light;
	}
	
	/**
	 * adds new plant to the garden
	 * @param plantName the name of the plant
	 * @author sunil
	 */
	public void updateGarden(String plantName)
	{
		Iterator<Plant> plantIterator = plantsToAdd.iterator();
		while(plantIterator.hasNext()) 
		{
			Plant check = plantIterator.next();
			if (check.getName().equals(plantName))
			{
				Plant addedPlant = new Plant(plantName, check.getPrice(), check.getNumLeps());
				thePlants.put(addedPlant.getName(), addedPlant);
				plantsInGarden.add(check);
				TotalLepCount += addedPlant.getNumLeps();
				budgetUsed += addedPlant.getPrice();	
				plantTotal += 1;
				if (budget-budgetUsed <= 0) 
				{
					model.getController().budgetWarning();
				}
			}
		}
	}
	
	/**
	 * Retrieves data for to save garden.
	 * @return data - the data that will be saved.
	 * @author parth
	 */
	public saveGarden retrieveData() {
    	saveGarden data = new saveGarden();
    	data.budgett = getGardenBudget();
    	System.out.println(data.budgett);
		data.soilTypeDD = getSoilType();
		System.out.println(data.soilTypeDD);
		data.sunLightDD = getSunlight();
		System.out.println(data.sunLightDD);
		data.moistureDD = getMoisture();
		System.out.println(data.moistureDD);
		data.plantsInGarden = getPlantsInGarden();
		System.out.println(data.plantsInGarden);
    	return data;
    }

	/**
	 * Recalls data that was previously saved to load.
	 * @param data - the data that was previously saved which will be loaded.
	 * @authot parth
	 */
	public void recallData(saveGarden data) {
		updateGardenBudget(data.budgett);
		System.out.println(data.budgett);
		updateGarden(data.soilTypeDD);
		System.out.println(data.soilTypeDD);
		updateGarden(data.sunLightDD);
		System.out.println(data.sunLightDD);
		updateGarden(data.moistureDD);
		System.out.println(data.moistureDD);
		updateGarden(data.plantsInGarden);
		System.out.println(data.plantsInGarden);
		setData(data);
	}

	/**
	 * Sets data from model. Wrapper for setData.
	 * @param data - data that was loaded and needs to be set back to values
	 * @author parth
	 */
	public void setData(saveGarden data) {
		model.setData(data);
	}

	
	/**
	 * removes a given plant from the garden's statistics
	 * @param plantName - the name of the type of plant
	 * @author sunil, Mike
	 */
	public void removePlant(String plantName) 
	{
		Plant removedPlant = thePlants.get(plantName);
		TotalLepCount -= removedPlant.getNumLeps();
		budgetUsed -= removedPlant.getPrice();
		plantTotal -= 1;
		Iterator<Plant> possiblePlants = plantsToAdd.iterator();
		while(possiblePlants.hasNext())
		{
			Plant plantToRemove = possiblePlants.next();
			if(plantToRemove.getName().equals(plantName))
			{
				plantsInGarden.remove(plantToRemove);
			}
		}
	}
	
	/**
	 * get the GardensX
	 * @param none
	 * @return - gardenX
	 * @author sunil
	 */
	public int getGardenX()
	{
		return gardenX;
	}
	/**
	 * get the GardensY
	 * @param none
	 * @return - gardenY
	 * @author sunil
	 */
	public int getGardenY()
	{
		return gardenY;
	}
	
	/**
	 * @param scale-the entered scale value in the scale tab from DrawScreen
	 * @author Nick
	 */
	public void updateGardenScale(double scale) {
		this.scale=scale;
	}
	
	/**
	 * @return int- which is the entered scale value in the scale tab from DrawScreen
	 * @author Nick
	 */
	public double getScale() {
		return this.scale;
	}
	
	/**
	 * @return doub_s/5 which is the width of scale tool because width=(1/5)length
	 * @author Nick
	 */
	public double getScaleW() {
		double doub_s=Double.valueOf(this.scale);
		return doub_s/5.0;
	}
	
	/**
	 * Sets the budget upon an update
	 * @param budget new number for the budget
	 * @author sunil
	 */
	public void updateGarden(double budget) {
		this.budget = budget;
	}
	
	public void updateGarden(HashSet<Plant> plants) {
		this.plantsInGarden = plants;
	}
	
	/**
	 * Updates garden budget. Converts double budget into string.
	 * @param budget - the new budget passed in as string
	 * @author parth
	 */
	public void updateGardenBudget(String budget) {

		bud = String.valueOf(budget);
	}
	
	/**
	 * Sets the budget upon an update
	 * @param soil new number for the budget
	 * @author sunil
	 */
	public void updateGarden(SoilType soil) {
		this.soil = soil;
	}
	
	/**
	 * Sets the budget upon an update
	 * @param sunLight new number for the budget
	 * @author sunil
	 */
	public void updateGarden(Sunlight sunLight) {
		this.light = sunLight;
	}
	
	/**
	 * Sets the budget upon an update
	 * @param moisture new number for the budget
	 * @author sunil
	 */
	public void updateGarden(Moisture moisture) {
		this.moisture = moisture;
	}
	
	/**
	 * Getter for the budget
	 * @return a double representing the budget
	 * @author sunil
	 */
	public double getBudget() {
		return this.budget;
	}
	
	/**
	 * Gets string value of budget which is double.
	 * @return string value of double budget
	 * @author parth
	 */
	public String getGardenBudget() {
		return String.valueOf(budget);
	}
	
	/**
	 * Gets Soil type.
	 * @return soil - the soil type selected by user
	 * @author parth
	 */
	public SoilType getSoilType() {
		return this.soil;
	}

	/**
	 * Gets Sunlight.
	 * @return sunlight - the sunlight selected by user
	 * @author parth
	 */
	public Sunlight getSunlight() {
		return this.light;
	}

	/**
	 * Gets moisture.
	 * @return moisture - the moisture selected by user
	 * @author parth
	 */
	public Moisture getMoisture() {
		return this.moisture;
	}

	/**
	 * Getter for the budget remaining
	 * @return a double representing the budget left
	 * @author sunil
	 */
	public double getRemainingBudget() {
		return this.budget - this.budgetUsed;
	}
	
	/**
	 * getter for the total number of leps supported by the garden
	 * @return int representing total lep count
	 * @author sunil
	 */
	public int getLeps() {
		return this.TotalLepCount;
	}
	
	/**
	 * Sets the possible plants for the garden
	 * @author sunil
	 */
	public void setPossiblePlants() {
		Iterator<Plant> iterator = model.possiblePlants.iterator();
		plantsToAdd = new HashSet<Plant>();
		while(iterator.hasNext())
		{
			Plant pPlant = iterator.next();
			if (pPlant.getMoisture() == moisture && pPlant.getSoil() == soil && pPlant.getSun() == light) {
				plantsToAdd.add(pPlant);
			}
		}
	}
	
	/**
	 * Sets the possible leps for the garden
	 * 
	 * @author Mike
	 */
	public void setPossibleLeps() {
		lepsToAdd = new HashSet<Lep>();
		Iterator<Plant> iterator = plantsInGarden.iterator();
		while(iterator.hasNext()) 
		{
			Plant plants = iterator.next();
			Iterator<Lep> iterator2 = model.possibleLeps.iterator();
			while(iterator2.hasNext())
			{	
				Lep allLeps = iterator2.next();
				Iterator<Lep> iterator3 = plants.getLeps().iterator();
				while(iterator3.hasNext())
				{
					Lep lepsInPlant = iterator3.next();
					if(allLeps.getCommonName().equals(lepsInPlant.getCommonName()))
					{
						lepsToAdd.add(lepsInPlant);
					}
				}
			}
		}	
	}
	
	/**
	 * gets the names of all plants which fit constraints to get put in the garden
	 * @return a hashset of all of the names
	 * @author sunil
	 */
	public HashSet<String> getAllPlantNames(){
		HashSet<String> names = new HashSet<String>();
		Iterator<Plant> iterator = plantsToAdd.iterator();
		while (iterator.hasNext()) {
			names.add(iterator.next().getName());
		}
		return names;
	}
	
	/**
	 * gets the names of all leps which fit constraints to get put in the garden
	 * 
	 * @return a hashset of all of the names
	 * @author Mike
	 */
	public HashSet<String> getAllLepNames()
	{
		HashSet<String> names = new HashSet<String>();
		Iterator<Lep> iterator = lepsToAdd.iterator();
		while(iterator.hasNext())
		{
			names.add(iterator.next().getCommonName());
		}
		return names;
	}
	
	public HashSet<Plant> getPlantsInGarden(){
		return this.plantsInGarden;
	}
	/**
	 * Getter for total plants in garden
	 * @return an int representing the total plants in the garden
	 * @author sunil
	 */
	public int getPlantTotal() {
		return this.plantTotal;
	}
	
	/**
	 * Sets the models values at zero when needed
	 * @author sunil
	 */
	public void setZeros() {
		TotalLepCount = 0;
		budgetUsed = 0;
	}
}
