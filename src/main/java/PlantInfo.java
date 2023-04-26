import javafx.collections.ObservableList;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text; 
import javafx.scene.text.TextAlignment; 
import javafx.scene.text.TextFlow; 
/**
 * Displays info about a selected plant
 * @author Mike
 *
 */
public class PlantInfo 
{
	double plantInfoHeight;
	double plantInfoWidth;
	public TextFlow textFlowPane;
	private final int lineSpacing = 2;
	
	/**
	 * Creates the default text object used in the add plants screen
	 * 
	 * @author Mike
	 */
	public PlantInfo()
	{
		textFlowPane = new TextFlow(); 
		textFlowPane.setTextAlignment(TextAlignment.JUSTIFY);
		textFlowPane.setLineSpacing(lineSpacing);
		Text text1=new Text("Plant Common name:    \n None Selected\n");
		Text text2=new Text("Plant Scientific name:\n None Selected\n");
		Text text3=new Text("Leps it can add:\n None Selected\n");
		Text text4=new Text("Plant price:\n None Selected\n");
		text1.setFont(Font.font("veranda", FontWeight.THIN, FontPosture.REGULAR, 30));
		text2.setFont(Font.font("veranda", FontWeight.THIN, FontPosture.REGULAR, 30));
		text3.setFont(Font.font("veranda", FontWeight.THIN, FontPosture.REGULAR, 30));
		text4.setFont(Font.font("veranda", FontWeight.THIN, FontPosture.REGULAR, 30));		
		ObservableList list = textFlowPane.getChildren();
	    list.addAll(text1, text2, text3, text4);
	    textFlowPane.setStyle("-fx-background-color: #99ea99;");
	}
	
	/**
	 * Returns the text object 
	 * 
	 * @return TextFlow - a textflow pane with the relevant info
	 * @author Mike
	 */
	public TextFlow getInfo()
	{
		return textFlowPane;
	}
	
	/**
	 * Updates the pane with new info
	 * 
	 * @param name - the common name of the plant
	 * @param price - the price of the plant
	 * @param lepadd - the amount of leps that the plant can add
	 * @param sci_name - the scientific name of the plant
	 * @param iv - the image of the plant
	 * @return TextFlow - a textflow pane with the relevant info
	 * @author Mike
	 */
	public TextFlow newPlantText(String name, double price, int lepadd, String sci_name, ImageView iv) 
	{
		textFlowPane = new TextFlow(); 
		textFlowPane.setTextAlignment(TextAlignment.JUSTIFY);
		textFlowPane.setLineSpacing(2);
		Text text1=new Text("Plant Common name:    \n "+name+"\n");
		Text text2=new Text("Plant Scientific name:\n "+sci_name+"\n");
		Text text3=new Text("Leps it can add:\n "+ lepadd+"\n");
		Text text4=new Text("Plant price:\n "+price+"\n\n");
		text1.setFont(Font.font("veranda", FontWeight.THIN, FontPosture.REGULAR, 30));
		text2.setFont(Font.font("veranda", FontWeight.THIN, FontPosture.REGULAR, 30));
		text3.setFont(Font.font("veranda", FontWeight.THIN, FontPosture.REGULAR, 30));
		text4.setFont(Font.font("veranda", FontWeight.THIN, FontPosture.REGULAR, 30));		
		ObservableList list = textFlowPane.getChildren();
	    list.addAll(text1, text2, text3, text4, iv);  
	    textFlowPane.setStyle("-fx-background-color: #99ea99;");
	    return textFlowPane;
	}
}
