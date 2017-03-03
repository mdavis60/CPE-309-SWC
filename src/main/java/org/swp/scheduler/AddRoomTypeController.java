package org.swp.scheduler;

import org.swp.scheduler.database.DatabaseManager;
import org.swp.scheduler.database.models.CourseType;
import org.swp.scheduler.database.models.Model;
import org.swp.scheduler.database.models.RoomType;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class AddRoomTypeController extends WindowController {

    @FXML private Button addRoomTypeButton;
    @FXML private TextField roomType;
    @FXML private RadioButton room;
    @FXML private RadioButton course;
    @FXML private RadioButton both;
    
    @FXML
    void enterPressed(KeyEvent key) throws Exception {
    	if(key.getCode() == KeyCode.ENTER){
    		addRoomType();
    	}
    }
    
    public void helper(Class id, String typeField) throws Exception {
    	Model type;
    	
    	if(id == RoomType.class)
			type = new RoomType(typeField);
    	else
			type = new CourseType(typeField);
    	
    	if(!DatabaseManager.getInstance().containsKey(id, typeField)) {
    		DatabaseManager.getInstance().storeSingle(type);
        	closeWindow(addRoomTypeButton);
		}
		else {
    		errorMessage("Room/Course type already exists", "Please specify a new Room/Course type");
		}
    }
    
    public void addRoomType() throws Exception {
    	String typeField = roomType.getText().trim().toUpperCase();
    	
    	if(!typeField.isEmpty()) {        		
    		if(both.isSelected()) {
    			helper(RoomType.class, typeField);
        		helper(CourseType.class, typeField);
    		}
    		else if(room.isSelected()) {
    			helper(RoomType.class, typeField);
    			//closeWindow(addRoomTypeButton);
    		}
    		else if(course.isSelected()) {
        		helper(CourseType.class, typeField);
        		//closeWindow(addRoomTypeButton);
    		}
    		else{
        		errorMessage("No button selected", "Please specify if this is a new Room/Course type or both");
    		}
    		
    	}
    	else {
    		errorMessage("Room/Course type field is empty", "Please specify a new Room/Course type");
    	}
    }
}
