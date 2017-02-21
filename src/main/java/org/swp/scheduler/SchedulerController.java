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

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;

import java.util.ArrayList;

import org.slf4j.*;
import org.swp.scheduler.database.models.*;

import javafx.collections.transformation.*;
import javafx.util.Callback;

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
	private TextField filterField; 
	
	@FXML
	private TableColumn<Section, String> profColumn;
	
	@FXML
	private TableColumn<Section, String> roomColumn;
	
	@FXML
	private TableColumn<Section, String> sectionTypeColumn;
	
	@FXML
	private TableColumn<Section, String> timeColumn;
	
	@FXML
    private ListView<Course> courseListView;

    @FXML
    private ListView<Teacher> teachersListView;

    @FXML
    private ListView<Room> roomsListView;
    
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

    protected ListProperty<Course> courseListProperty = new SimpleListProperty<>();
    protected ListProperty<Teacher> teacherListProperty = new SimpleListProperty<>();
    protected ListProperty<Room> roomListProperty = new SimpleListProperty<>();
    
    public SchedulerController() {
    	MasterController.getInstance().addToData(new Section("CPE 309", "T. Kearns", "14-202", "Lecture", "M W F", "9", "11"));
    	MasterController.getInstance().addToData(new Section("CPE 309", "T. Kearns", "14-202", "Lecture", "M W F", "9", "11"));
    	MasterController.getInstance().addToData(new Section("CPE 309", "T. Kearns", "14-202", "Lecture", "M W F", "9", "11"));
    	MasterController.getInstance().addToData(new Section("CPE 309", "T. Kearns", "14-202", "Lecture", "M W F", "9", "11"));
    	MasterController.getInstance().addToData(new Section("CPE 309", "T. Kearns", "14-202", "Lecture", "M W F", "9", "11"));
    	MasterController.getInstance().addToData(new Section("CPE 309", "T. Kearns", "14-202", "Lecture", "M W F", "9", "11"));
    	MasterController.getInstance().addToData(new Section("CPE 309", "T. Kearns", "14-202", "Lecture", "M W F", "9", "11"));
    	MasterController.getInstance().addToData(new Section("CPE 309", "T. Kearns", "14-202", "Lecture", "M W F", "9", "11"));
    	MasterController.getInstance().addToData(new Section("CPE 309", "T. Kearns", "14-202", "Lecture", "M W F", "9", "11"));

    	MasterController.getInstance().addToCourses(new Course(1 , "309", "CPE", null ));
    	MasterController.getInstance().addToCourses(new Course(2 , "308", "CPE", null ));
    	MasterController.getInstance().addToCourses(new Course(3 , "307", "CPE", null ));
    	MasterController.getInstance().addToCourses(new Course(4 , "357", "CPE", null ));
    	MasterController.getInstance().addToCourses(new Course(5 , "123", "CPE", null ));
    	MasterController.getInstance().addToCourses(new Course(6 , "101", "CPE", null ));
    	MasterController.getInstance().addToCourses(new Course(7 , "102", "CPE", null ));
    	MasterController.getInstance().addToCourses(new Course(8 , "103", "CPE", null ));
    	MasterController.getInstance().addToCourses(new Course(9 , "225", "CPE", null ));
    	MasterController.getInstance().addToCourses(new Course(10 , "484", "CPE", null ));
    	MasterController.getInstance().addToCourses(new Course(11 , "365", "CPE", null ));
    	System.out.println("Added Courses");
    	
    	MasterController.getInstance().addToRooms(new Room(1 , 250, 14, 35, "computers"));
    	MasterController.getInstance().addToRooms(new Room(2 , 251, 14, 35, "none"));
    	MasterController.getInstance().addToRooms(new Room(3 , 252, 14, 35, "computers"));
    	MasterController.getInstance().addToRooms(new Room(4 , 253, 14, 35, "computers"));
    	MasterController.getInstance().addToRooms(new Room(5 , 254, 14, 35, "computers"));
    	MasterController.getInstance().addToRooms(new Room(6 , 255, 14, 35, "computers"));
    	MasterController.getInstance().addToRooms(new Room(7 , 258, 14, 35, "computers"));
    	MasterController.getInstance().addToRooms(new Room(8 , 259, 14, 35, "computers"));
    	MasterController.getInstance().addToRooms(new Room(9 , 261, 14, 35, "computers"));
    	System.out.println("Added Rooms");

    	MasterController.getInstance().addToTeachers(new Teacher( "1", "Kearns"));
    	MasterController.getInstance().addToTeachers(new Teacher( "2", "Daulby"));
    	MasterController.getInstance().addToTeachers(new Teacher( "1", "Mammen"));
    	MasterController.getInstance().addToTeachers(new Teacher( "1", "Workman"));
    	MasterController.getInstance().addToTeachers(new Teacher( "1", "Woods"));
    	MasterController.getInstance().addToTeachers(new Teacher( "1", "Sakellariou"));
    	System.out.println("Added Teachers");
    	
        /*ArrayList<Section> sections = Database.getSections();
        for(Section s : sections) {
          addSection(s);
        }*/
    }
    
    @FXML
    private void initialize() {
    	System.out.println("Top");
		sectionNameColumn.setCellValueFactory(cellData -> cellData.getValue().courseProperty());
		profColumn.setCellValueFactory(cellData -> cellData.getValue().profProperty());
		roomColumn.setCellValueFactory(cellData -> cellData.getValue().roomProperty());
		sectionTypeColumn.setCellValueFactory(cellData -> cellData.getValue().courseCompProperty());
		timeColumn.setCellValueFactory(cellData -> cellData.getValue().startTimeProperty());

		courseListView.setItems(MasterController.getInstance().getCourseData());
		roomsListView.setItems(MasterController.getInstance().getRoomData());
		teachersListView.setItems(MasterController.getInstance().getTeacherData());
		
		courseListView.setCellFactory(new Callback<ListView<Course>, ListCell<Course>>(){
			 
            @Override
            public ListCell<Course> call(ListView<Course> p) {
                 
                ListCell<Course> cell = new ListCell<Course>(){
 
                    @Override
                    protected void updateItem(Course t, boolean bln) {
                        super.updateItem(t, bln);
                        if (t != null) {
                            setText(t.getCourseName());
                        }
                    }
 
                };
                 
                return cell;
            }
        });
		teachersListView.setCellFactory(new Callback<ListView<Teacher>, ListCell<Teacher>>(){
			 
            @Override
            public ListCell<Teacher> call(ListView<Teacher> p) {
                 
                ListCell<Teacher> cell = new ListCell<Teacher>(){
 
                    @Override
                    protected void updateItem(Teacher t, boolean bln) {
                        super.updateItem(t, bln);
                        if (t != null) {
                            setText(t.getTeacherName());
                        }
                    }
 
                };
                 
                return cell;
            }
        });
        roomsListView.setCellFactory(new Callback<ListView<Room>, ListCell<Room>>(){
			 
            @Override
            public ListCell<Room> call(ListView<Room> p) {
                 
                ListCell<Room> cell = new ListCell<Room>(){
 
                    @Override
                    protected void updateItem(Room t, boolean bln) {
                        super.updateItem(t, bln);
                        if (t != null) {
                            setText(t.getRoom());
                        }
                    }
 
                };
                 
                return cell;
            }
        });
		

        // 1. Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Section> filteredData = new FilteredList<>(MasterController.getMasterData(), p -> true);
        
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(section -> {
				// If filter text is empty, display all persons.
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (section.getCourse().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches first name.
				} else if (section.getProf().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				return false; // Does not match.
			});
		});

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
    
    @FXML
    void onAddSection() {
        System.out.println("Added new Section");
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
	
	public void onAddSection(Section section) throws Exception {
    	MasterController.getInstance().addToData(new Section("CPE 309", "M. McAniff", "14-202", "Lecture", "M W F", "9", "11"));
	} 
	
	public void addSection(Section section) throws Exception {
		
	}

	
	public void addSectionToCalendarView(Section section) throws Exception {

	}	
	
	public void addSectionToListView(Section section) throws Exception {

		
	}
	
	
	
}
