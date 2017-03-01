package org.swp.scheduler;

import org.swp.scheduler.database.DatabaseManager;
import org.swp.scheduler.database.models.RoomType;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class AddRoomTypeController extends WindowController {

    @FXML private Button addRoomTypeButton;
    @FXML private TextField roomType;
    
    @FXML
    void enterPressed(KeyEvent key) throws Exception {
    	if(key.getCode() == KeyCode.ENTER){
    		addRoomType();
    	}
    }
    
    public void addRoomType() throws Exception {
    	String roomTypeField = roomType.getText().toUpperCase();
    	
    	if(!roomTypeField.isEmpty()) {    		
    		if(!DatabaseManager.getInstance().containsKey(RoomType.class, roomTypeField.trim())) {
    			RoomType type = new RoomType(roomTypeField.trim());
        		DatabaseManager.getInstance().storeSingle(type);
            	closeWindow(addRoomTypeButton);
    		}
    		else {
        		errorMessage("Room type already exists", "Please specify a new room type");
    		}
    	}
    	else {
    		errorMessage("Room type field is empty", "Please specify a new room type");
    	}
    }
}
