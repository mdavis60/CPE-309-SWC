package org.swp.scheduler;

import org.swp.scheduler.database.DatabaseException;
import org.swp.scheduler.database.DatabaseManager;
import org.swp.scheduler.database.models.LoginData;
import org.swp.scheduler.database.models.StudentFeedback;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class StudentFeedbackController extends WindowController {

  @FXML
  private TextField studentIdField;
  @FXML
  private TextField subjectField;
  @FXML
  private TextArea messageField;
  @FXML
  private Button submitButton;
  @FXML
  private Button cancelButton;

  // function to submit student feedback
  public void submitFeedback() throws Exception {
    String studentId = studentIdField.getText();
    String subject = subjectField.getText();
    String message = messageField.getText();

    if (!subject.isEmpty() && !message.isEmpty()) {
      // Change hardcoded term
      StudentFeedback feedback = new StudentFeedback(studentId, subject,
          message, Constants.getTerm("winter", 2017));
      DatabaseManager.getInstance().storeSingle(feedback);
    }
  }

  @FXML
  void submitPressed() throws Exception {
    System.out.println("Submit button was pressed!");
    submitFeedback();
    closeWindow(submitButton);
  }

  @FXML
  void cancelPressed() {
    System.out.println("Cancel button was pressed!");
    closeWindow(cancelButton);
  }
}
