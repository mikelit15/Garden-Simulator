import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.geometry.Orientation;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.layout.BorderPane;

/**
 * The screen to welcome the user to the program
 * @author sunil
 *
 */
public class StartScreen extends AScreen
{
	public final int height = 400;
/**
 * A screen to welcome the user
 * @param view - the view component of MVC
 * @author sunil
 */
  public StartScreen(View view) {
	  super(view);
	  Text title = new Text("Garden Creator");
      title.setFont(Font.font("veranda", FontWeight.BOLD, FontPosture.REGULAR, 80));
      title.setFill(Color.web("#dfae43"));
      Text subtitle = new Text("Press 'Info' for information\nPress 'Inputs' to start designing");
      subtitle.setFont(Font.font("veranda", FontWeight.BOLD, FontPosture.REGULAR, 55));
      title.setFill(Color.web("#dfae43"));
      subtitle.setFill(Color.web("#dfae43"));
	  TilePane pane= new TilePane(title, subtitle);
	  pane.setOrientation(Orientation.VERTICAL);
	  pane.setStyle("-fx-background-color: #228b22;");
  	  borderPane.setCenter(pane);
  	  borderPane.setTop(topPane);
  	  ImageView alexander = new ImageView(new Image(getClass().getResourceAsStream("/img/golden club.png")));
      ImageView moonbeam = new ImageView(new Image(getClass().getResourceAsStream("/img/Moonbeam.jpg")));
  	  ImageView pinkPearl = new ImageView(new Image(getClass().getResourceAsStream("/img/PinkPearl.jpg")));
  	  ImageView honeySuckle = new ImageView(new Image(getClass().getResourceAsStream("/img/trumpet honeysuckle.png")));
  	  BorderPane botPlant = new BorderPane();
  	  alexander.setPreserveRatio(true);
	  alexander.setFitHeight(height); 
	  moonbeam.setPreserveRatio(true);
	  moonbeam.setFitHeight(height); 
	  pinkPearl.setPreserveRatio(true);
	  pinkPearl.setFitHeight(height); 
	  honeySuckle.setPreserveRatio(true);
	  honeySuckle.setFitHeight(height);
	  borderPane.setLeft(pinkPearl);
	  borderPane.setRight(alexander);
	  borderPane.setBottom(botPlant);
	  botPlant.setLeft(moonbeam);
	  botPlant.setRight(honeySuckle);
	  borderPane.setStyle("-fx-background-color: #228b22;");
  	  this.view = view;
    
  }
}
  
