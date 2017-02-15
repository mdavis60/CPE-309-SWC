package org.swp.scheduler;

import javafx.fxml.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.scene.shape.*;
import javafx.application.*;
import javafx.collections.*;
import java.util.ArrayList;
import org.slf4j.*;
import org.swp.scheduler.database.models.*;

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
	private Group listGroup; 
	
	@FXML
	private Group calendarGroup;

	@FXML 
	private HBox listHBox;
	
	@FXML
	private Group innercalendarGroup; 
	

	@FXML 
	private HBox calendarHBox; 
	
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
    	System.out.println("TimePreference");
    	openWindow("TimePreference");
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
		room.roomNumber = 255; 
		
		Section section = new Section();
		section.course = course; 
		section.courseComp = comp; 
		section.prof = prof; 
		section.room = room;
		section.startTime = 9; 
		section.endTime = 11; 
		section.dow = "M W F";
	
		addSection(section);
	} 
	
	public void addSection(Section section) throws Exception {
		addSectionToListView(section);
		addSectionToCalendarView(section);
	}

	public void addSectionToCalendarView(Section section) throws Exception {
		Text name = new Text(section.course.courseName);
		Text prof = new Text(section.prof.teacherName);
		Text hours = new Text(section.startTime + "-" + section.endTime);
		
		
		ObservableList<Node> list = calendarHBox.getChildren(); 
		
		AnchorPane ap = new AnchorPane();
		VBox v = new VBox();
		ap.getStyleClass().add("calendar-block");
		ap.setPrefHeight(100);
		ap.getChildren().add(v);
		v.getChildren().add(name);
		v.getChildren().add(prof);
		v.getChildren().add(hours);
		
		
		//for (int i = 0; i < list.size(); i++) {
			VBox vb = (VBox) list.get(0);
			vb.getChildren().add(ap);
	//	}
		
		
	}	
	
	public void addSectionToListView(Section section) throws Exception {
		ArrayList<String> sectionInfo = new ArrayList<String>();
		sectionInfo.add(section.course.courseName);
		sectionInfo.add(String.valueOf(section.dow + " : " + section.startTime) + " - " + String.valueOf(section.endTime));
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
