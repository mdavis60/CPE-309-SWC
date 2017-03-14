package org.swp.scheduler;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.swp.scheduler.database.DatabaseManager;
import org.swp.scheduler.database.models.CourseComponent;
import org.swp.scheduler.database.models.CourseType;
import org.swp.scheduler.database.models.Model;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 *
 *
 */

@SuppressWarnings("restriction")
public class CourseComponentController extends AnchorPane implements
    Initializable {

  @FXML
  private Group blankComponent;

  @FXML
  private TextField workUnits;

  @FXML
  private TextField studentUnits;

  @FXML
  private Button removeButton;

  @FXML
  private ComboBox<String> courseType;

  @FXML
  private Button courseTypeButton;

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    try {
      List<Model> list = DatabaseManager.getInstance().getAll(CourseType.class);
      ObservableList<String> options = FXCollections.observableArrayList();

      for (Model type : list) {
        options.add(((CourseType) type).courseType);
      }

      courseType.getItems().removeAll(courseType.getItems());
      courseType.getItems().addAll(options);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public CourseComponentController() {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
        "/fxml/CourseComponentTemplate.fxml"));
    fxmlLoader.setRoot(this);
    fxmlLoader.setController(this);

    try {
      fxmlLoader.load();
    } catch (IOException exception) {
      throw new RuntimeException(exception);
    }
  }

  @FXML
  public void courseTypePressed() {
    initialize(null, null);
  }

  @FXML
  public void buttonPressed() {
    try {
      String fxmlFile = "/fxml/AddRoomType.fxml";
      Pane myPane = FXMLLoader.load(getClass().getResource(fxmlFile));

      Scene scene = new Scene(myPane);
      scene.getStylesheets().add("/styles/styles.css");

      Stage stage = new Stage();
      stage.setTitle("AddRoomType");
      stage.setScene(scene);
      stage.setResizable(false);
      stage.show();
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("Could not instantiate stage");
    }
  }

  public String getWorkUnits() {
    return workUnits.getText();
  }

  public String getStudentUnits() {
    return studentUnits.getText();
  }

  public CourseComponent getComponent() {
    try {
      CourseComponent component = new CourseComponent(courseType.getValue(),
          Integer.parseInt(getWorkUnits()),
          Integer.parseInt(getStudentUnits()),
          Integer.parseInt(getStudentUnits()));
      return component;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  @FXML
  void onRemove() {
    System.out.println("Removed Component");
    ((VBox) this.getParent()).getChildren().remove(this);
  }
}
