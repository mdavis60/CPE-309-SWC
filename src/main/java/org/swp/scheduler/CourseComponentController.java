package org.swp.scheduler;
import java.io.IOException;

import org.swp.scheduler.database.models.CourseComponent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

@SuppressWarnings("restriction")
public class CourseComponentController extends AnchorPane {

 
	@FXML
    private Group blankComponent;

    @FXML
    private TextField workUnits;

    @FXML
    private TextField studentUnits;
    
    @FXML
    private Label componentType;
    
    public CourseComponentController()
    {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
    			"/fxml/CourseComponentTemplate.fxml"));
    	fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
    
    public String getWorkUnits()
    {
    	return workUnits.getText();
    }
    public String getStudentUnits()
    {
    	return studentUnits.getText();
    }
    public CourseComponent getComponent()
    {
    	try
    	{
    		CourseComponent component = new CourseComponent(componentType.getText(), Integer.parseInt(getWorkUnits()), Integer.parseInt(getStudentUnits()));
    		return component;
    	}catch(Exception e)
    	{
    		e.printStackTrace();
    		return null;
    	}
    }
}
