import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * A bigger version of the button
 * @author sunil
 *
 */
public class BigButton extends Button {
	private final int fontsize = 20;
	/**
	 * Creates the bigger version of the button
	 * @param name the text to be displayed on the button
	 * @author sunil
	 */
	public BigButton(String name) {
		Text t = new Text(name);
		t.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, fontsize));
		this.setGraphic(t);
		this.setStyle("-fx-background-radius: 19px;");
	}
}
