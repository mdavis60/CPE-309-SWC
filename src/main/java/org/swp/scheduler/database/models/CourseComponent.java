package org.swp.scheduler.database.models;

import org.swp.scheduler.database.DatabaseException;
import org.swp.scheduler.database.DatabaseManager;

import javax.persistence.*;

/**
 * Created by jackson on 2/3/17.
 * 
 * CourseComponents are things like the Lab component of Course, or other categories
 * defined in CourseType. These are specifically tied to a Course and Sections are created
 * from CourseComponents.
 */

@Entity
@Table(name = "CourseComponent")
public class CourseComponent extends Model {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  public int courseComponentId;
  public int classHours;
  public String courseId;
  public String type;
  public int workUnits;
  public int studentUnits;

  public CourseComponent() {
  }

  public CourseComponent(CourseType type, int workUnits, int classHours,
      Course course) throws DatabaseException {
    // verify the type
    if (DatabaseManager.getInstance().containsKey(CourseType.class,
        type.courseType)) {
      this.type = type.courseType;
      this.workUnits = workUnits;
      this.classHours = classHours;
      this.courseId = course.courseId;

    } else {
      throw new DatabaseException("Not a valid course type");
    }
  }

  public CourseComponent(String type, int workUnits, int studentUnits,
      int classHours) {
    // verify the type
    // if (DatabaseManager.getInstance().containsKey(CourseType.class, type)) {
    this.type = type;
    this.workUnits = workUnits;
    this.classHours = classHours;
    this.studentUnits = studentUnits;

    // } else {
    // throw new DatabaseException("Not a valid course type");
    // }
  }

  public void setCourseID(String id) {
    courseId = id;
  }

  public String getCourseId() {
    return courseId;
  }

  public String getCourseType() {
    return type;
  }
  public String toString()
  {
    return type;
  }
}
