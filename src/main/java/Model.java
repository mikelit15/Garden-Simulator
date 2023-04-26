import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 * Model component of MVC
 * 
 * @author sunil
 */
public class Model implements java.io.Serializable {

	Garden garden;
	HashSet<Plant> possiblePlants = new HashSet<Plant>();
	HashSet<Lep> possibleLeps = new HashSet<Lep>();
	Controller controller;
	saveGarden data;
	double doub;

	private static final long serialVersionUID = 1L;
	/*
	 * public String budgett; public SoilType soilTypeDD; public Sunlight
	 * sunLightDD; public Moisture moistureDD; public WritableImage getImagee;
	 **/

	public saveGarden retrieveData() {
		saveGarden data = new saveGarden();
		data = garden.retrieveData();
		return data;
	}

	public void setData(saveGarden data) {
		controller.setData(data);
	}

	public void processSave() {
		FileChooser fc = new FileChooser();
		  fc.getExtensionFilters().add(new ExtensionFilter("Text Files", "*.ser"));
		  File sF = fc.showSaveDialog(null);
		  data = retrieveData();
		  
		  try {
//			  Model.saveData(data, sF);
			  savePlants(sF);
			  Text saved = new Text("Garden Saved");
	          	saved.setFont(Font.font("Calibri", FontWeight.BOLD, FontPosture.REGULAR, 50));
	          	
	          	VBox layout = new VBox(10);
	          	layout.getChildren().addAll(saved);
	          	layout.setAlignment(Pos.CENTER);
	          	
	          	Scene scene = new Scene(layout);
	          	Stage addedStage = new Stage();
	          	addedStage.setScene(scene);
	          	addedStage.showAndWait(); 
	    	}
			 
        	
		  catch (Exception e1) {
			  System.out.println("Not Saved");
		  }
	}

	public void processLoad() {
		FileChooser fc = new FileChooser();
		fc.getExtensionFilters().add(new ExtensionFilter("Text Files", "*.txt"));
		File sF = fc.showOpenDialog(null);
		try {
			data = (saveGarden) loadData(sF);
			garden.recallData(data);
			Text saved = new Text("Garden Loaded");
			saved.setFont(Font.font("Calibri", FontWeight.BOLD, FontPosture.REGULAR, 50));

			VBox layout = new VBox(10);
			layout.getChildren().addAll(saved);
			layout.setAlignment(Pos.CENTER);

			Scene scene = new Scene(layout);
			Stage addedStage = new Stage();
			addedStage.setScene(scene);
			addedStage.showAndWait();

			System.out.println("gotcha");

		} catch (Exception e1) {
			System.out.println("Not loaded");
		}
	}

	/**
	 * 
	 * @param data     - Data that is being saved
	 * @param fileName - Name of the file being saved
	 * @throws Exception
	 * @author parth
	 */
	public static void saveData(Serializable data, File fileName) throws Exception 
	{
		try (FileOutputStream fos = new FileOutputStream(fileName); ObjectOutputStream oos = new ObjectOutputStream(fos)) 
		{
			
			oos.writeObject(data);
		}
	}
	
	public void savePlants(File fileName) throws Exception 
	{
		try (FileOutputStream fos = new FileOutputStream(fileName); ObjectOutputStream oos = new ObjectOutputStream(fos)) 
		{
			
			oos.writeObject(getGarden().getPlantsInGarden());
		}
	}

	/**
	 * 
	 * @param fileName - Takes in the file with the data
	 * @return ois.readObject() - reads the data and sets it back to variables
	 * @throws Exception
	 * @author parth
	 */
	public static Object loadData(File fileName) throws Exception {
		try (FileInputStream fos = new FileInputStream(fileName); ObjectInputStream ois = new ObjectInputStream(fos)) {
			return ois.readObject();
		}

	}

	/**
	 * getter for garden
	 * 
	 * @return the garden of this model
	 * @author sunil
	 */
	public Garden getGarden() {
		return this.garden;
	}

	/**
	 * Creates model part of mvc
	 * 
	 * @param controller - this models controller
	 * @author sunil
	 */
	public Model(Controller controller) {
		importPlants();
		importLeps();
		this.controller = controller;
		garden = new Garden(this);
	}

	/**
	 * Returns the controller for this model
	 * 
	 * @return controller
	 * @author sunil
	 */
	public Controller getController() {
		return this.controller;
	}

	/**
	 * Getter for the possible plants for any garden
	 * 
	 * @return a hashset of the plants
	 * @author sunil
	 */
	public HashSet<Plant> getPossiblePlants() {
		return this.possiblePlants;
	}

