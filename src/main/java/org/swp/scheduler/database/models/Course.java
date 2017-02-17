package org.swp.scheduler.database.models;

import org.swp.scheduler.database.DatabaseManager;

import javax.persistence.*;
import java.util.List;

/**
 * Created by jackson on 2/3/17.
 */
@Entity
@Table(name = "Courses")
public class Course extends Model {
    @Id
    public int courseId;
    public String courseName;
    public String department;

    //https://stackoverflow.com/questions/18379766/hql-hibernate-inner-join
    //@OneToMany(mappedBy="employee",cascade=CascadeType.ALL)
    @OneToMany
    @JoinColumn(name="componentId")
    public List<CourseComponent> componentList;

    //courseName, courseNumber, courseType, prereqs, workUnits, studentUnits
    public Course() {
    }

    public Course(int courseId, String courseName, String department, List<CourseComponent> components) {
        this.courseId = courseId;
        this.department = department;
        this.courseName = courseName;
    }
}
