import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

/**
 * The screen responsible for adding all of the plants to the garden through the
 * use of drag and drop
 * 
 * @author sunil Nick
 *
 */

public class AddPlantsScreen extends AScreen {
	Button finish;
	private final int titleFont = 50;
	Button reset;
	public StackPane tpane;
	public FlowPane fpane;
	public static StackPane spane;
	public HashMap<String, PlantView> possiblePlants;
	public HashSet<PlantView> usablePlants;
	public static HashSet<PlantView> plantsInGarden = new HashSet<PlantView>();
	public HashSet<Circle> plants = new HashSet<Circle>();
	View view;
	int numPlantsAdded;
	double budget;
	int lepTOT;
	boolean first = true;
	boolean saved;
	int plant_amount;
	static ImageView iv;
	public TextFlow plantInfo;
	public TextFlow gardenInfo;
	public TextFlow TFP2;
	private final int titleOffset = 380;
	PlantInfo pInfo = new PlantInfo();
	GardenInfo gInfo = new GardenInfo();
	boolean ignoreAll = false;
	double translate = -700.00;
	final double offset=200.00;
	ListView<Circle> listView = new ListView<>();
    ObservableList<PlantView> plantViews = FXCollections.observableArrayList();
    ObservableList<Circle> plantImages = FXCollections.observableArrayList();

