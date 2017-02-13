package org.swp.scheduler;

import org.swp.scheduler.database.DatabaseManager;
import org.swp.scheduler.database.models.LoginData;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.shape.Rectangle;

public class CreateCourseController extends WindowController {

    @FXML
    private TextField courseName;

    @FXML
    private TextField courseNumber;

    @FXML
    private TextField courseType;

    @FXML
    private TextField prereqs;

    @FXML
    private TextField workUnits;

    @FXML
    private TextField studentUnits;

    @FXML
    private Rectangle createButton;

    @FXML
    void CreateCourse() {

      //Course data = new Course(courseName, courseNumber, "password", LoginData.AuthType.ADIMIN);
      //DatabaseManager.getInstance().storeSingle(data);
      
      closeWindow(createButton);
    }
}
