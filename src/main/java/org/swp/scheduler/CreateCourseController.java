package org.swp.scheduler;

import org.swp.scheduler.database.DatabaseManager;
import org.swp.scheduler.database.models.LoginData;
import org.swp.scheduler.database.models.Course;
import org.swp.scheduler.database.models.CourseComponent;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Button;

public class CreateCourseController extends WindowController {

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
    private Button createButton;

    @FXML
    void CreateCourse() {

      //Course data = new Course(courseName, courseNumber, "password", LoginData.AuthType.ADIMIN);
      //DatabaseManager.getInstance().storeSingle(data);
      String name = courseName.getText();
      int cnum = Integer.parseInt(courseNumber.getText());
      String type = courseType.getText();
      String prereq = prereqs.getText();
      int wu = Integer.parseInt(workUnits.getText());
      int su = Integer.parseInt(studentUnits.getText());
      
      MasterController.getInstance().addToCourses(new Course(cnum, String.valueOf(cnum), name, null));
      closeWindow(createButton);
    }
}