	/**
	 * creates the screen on which the user drags and drops plants
	 * 
	 * @param view   the view component of mvc
	 * @param plants a list of plants to be added
	 * @author sunil
	 */
	public AddPlantsScreen(View view, HashSet<Circle> plants) {
		super(view);
		this.view = view;
		possiblePlants = new HashMap<String, PlantView>();
		tpane = new StackPane();
		tpane.setStyle("-fx-background-color: #f2e6c6;");
		fpane = new FlowPane();
		fpane.setStyle("-fx-background-color: transparent");
		Text title = new Text("Add Plants to Your Garden");
		title.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, titleFont));
		title.setTranslateX(titleOffset);
		spane = new StackPane();
		spane.setStyle("-fx-background-color: #dfae43;");
		borderPane.setCenter(spane);
		borderPane.setBottom(tpane);
		plantInfo = new TextFlow();
		plantInfo = pInfo.getInfo();
		borderPane.setLeft(plantInfo);
		gardenInfo = new TextFlow();
		gardenInfo = gInfo.getInfo();
		borderPane.setRight(gardenInfo);
		importPlants();
	}
	
	/**
	 * Creates text on the right pane to help user keep track of what's happening in
	 * the garden
	 * 
	 * @param TextFlow text
	 * @return none
	 * @author Mike
	 */
	public void newPlantText(TextFlow text) {
		borderPane.setLeft(text);
	}

	/**
	 * Creates text on the right pane to help user keep track of what's happening in
	 * the garden
	 * 
	 * @param TextFlow text
	 * @return none
	 * @author Mike
	 */
	public void newGardenText(TextFlow text) {
		borderPane.setRight(text);
	}

	/**
	 * Sets the center of the canvas to reflect the updated drawScreen output. Adds
	 * the image to the stackPane.
	 * 
	 * @return none
	 * @author Mike Nick
	 */
	public void updateCanvas() {
		if (view.drawScreen.getSaved()) {
			iv = new ImageView(view.drawScreen.getImage());
			iv.setId("B");
			spane.getChildren().add(iv);
		}
	}

	/**
	 * Imports plant images to be used during garden population
	 * 
	 * @param plants - the plants to be imported
	 * @author sunil
	 */
	public void importPlants() {
		Scanner sc = new Scanner(getClass().getResourceAsStream("plants.csv"));
		sc.useDelimiter("\n");
		while (sc.hasNext()) {
			String addingLine = sc.next();
			String name = addingLine.split(",")[1];
			String image = addingLine.split(",")[2];
			try {
				PlantView addedPlant = new PlantView(name, image);
				plants.add(addedPlant.getCircle());
				possiblePlants.put(addedPlant.getName(), addedPlant);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		sc.close();
	}
	
	
	
	
	/**
	 * creates a null set for the usable plants
	 * 
	 * @author sunil
	 */
	public void nullUsablePlants() {
		this.usablePlants = new HashSet<PlantView>();
		this.plantViews.clear();
		this.plantImages.clear();
	}

	/**
	 * adds a plant to the usable plants set
	 * 
	 * @param name the name of the plant to add
	 * @author sunil
	 */
	public void updateUsablePlants(String name) 
	{
		this.usablePlants.add(possiblePlants.get(name));
		this.plantsInGarden.add(possiblePlants.get(name));
	}

	/**
	 * Updates the bottom tile of the screen when new plant specifications come in
	 * 
	 * @author Mike Nick
	 */
	public void updateTile() 
	{
		StackPane newPane = new StackPane();
		newPane.setStyle("-fx-background-color: #228b22;");
		for(PlantView newV : usablePlants)
		{
			try 
			{
				PlantView addingView = new PlantView(this.view, newV.getName(), newV.getImg(), getRadiusRet(newV.getName()));
				newPane.getChildren().add(addingView.getCircle());	
				plantViews.addAll(addingView);
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		listView.setOrientation(Orientation.HORIZONTAL);
		Iterator<PlantView> plantViewIterator = plantViews.iterator();
		{
			while(plantViewIterator.hasNext())
			{
				PlantView plantAdded = plantViewIterator.next();
				plantImages.add(plantAdded.getCircle());
			}
		}
		listView.setItems(plantImages);
		listView.setOnMousePressed(new EventHandler<MouseEvent>()
		{
			public void handle(MouseEvent event1)
			{
				double originalX = event1.getX();
				double originalY = event1.getY();
				Circle iva = listView.getSelectionModel().getSelectedItem();
				Iterator<PlantView> plantViewIterator = plantViews.iterator();
				while(plantViewIterator.hasNext())
				{
					PlantView plantAdded = plantViewIterator.next();
					if(plantAdded.getCircle().equals(iva))
					{
						view.getController().getHandlerForHover(plantAdded.getName());
					}
					listView.setOnMouseReleased(new EventHandler<MouseEvent>()
						{
							public void handle(MouseEvent event)
							{
								if(originalX != event.getX() && originalY!=event.getY())
								{
									Circle selectedPlant = listView.getSelectionModel().getSelectedItem();
									Iterator<PlantView> plantViewIterator = plantViews.iterator();
									while(plantViewIterator.hasNext())
									{
										PlantView plantAdded = plantViewIterator.next();
										if(plantAdded.getCircle().equals(selectedPlant))
										{
											PlantView newPlant;
											try {
												newPlant = new PlantView(view, plantAdded.getName(), plantAdded.getImg(),plantAdded.getRad(), event.getX(), event.getY());
												spane.getChildren().add(newPlant.getCircleDrag(view,newPlant.getRad()));
												newPlant.setDraggable(false);
												plantsInGarden.add(newPlant);
												view.getController().addPlants(plantAdded.getName());
												view.getController().setCoord(event.getX(), event.getY());
											} catch (IOException e) {
												e.printStackTrace();
											}
										}
									}	
								}
							}
						});
					}
				}
		});
		listView.setPrefWidth(1400);
		listView.setPrefHeight(150);
		listView.setStyle("-fx-background-color: green");
	  	borderPane.setBottom(listView);
		System.out.println("UpdatedCanvas");
	}
	
	/**
	 * @param name of the plants radius thats wanted to be returned
	 * @return the int value of the base root spread
	 * @author Nick
	 */
	public int getRadiusRet(String name) {
		return view.controller.radiusRet(name);
	}
	

	/**
	 * warns the user if the budget gets too low
	 * 
	 * @author sunil
	 */
	public void warning() {
		Stage addedStage = new Stage();
		Text warning = new Text(
				"Warning: budget at or below 0\nCompost a plant by right-clicking or type in new budget");
		TextField budgetAdder = new TextField("");
		BigButton updateBudget = new BigButton("Update Budget");
		BigButton stop = new BigButton("Ignore");
		BigButton stopSeeing = new BigButton("Ignore all future budget deficits");
		
		updateBudget.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				view.getController().updateModel(Integer.parseInt(budgetAdder.getText()));
				addedStage.close();
			}
		});
		stop.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				addedStage.close();
			}
		});
		stopSeeing.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				ignoreAll = true;
				addedStage.close();
			}
		});
		warning.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, titleFont));
		VBox layout = new VBox(10);
		layout.getChildren().addAll(warning, budgetAdder, updateBudget, stop, stopSeeing);
		layout.setAlignment(Pos.CENTER);
		layout.setStyle("-fx-background-color: #99ea99;");
		Scene scene = new Scene(layout);
		addedStage.setScene(scene);
		if(!ignoreAll) {
			addedStage.showAndWait();
		}
	}
}