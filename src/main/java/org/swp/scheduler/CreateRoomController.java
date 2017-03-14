package org.swp.scheduler;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.swp.scheduler.database.DatabaseManager;
import org.swp.scheduler.database.models.Model;
import org.swp.scheduler.database.models.Room;
import org.swp.scheduler.database.models.RoomType;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Adds a room to the list of available rooms
 *
 *
 * SRS: Section 4.1.3
 */
public class CreateRoomController extends WindowController implements
    Initializable {

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

  @SuppressWarnings({ "restriction" })
  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    try {
      list = DatabaseManager.getInstance().getAll(RoomType.class);
      options = FXCollections.observableArrayList();

      for (Model type : list) {
        options.add(((RoomType) type).roomType);
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

    MasterController.getInstance().addToRooms(
        new Room(roomNumber, roomNumber, buildingNumber, capacity, attributes
            .getText()));
    System.out.println("Room Created");
    closeWindow(createButton);
  }
}