package org.swp.scheduler;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

public class CoursePreferencesController {

    @FXML private ComboBox<?> qualifiedCoursesField;
    @FXML private ComboBox<?> preferredCoursesField;
    
    //function to set course preferences
    public void setCoursePreferences() {
    	
    	qualifiedCoursesField.setPromptText("Course Number or Course Name");
    	preferredCoursesField.setPromptText("Course Number or Course Name");
    }
}
