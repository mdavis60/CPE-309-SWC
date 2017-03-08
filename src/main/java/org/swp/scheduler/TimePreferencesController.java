package org.swp.scheduler;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class TimePreferencesController extends WindowController {

  @FXML
  private Button nextButton;
  @FXML
  private Button backButton;
  @FXML
  private Button cancelButton;

  @FXML
  void backPressed() {
    System.out.println("Back button was pressed!");
  }

  @FXML
  void cancelPressed() {
    System.out.println("Cancel button was pressed!");
    closeWindow(cancelButton);
  }

  @FXML
  void nextPressed() {
    System.out.println("Next button was pressed!");
  }

}
