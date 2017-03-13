package org.swp.scheduler;

import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.util.Callback;
import javafx.collections.*;

import java.util.List;

import org.swp.scheduler.database.models.*;

@SuppressWarnings("restriction")
public class CreateSectionController extends WindowController {

  @FXML
  private Text addComponent;

  @FXML
  private AnchorPane mainAnchor;

  @FXML
  private VBox vBox;

  @FXML
  private ComboBox<Course> courseBox;

  @FXML
  private Button createButton;

  private Course theCourse;

  @FXML
  private void initialize() {

    courseBox
        .setCellFactory(new Callback<ListView<Course>, ListCell<Course>>() {

          @Override
          public ListCell<Course> call(ListView<Course> param) {

            ListCell<Course> cell = new ListCell<Course>() {
              @Override
              protected void updateItem(Course t, boolean bln) {
                super.updateItem(t, bln);
                if (t != null) {
                  setText(t.getCourseName());
                }
              }

            };

            return cell;
          }
        });

    ObservableList<Course> courses = MasterController.getInstance()
        .getCourseData();
    courseBox.getItems().addAll(courses);

  }

  @FXML
  public void onAddSection() {
    if (theCourse != null) {
      System.out.println("Adding component");
      vBox.getChildren().add(vBox.getChildren().size() - 1,
          new SectionComponentController(theCourse));
    }
  }

  @FXML
  void setCourse() {
    theCourse = courseBox.getValue();
    onAddSection();

  }

  @FXML
  void createSection() {
    try {
      for (javafx.scene.Node component : vBox.getChildren()) {
        if (component instanceof SectionComponentController) {

          Section section = ((SectionComponentController) component)
              .getSection();

          List<Section> existingSections = MasterController.getInstance()
              .getMasterData();
          boolean teacherConflict = false;
          boolean roomConflict = false;
          for (Section s : existingSections) {
            int oldStart = GetTime(s.startTime);
            int oldEnd = GetTime(s.endTime);
            int newStart = GetTime(section.startTime);
            int newEnd = GetTime(section.endTime);
            if ((oldStart >= newStart && oldStart < newEnd)
                || (oldEnd > newStart && oldEnd <= newEnd)
                || (oldStart <= newStart && oldEnd >= newEnd)) {
              if (s.teacherId.equals(section.teacherId) && !teacherConflict) {
                errorMessage("Teacher Time Conflict(s)",
                    "Teacher has been scheduled for another section during this time");
                teacherConflict = true;
              }

              if (s.roomId == section.roomId && !roomConflict) {
                errorMessage("Room Time Conflict(s)",
                    "Room has been scheduled for another section during this time");
                roomConflict = true;
              }
            }

          }

          MasterController.getInstance().addToData(section);
        }
      }
    } catch (Exception e) {
    }

    closeWindow(createButton);
  }

  private int GetTime(String input) {
    String[] elements = input.split(" ");
    int time = Integer.parseInt(elements[0]);

    if (time == 12) {
      time = 0;
    }

    if (elements[1].toUpperCase().equals("PM")) {
      time += 12;
    }
    return time;
  }
}
