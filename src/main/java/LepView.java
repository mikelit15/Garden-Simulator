import java.io.IOException;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Holds the viewable information for leps
 * @author sunil
 *
 */
public class LepView
{
	Image img;
	String nameCommon;
	ImageView iv;
	
	/**
	 * Constructor for a lepView object
	 * 
	 * @param view - the current view pane
	 * @param nameCommon - the common name of the lep
	 * @param img - the image of the lep
	 * @throws IOException
	 * @author sunil, Mike
	 */
	public LepView(View view, String nameCommon, Image img) throws IOException
	{
		iv = new ImageView();
		this.img = img;
		iv.setImage(img);
		iv.setPreserveRatio(true);
		iv.setFitHeight(100);
		this.nameCommon = nameCommon;
		iv.setOnMouseEntered(view.getController().getHandlerForLepHover(nameCommon));
		iv.setOnMouseExited(view.getController().getHandlerForLeaveHover());
	}
	
	/**
	 * Second constructor for a lepView object
	 * 
	 * @param nameCommon - the common name of the lep
	 * @param img - the image of the lep
	 * @throws IOException
	 * @author Mike
	 */
	public LepView(String nameCommon, Image img) throws IOException {
		this.img = img;
		this.nameCommon = nameCommon;
	}

	/**
	 * Getter for the imageView of the lep
	 * 
	 * @return ImageView - the imageView of the lep
	 * @author Mike
	 */
	public ImageView getImageView() {
		return iv;
	}
	
	/**
	 * Getter for the common name of the lep
	 * 
	 * @return String - the common name of the lep
	 * @author Mike
	 */
	public String getNameCommon()
	{
		return nameCommon;
	}
	
	/**
	 * Getter for the image of the lep
	 * 
	 * @return Image - the image of the lep
	 * @author Mike
	 */
	public Image getImg()
	{
		return img;
	}
}