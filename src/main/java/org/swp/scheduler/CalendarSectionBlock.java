package org.swp.scheduler;

import javafx.fxml.FXML;

import org.swp.scheduler.database.models.Section;

import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Label;

public class CalendarSectionBlock extends AnchorPane {

  @FXML
  private Label course;

  @FXML
  private Label sectionNumber;

  @FXML
  private Label startTime;

  @FXML
  private Label endTime;

  @FXML
  private Label professor;

  @FXML
  private Label room;

  public CalendarSectionBlock(Section section, int sectionNumber) {
    course.setText(section.getCourse().getCourseName());
    this.sectionNumber.setText("" + sectionNumber);
    professor.setText(section.getTeacher().getTeacherName());
    room.setText(section.getRoom().getRoom());
    startTime.setText(section.getStartTime());
    endTime.setText(section.getEndTime());
  }
}
