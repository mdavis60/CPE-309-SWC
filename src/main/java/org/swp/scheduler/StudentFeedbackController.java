package org.swp.scheduler;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class StudentFeedbackController {

    @FXML private TextArea messageField;
    @FXML private TextField subjectField;
    
    //function to submit student feedback
    public void submitFeedback() {
    	
    	String subject = subjectField.getText();
    	String message = messageField.getText();

        subjectField.setText("Test");
        messageField.setText("This is a test");
    }
}
