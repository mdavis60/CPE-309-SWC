package org.swp.scheduler;

import java.awt.Button;
import java.awt.event.ActionEvent;

import org.swp.scheduler.database.DatabaseManager;
import org.swp.scheduler.database.models.LoginData;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.Node;

public abstract class WindowController {

  //Creates a new popup window
  public void openWindow(String window) {
    try{
      String fxmlFile = "/fxml/" + window + ".fxml";
      Pane myPane = FXMLLoader.load(getClass().getResource(fxmlFile));
      
      Scene scene = new Scene(myPane);
      scene.getStylesheets().add("/styles/styles.css");
      
      Stage stage = new Stage();
      stage.setTitle(window);
      stage.setScene(scene);
      stage.setResizable(false);
      stage.show();
    } catch(Exception e) {
      e.printStackTrace();
      System.out.println("Could not instantiate stage");
    }
  }

  public void closeWindow(Node node) {
    node.getScene().getWindow().hide();
  }
}
