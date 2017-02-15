package org.swp.scheduler.database.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by jackson on 2/13/17.
 */
@Entity
@Table(name = "CoursePreferences")
public class CoursePreference {
    @Id
    int preferenceId;

    public int courseId;
    public int teacherId;
    public int preferenceLevel;

    public CoursePreference() {
    }

    public CoursePreference(Course course, Teacher teacher, int preferenceLevel) {
        this.preferenceLevel = preferenceLevel;
        this.teacherId = teacher.teacherId;
        this.courseId = course.courseId;
    }
}
