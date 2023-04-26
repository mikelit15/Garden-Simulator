import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashSet;
import java.util.Iterator;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.effect.DropShadow;

/**
 * Controller portion of MVC
 * @author sunil
 *
 */
public class Controller {
    Model model;
    View view;
    Button button;
    double coordX;
    double coordY;
    
    /**
     * Controller part of MVC
     * @param view part of MVC
     * @author sunil
     */
    public Controller(View view) {
        this.view = view;
        this.model = new Model(this);
    }
    
    /**
     * sets the data from view
     * @param data - saved data that needs to be set
     * @author parth
     */
    public void setData(saveGarden data) {
		view.setData(data);
	}
    
    /**
     * calls processSave from model. Wrapper for processSave.
     * @author parth
     */
    public void processSave() {
    	model.processSave();
    }
    
    /**
     * calls processLoad from model. Wrapper for processLoad.
     * @author parth
     */
    public void processLoad() {
		model.processLoad();
	}
    
    /**
     * Gets handler when a node is clicked
     * @param name - the name of the plant
     * @return the eventhandler for the action
     * @author sunil
     */
    public EventHandler getHandlerForClick(String name) {
        return event -> RemoveClick((MouseEvent) event, name);
    }
    
    /**
     * Removes a plant when a plant is right-clicked on the addScreen
     * @param event - the click which triggered the event
     * @param name - the name of the plant
     * @author sunil
     */
    public void RemoveClick(MouseEvent event, String name) {
    	if(event.getButton() == MouseButton.SECONDARY)
    	{
    		view.updateScreen(event, name);
    	}
    }
    
    /**
     * Initiates the removal of a plant when called by the view
     * @author sunil
     */
    public void initiateRemoval(String name) {
    	model.removePlant(name);
    }
    
    /**
     * Gets handler when a node is dragged
     * @return the event handler for the action
     * @author Mike
     */
    public EventHandler getHandlerForDrag() {
        return event -> drag((MouseEvent) event);
    }
    
    /**
     * Drags the node once the event has been set off
     * @param event the event for the drag
     * @author Mike
     */
    public void drag(MouseEvent event) 
    {
    	if(event.getButton() == MouseButton.PRIMARY)
    	{
	        Node n = (Node)event.getSource();
	        n.setTranslateX(n.getTranslateX() + event.getX());
	        n.setTranslateY(n.getTranslateY() + event.getY());
	        collision(n);
	        setCoord(event.getX(), event.getY());
    	}
    }
    /**
     * Sets the coordinate
     * @param x - x coordinate
     * @param y - y coordinate
     * @author parth
     */
    public void setCoord(double x, double y) {
    	coordX = x;
    	coordY = y;
    }
    
    public double getCoordX()
    {
    	return this.coordX;
    }
    
    public double getCoordY()
    {
    	return this.coordY;
    }
    /**
     * adds plant to the garden once the view has been updated
     * @param plantName - the name of the plant to be added
     * @author sunil
     */
    public void addPlants(String plantName) 
    {
        model.garden.updateGarden(plantName);
        view.addPlantScreen.gInfo.newGardenText(model.getGarden().getBudget(), model.getGarden().getRemainingBudget(), model.getGarden().getLeps(), model.getGarden().getPlantTotal());
    }
    
    /**
     * adds lep to the garden once the view has been updated
     * @param lepName - the name of the lep to be added
     * @author Mike
     */
    public void addLeps(String lepName) 
    {
        model.garden.updateGarden(lepName);
        view.addPlantScreen.gInfo.newGardenText(model.getGarden().getBudget(), model.getGarden().getRemainingBudget(), model.getGarden().getLeps(), model.getGarden().getPlantTotal());
    }
    
