package org.swp.scheduler;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.*;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.stage.Stage;
import javafx.*;

import java.awt.Panel;
import java.util.ArrayList;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.swp.scheduler.database.models.Course;
import org.swp.scheduler.database.models.CourseComponent;
import org.swp.scheduler.database.models.Room;
import org.swp.scheduler.database.models.Section;
import org.swp.scheduler.database.models.Teacher;

public class SchedulerController extends WindowController {


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
	
	@FXML 
	private Group calendarGroup; 
	
	@FXML
	private Group listGroup; 

	@FXML 
	private HBox listHBox; 
	
	private boolean feedbackIsShown = true; 

	
    public void start(Stage stage) throws Exception {
    	System.out.println("Started");
    }

    @FXML
    void launchNewCourse() {
    	System.out.println("New Course");
    	openWindow("CreateCourse");
    }

    

    @FXML
    void launchNewSection() {
    	System.out.println("New Section");
    	openWindow("CreateSection");
    }

    @FXML
    void launchNewRoom() {
    	System.out.println("New Room");
    	openWindow("CreateRoom");    }
    @FXML
    void launchStudentFeedback() {
    	System.out.println("Student Feedback");
    	openWindow("StudentFeedback");    }

    @FXML
    void launchTimePreferences() {
    	System.out.println("TimePreferences");
    	openWindow("TimePreferences");
    }
    
	// Hides or shows the Student Feedback panel depending on the current state
	public void onExpandFeedback() throws Exception {
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
	
	public void onCalendarView() throws Exception {
		calendarGroup.setVisible(true);
		listGroup.setVisible(false);
	}
	
	public void onListView() throws Exception {
		calendarGroup.setVisible(false);
		listGroup.setVisible(true);
	}
	
	public void onAddSection() throws Exception {
		Course course = new Course(); 
		Teacher prof = new Teacher();
		Room room = new Room(); 
		CourseComponent comp = new CourseComponent(); 
		
		course.courseName = "CPE 309";
		comp.type = "Lecture";
		prof.teacherName = "T. Kearns";
		room.building = "14";
		room.roomId = 255; 
		
		Section section = new Section();
		section.course = course; 
		section.courseComp = comp; 
		section.prof = prof; 
		section.room = room;
		section.startTime = 9; 
		section.endTime = 11; 
	
		addSection(section);
	} 
	
	public void addSection(Section section) throws Exception {
		ArrayList<String> sectionInfo = new ArrayList<String>();
		sectionInfo.add(section.course.courseName);
		sectionInfo.add(String.valueOf(section.startTime) + " - " + String.valueOf(section.endTime));
		sectionInfo.add(section.prof.teacherName);
		sectionInfo.add(section.room.getRoom());
		sectionInfo.add(section.courseComp.type);
		
		ObservableList<Node> list = listHBox.getChildren(); 
		
		for (int i = 0; i < list.size(); i++) {
			VBox vb = (VBox) list.get(i);
			Text text = new Text(sectionInfo.get(i));
			text.setTextAlignment(TextAlignment.CENTER);
			vb.getChildren().add(text);
		}
	
	}

	
	
	
}
