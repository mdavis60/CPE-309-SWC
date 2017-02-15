package org.swp.scheduler.database.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

/**
 * Created by jackson on 2/3/17.
 */

@Entity
@Table(name = "Teachers")
public class Teacher extends Model {
    @Id
    public String teacherId;
    public String teacherName;
    public List<TimePreference> timePreferences;
    public List<CoursePreference> coursePreferences;

    public Teacher() {
    }

    public Teacher(String teacherId, String teacherName) {
        this.teacherId = teacherId;
        this.teacherName = teacherName;
    }
}
