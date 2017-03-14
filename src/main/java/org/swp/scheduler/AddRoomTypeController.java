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

/**
 * Creates a valid room type and saves it to the database.
 *
 *
 * SRS: Section 4.1.1
 */
public class AddRoomTypeController extends WindowController {

  @FXML
  private Button addRoomTypeButton;
  @FXML
  private TextField roomType;
  @FXML
  private RadioButton room;
  @FXML
  private RadioButton course;
  @FXML
  private RadioButton both;

  @FXML
  void enterPressed(KeyEvent key) throws Exception {
    if (key.getCode() == KeyCode.ENTER) {
      addRoomType();
    }
  }

  public void add(Class id, String typeField) throws Exception {
    Model type;
    String message;

    if (id == RoomType.class) {
      type = new RoomType(typeField);
      message = "Room";
    } else {
      type = new CourseType(typeField);
      message = "Course";
    }

    if (!DatabaseManager.getInstance().containsKey(id, typeField)) {
      DatabaseManager.getInstance().storeSingle(type);
      closeWindow(addRoomTypeButton);
    } else {
      errorMessage(message + " type already exists", "Please specify a new "
          + message + " type");
    }
  }

  public void addRoomType() throws Exception {
    String typeField = roomType.getText().trim().toUpperCase();

    if (!typeField.isEmpty()) {
      if (both.isSelected()) {
        add(RoomType.class, typeField);
        add(CourseType.class, typeField);
      } else if (room.isSelected()) {
        add(RoomType.class, typeField);
      } else if (course.isSelected()) {
        add(CourseType.class, typeField);
      } else {
        errorMessage("No button selected",
            "Please specify if this is a new Room/Course type or both");
      }

    } else {
      errorMessage("Room/Course type field is empty",
          "Please specify a new Room/Course type");
    }
  }
}
