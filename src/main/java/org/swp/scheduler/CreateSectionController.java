package org.swp.scheduler;

import javafx.fxml.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.scene.shape.*;
import javafx.application.*;
import javafx.collections.*;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;

import java.util.ArrayList;

import org.slf4j.*;
import org.swp.scheduler.database.models.*;

import javafx.collections.transformation.*;
import javafx.util.Callback;
public class CreateSectionController extends WindowController {

	@FXML
	private Text addComponent; 

	@FXML
	private AnchorPane mainAnchor;

	@FXML
	private VBox vBox; 


	@FXML
	private void initialize() {
    	vBox.getChildren().add(vBox.getChildren().size()-1, new SectionComponentController());		
	}
	
	@FXML
	public void onAddComponent() {
		System.out.println("Adding component");
		vBox.getChildren().add(vBox.getChildren().size()-1, new SectionComponentController());
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
    }*/
}
