import java.util.HashSet;
import javafx.scene.Scene;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.application.Application;
import javafx.scene.shape.Circle;

/**
 * The view component of MVC
 * @author sunil
 *
 */
public class View extends Application {
	public Screens background;

	HashSet<Circle> plants = new HashSet<Circle>();
	HashSet<ImageView> leps = new HashSet<ImageView>();
	Controller controller= new Controller(this);
	StartScreen startScreen = new StartScreen(this);
	InputScreen inputScreen = new InputScreen(this);
	DrawScreen drawScreen = new DrawScreen(this);
	InfoScreen infoScreen = new InfoScreen(this);
	BorderPane bp = new BorderPane();
	final double width = 1500;
	final double height = 800;
	boolean saved = false;
	Stage stage = new Stage();
	Scene scene = new Scene(bp, width, height);
	AddPlantsScreen addPlantScreen = new AddPlantsScreen(this, plants);
	LepScreen lepScreen = new LepScreen(this, leps);

	/**
	 * constructor portion of the view for mvc
	 * @author sunil
	 */
	public View() {
//		stage.initModality(Modality.APPLICATION_MODAL);
		this.background = Screens.WELCOME;
	}

	/**
	 * resets the look of the view after every change in screen
	 * @author sunil
	 * 
	 */
	public void updateView() {
		stage.setScene(scene);
		System.out.println(background.getName());
		switch (background) {
		case WELCOME:
			scene.setRoot(startScreen.getBorderPane());
			stage.show();
			break;
		case INFO:
			scene.setRoot(infoScreen.getBorderPane());
			break;
		case SELECTION:
			scene.setRoot(inputScreen.getBorderPane());
			break;
		case MAPDRAWING:
			scene.setRoot(drawScreen.getBorderPane());
			break;
		case POPULATING:
			controller.prepareBudget();
			addPlantScreen.updateCanvas();
			getController().updatePlants();
			drawScreen.saved = false;
			scene.setRoot(addPlantScreen.getBorderPane());;
			break;
		case FINISH:
			getController().updateLeps();
			scene.setRoot(lepScreen.getBorderPane());
			break;
		case EXIT:
			break;
		default:
			System.out.println("Error");
			break;
		}
	}

	/**
	 * sets the new x for a node being dragged
	 * 
	 * @param x - the x amount to be dragged
	 * @param node - the y amount to be dragged
	 * @author sunil
	 */
	public void setX(double x, Node node) {
		node.setTranslateX(node.getLayoutX() - width / 2 + x);
	}

	/**
	 * sets the new y for a node being dragged
	 * 
	 * @param y - the y amount to be dragged
	 * @param node - the node to be dragged
	 * @author sunil
	 */
	public void setY(double y, Node node) {
		node.setTranslateX(node.getLayoutY() - width / 2 + y);
	}

	/**
	 * returns the stage
	 * 
	 * @return the stage for the program
	 * @author sunil
	 */
	public Stage getStage() {
		return stage;
	}

	/**
	 * Main for the program
	 * @param args
	 * @author sunil
	 */
	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * Overrides the start method to bring up a screen with the application running
	 * 
	 * @param stage - the stage to be used
	 * @author sunil
	 */
	@Override
	public void start(Stage stage) {
		updateView();
	}
	
	/**
	 * Getter for the controller
	 * @return controller 
	 * @author sunil
	 */
	public Controller getController(){
		return controller;
	}
	
	/**
	 * getter for if drawing has been drawn
	 * @return a boolean representing if the screen has been saved
	 * @author sunil
	 */
	public boolean getSaved()
	{
		drawScreen.saved = this.saved;
		return saved;
	}
	
	public void setData(saveGarden data) {
		inputScreen.setData(data);
	}
	
	
	public void processSave() {
    	controller.processSave();
    }
	
	public void processLoad() {
		controller.processLoad();
	}
	
	/**
	 * Fires an updateScreen when the controller indicates
	 * @param event - the event  which triggered the update
	 * @param name - the name of the screen
	 * @author sunil
	 */
	public void updateScreen(MouseEvent event, String name) {
		Node n = (Node)event.getSource();
		Text warning = new Text("Would you like to compost this plant?");
		warning.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 50));
		VBox layout = new VBox(10);
		BigButton compost = new BigButton("Compost");
		BigButton no = new BigButton("Don't Compost");
		Stage addedStage = new Stage();
		layout.setStyle("-fx-background-color: #99ea99;");
		compost.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				controller.initiateRemoval(name);
				addPlantScreen.spane.getChildren().remove(n);
				addPlantScreen.plantsInGarden.remove(n);
				controller.prepareBudget();
				addedStage.close();
			}
		});
		
		no.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				addedStage.close();
			}
		});
		
		layout.getChildren().addAll(warning, compost, no);
		layout.setAlignment(Pos.CENTER);
		Scene scene = new Scene(layout);
		addedStage.setScene(scene);
		addedStage.showAndWait();
	}

}
