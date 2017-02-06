package org.swp.scheduler;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public class CoursePreferencesController {

    @FXML private ComboBox<?> qualifiedCoursesField;
    @FXML private ComboBox<?> preferredCoursesField;
    @FXML private Button cancelButton;
    @FXML private Button submitButton;
    
    //function to set course preferences
    public void setCoursePreferences() {
    	
    	qualifiedCoursesField.setPromptText("Course Number or Course Name");
    	preferredCoursesField.setPromptText("Course Number or Course Name");
    }
    
    @FXML
    void submitPressed() {
    	System.out.println("Submit button was pressed!");
    }
    
    @FXML
    void cancelPressed() {
    	System.out.println("Cancel button was pressed!");
    }
}
