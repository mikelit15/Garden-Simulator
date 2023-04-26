import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
* screen where the user looks at leps to gain more lep knowlege
* @author sunil
*/
public class LepScreen extends AScreen
{
       public TextFlow lepInfo;
       LepInfo lInfo = new LepInfo();
       HashMap<String, LepView> possibleLeps;
       HashSet<LepView> helpedLeps;
       public HashSet<ImageView> leps = new HashSet<ImageView>();	
       static Boolean resetG = false;
       static FlowPane spane= new FlowPane();
       
       /**
        * screen where the user looks at leps to gain more lep knowlege
        * @param view - the view component of MVC
        * @param HashSet<ImageView> leps - the set of leps to be added onto the screen
        * @author sunil
        */
       public LepScreen(View view, HashSet<ImageView> leps) {
    	   	  super(view);
    	   	  possibleLeps = new HashMap<String, LepView>();
                Button exit = new Button("Exit");
//           	title.setTranslateX(-150);
                
                exit.setOnAction(new EventHandler<ActionEvent>()
                {
                       @Override
                       public void handle(ActionEvent e)
                       {
                             view.background = Screens.EXIT;
                             view.updateView();
                       }
                }); 
                Text title = new Text("Lets learn about leps!!");
                title.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 50));
                spane.setStyle("-fx-background-color: #228b22;");
                borderPane.setCenter(spane);
                lepInfo = new TextFlow();
                lepInfo = lInfo.getInfo();
                borderPane.setLeft(lepInfo);
                importLeps();   
       }
       
   	/**
   	 * Imports lep images to be used during last slide
   	 * 
   	 * @author sunil
   	 */
   	public void importLeps() {
  		Scanner sc = new Scanner(getClass().getResourceAsStream("leps.csv"));
   		sc.useDelimiter("\n");
   		while (sc.hasNext()) {
   			String addingLine = sc.next();
   			String nameCommon = addingLine.split(",")[0];
   			Image image = new Image(getClass().getResourceAsStream("/img/leps/" + nameCommon + ".jpg"));
   			try 
   			{
   				LepView addedLep = new LepView(nameCommon, image);
   				leps.add(addedLep.getImageView());
				possibleLeps.put(addedLep.getNameCommon(), addedLep);
			}
   			catch (IOException e) 
   			{
				e.printStackTrace();
			}
   		}
   		sc.close();
   	}
   	
   	/**
	 * Updates the center tile of the screen when new lep specifications come in
	 * 
	 * @author Mike
	 */
   	public void updateTile() 
	{
   		Iterator<LepView> iterator = helpedLeps.iterator();
		FlowPane newPane = new FlowPane();
		newPane.setStyle("-fx-background-color: #228b22;");
		while (iterator.hasNext()) 
		{
			LepView newV = iterator.next();
			try 
			{
				LepView addingView = new LepView(this.view, newV.getNameCommon(), newV.getImg());
				newPane.getChildren().add(addingView.getImageView());				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		spane = newPane;
		borderPane.setCenter(spane);
		System.out.println("UpdatedButterflies");
	}
   	
   	/**
	 * creates a null set for the helped leps
	 * 
	 * @author Mike
	 */
   	public void nullHelpedLeps() {
		this.helpedLeps = new HashSet<LepView>();
	}
   	
   	/**
	 * adds a lep to the helped lep set
	 * 
	 * @param name the name of the lep to add
	 * @author Mike
	 */
	public void updateHelpedLeps(String name) 
	{
		this.helpedLeps.add(possibleLeps.get(name));
	}

    /**
     * Updates the pane of the left with the currently hovered lep info
     * 
     * @param text - the passing in of the new lep text
     * @author Mike
     */
    public void newLepText(TextFlow text) 
    {
        borderPane.setLeft(text);
    }

}