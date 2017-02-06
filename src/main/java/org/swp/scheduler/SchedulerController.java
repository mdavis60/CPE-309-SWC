package org.swp.scheduler;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;

public class SchedulerController {
	

	 	@FXML
	    private Group calendar;

	    @FXML
	    private Group studentFeedback;

	    @FXML
	    private AnchorPane rightPane;

	    @FXML
	    private AnchorPane leftPane;

	    @FXML
	    private SplitPane splitPane;

	    @FXML
	    private Group sidePanel;
	    
	    @FXML
	    private AnchorPane calendarAnchor;
	    
	    
	    

    public void onMouseClicked() throws Exception {
    	System.out.println("Clicked");
    	calendarAnchor.setScaleX(1.3);
    }

}
