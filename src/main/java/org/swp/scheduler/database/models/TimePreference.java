package org.swp.scheduler.database.models;

import javax.persistence.*;

/**
 * Created by jackson on 2/13/17.
 *
 * One to Many relationship with Teacher
 *
 * The times along with preference levels the teachers would like to teach.
 */

@Entity
@Table(name = "TimePreferences")
public class TimePreference extends Model {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  int timePreferenceId;

  public String teacherId;
  public int preferenceLevel;

  public String days;
  public int startTime;
  public int endTime;

  public TimePreference() {
  }

  public TimePreference(Teacher teacher, int preferenceLevel, int startTime,
      int endTime, String days) {
    this.preferenceLevel = preferenceLevel;
    this.teacherId = teacher.teacherId;
    this.startTime = startTime;
    this.endTime = endTime;
  }
}
