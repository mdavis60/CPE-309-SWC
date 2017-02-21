package org.swp.scheduler.database.models;

import javax.persistence.*;
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
    public int targetWorkUnits;
    public int currWorkUnits;


    @OneToMany
    @JoinColumn(name="timePreferenceId")
    public List<TimePreference> timePreferences;

    @OneToMany
    @JoinColumn(name="coursePreferenceId")
    public List<CoursePreference> coursePreferences;


    public Teacher() {
    }

    public Teacher(String teacherId, String teacherName) {
        this.teacherId = teacherId;
        this.teacherName = teacherName;
    }
    public String getTeacherName()
    {
    	return teacherName;
    }
}
