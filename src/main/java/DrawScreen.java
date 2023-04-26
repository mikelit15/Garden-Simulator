import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import java.io.File;
import java.io.IOException;
import javafx.embed.swing.SwingFXUtils;
import javax.imageio.ImageIO;

/**
 * Screen onto which the user draws the desired shape of the garden
 * @author sunil
 *
 */
/**
 * @author nicho
 *
 */
public class DrawScreen extends AScreen {
	BigButton reset;
	static BigButton save;
	BigButton setScale;
	BigButton saveGardenImage;
	BigButton loadGardenImage;
	boolean saved = false;
//	Image grid=new Image("img/grid.jpg");
	private final static int canvasWidth = 1400;
	private final static int canvasHeight = 650;
	private final int titleFont = 25;
	private final int fontSize = 50;
	private final int rectFont=14;
	private final int lineWidth = 3;
	public static Canvas canvas = new Canvas(canvasWidth, canvasHeight);
	static GraphicsContext graphicsContext;
	WritableImage image = new WritableImage(canvasWidth, canvasHeight);
	boolean ignoreAll = false;
	static double scale_length;
	double scale_width;
	Rectangle r2;
	Text text;
	public static FlowPane pane;

	/**
	 * creates screen on which to draw the garden
	 * @param view - the view component of MVC
	 * @author sunil
	 */
	/**
	 * @param view
	 */
	public DrawScreen(View view) 
	{
		super(view);
		reset = new BigButton("Reset Drawing");
		save = new BigButton("Save Drawing");
		setScale=new BigButton("Set Scale");
		saveGardenImage = new BigButton("Save Shape");
		loadGardenImage = new BigButton("Upload Shape");
		Text title = new Text("Draw the garden");
		title.setTextAlignment(TextAlignment.CENTER);
		title.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, fontSize));
		this.pane = new FlowPane();
		this.pane.setStyle("-fx-background-color: #228b22;");
		borderPane.setCenter(this.pane);
		TilePane bot = new TilePane();
		bot.setStyle("-fx-background-color: #dfae43;");
		bot.getChildren().addAll(reset, save, setScale, saveGardenImage, loadGardenImage);
		borderPane.setBottom(bot);
		reset.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				graphicsContext = canvas.getGraphicsContext2D();
	            graphicsContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
	            graphicsContext.closePath();
	            graphicsContext.beginPath();
	            save.fire();
			}
		});
		save.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) 
			{
		        saved = true;
		    	SnapshotParameters params = new SnapshotParameters();
		        params.setFill(Color.TRANSPARENT);         
		        canvas.snapshot(params, image);
			}
		});
		saveGardenImage.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) 
			{
		        save.fire();
		        FileChooser fc = new FileChooser();
	    		  fc.getExtensionFilters().add(new ExtensionFilter("Image Files", "*.png"));
	    		  File sF = fc.showSaveDialog(null);

	    		  try {

					ImageIO.write(SwingFXUtils.fromFXImage(image, null),
					          "png", sF);

				} catch (IOException e1) {

					e1.printStackTrace();
				}

			}
		});
		loadGardenImage.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) 
			{
				save.fire();
				 FileChooser fc = new FileChooser();
	    		  fc.getExtensionFilters().add(new ExtensionFilter("Image Files", "*.png"));
	    		  File sF = fc.showOpenDialog(null);
	    		  try {
	    			  Image im1 = new Image(sF.toURI().toString());
	    			  AddPlantsScreen.iv.setImage(im1);
	    			  AddPlantsScreen.spane.getChildren().add(AddPlantsScreen.iv);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
	  		
			}
		});
		setScale.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) 
			{
				scaleSetUp();
			}
		});
		draw(this.pane);
	}
	/**
	 * Draws the scale rectangle
	 * @param pane - the pane passed in
	 * @return - none
	 * @author Nick
	 */
	private void drawRect(FlowPane pane) {
		pane.getChildren().removeAll(r2,text);
		r2 = new Rectangle(0.5,200.5,200.5,40.5);
		r2.setStroke(Color.BLACK);
		r2.setFill(null);
		r2.setStrokeWidth(3);
		r2.setFill(Color.BLUE);
		drawRect_text(pane);
        pane.getChildren().addAll(r2,text);
	}
	
	/**
	 * 
	 * @param pane
	 */
	private void drawRect_text(FlowPane pane) {
		text = new Text();
        text.setText("<-Measurement scale "+scale_length+"'x"+scale_width+"'" );
        text.setX(canvasWidth-canvasWidth);
        text.setY(canvasHeight-canvasHeight+250);
        text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, rectFont));
	}
	/**
	 * Draws the garden via MouseInput
	 * @return - none
	 * @author Mike Nick
	 */
	private void draw(FlowPane pane) 
	{
        pane.getChildren().addAll(canvas);
        drawRect(pane);
        graphicsContext = canvas.getGraphicsContext2D();
        graphicsContext.setFill(Color.BLUE);
        canvas.setOnMouseDragged((event) -> 
        {
            graphicsContext.lineTo(event.getX(), event.getY());
            graphicsContext.stroke();
            graphicsContext.setLineWidth(lineWidth);
            graphicsContext.closePath();
            graphicsContext.beginPath();
            graphicsContext.moveTo(event.getX(), event.getY());
        });
        canvas.setOnMouseReleased((event) ->
        {
            graphicsContext.closePath();
            graphicsContext.beginPath();
        });
    }
	
	/**
	 * Getter for canvas
	 * @return - the canvas
	 * @author sunil
	 */
	public Canvas getCanvas() {
		return canvas;
	}
	
	/**
	 * Getter for Saved 
	 * @return - the value of the boolean saved, to check whether or not the save button has been pressed
	 * @author Mike
	 */
	public boolean getSaved()
	{
		return saved;
	}
	
	/**
	 * Getter for the canvas screenshot 
	 * @return - the image created for the screenshot of the canvas
	 * @author Mike
	 */
	public WritableImage getImage()
	{
		return image;
	}
	
	/**
	 * Sets the image, used for loadGarden
	 * @param im - the image passed in
	 * @author Mike
	 */
	public void setImage(WritableImage im)
    {
        image = im;
    }
	
	/**
	 * Creates the tab where user enters the length of presented blue rectangle to help determine the scale
	 * @author Nick
	 */
	public void scaleSetUp() {
		Stage addedStage = new Stage();
		Text scaleDirect = new Text("Enter Length of Blue Rectangle in Feet.\nThe Width Will Be 1/5 of Length");
		TextField scale = new TextField("");
		BigButton setScale = new BigButton("Set Scale");
		setScale.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				view.getController().updateModelScale(Double.parseDouble(scale.getText()));
				drawRect(view.drawScreen.pane);
				addedStage.close();
			}
		});
		scaleDirect.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, titleFont));
		VBox layout = new VBox(10);
		layout.getChildren().addAll(scaleDirect, scale, setScale);
		layout.setAlignment(Pos.CENTER);
		layout.setStyle("-fx-background-color: #99ea99;");
		Scene scene = new Scene(layout);
		addedStage.setScene(scene);
		if(!ignoreAll) {
			addedStage.showAndWait();
		}
	}
	
	/**
	 * helps retrieve the dimensions of the rectangle so they can be applied to scale
	 * @param length-length of the blue rectangle a.k.a the scale
	 * @param width- the width of the blue rectangle a.k.a the scale
	 * @author Nick
	 */
	public void ScaleVals(double length, double width) {
		this.scale_length=length;
		this.scale_width=width;
	}
	
	/**
	 * Sends user back to Drawscreen if a scale value wasnt entered
	 * @return boolean stating if a value was entered for scale
	 * @author Parth
	 */
	public static boolean ifScaleNull() {
		if(scale_length <= 0) {
			return true;
		}
		else {
			return false;
		}
	}

}