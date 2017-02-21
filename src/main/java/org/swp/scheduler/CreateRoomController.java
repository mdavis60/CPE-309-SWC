package org.swp.scheduler;

import org.swp.scheduler.database.models.Room;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Button;

public class CreateRoomController extends WindowController {

    @FXML
    private TextField buildNum;

    @FXML
    private TextField roomNum;

    @FXML
    private TextField attributes;

    @FXML
    private TextField roomType;

    @FXML
    private TextField maxCap;

    @FXML
    private Button createButton;

    @FXML
    void CreateRoom() {
    	int roomNumber = Integer.parseInt(roomNum.getText());
    	int buildingNumber = Integer.parseInt(buildNum.getText());
    	int capacity = Integer.parseInt(maxCap.getText());
    	
    	MasterController.getInstance().addToRooms(new Room(roomNumber, roomNumber, buildingNumber, capacity, attributes.getText()));
    	System.out.println("Room Created");
    	closeWindow(createButton);
    }

}