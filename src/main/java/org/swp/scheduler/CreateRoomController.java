package org.swp.scheduler;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.shape.Rectangle;

public class CreateRoomController {

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
    private Rectangle createButton;

    @FXML
    void CreateRoom() {
      System.out.println("Room Created");
    }

}