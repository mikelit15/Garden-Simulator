import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.TilePane;
import javafx.geometry.Orientation;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;

/**
 * Screen where the user inputs their desired garden specifications
 * 
 * @author sunil
 *
 */
public class InputScreen extends AScreen {
	private final double fontSize = 35;
	private final double height = 200;

	/*
	 * ChoiceBox<Integer> budgetD = new ChoiceBox<Integer>(); ChoiceBox<SoilType>
	 * soilTypeD = new ChoiceBox<SoilType>(); ChoiceBox<Sunlight> sunLightD = new
	 * ChoiceBox<Sunlight>(); ChoiceBox<Moisture> moistureD = new
	 * ChoiceBox<Moisture>();
	 */
	// ComboBox<Integer> budgetD = new ComboBox<Integer>();
	TextField budgetD = new TextField("0");
	ComboBox<SoilType> soilTypeD = new ComboBox<SoilType>();
	ComboBox<Sunlight> sunLightD = new ComboBox<Sunlight>();
	ComboBox<Moisture> moistureD = new ComboBox<Moisture>();
	static Button saveBudg;
	

	/**
	 * 
	 * @param value   - The entered value by the user in TextField
	 * @param message - Used to get the value of the text
	 * @return boolean If the input value is an double it returns true else false
	 * @author parth
	 */
	public boolean ifInteger(TextField value, String message) {
		try {
			double budg = Double.parseDouble(value.getText());
			System.out.println("Budget: " + budg);
			return true;
		} catch (NumberFormatException e) {
			System.out.println("Try again integers only");
			return false;

		}
	}

	/**
	 * creates the screen on which the user inputs garden specifications
	 * 
	 * @param view - the view component of MVC
	 * @author sunil
	 */
	public InputScreen(View view) {
		super(view);
		saveBudg = new BigButton("Save Your Specifications");
		Text title = new Text("Input your garden details");
		Text budgetText = new Text("Budget:");
		Text soilTypeText = new Text("Soil Type:");
		Text sunLightText = new Text("Sunlight Level:");
		Text moistureText = new Text("Moisture Level:");
		Text tryAgainBudg = new Text("Please Enter a Numerical Value");
		budgetText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, fontSize));
		soilTypeText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, fontSize));
		sunLightText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, fontSize));
		moistureText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, fontSize));
		moistureText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, fontSize));
		tryAgainBudg.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, fontSize));
		title.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 50));
		FlowPane pane = new FlowPane(budgetText, budgetD, soilTypeText, soilTypeD, sunLightText, sunLightD,
				moistureText, moistureD);
		pane.setStyle("-fx-background-color: #228b22;");
		pane.getChildren().add(saveBudg);
		pane.setOrientation(Orientation.VERTICAL);
		borderPane.setCenter(pane);
		
		
		TilePane rightPane = new TilePane();
		rightPane.setOrientation(Orientation.VERTICAL);
		
	  	  ImageView ITM = new ImageView(new Image(getClass().getResourceAsStream("/img/leps/Isabella Tiger Moth.jpg")));
	      ImageView CS = new ImageView(new Image(getClass().getResourceAsStream("/img/leps/Cloudless Sulpher.jpg")));
	  	  ImageView MC = new ImageView(new Image(getClass().getResourceAsStream("/img/leps/Mourning Cloak.jpg")));
	  	  ITM.setPreserveRatio(true);
		  ITM.setFitHeight(height); 
		  CS.setPreserveRatio(true);
		  CS.setFitHeight(height); 
		  MC.setPreserveRatio(true);
		  MC.setFitHeight(height); 
		  
		  rightPane.getChildren().addAll(ITM, CS, MC);
		  rightPane.setStyle("-fx-background-color: #dfae43;");
		  
		  borderPane.setRight(rightPane);

		saveBudg.setOnAction(e -> ifInteger(budgetD, budgetD.getText()));

		soilTypeD.getItems().addAll(SoilType.CLAY, SoilType.SANDY, SoilType.LOAMY);

		sunLightD.getItems().addAll(Sunlight.SHADE, Sunlight.PARTIALSUN, Sunlight.FULLSUN);

		moistureD.getItems().addAll(Moisture.DRY, Moisture.MOIST, Moisture.WET, Moisture.FLOODED);

		saveBudg.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				if (ifInteger(budgetD, budgetD.getText()) == false) {
					Alert notInt = new Alert(AlertType.INFORMATION);
					notInt.setContentText("Please Enter Numerical Value");
					notInt.showAndWait();
				}
				view.getController().updateModel(Double.parseDouble(budgetD.getText()));
				view.getController().updateModel(soilTypeD.getValue());
				view.getController().updateModel(moistureD.getValue());
				view.getController().updateModel(sunLightD.getValue());
				view.getController().updatePlants();
			}
		});

	}
	
	/**
	 * Sets the loaded data back to value.
	 * @param data - data that is being set back to values
	 * @author parth
	 */
	public void setData(saveGarden data) {
		  budgetD.setText(data.budgett);//.substring(0, budgetD.getText().length()-2));
		  soilTypeD.setValue(data.soilTypeDD);
		  sunLightD.setValue(data.sunLightDD);
		  moistureD.setValue(data.moistureDD);
	}
}
