package org.swp.scheduler;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.shape.Rectangle;

public class CreateCourseController {

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
      System.out.println("Create Course");
    }

}
