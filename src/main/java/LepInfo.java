import javafx.collections.ObservableList;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
/**
 * Pane which displays info about the selected lep
 * 
 * @author Mike
 */
public class LepInfo 
{
	public TextFlow textFlowPane;
	private final int lineSpacing = 2;
	
	/**
	 * Creates the default text object used in lep screen
	 * 
	 * @author Mike
	 */
	public LepInfo()
	{
		textFlowPane = new TextFlow();
        textFlowPane.setTextAlignment(TextAlignment.JUSTIFY);
        textFlowPane.setLineSpacing(lineSpacing);
        Text text1=new Text("Lep Scientific name:          \nNone Selected \n");
        Text text2=new Text("Lep Common name:\nNone Selected\n");
        ObservableList list = textFlowPane.getChildren();
        text1.setFont(Font.font("veranda", FontWeight.NORMAL, FontPosture.REGULAR, 30));
		text2.setFont(Font.font("veranda", FontWeight.NORMAL, FontPosture.REGULAR, 30));
        textFlowPane.setStyle("-fx-background-color: #99ea99;");
        list.addAll(text1, text2);
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
	 * @param name - the common name of the lep
	 * @param sci_name - the scientific name of the lep
	 * @return TextFlow - text object that stores the new info
	 * @author Mike
	 */
    public TextFlow newLepText(String name, String sci_name) 
    {
	   textFlowPane.getChildren().clear();
       textFlowPane.setTextAlignment(TextAlignment.JUSTIFY);
       textFlowPane.setLineSpacing(2);
       Text text1=new Text("Lep Scientific name:          \n"+sci_name+"\n");
       Text text2=new Text("Lep Common name:\n"+name+"\n");
       text1.setFont(Font.font("veranda", FontWeight.NORMAL, FontPosture.REGULAR, 30));
	   text2.setFont(Font.font("veranda", FontWeight.NORMAL, FontPosture.REGULAR, 30));
	   ObservableList list = textFlowPane.getChildren();
       list.addAll(text1, text2); 
       textFlowPane.setStyle("-fx-background-color: #99ea99;");
       return textFlowPane;
    }
}
