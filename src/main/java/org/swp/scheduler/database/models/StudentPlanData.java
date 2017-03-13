package org.swp.scheduler.database.models;

import javax.persistence.*;

/**
 * Created by jackson on 2/2/2017.
 *
 * Data from PASS loaded into a DB table
 */

@Entity
@Table(name = "StudentPlanData")
public class StudentPlanData extends Model {
  @Id
  public int courseId;

  public String term;
  public String college;
  public String department;
  public String subjectCode;
  public int catalogNum;
  public String courseTitle;
  public String component;
  public int seatDemand;
  public int sectionsOffered;
  public int enrollmentCapacity;
  public int unmetSeatDemand;
  public double percentUnmetDemand;

  public StudentPlanData() {

  }

  public StudentPlanData(String term, String college, String department,
      int courseId, String subjectCode, int catalogNum, String courseTitle,
      String component, int seatDemand, int sectionsOffered,
      int enrollmentCapacity, int unmetSeatDemand, int percentUnmetDemand) {

    this.courseId = courseId;
    this.term = term;
    this.college = college;
    this.department = department;
    this.subjectCode = subjectCode;
    this.component = component;
    this.catalogNum = catalogNum;
    this.courseTitle = courseTitle;
    this.unmetSeatDemand = unmetSeatDemand;
    this.percentUnmetDemand = percentUnmetDemand;
    this.enrollmentCapacity = enrollmentCapacity;
    this.seatDemand = seatDemand;
    this.sectionsOffered = sectionsOffered;
  }

  @Id
  public Integer courseId() {
    return this.courseId;
  }

  @Override
  public String toString() {
    StringBuilder toRet = new StringBuilder();
    return toRet.toString();
  }
}
