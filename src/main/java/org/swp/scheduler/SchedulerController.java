package org.swp.scheduler;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.swp.scheduler.database.models.Course;
import org.swp.scheduler.database.models.Room;
import org.swp.scheduler.database.models.Section;
import org.swp.scheduler.database.models.Teacher;

import java.io.IOException;

/**
 * Controls the most used models and use cases in the program. The scheduler controller
 * handles adding, removing, filtering, and sorting schedules for a given quarter.
 *
 *
 * SRS: 4.2.2
 */

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
  private TableColumn<Section, String> startTimeColumn;

  @FXML
  private TableColumn<Section, String> endTimeColumn;

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
  private MenuItem createNewAccountMI;

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

  @FXML
  private HBox filterHBox;

  private boolean feedbackIsShown = true;

  protected ListProperty<Course> courseListProperty = new SimpleListProperty<>();
  protected ListProperty<Teacher> teacherListProperty = new SimpleListProperty<>();
  protected ListProperty<Room> roomListProperty = new SimpleListProperty<>();

  public SchedulerController() {
  }

  @FXML
  private void initialize() {

    MasterController.initializeLists();
    try {
      System.out.println("Top");
      sectionNameColumn.setCellValueFactory(cellData -> cellData.getValue()
          .courseProperty());
      profColumn.setCellValueFactory(cellData -> cellData.getValue()
          .profProperty());
      roomColumn.setCellValueFactory(cellData -> cellData.getValue()
          .roomProperty());
      sectionTypeColumn.setCellValueFactory(cellData -> cellData.getValue()
          .courseCompProperty());
      startTimeColumn.setCellValueFactory(cellData -> cellData.getValue()
          .startTimeProperty());
      endTimeColumn.setCellValueFactory(cellData -> cellData.getValue()
          .endTimeProperty());
    } catch (Exception e) {
    }


    /**
     * Initializes boilerplate that allows filtering and sorting by different attributes
     *
     */

    courseListView.setItems(MasterController.getInstance().getCourseData());
    roomsListView.setItems(MasterController.getInstance().getRoomData());
    teachersListView.setItems(MasterController.getInstance().getTeacherData());

    courseListView
        .setCellFactory(new Callback<ListView<Course>, ListCell<Course>>() {

          @Override
          public ListCell<Course> call(ListView<Course> p) {

            ListCell<Course> cell = new ListCell<Course>() {

              @Override
              protected void updateItem(Course t, boolean bln) {
                super.updateItem(t, bln);
                if (t != null) {
                  setText(t.getCourseName() + " - " + t.getCourseTitle());
                }
              }

            };

            return cell;
          }
        });
    teachersListView
        .setCellFactory(new Callback<ListView<Teacher>, ListCell<Teacher>>() {

          @Override
          public ListCell<Teacher> call(ListView<Teacher> p) {

            ListCell<Teacher> cell = new ListCell<Teacher>() {

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
    roomsListView
        .setCellFactory(new Callback<ListView<Room>, ListCell<Room>>() {

          @Override
          public ListCell<Room> call(ListView<Room> p) {

            ListCell<Room> cell = new ListCell<Room>() {

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

    // 1. Wrap the ObservableList in a FilteredList (initially display all
    // data).
    FilteredList<Section> filteredData = new FilteredList<>(
        MasterController.getInstance().getMasterData(), p -> true);

    filterField.textProperty().addListener(
        (observable, oldValue, newValue) -> {
          filteredData.setPredicate(section -> {
            // If filter text is empty, display all
            // persons.
              if (newValue == null || newValue.isEmpty()) {
                return true;
              }

              // Compare first name and last name of
              // every person with filter text.
              String lowerCaseFilter = newValue.toLowerCase();

              try {
                if (("" + section.getCourse().courseId).toLowerCase().indexOf(
                    lowerCaseFilter) != -1) {
                  return true; // Filter matches
                               // first name.
                } else if (section.getTeacher().getTeacherId().toLowerCase()
                    .indexOf(lowerCaseFilter) != -1) {
                  return true; // Filter matches
                               // last name.
                } else if (section.getCourse().getCourseTitle().toLowerCase()
                    .indexOf(lowerCaseFilter) != -1) {
                  return true; // Filter by course
                               // name
                } else if (section.getStartTime().toLowerCase()
                    .indexOf(lowerCaseFilter) != -1
                    || section.getEndTime().toLowerCase()
                        .indexOf(lowerCaseFilter) != -1) {
                  return true; // Filter by Start
                               // and end time
                } else if (section.getRoom().getRoom().toLowerCase()
                    .indexOf(lowerCaseFilter) != -1) {
                  return true;
                } else if (section.getTeacher().getTeacherName().toLowerCase()
                    .indexOf(lowerCaseFilter) != -1) {
                  return true;
                }
              } catch (Exception e) {
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

  public void enterPressedOnFilter(KeyEvent key) {
    if (key.getCode().equals(KeyCode.ENTER)) {
      if (filterHBox.getChildren().size() < 3) {
        filterHBox.getChildren().add(new FilterController());
      }
    }
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
    openWindow("CreateRoom");
  }

  @FXML
  void launchNewType() {
    System.out.println("New Room Type");
    openWindow("AddRoomType");
  }

  @FXML
  void launchStudentFeedback() {
    System.out.println("Student Feedback");
    openWindow("StudentFeedback");
  }

  @FXML
  void launchTimePreferences() {
    System.out.println("TimePreference");
    openWindow("TimePreferences");
  }

  @FXML
  void onAddSection() {
    System.out.println("Added new Section");
  }

  @FXML
  void launchCreateNewAccount() {
    openWindow("CreateAccount");
  }

  @FXML
  void onSave() {

    try {
      System.out.println("Data saved to database");
      MasterController.getInstance().saveDataToDB();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @FXML
  void importSchedule() {

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
    // MasterController.getInstance().addToData(new Section("CPE 309",
    // "M. McAniff", "14-202", "Lecture", "M W F", "9", "11"));
  }

  public void addSection(Section section) throws Exception {

  }

  public void addSectionToCalendarView(Section section) throws Exception {

  }

  public void addSectionToListView(Section section) throws Exception {

  }

  private static class FilterController extends AnchorPane {

    public FilterController() {
      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
          "/fxml/FilterTemplate.fxml"));
      fxmlLoader.setRoot(this);
      fxmlLoader.setController(this);

      try {
        fxmlLoader.load();
      } catch (IOException exception) {
        throw new RuntimeException(exception);
      }
    }

  }

}
