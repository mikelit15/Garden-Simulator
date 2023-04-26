/**
 * Keeps track of what screen is currently being shown
 * @author sunil
 *
 */
public enum Screens {
	//This enum helps change the screens

	WELCOME("Welcome"),
	INFO("Info"),
	LOADNEW("Loadnew"),
	SELECTION("Selection"),
	MAPDRAWING("Mapdrawing"),
	POPULATING("Populating"),
	FINISH("Finish"),
	EXIT("Exit");
	
	private String name = null;
	
	/**
	 * 
	 * @param screenName
	 */
	private Screens(String screenName){
		name = screenName;
	}
	
	/**
	 * gets the current screen
	 * @return - the name of the screen (String)
	 * @author sunil
	 */
	public String getName() {
		return name;
	}


}
