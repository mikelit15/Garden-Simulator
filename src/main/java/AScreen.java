import java.util.HashSet;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.geometry.Pos;
import javafx.scene.control.ButtonType;

/**
 * Class for screens which is implemented by each type of screen the
 * user switches to
 * 
 * @author sunil
 *
 */
public class AScreen {
	BigButton Start;
	BigButton Inputs;
	BigButton Draw;
	BigButton AddPlants;
	BigButton ShowLeps;
	BigButton Exit;
	BigButton Info;
	BigButton SaveGarden;
	BigButton LoadGarden;
	BigButton Restart;
	saveGarden data;
	BorderPane borderPane;
	View view;
	Controller controller;
	TilePane topPane;

	/**
	 * Creates the top panel for each screen and sets it up for its own constructor
	 * @return
	 */
	public  AScreen(View view) {
		this.view = view;
		borderPane = new BorderPane();
		Start = new BigButton("Home");
		Inputs = new BigButton("Inputs");
		Draw = new BigButton("Draw");
		AddPlants = new BigButton("Add Plants");
		ShowLeps = new BigButton("Show Leps");
		Exit = new BigButton("Exit");
		Info = new BigButton("Info");
		SaveGarden = new BigButton("Save Garden");
		LoadGarden = new BigButton("Load Garden");
		Restart = new BigButton("Restart");
		Start.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				view.background = Screens.WELCOME;
				view.updateView();
			}
		});
		Info.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				view.background = Screens.INFO;
				view.updateView();
			}
		});
		Inputs.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				view.background = Screens.SELECTION;
				view.updateView();
			}
		});
		Draw.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				view.background = Screens.MAPDRAWING;
				view.updateView();
			}
		});
		AddPlants.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				view.background = Screens.POPULATING;
				view.updateView();
				if(DrawScreen.ifScaleNull() == true)
				{
					Alert null1 = new Alert(AlertType.INFORMATION);
					null1.setContentText("Please Enter Scale Value Greater than Zero");
					null1.showAndWait();
					view.background = Screens.MAPDRAWING;
					view.updateView();
				}
			}
		});
		ShowLeps.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				view.background = Screens.FINISH;
				view.updateView();
			}
		});
		Exit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				System.exit(0);
			}
		});
		SaveGarden.setOnAction(new EventHandler<ActionEvent>() 
		    {
		    	@Override
		    	public void handle(ActionEvent e)
		    	{
		    		view.processSave();
		    		
		    		
		    	}
		    	});
		LoadGarden.setOnAction(new EventHandler<ActionEvent>() 
	    {
	    	@Override
	    	public void handle(ActionEvent e)
	    	{  		      
	        		view.processLoad();
		    		//view.inputScreen.budgetD.setText(data.budgett);
		    		
		    		InputScreen.saveBudg.fire();
		    		
	    	}
	    	});
        Restart.setOnAction(new EventHandler<ActionEvent>()
        {
               @Override
               public void handle(ActionEvent e)
               {
				Alert reset = new Alert(AlertType.CONFIRMATION);
				reset.setContentText("Are you sure you want to restart?");
	
				Optional<ButtonType> result = reset.showAndWait();
				if(result.isPresent() && result.get() == ButtonType.OK) {
	            view.background = Screens.WELCOME;
	            view.updateView();
	
	            view.inputScreen.budgetD.setText("0");
	            view.inputScreen.soilTypeD.setValue(null);
	  			view.inputScreen.sunLightD.setValue(null);
	  			view.inputScreen.moistureD.setValue(null);
	  			DrawScreen.graphicsContext = DrawScreen.canvas.getGraphicsContext2D();
	            DrawScreen.graphicsContext.clearRect(0, 0, DrawScreen.canvas.getWidth(), DrawScreen.canvas.getHeight());
	            DrawScreen.graphicsContext.closePath();
	            DrawScreen.graphicsContext.beginPath();
	            DrawScreen.save.fire();
	            view.getController().model.getGarden().plantsToAdd = new HashSet<Plant>();
	            view.getController().model.getGarden().lepsToAdd = new HashSet<Lep>();
	            view.getController().model.getGarden().plantsInGarden = new HashSet<Plant>();
	            AddPlantsScreen.spane.getChildren().clear();
	            LepScreen.spane.getChildren().clear();
	  				}
               }
        });
        topPane = new TilePane(Start, Info, Inputs, Draw, AddPlants, ShowLeps, SaveGarden, LoadGarden, Exit, Restart); 
		Exit.setAlignment(Pos.BASELINE_RIGHT);
		topPane.setStyle("-fx-background-color: #a5550d;");
		borderPane.setTop(topPane);
	}

	/**
	 * getter for borderPane for each class. This is how we change the screen
	 * 
	 * @return the borderpane to be switched to
	 * @author sunil
	 */
	public BorderPane getBorderPane() {
		return borderPane;
	}
}
