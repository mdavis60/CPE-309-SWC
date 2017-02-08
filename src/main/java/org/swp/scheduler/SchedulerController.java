package org.swp.scheduler;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.*;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.stage.Stage;

import java.awt.Panel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.*;

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
	
	@FXML
	private Text closeFeedback; 

	private boolean feedbackIsShown = true; 

	
    public void start(Stage stage) throws Exception {
    	System.out.println("Started");
    }

	// Hides or shows the Student Feedback panel depending on the current state
	public void onCalendarScale() throws Exception {
		System.out.println("SCale");
		if (feedbackIsShown) {
			calendarAnchor.setScaleX(1.4);
			calendar.setLayoutX(125);
			studentFeedback.setOpacity(0);
			closeFeedback.setText("Expand Feedback");
			feedbackIsShown = false;
		} else {
			calendarAnchor.setScaleX(1);
			calendar.setLayoutX(0);
			studentFeedback.setOpacity(1);
			closeFeedback.setText("Close Feedback");
			feedbackIsShown = true; 
		}

	}

    @FXML
    void launchNewCourse() {
    	System.out.println("New Course");
    	   try{
               FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/CreateCourse.fxml"));
               Parent root1 = (Parent) fxmlLoader.load();
               Stage stage = new Stage();
               stage.setTitle("ABC");
               stage.setScene(new Scene(root1));  
               stage.show();
             } catch(Exception e) {
            	 e.printStackTrace();
            	 System.out.println("Could not instantiate stage");
             }
    }

    

    @FXML
    void launchNewSection() {
    	System.out.println("New Section");

    }

    @FXML
    void launchNewRoom() {
    	System.out.println("New Room");

    }
    @FXML
    void launchStudentFeedback() {
    	System.out.println("Student Feedback");

    }

    @FXML
    void launchTimePreferences() {
    	System.out.println("TimePreferences");

    }

}
