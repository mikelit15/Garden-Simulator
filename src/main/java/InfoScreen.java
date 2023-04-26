import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;
import javafx.geometry.Orientation;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;


/**
 * The screen which informs the user about how to use the program
 * 
 * @author sunil
 *
 */
public class InfoScreen extends AScreen {
	public final int height = 200;

	/**
	 * Screen which inform user about how to use program
	 * 
	 * @param view - the view component of MVC
	 * @author sunil
	 */
	public InfoScreen(View view) {
		super(view);
		TilePane pane = new TilePane();
		Text title = new Text("Information and Instructions:");
		title.setFont(Font.font("veranda", FontWeight.BOLD, FontPosture.REGULAR, 65));
		title.setFill(Color.web("#dfae43"));
		pane.getChildren().add(title);
		Text text = new Text("This program will help you design a garden\n"
				+ "The goal is to use plants native to Delaware to support butterfly populations\n"
				+ "Navigate through the program with the tabs on top\n"
				+ "Press 'Input' to input the specifications of your garden\n"
				+ "Press 'Draw' to draw the shape of your garden\n"
				+ "Press 'Add Plants' to put plants in your garden\n"
				+ "Press 'Show Butterflies' to show the butterflies your garden supports\n"
				+ "For more information, click on Mt. Cuba's website below");

		Hyperlink link = new Hyperlink("https://mtcubacenter.org/");

		link.setOnMouseClicked((MouseEvent event) -> {
			try {
	            Desktop.getDesktop().browse(new URI("https://mtcubacenter.org/"));
	        } catch (IOException e1) {
	            e1.printStackTrace();
	        } catch (URISyntaxException e1) {
	            e1.printStackTrace();
	        }

		});

		TilePane leftPane = new TilePane();
		TilePane rightPane = new TilePane();
		ImageView fern = new ImageView(new Image(getClass().getResourceAsStream("/img/Northern Lady Fern.png")));
		ImageView bellwort = new ImageView(new Image(getClass().getResourceAsStream("/img/perfoliate bellwort.png")));
		ImageView pickerelweed = new ImageView(new Image(getClass().getResourceAsStream("/img/pickerelweed.png")));
		ImageView CloudlessSulpher = new ImageView(new Image(getClass().getResourceAsStream("/img/leps/Zebra Swallowtail.jpg")));
		ImageView monarch = new ImageView(new Image(getClass().getResourceAsStream("/img/leps/Monarch.jpg")));
		ImageView rsp = new ImageView(new Image(getClass().getResourceAsStream("/img/leps/Red-spotted Purple.jpg")));

		fern.setPreserveRatio(true);
		fern.setFitHeight(height);
		bellwort.setPreserveRatio(true);
		bellwort.setFitHeight(height);
		pickerelweed.setPreserveRatio(true);
		pickerelweed.setFitHeight(height);
		CloudlessSulpher.setPreserveRatio(true);
		CloudlessSulpher.setFitHeight(height);
		monarch.setPreserveRatio(true);
		monarch.setFitHeight(height);
		rsp.setPreserveRatio(true);
		rsp.setFitHeight(height);
		
		leftPane.setOrientation(Orientation.VERTICAL);
		rightPane.setOrientation(Orientation.VERTICAL);

		leftPane.getChildren().addAll(fern, CloudlessSulpher, bellwort);
		rightPane.getChildren().addAll(monarch, pickerelweed, rsp);

		
		
		text.setFont(Font.font("veranda", FontWeight.BOLD, FontPosture.REGULAR, 30));
		pane.getChildren().add(text);
		text.setTextAlignment(TextAlignment.CENTER);
		text.setFill(Color.web("#dfae43"));
		pane.getChildren().add(link);
		link.setFont(Font.font("veranda", FontWeight.BOLD, FontPosture.REGULAR, 40));
		link.setTextFill(Color.web("#dfae43"));
		pane.setStyle("-fx-background-color: #228b22;");
		leftPane.setStyle("-fx-background-color: #99ea99;");
		rightPane.setStyle("-fx-background-color: #99ea99;");
		
		borderPane.setCenter(pane);
		borderPane.setLeft(leftPane);
		borderPane.setRight(rightPane);
	}
}
