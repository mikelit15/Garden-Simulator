import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
/**
 * Displays information about the garden
 * 
 * @author Mike
 */
public class GardenInfo 
{
	double gardenInfoHeight;
	double gardenInfoWidth;
	public TextFlow textFlowPane;
	private final int lineSpacing = 2;
	int plant_amount;
	double budget;
	double totalBudget;
	
	/**
	 * Creates the default text object used in the add plants screen
	 * 
	 * @author sunil, Mike
	 */
	public GardenInfo()
	{
		textFlowPane = new TextFlow(); 
		textFlowPane.setTextAlignment(TextAlignment.JUSTIFY);
		textFlowPane.setLineSpacing(lineSpacing);
		Text text4=new Text("Total Budget: "+ totalBudget +"\n");
		Text text5=new Text("Remaining Budget: "+ budget +"\n");
		Text text6=new Text("ButterFly Total: 0\n");
		Text text7=new Text("Plant amount: 0\n");
		Text text8=new Text("Right-click a plant to compost it      ");
		text4.setFont(Font.font("veranda", FontWeight.NORMAL, FontPosture.REGULAR, 35));
		text5.setFont(Font.font("veranda", FontWeight.NORMAL, FontPosture.REGULAR, 35));
		text6.setFont(Font.font("veranda", FontWeight.NORMAL, FontPosture.REGULAR, 35));
		text7.setFont(Font.font("veranda", FontWeight.NORMAL, FontPosture.REGULAR, 35));
		text8.setFont(Font.font("veranda", FontWeight.NORMAL, FontPosture.REGULAR, 35));
		ObservableList<Node> list = textFlowPane.getChildren();
	    list.addAll(text4, text5, text6, text7, text8);
	    textFlowPane.setStyle("-fx-background-color: #99ea99;");
	}
	
	/**
	 * Returns the pane 
	 * @return a textflow pane with the relevant info
	 * @author Mike
	 */
	public TextFlow getInfo()
	{
		return textFlowPane;
	}
	
	/**
	 * Updates the pane with new info
	 * @param totalBudget - a double representing the budget
	 * @param budget - a double representing the remaining budget
	 * @param leps - the number of leps in the garden 
	 * @param plant_amount - the amount of plants in the garden
	 * @return TextFlow - text object that stores the new info
	 * @author sunil, Mike
	 */
	public TextFlow newGardenText(double totalBudget, double budget, int leps, int plant_amount) 
	{
		textFlowPane.getChildren().clear();
		textFlowPane.setTextAlignment(TextAlignment.JUSTIFY);
		textFlowPane.setLineSpacing(lineSpacing);
		Text text4=new Text("Total Budget:\n "+ totalBudget +"\n");
		Text text5=new Text("Remaining Budget:\n "+ budget +"\n");
		Text text6=new Text("Butterfly Total:\n "+leps +"\n");
		Text text7=new Text("Plant Total:\n " +plant_amount + "\n");
		Text text8=new Text("Right-click a plant to\ncompost it");
		text4.setFont(Font.font("veranda", FontWeight.THIN, FontPosture.REGULAR, 30));
		text5.setFont(Font.font("veranda", FontWeight.THIN, FontPosture.REGULAR, 30));
		text6.setFont(Font.font("veranda", FontWeight.THIN, FontPosture.REGULAR, 30));
		text7.setFont(Font.font("veranda", FontWeight.THIN, FontPosture.REGULAR, 30));
		text8.setFont(Font.font("veranda", FontWeight.THIN, FontPosture.REGULAR, 30));
		ObservableList listG = textFlowPane.getChildren();
	    listG.addAll(text4, text5, text6, text7, text8);  
	    textFlowPane.setStyle("-fx-background-color: #99ea99;");
	    return textFlowPane;   
	}
}
