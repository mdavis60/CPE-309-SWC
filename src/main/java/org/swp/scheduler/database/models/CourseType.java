package org.swp.scheduler.database.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by jackson on 2/15/17.
 *
 * CourseTypes is basically a persistent Enum that lives in a table called CourseTypes
 */

@Entity
@Table(name = "CourseTypes")
public class CourseType extends Model {
  @Id
  public String courseType;

  public CourseType() {
  }

  public CourseType(String courseType) {
    this.courseType = courseType;
  }

  public String toString() {
    return courseType;
  }
}
