package org.swp.scheduler;

import javafx.fxml.FXML;
import javafx.scene.control.Button;	
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class StudentFeedbackController extends WindowController {

    @FXML private TextArea messageField;
    @FXML private TextField subjectField;
    @FXML private Button submitButton;
    @FXML private Button cancelButton;
    
    //function to submit student feedback
    public void submitFeedback() {
    	
    	String subject = subjectField.getText();
    	String message = messageField.getText();

        subjectField.setText("Test");
        messageField.setText("This is a test");
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
