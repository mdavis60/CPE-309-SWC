package org.swp.scheduler;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.stage.Stage;

import java.awt.Panel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainApp extends Application {

  private static final Logger log = LoggerFactory.getLogger(MainApp.class);

  /**
   * Starts the main app
   *
   * @param args command line arguments
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    launch(args);
  }

  /**
   * Initializes the login model and displays it with JavaFX
   *
   * @param stage
   * @throws Exception
   */
  public void start(Stage stage) throws Exception {

    // initialize the application at the LogIn page
    String fxmlFile = "/fxml/LogIn.fxml";
    log.debug("Loading FXML for main view from: {}", fxmlFile);
    FXMLLoader loader = new FXMLLoader();
    Parent parent = (Parent) loader.load(getClass().getResourceAsStream(
        fxmlFile));

    log.debug("Showing JFX scene");
    Scene scene = new Scene(parent);
    scene.getStylesheets().add("/styles/styles.css");

    stage.setTitle("Log-In Page");
    stage.setScene(scene);
    stage.setResizable(false);
    stage.show();

  }
}