	/**
	 * Getter for the possible leps for any garden
	 * 
	 * @return HashSet<Lep> - a hashset of the leps
	 * @author Mike
	 */
	public HashSet<Lep> getPossibleLeps() {
		return this.possibleLeps;
	}

	/**
	 * takes in information from the plants as a csv file and determines a list of
	 * possible plants
	 * 
	 * @author sunil
	 */
	public void importPlants() {
		Scanner sc = new Scanner(getClass().getResourceAsStream("Plants.csv"));
		sc.useDelimiter("\n");
		while (sc.hasNext()) {
			String addingLine = sc.next();
			String commonName = addingLine.split(",")[0];
			String sciName = addingLine.split(",")[1];
			int radius = Integer.valueOf(addingLine.split(",")[3]);
			Sunlight light = assignLight(addingLine.split(",")[4]);
			Moisture moisture = assignMoisture(addingLine.split(",")[5]);
			int numLeps = Integer.valueOf(addingLine.split(",")[7]);
			boolean Woody = addingLine.split(",")[8] == "T";
			SoilType soil = assignSoil(addingLine.split(",")[6]);
			Lep first = new Lep(addingLine.split(",")[10]);
			Lep second = new Lep(addingLine.split(",")[11]);
			Lep third = new Lep(addingLine.split(",")[12]);
			HashSet<Lep> leps = new HashSet<Lep>();
			leps.add(first);
			possiblePlants.add(new Plant(commonName, sciName, radius, numLeps, Woody, soil, light, moisture, leps));
		}
		sc.close();
	}

	/**
	 * takes in information from the leps as a csv file
	 * 
	 * @author sunil
	 */
	public void importLeps() {
		Scanner sc = new Scanner(getClass().getResourceAsStream("leps.csv"));
		sc.useDelimiter("\n");
		while (sc.hasNext()) {
			String addingLine = sc.next();
			String commonName = addingLine.split(",")[0];
			String sciName = addingLine.split(",")[1];
			possibleLeps.add(new Lep(commonName, sciName));
		}
		sc.close();
	}

	/**
	 * Assigns the sunlight enum based on an integer input
	 * 
	 * @param light int input from the csv file
	 * @return sunlight enum
	 * @author sunil
	 */
	public Sunlight assignLight(String light) {
		switch (light) {
		case "Shade":
			return Sunlight.SHADE;
		case "Partial":
			return Sunlight.PARTIALSUN;
		case "Full":
			return Sunlight.FULLSUN;
		default:
			return Sunlight.FULLSUN;
		}
	}

	/**
	 * Assigns the moisture enum based on an integer input
	 * 
	 * @param moisture int input from the csv file
	 * @return moisture enum
	 * @author sunil
	 */
	public Moisture assignMoisture(String moisture) {
		switch (moisture) {
		case "Dry":
			return Moisture.DRY;
		case "Moist":
			return Moisture.MOIST;
		case "Wet":
			return Moisture.WET;
		case "Flooded":
			return Moisture.FLOODED;
		default:
			return Moisture.FLOODED;
		}
	}

	/**
	 * Assigns the soiltype enum based on an integer input
	 * 
	 * @param soil int input from the csv file
	 * @return SoilType enum
	 * @author sunil
	 */
	public SoilType assignSoil(String soil) {
		switch (soil) {
		case "Chalky":
			return SoilType.CHALKY;
		case "Sandy":
			return SoilType.SANDY;
		case "Loamy":
			return SoilType.LOAMY;
		case "Peaty":
			return SoilType.PEATY;
		case "Silty":
			return SoilType.SILTY;
		case "Clay":
			return SoilType.CLAY;
		default:
			return SoilType.CLAY;
		}
	}

	/**
	 * Removes a plant from the garden
	 * 
	 * @param name string representing the plants name
	 * @author sunil
	 */
	public void removePlant(String name) {
		this.garden.removePlant(name);
	}

	/**
	 * 
	 * @param name
	 * @return
	 */
	public int radiusRet(String name) {
		for (Plant plants : possiblePlants) {
			if (plants.nameScientific.equals(name)) {
				return plants.radius;
			}
		}
		return 0;
	}

	/**
	 * @param radius- radius in feet of the root spread
	 * @return double that is the new scaled radius
	 * @author Nick
	 */
	public double radiusConvert(int radius) {
		this.doub = Double.valueOf(radius);
		this.doub = (doub * 40) / (garden.getScaleW());
		return doub;
	}

	/**
	 * @return get the converted radius
	 * @author Nick
	 */
	public double getradiusConvert() {
		return doub;
	}
}
