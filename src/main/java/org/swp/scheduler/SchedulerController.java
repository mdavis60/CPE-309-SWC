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

import javafx.collections.transformation.*;

public class SchedulerController extends WindowController {


	@FXML
	private Group calendar;

	@FXML
	private Group studentFeedback;

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
	private TableView<Section> listTable;
	
	@FXML
	private Group innercalendarGroup; 
	
	@FXML 
	private HBox calendarHBox; 
	
	@FXML
	private TableColumn<Section, String> sectionNameColumn;
	
	@FXML
	private TableColumn<Section, String> profColumn;
	
	@FXML
	private TableColumn<Section, String> roomColumn;
	
	@FXML
	private TableColumn<Section, String> sectionTypeColumn;
	
	@FXML
	private TableColumn<Section, String> timeColumn;
	@FXML
    private MenuItem newMI;

    @FXML
    private MenuItem openMI;

    @FXML
    private MenuItem saveMI;

    @FXML
    private MenuItem saveAsMI;

    @FXML
    private MenuItem ImportScheduleMI;

    @FXML
    private Menu editMenu;

    @FXML
    private MenuItem addCourseMI;

    @FXML
    private MenuItem addSectionMI;

    @FXML
    private MenuItem addRoomMI;

    @FXML
    private MenuItem editTimePrefMI;

    @FXML
    private MenuItem editCoursePrefMI;

    @FXML
    private Menu windowMenu;

    @FXML
    private MenuItem viewTimePrefMI;

    @FXML
    private MenuItem viewCohortDataMI;

    @FXML
    private MenuItem ListViewMI;

    @FXML
    private MenuItem CalViewMI;

    @FXML
    private Menu helpMenu;

    @FXML
    private MenuItem aboutMI;

	
	private boolean feedbackIsShown = true; 
	
	
    private ObservableList<Section> masterData = FXCollections.observableArrayList();
    
    public SchedulerController() {
    	masterData.add(new Section("CPE 309", "T. Kearns", "14-202", "Lecture", "M W F", "9", "11"));
    	masterData.add(new Section("CPE 308", "T. Kearns", "14-202", "Lecture", "M W F", "1", "3"));
    	masterData.add(new Section("CPE 305", "T. Kearns", "14-202", "Lecture", "M W F", "12", "1"));
    	masterData.add(new Section("CPE 357", "T. Kearns", "14-202", "Lecture", "M W F", "8", "9"));
    	masterData.add(new Section("CPE 101", "T. Kearns", "14-202", "Lecture", "M W F", "3", "5"));
    	masterData.add(new Section("CPE 102", "T. Kearns", "14-202", "Lecture", "M W F", "5", "7"));
    }
    
    @FXML
    private void initialize() {
    	System.out.println("Top");
		sectionNameColumn.setCellValueFactory(cellData -> cellData.getValue().courseProperty());
		profColumn.setCellValueFactory(cellData -> cellData.getValue().profProperty());
		roomColumn.setCellValueFactory(cellData -> cellData.getValue().roomProperty());
		sectionTypeColumn.setCellValueFactory(cellData -> cellData.getValue().courseCompProperty());
		timeColumn.setCellValueFactory(cellData -> cellData.getValue().startTimeProperty());


        // 1. Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Section> filteredData = new FilteredList<>(masterData, p -> true);

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Section> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(listTable.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        listTable.setItems(sortedData);
        System.out.println("Added Sorted Data");
    }
	
    public void start(Stage stage) throws Exception {
    	System.out.println("Started");
    }

    @FXML
    void launchNewCourse() {
    	System.out.println("New Course");
    	openWindow("CreateCourse");
    }
    @FXML
    void launchCoursePreferences() {
    	System.out.println("Course Preferences");
    	openWindow("CoursePreferences");
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
    	masterData.add(new Section("CPE 309", "M. McAniff", "14-202", "Lecture", "M W F", "9", "11"));
	} 
	
	
	
	public void addSection(Section section) throws Exception {
		
	}

	
	public void addSectionToCalendarView(Section section) throws Exception {

	}	
	
	public void addSectionToListView(Section section) throws Exception {

		
	}
}
