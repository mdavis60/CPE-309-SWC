package org.swp.scheduler.database.models;

import org.swp.scheduler.database.DatabaseException;
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
    public String courseId;
    public String department;
    public String courseName;
    public String prerequisites;
    public int courseNumber;

    //https://stackoverflow.com/questions/18379766/hql-hibernate-inner-join
    //@OneToMany(mappedBy="employee",cascade=CascadeType.ALL)

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="courseId")
    public List<CourseComponent> componentList;

    //courseName, courseNumber, courseType, prereqs, workUnits, studentUnits
    public Course() {
    }

    public Course(int courseNumber, String courseName, String prerequisites,
                  String department, List<CourseComponent> components) throws DatabaseException {
        this(courseNumber, courseName, prerequisites, department);
        addComponents(components);
    }

    public void addComponents(List<CourseComponent> components) throws DatabaseException {
        for (CourseComponent c : components) {
            DatabaseManager.getInstance().storeSingle(c);
        }
    }
    public void addComponent(CourseComponent component) throws DatabaseException {
    	DatabaseManager.getInstance().storeSingle(component);
    }

    public Course(int courseNumber, String courseName, String prerequisites,
                  String department) throws DatabaseException {
        this.courseNumber = courseNumber;
        this.courseName = courseName;
        this.department = department.toUpperCase();
        this.courseName = courseName;
        this.prerequisites = prerequisites;
        this.courseId = department.toUpperCase() + " " + courseNumber;
    }
    public String getCourseName()
    {
    	return getCourseID();
    }
    public String getCourseID()
    {
    	return courseId;
    }
    
}
