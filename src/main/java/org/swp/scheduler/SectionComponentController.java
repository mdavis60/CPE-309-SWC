package org.swp.scheduler;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.scene.shape.*;
import javafx.application.*;
import javafx.collections.*;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.slf4j.*;
import org.swp.scheduler.database.models.*;

import javafx.collections.transformation.*;
import javafx.util.Callback;
import javafx.fxml.FXMLLoader;

@SuppressWarnings("restriction")
public class SectionComponentController extends AnchorPane {

  private static ObservableList<String> times = FXCollections
      .observableArrayList("7 am", "8 am", "9 am", "10 am", "11 am", "12 pm",
          "1 pm", "2 pm", "3 pm", "4 pm", "5 pm", "6 pm", "7 pm", "8 pm",
          "9 pm", "10 pm");
  private Course course;

  @FXML
  private ComboBox<CourseComponent> sectionType;

  @FXML
  private ComboBox<Teacher> professor;

  @FXML
  private ComboBox<Room> room;

  @FXML
  private ComboBox<String> startTime;

  @FXML
  private ComboBox<String> endTime;

  @FXML
  private RadioButton mBut;

  @FXML
  private RadioButton tBut;

  @FXML
  private RadioButton wBut;

  @FXML
  private RadioButton rBut;

  @FXML
  private RadioButton fBut;

  private Section thisSection;

  public SectionComponentController(Course c) {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
        "/fxml/SectionComponentTemplate.fxml"));
    fxmlLoader.setRoot(this);
    fxmlLoader.setController(this);
    this.course = c;
    thisSection = new Section();
    thisSection.setCourse(c);
    try {
      fxmlLoader.load();
    } catch (IOException exception) {
      throw new RuntimeException(exception);
    }
  }

  @FXML
  public void initialize() {
    professor
        .setCellFactory(new Callback<ListView<Teacher>, ListCell<Teacher>>() {

          @Override
          public ListCell<Teacher> call(ListView<Teacher> param) {

            ListCell<Teacher> cell = new ListCell<Teacher>() {
              @Override
              protected void updateItem(Teacher t, boolean bln) {
                super.updateItem(t, bln);
                if (t != null) {
                  setText(t.getTeacherName());
                }
              }
            };

            return cell;
          }
        });

    room.setCellFactory(new Callback<ListView<Room>, ListCell<Room>>() {

      @Override
      public ListCell<Room> call(ListView<Room> param) {

        ListCell<Room> cell = new ListCell<Room>() {

          @Override
          protected void updateItem(Room t, boolean bln) {
            super.updateItem(t, bln);
            if (t != null) {
              setText(t.getRoom());
            }
          }
        };

        return cell;
      }
    });

    sectionType
        .setCellFactory(new Callback<ListView<CourseComponent>, ListCell<CourseComponent>>() {

          @Override
          public ListCell<CourseComponent> call(ListView<CourseComponent> param) {

            ListCell<CourseComponent> cell = new ListCell<CourseComponent>() {

              @Override
              protected void updateItem(CourseComponent t, boolean bln) {
                super.updateItem(t, bln);
                if (t != null) {
                  setText(t.getCourseType());
                }
              }
            };

            return cell;
          }
        });
    ObservableList<Room> rooms = MasterController.getInstance().getRoomData();
    room.getItems().addAll(rooms);

    ObservableList<Teacher> teachers = MasterController.getInstance()
        .getTeacherData();
    professor.getItems().addAll(teachers);

    ObservableList<CourseComponent> components = MasterController.getInstance()
        .getComponentsForCourse(course);
    sectionType.getItems().addAll(components);

    startTime.setItems(times);
    endTime.setItems(times);

  }

  public Section getSection() {
    thisSection.setDow(getDow());
    thisSection.setCourse(course);
    return thisSection;
  }

  @FXML
  void setEndTime() {
    thisSection.endTime = endTime.getValue();
    System.out.println(thisSection.endTime);
  }

  @FXML
  void setRoom() {
    thisSection.setRoom(room.getValue());
    thisSection.roomId = room.getValue().roomId;
    System.out.println(thisSection.getRoom().getRoom());
  }

  @FXML
  void setStartTime() {
    thisSection.startTime = startTime.getValue();
    System.out.println(thisSection.startTime);
  }

  @FXML
  void setTeacher() {
    thisSection.setTeacher(professor.getValue());
    thisSection.teacherId = professor.getValue().getTeacherId();
    System.out.println(thisSection.getTeacher().getTeacherName());
  }

  @FXML
  void setType() {
    thisSection.setCourseComponent(sectionType.getValue());
    thisSection.courseComponentId = sectionType.getValue().courseComponentId;
    System.out.println(thisSection.getCourseComponent().getCourseType());
  }

  private String getDow() {
    String dow = "";
    if (mBut.isSelected()) {
      dow += "M ";
    }
    if (tBut.isSelected()) {
      dow += "T ";
    }
    if (wBut.isSelected()) {
      dow += "W ";
    }
    if (rBut.isSelected()) {
      dow += "R ";
    }
    if (fBut.isSelected()) {
      dow += "F ";
    }

    return dow.trim();
  }

  @FXML
  void onRemove() {
    ((VBox) this.getParent()).getChildren().remove(this);
  }

}
