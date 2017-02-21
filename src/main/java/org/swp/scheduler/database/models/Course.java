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
    @GeneratedValue(strategy= GenerationType.AUTO)
    public int courseId;
    public String department;
    public String courseName;
    public int courseNumber;
    public String prerequisites;

    //https://stackoverflow.com/questions/18379766/hql-hibernate-inner-join
    //@OneToMany(mappedBy="employee",cascade=CascadeType.ALL)
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="courseId")
    public List<CourseComponent> componentList;

    //courseName, courseNumber, courseType, prereqs, workUnits, studentUnits
    public Course() {
    }

    public Course(int courseNumber, String courseName, String prerequisites, String department) {
        this.courseNumber = courseNumber;
        this.courseName = courseName;
        this.department = department;
        this.courseName = courseName;
        this.prerequisites = prerequisites;
    }
    public String getCourseName()
    {
    	return department + " " + courseName;
    }
}
