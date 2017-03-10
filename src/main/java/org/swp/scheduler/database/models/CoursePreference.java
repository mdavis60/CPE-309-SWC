package org.swp.scheduler.database.models;

import javax.persistence.*;

/**
 * Created by jackson on 2/13/17.
 * 
 * These objects compose Courses
 */

@Entity
@Table(name = "CoursePreferences")
public class CoursePreference extends Model {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  int coursePreferenceId;

  public String courseId;
  public String teacherId;
  public int preferenceLevel;

  public CoursePreference() {
  }

  public CoursePreference(Teacher teacher, Course course, int preferenceLevel) {
    this.preferenceLevel = preferenceLevel;
    this.teacherId = teacher.teacherId;
    this.courseId = course.courseId;
  }
}