    /**
	 * Displays the information for the hovered plant
	 * 
	 * @param String name, MouseEvent event
	 * @author Mike
	 */
    public void enterHover(String name,MouseEvent event) 
    {
    	Iterator<Plant>plants = model.possiblePlants.iterator();
    	HashSet<PlantView> allPlants = view.addPlantScreen.usablePlants;
    	allPlants.addAll(view.addPlantScreen.plantsInGarden);
    	Iterator<PlantView>plantImages = allPlants.iterator();
    	while(plants.hasNext())
    	{
    		Plant plant = plants.next();
    		if(plant.getName().equals(name))
    		{
    			while(plantImages.hasNext())
    			{
    				ImageView plantImage = new ImageView();
    				PlantView plantImageList = plantImages.next();
    				if(plantImageList.getName().equals(name))
    				{
    					URL url;
						try 
						{
							url = new URL(plantImageList.getImg());
							try 
							{
								URLConnection conn = url.openConnection();
								plantImage.setImage(new Image(conn.getInputStream()));
							} 
							catch (IOException e) 
							{
								e.printStackTrace();
							}
						} 
						catch (MalformedURLException e) 
						{
							e.printStackTrace();
						}
						plantImage.setFitHeight(200);
						plantImage.setPreserveRatio(true);
    					view.addPlantScreen.newPlantText(view.addPlantScreen.pInfo.newPlantText(plant.getCommonName(),plant.getPrice(),plant.getNumLeps(), 
    							plant.getName(), plantImage));
    				}
    			}
    		}
    	}
    }
    
    /**
	 * Displays the information for the hovered lep
	 * 
	 * @param String name, MouseEvent event
	 * @author Mike
	 */
    public void enterLepHover(String name, MouseEvent event)
    {
    	Iterator<Lep>leps = model.possibleLeps.iterator();
    	while(leps.hasNext())
    	{
    		Lep hoveredLep = leps.next();
    		if(hoveredLep.getCommonName().equals(name))
    		{
    			view.lepScreen.newLepText(view.lepScreen.lInfo.newLepText(hoveredLep.getCommonName(), hoveredLep.getName()));
    		}
    	}
    }
    
    /**
	 * When the user isn't hovering a plant, displays default info
	 * 
	 * @param MouseEvent event
	 * @author Mike
	 */
    public void leaveHover(MouseEvent event) 
    {
    	view.addPlantScreen.pInfo = new PlantInfo();
    	view.addPlantScreen.newPlantText(view.addPlantScreen.pInfo.getInfo());
    	view.lepScreen.lInfo = new LepInfo();
    	view.lepScreen.newLepText(view.lepScreen.lInfo.getInfo());
    }
    
    /**
	 * calls enter hover
	 * @param String name
	 * @return EventHandler
	 * @author Nick
	 */
    public EventHandler getHandlerForHover(String name) {
        return event -> enterHover(name,(MouseEvent)event);
        
    }
    
    /**
	 * calls enter LepHover
	 * 
	 * @param String name
	 * @return EventHandler
	 * @author Mike
	 */
    public EventHandler getHandlerForLepHover(String name) {
        return event -> enterLepHover(name,(MouseEvent)event);
        
    }
    
    /**
	 * calls leave hover
	 * 
	 * @return EventHandler
	 * @author Mike
	 */
    public EventHandler getHandlerForLeaveHover() {
        return event -> leaveHover((MouseEvent)event);
        
    }    
    
    /**
     * updates model when view changes
     * @param budget updates the budget double changes
     * @author sunil
     */
    public void updateModel(double budget) {
    	this.model.getGarden().updateGarden(budget);
    }
    
    /**
     * updates model when view changes
     * @param moisture updates the moisture enum
     * @author sunil
     */
    public void updateModel(Moisture moisture) {
    	this.model.getGarden().updateGarden(moisture);
    }
    
    /**
     * updates model when view changes
     * @param soil updates the soil type enum
     * @author sunil
     */
    public void updateModel(SoilType soil) {
    	this.model.getGarden().updateGarden(soil);
    }
    
