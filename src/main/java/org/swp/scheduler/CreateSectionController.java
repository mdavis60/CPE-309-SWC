package org.swp.scheduler;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class CreateSectionController {

    @FXML
    private TextField courseType;

    @FXML
    private ListView<?> courseTypeList;

    @FXML
    private TextField professor;

    @FXML
    private TextField room;

    @FXML
    private ToggleGroup WeekDay;

    @FXML
    private TextField startTime;

    @FXML
    private TextField endTime;

    @FXML
    private Button createButton;
    
    public void OpenList(){
      courseTypeList.setVisible(true);
    }
    
    public void CloseList(){
      courseTypeList.setVisible(false);
    }

}
