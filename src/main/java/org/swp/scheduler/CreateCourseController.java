package org.swp.scheduler;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import org.swp.scheduler.database.models.Course;
import org.swp.scheduler.database.models.CourseComponent;

/**
 * Controller that handles adding Courses
 *
 * SRS: Section 4.1.9
 */
@SuppressWarnings("restriction")
public class CreateCourseController extends WindowController {

  @FXML
  private TextField department;

  @FXML
  private TextField courseNumber;

  @FXML
  private TextField courseName;

  @FXML
  private TextField prereqField;

  @FXML
  private ListView<Course> availablePrereqs;

  @FXML
  private ListView<Course> prereqList;

  @FXML
  private VBox courseComponents;

  @FXML
  private Button createButton;

  private ObservableList<Course> selectedPrereqs;

  @FXML
  private void initialize() {

    FilteredList<Course> filteredCourseData = new FilteredList<>(
        MasterController.getInstance().getCourseData(), p -> false);
    prereqField.textProperty()
        .addListener(
            (observable, oldValue, newValue) -> {
              filteredCourseData.setPredicate(course -> {
                // If filter text is empty, display all persons.
                  if (newValue == null || newValue.isEmpty()) {
                    availablePrereqs.setPrefHeight(0);
                    return false;
                  }

                  // Compare first name and last name of every person with
                  // filter text.
                  String lowerCaseFilter = newValue.toLowerCase();
                  System.out.println("Filter: " + lowerCaseFilter);
                  System.out.println("Courses: "
                      + course.getCourseName().toLowerCase());
                  System.out.println(course.getCourseName().toLowerCase()
                      .indexOf(lowerCaseFilter));
                  if (course.getCourseName().toLowerCase()
                      .indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches first name.
                  }
                  return false; // Does not match.
                });
              availablePrereqs.setPrefHeight(filteredCourseData.size() * 23.0);
            });
    availablePrereqs
        .setCellFactory(new Callback<ListView<Course>, ListCell<Course>>() {

          @Override
          public ListCell<Course> call(ListView<Course> p) {

            ListCell<Course> cell = new ListCell<Course>() {

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
    availablePrereqs.setItems(filteredCourseData);

    selectedPrereqs = FXCollections.observableArrayList();
    prereqList.setItems(selectedPrereqs);
    prereqList
        .setCellFactory(new Callback<ListView<Course>, ListCell<Course>>() {

          @Override
          public ListCell<Course> call(ListView<Course> p) {

            ListCell<Course> cell = new ListCell<Course>() {

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

    addComponent();
  }

  @FXML
  void addComponent() {
    courseComponents.getChildren().add(
        courseComponents.getChildren().size() - 1,
        new CourseComponentController());
  }

  @FXML
  void CreateCourse() {

    String depart = department.getText();
    int cNumber = Integer.parseInt(courseNumber.getText());
    String cName = courseName.getText();
    String prereq = prereqField.getText();

    try {
      Course theCourse = new Course(cNumber, cName, "PREREQZ", depart);
      for (javafx.scene.Node component : courseComponents.getChildren()) {
        if (component instanceof CourseComponentController) {
          CourseComponent c = ((CourseComponentController) component)
              .getComponent();
          c.setCourseID(theCourse.getCourseID());

          theCourse.addComponent(c);
        }
      }
      MasterController.getInstance().addToCourses(theCourse);
    } catch (Exception e) {
      e.printStackTrace();
    }

    closeWindow(createButton);
  }

  @FXML
  void deselectPrereq() {
    selectedPrereqs.remove(prereqList.getSelectionModel().getSelectedItem());
    prereqList.setPrefHeight(selectedPrereqs.size() * 23.0);
  }

  @FXML
  void selectPrereq() {
    Course selected = availablePrereqs.getSelectionModel().getSelectedItem();
    if (!selectedPrereqs.contains(selected)) {
      selectedPrereqs.add(selected);
      prereqList.setPrefHeight(prereqList.getPrefHeight() + 23.0);
      prereqField.setText("");
    }
  }

}