    /**
     * updates model when view changes
     * @param sunlight updates the sunlight enum
     * @author sunil
     */
    public void updateModel(Sunlight sunlight) {
    	this.model.getGarden().updateGarden(sunlight);
    }
    
    /**
     * updates the possible plants in the model after changing the constraints
     * @author sunil
     */
    public void updatePlants() {
    	this.model.getGarden().setPossiblePlants();
    	model.getGarden().setZeros();
    	this.view.addPlantScreen.nullUsablePlants();
    	HashSet<String> names = model.getGarden().getAllPlantNames();
    	Iterator<String> iterator = names.iterator();
    	while(iterator.hasNext()) {
    		this.view.addPlantScreen.updateUsablePlants(iterator.next());
    	}
    	view.addPlantScreen.updateTile();
    }
    
    /**
     * updates the possible leps in the model after changing the constraints
     * 
     * @author Mike
     */
    public void updateLeps()
    {
    	this.model.getGarden().setPossibleLeps();
    	this.view.lepScreen.nullHelpedLeps();
    	HashSet<String> names = model.getGarden().getAllLepNames();
    	Iterator<String> iterator = names.iterator();
    	while(iterator.hasNext())
    	{
    		this.view.lepScreen.updateHelpedLeps(iterator.next());
    	}
    	view.lepScreen.updateTile();
    }

    /**
     * warns the user if the budget is too low
     * @author sunil
     */
    public void budgetWarning() {
    	System.out.println("warning: budget at or below 0");
    	view.addPlantScreen.warning();
    }
    
    /**
     * Takes budget and lep counts from the model and adds to the view 
     * @author sunil
     */
    public void prepareBudget() 
    {
    	double totalBud = this.model.getGarden().getBudget();
    	double bud = this.model.getGarden().getRemainingBudget();
    	int leps = this.model.getGarden().getLeps();
    	int plants = this.model.getGarden().getPlantTotal();
    	view.addPlantScreen.newGardenText(view.addPlantScreen.gInfo.newGardenText(totalBud, bud, leps, plants));
    }
    
    /**
     * @param cir is the node thats currently being dragged to check if it on anyother plants rootspread
     * @author Nick
     */
    public void collision(Node cir) {
    	boolean collisionDetected = false;
    	  for (Node static_bloc : view.addPlantScreen.spane.getChildren() ) {
    	    if (!static_bloc.equals(cir)) {
    	      static_bloc.setEffect(new DropShadow(+0d, 0d, +0d, Color.GREEN));

    	      if (cir.getBoundsInParent().intersects(static_bloc.getBoundsInParent())&& !static_bloc.equals(view.addPlantScreen.spane.lookup("#B"))) {
    	        collisionDetected = true;
    	        }
    	    }
    	  }

    	  if (collisionDetected) {
    	    cir.setEffect(new DropShadow(+5d, 0d, +0d, Color.RED));
    	  } else {
    	    cir.setEffect(new DropShadow(+2d, 0d, +0d, Color.FORESTGREEN));
    	  }
	}
    
    /**
     * @param scale that the user inputted they want the garden scale to be
     * @return the length of the rectange and the width of the rectangle which is 1/5(length)
     * @author Nick
     */
    public void updateModelScale(double scale) {
    	this.model.getGarden().updateGardenScale(scale);
    	view.drawScreen.ScaleVals(model.getGarden().getScale(), model.getGarden().getScaleW());
    }
    
    /**
     * @param name of the plant whose root spread is in need
     * @return the root spread
     * @author Nick
     */
    public int radiusRet(String name) {
    	return model.radiusRet(name);
    }
   
    /**
     *@param name of the plant whose root spread that needs to be converted to the scale
     * @return the scaled root spread
     * @author Nick
     */
    public double radiusConv(String name) {
    	int rad=model.radiusRet(name);
    	return model.radiusConvert(rad);
    }
}