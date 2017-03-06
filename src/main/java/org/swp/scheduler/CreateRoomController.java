package org.swp.scheduler;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.swp.scheduler.database.DatabaseException;
import org.swp.scheduler.database.DatabaseManager;
import org.swp.scheduler.database.models.Model;
import org.swp.scheduler.database.models.Room;
import org.swp.scheduler.database.models.RoomType;

import javafx.fxml.FXML;
import javafx.fxml.*;
import javafx.scene.control.ComboBox;
import javafx.collections.*;
import javafx.scene.control.TextField;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Button;

public class CreateRoomController extends WindowController implements Initializable {

    @FXML
    private TextField buildNum;

    @FXML
    private TextField roomNum;

    @FXML
    private TextField attributes;

    @FXML
    private ComboBox<String> roomType;

    @FXML
    private TextField maxCap;

    @FXML
    private Button createButton;
    
    @FXML
    private Button roomTypeButton;
    
    private List<Model> list;
    private ObservableList<String> options;

    @SuppressWarnings({"restriction"})
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
        try {
			list = DatabaseManager.getInstance().getAll(RoomType.class);
	    	options = FXCollections.observableArrayList();
	    	
	    	for(Model type: list){
	    		options.add(((RoomType)type).roomType);
	    	}
	    	
	    	roomType.getItems().removeAll(roomType.getItems());
	    	roomType.getItems().addAll(options);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
    @FXML
    void roomTypePressed() {
    	System.out.println("RoomType Pressed!");
    	initialize(null, null);
    }
    
    @FXML
    void roomTypeButtonPressed() {
    	openWindow("AddRoomType");
    }
    
    @FXML
    void createRoom() {
    	int roomNumber = Integer.parseInt(roomNum.getText());
    	int buildingNumber = Integer.parseInt(buildNum.getText());
    	int capacity = Integer.parseInt(maxCap.getText());
    	
    	MasterController.getInstance().addToRooms(new Room(roomNumber, roomNumber, buildingNumber, capacity, attributes.getText()));
    	System.out.println("Room Created");
    	closeWindow(createButton);
    }
}