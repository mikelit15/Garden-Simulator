import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;


/**
 * Stores the information corresponding to each plants visible portion
 * 
 * @author sunil
 *
 */
public class PlantView extends Circle{
	private final int radius_bot=25;
	private double xoffset = 970;
	private double yoffset = 410;
	private String name;
	private Circle circ;
	private String img;
	private int radius;
	public boolean draggable;
	private double scaledRad;
	public double pixels = 40;
	/**
	 * Shows the plants along the bottom of the UI
	 * 
	 * @param view - the view component of MVC
	 * @param name - the name of the plant
	 * @param img  - a string representing the file path to the plant image
	 * @author sunil
	 * @throws IOException
	 */
	 public PlantView(View view, String name, String img, int radius) throws IOException {
		this.radius=radius;
		draggable = true;
		this.img = img;
		this.circ= new Circle();
		URL url = new URL(img);
		URLConnection conn = url.openConnection();
		Image replace=new Image(conn.getInputStream());
		//this.circsetImage(new Image(conn.getInputStream()));
		//this.iv.setPreserveRatio(true);
		//this.iv.setFitHeight(height);
		circ.setFill(new ImagePattern(replace));
		circ.setOpacity(2.0);
		circ.setRadius(radius_bot);
		this.name = name;
		this.circ.setOnDragDetected((MouseEvent event) -> {
			if (draggable) 
			{
				PlantView newPlant;
				try 
				{
					newPlant = new PlantView(view, name, img, radius);
					//view.addPlantScreen.tpane.getChildren().add(newPlant.getCircle());
					//view.addPlantScreen.plants.add(newPlant.getCircle());
					draggable = false;
				}
				catch (IOException e) {
					e.printStackTrace();
				}
//				view.getController().addPlants(name);
			}
		});
		this.circ.setOnMouseEntered(view.getController().getHandlerForHover(name));
		this.circ.setOnMouseExited(view.getController().getHandlerForLeaveHover());
		this.circ.setOnMouseClicked(view.getController().getHandlerForClick(name));
	}

	/**
	 * Second constructor for PlantView
	 * 
	 * @author sunil
	 */
	public PlantView(String name, String img) throws IOException {
		this.img = img;
		this.name = name;
	}
	
	/**
	 * Third constructor for plantView, includes coordinates for creating a new plantView
	 * 
	 * @param view - the view component of MVC
	 * @param name - the name of the plant
	 * @param img - a string representing the file path to the plant image
	 * @param x - a double representing the x coordinate
	 * @param y - a double representing the y coordinate
	 * @author Mike
	 * @throws IOException
	 */
	public PlantView(View view, String name, String img, int radius, double x, double y) throws IOException {
		draggable = true;
		this.radius=radius;
		this.img = img;
		this.circ = new Circle();
		URL url = new URL(img);
		URLConnection conn = url.openConnection();
		Image replace=new Image(conn.getInputStream());
		//this.circsetImage(new Image(conn.getInputStream()));
		//this.iv.setPreserveRatio(true);
		//this.iv.setFitHeight(height);
		circ.setFill(new ImagePattern(replace));
		circ.setOpacity(2.0);
		circ.setRadius(radius_bot);
		this.name = name;
		this.circ.setOnDragDetected((MouseEvent event) -> {
			if (draggable) 
			{
				PlantView newPlant;
				try 
				{
					newPlant = new PlantView(view, name, img, radius);
//					view.addPlantScreen.spane.getChildren().add(newPlant.getCircle());
//					view.addPlantScreen.plants.add(newPlant.getCircle());
					draggable = false;
				}
				catch (IOException e) {
					e.printStackTrace();
				}
//				view.getController().addPlants(name);
			}
		});
		this.circ.setTranslateX(x-xoffset);
		this.circ.setTranslateY(y+yoffset);
		this.circ.setOnMouseDragged(view.getController().getHandlerForDrag());
		this.circ.setOnMouseEntered(view.getController().getHandlerForHover(name));
		this.circ.setOnMouseExited(view.getController().getHandlerForLeaveHover());
		this.circ.setOnMouseClicked(view.getController().getHandlerForClick(name));
	}

	/**
	 * getter for the name
	 * 
	 * @return String - a string representing the name of the plant
	 * @author sunil
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * The getter of the string representing the image of the plant
	 * 
	 * @return String - the image of the plant
	 * @author Mike
	 */
	public String getImg()
	{
		return img;
	}
	
	/**
	 * The setter of the boolean representing if the plant is draggable, used as a hotfix
	 * 
	 * @param set - the boolean value used to set draggable
	 * @author Mike
	 */
	public void setDraggable(boolean set)
	{
		this.draggable = set;
	}
	
	/**
	 * @return Circle used to help drag the circle node
	 * @author Nick
	 */
	public Circle getCircle() 
	{
		return this.circ;
	}
	/**
	 * @param view of the current circle node
	 * @param radius of the current circle node so it can be changed
	 * @return Circle-scaled root radius applied to circle when dragged
	 * @author Nick
	 */
	public Circle getCircleDrag(View view,int radius) 
	{
		//every 40 pixels =1/5 of the scale
		System.out.println(radius+"yer");
		double doub=Double.valueOf(radius);
		System.out.println(doub+" "+view.drawScreen.scale_width);
		doub=(doub*pixels)/(view.drawScreen.scale_width);
		this.circ.setRadius(doub);
		System.out.println("radius on add"+doub);
		return this.circ;
	}
	/**
	 * @return int-getter for a circles radius
	 * @author Nick
	 */
	public int getRad() {
		return this.radius;
	}
	

	/**
	 * @param update- value of the scaled root radius
	 * @return the scaled root radius onto a global variable
	 * @author Nick
	 */
	public double ScaledRad(double update) {
		this.scaledRad=update;
		return this.scaledRad;
	}
}