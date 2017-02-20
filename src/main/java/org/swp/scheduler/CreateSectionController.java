package org.swp.scheduler;

import org.swp.scheduler.database.models.Section;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class CreateSectionController extends WindowController {

    @FXML
    private TextField courseType;

    @FXML
    private ListView<?> courseTypeList;

    @FXML
    private TextField professor;

    @FXML
    private TextField room;

    @FXML
    private RadioButton mondayToggle;

    @FXML
    private RadioButton tuesdayToggle;

    @FXML
    private RadioButton wednesdayToggle;

    @FXML
    private RadioButton thursdayToggle;

    @FXML
    private RadioButton fridayToggle;

    @FXML
    private TextField startTime;

    @FXML
    private TextField endTime;

    @FXML
    private Button createButton;
    
    public void OpenList(){
      courseTypeList.setVisible(true);
    }
    
    public void CloseList(){
      courseTypeList.setVisible(false);
    }

    @FXML
    void CreateSection() {
      System.out.println("Section Created");
      String course = courseType.getText();
      String prof = professor.getText();
      String roomNum = room.getText();
      String start = startTime.getText();
      String end = endTime.getText();
      
      MasterController.getInstance().addToData(new Section(course, prof , roomNum, "Lecture", getDaysOfWeek(), start, end));
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
    }
}
