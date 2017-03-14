package org.swp.scheduler;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

/**
 * Many to One relationship with Teachers that allows them to specify
 * which courses they prefer, or prefer not to teach. This process involves
 * adding only a single row to the database in the worst case.
 *
 *
 * SRS: Section 4.2.1
 */
public class CoursePreferencesController extends WindowController {

  @FXML
  private ComboBox<?> qualifiedCoursesField;
  @FXML
  private ComboBox<?> preferredCoursesField;
  @FXML
  private Button cancelButton;
  @FXML
  private Button submitButton;

  // function to set course preferences
  public void setCoursePreferences() {

    qualifiedCoursesField.setPromptText("Course Number or Course Name");
    preferredCoursesField.setPromptText("Course Number or Course Name");
  }

  @FXML
  void submitPressed() {
    System.out.println("Submit button was pressed!");
    closeWindow(submitButton);
  }

  @FXML
  void cancelPressed() {
    System.out.println("Cancel button was pressed!");
    closeWindow(cancelButton);
  }
}
