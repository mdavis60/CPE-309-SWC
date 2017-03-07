package org.swp.scheduler;

import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.util.Callback;
import javafx.collections.*;

import java.util.List;

import org.swp.scheduler.database.models.*;

@SuppressWarnings("restriction")
public class CreateSectionController extends WindowController {

	@FXML
	private Text addComponent; 

	@FXML
	private AnchorPane mainAnchor;

	@FXML
	private VBox vBox; 
	
	@FXML
	private ComboBox<Course> courseBox;
	
	@FXML 
	private Button createButton; 
	
	@FXML
	private void initialize() {
	    
		courseBox.setCellFactory(new Callback<ListView<Course>, ListCell<Course>>() {

			@Override
			public ListCell<Course> call(ListView<Course> param) {
				
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
		
		ObservableList<Course> courses = MasterController.getCourseData();
		courseBox.getItems().addAll(courses);

    	vBox.getChildren().add(vBox.getChildren().size()-1, new SectionComponentController(null));		
	}
	
	@FXML
	public void onAddSection() {
		System.out.println("Adding component");
		vBox.getChildren().add(vBox.getChildren().size()-1, new SectionComponentController(null));
	} 
	
	   @FXML
	    void createSection() { 
		   try{
		      Section theCourse = new Section();
		      for(javafx.scene.Node component : vBox.getChildren())
		      {		    	  
		    	  if(component instanceof SectionComponentController)
		    	  {
    				
    				  Section section = ((SectionComponentController) component).getSection(); 

		    		  section.course = courseBox.getValue();
		    		  
		    		  List<Section> existingSections = MasterController.getInstance().getMasterData();
		    		  boolean teacherConflict = false;
		    		  boolean roomConflict = false;
		    		  for(Section s : existingSections){
		    		    int oldStart = GetTime(s.startTime);
		    		    int oldEnd = GetTime(s.endTime);
		    		    int newStart = GetTime(section.startTime);
		    		    int newEnd = GetTime(section.endTime);
		    		    if ((oldStart >= newStart && oldStart < newEnd) || (oldEnd > newStart && oldEnd <= newEnd) || (oldStart <= newStart && oldEnd >= newEnd)) {
		    		      if(s.teacherId.equals(section.teacherId) && !teacherConflict) {
		    		        errorMessage("Teacher Time Conflict(s)", "Teacher has been scheduled for another section during this time");
		    		        teacherConflict = true;
		    		      }
		    		      
		    		      if(s.roomId == section.roomId && !roomConflict) {
		    		        errorMessage("Room Time Conflict(s)", "Room has been scheduled for another section during this time");
		    		        roomConflict = true;
		    		      }
		    		    }
		    		    
		    		  }
		    	  		    		  
				      MasterController.getInstance().addToData(section);
		    	  }
		      }
	      } catch(Exception e) {}
	     
	      closeWindow(createButton);
	    }
	   
	   private int GetTime(String input) {
	     String[] elements = input.split(" ");
	     int time = Integer.parseInt(elements[0]);
	     
	     if (time == 12) {
	       time = 0;
	     }
	     
	     if (elements[1].toUpperCase().equals("PM")) {
	       time += 12;
	     }
	     return time;
	   }


	/*
    @FXML
    void CreateSection() {
      System.out.println("Section Created");
      String course = courseType.getText();
      String prof = professor.getText();
      String roomNum = room.getText();
      String start = startTime.getText();
      String end = endTime.getText();

      // TODO
      //MasterController.getInstance().addToData(new Section(course, prof , roomNum, "Lecture", getDaysOfWeek(), start, end));
      closeWindow(createButton);
    }
    
    private String getDaysOfWeek()
    {
    	String days = "";
    	if(mondayToggle.isSelected())
    	{
    		days += "M ";
    	}
    	if(tuesdayToggle.isSelected())
    	{
    		days += "T ";
    	}
    	if(wednesdayToggle.isSelected())
    	{
    		days += "W ";
    	}
    	if(thursdayToggle.isSelected())
    	{
    		days += "R ";
    	}
    	if(fridayToggle.isSelected())
    	{
    		days += "F ";
    	}
    	return days.trim();
    }*/
}
