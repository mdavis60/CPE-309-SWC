package org.swp.scheduler;


import java.util.ArrayList;
import java.util.List;

import org.hibernate.mapping.Component.StandardGenerationContextLocator;
import org.swp.scheduler.database.DatabaseManager;
import org.swp.scheduler.database.models.LoginData;
import org.swp.scheduler.database.models.Course;
import org.swp.scheduler.database.models.CourseComponent;
import org.swp.scheduler.database.models.Section;
import org.swp.scheduler.database.models.Teacher;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.Group;
import javafx.util.Callback;
import javafx.stage.Stage;
import javafx.stage.Window;

@SuppressWarnings("restriction")
public class CreateCourseController extends WindowController {

	@FXML
    private VBox courseComponents;
	@FXML
    private TextField courseName;

    @FXML
    private TextField courseNumber;

    @FXML
    private TextField courseType;

    @FXML
    private ListView<Course> availablePrereqs;

    @FXML
    private ListView<Course> prereqList;

    @FXML
    private TextField workUnits;

    @FXML
    private TextField studentUnits;

    @FXML
    private Button createButton;
    
    @FXML
    private Group blankComponent;
    
    @FXML
    private TextField prereqField;
    
    private ObservableList<Course> selectedPrereqs;
    
	@FXML
    private void initialize() {
    	
    	FilteredList<Course> filteredCourseData = new FilteredList<>(MasterController.getCourseData(), p -> false);
    	prereqField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredCourseData.setPredicate(course -> {
				// If filter text is empty, display all persons.
				if (newValue == null || newValue.isEmpty()) {
					availablePrereqs.setPrefHeight(0);
					return false;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				System.out.println("Filter: " + lowerCaseFilter);
				System.out.println("Courses: " + course.getCourseName().toLowerCase());
				System.out.println(course.getCourseName().toLowerCase().indexOf(lowerCaseFilter));
				if (course.getCourseName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches first name.
				}
				return false; // Does not match.
			});
            availablePrereqs.setPrefHeight(filteredCourseData.size() * 23.0);
		});
    	availablePrereqs.setCellFactory(new Callback<ListView<Course>, ListCell<Course>>(){
			 
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
    	availablePrereqs.setItems(filteredCourseData);
    	
    	selectedPrereqs = FXCollections.observableArrayList();
    	prereqList.setItems(selectedPrereqs);
    	prereqList.setCellFactory(new Callback<ListView<Course>, ListCell<Course>>(){
			 
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
    }
    
    @FXML
    void addComponent() {
    	courseComponents.getChildren().add(courseComponents.getChildren().size()-1, new CourseComponentController());
//    	Window thisWindow = courseComponents.getScene().getWindow();
//    	thisWindow.setHeight(thisWindow.getHeight() + 80);
    	

    }
    @FXML
    void CreateCourse() {

      //Course data = new Course(courseName, courseNumber, "password", LoginData.AuthType.ADIMIN);
      //DatabaseManager.getInstance().storeSingle(data);
      String name = courseName.getText();
      int cnum = Integer.parseInt(courseNumber.getText());
      String type = courseType.getText();
      String prereq = prereqField.getText();
      List<CourseComponent> components = new ArrayList<CourseComponent>();
      for(javafx.scene.Node component : courseComponents.getChildren())
      {
    	  if(component instanceof CourseComponentController)
    	  {
    		  CourseComponent c = ((CourseComponentController)component).getComponent();
    		  c.setCourseID(cnum);
    		  components.add(c);
    	  }
      }
      
      MasterController.getInstance().addToCourses(new Course(cnum, String.valueOf(cnum), name, components));
      closeWindow(createButton);
    }

    @FXML
    void deselectPrereq() {
    	selectedPrereqs.remove(
    			prereqList.getSelectionModel().getSelectedItem());
    	prereqList.setPrefHeight(selectedPrereqs.size() * 23.0);
    }

    @FXML
    void selectPrereq() {
    	Course selected = availablePrereqs.getSelectionModel().getSelectedItem();
    	if(!selectedPrereqs.contains(selected)) {
    		selectedPrereqs.add(selected);
    		prereqList.setPrefHeight(prereqList.getPrefHeight() + 23.0);
    	    prereqField.setText("");
    	}
    }

}
