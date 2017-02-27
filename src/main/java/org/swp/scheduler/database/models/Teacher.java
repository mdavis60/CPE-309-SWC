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


    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="teacherId")
    public List<TimePreference> timePreferences;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="teacherId")
    public List<CoursePreference> coursePreferences;


    public Teacher() {
    }

    public Teacher(String teacherId, String teacherName) {
        this.teacherId = teacherId;
        this.teacherName = teacherName;
        this.targetWorkUnits = 16;
        this.currWorkUnits = 0;
    }
    public String getTeacherName()
    {
    	return teacherName;
    }
}
