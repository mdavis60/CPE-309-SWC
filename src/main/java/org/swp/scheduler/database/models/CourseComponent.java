package org.swp.scheduler.database.models;

import org.swp.scheduler.database.DatabaseException;
import org.swp.scheduler.database.DatabaseManager;

import javax.persistence.*;

/**
 * Created by jackson on 2/3/17.
 */

@Entity
@Table(name = "CourseComponent")
public class CourseComponent extends Model {
    @Id
    public int courseComponentId;
    public String type;
    public int workUnits;
    public int studentUnits;
    public int classHours;

    public int courseId;

    public CourseComponent() {
    }

    public CourseComponent(String type, int workUnits, int classHours, int courseId) throws DatabaseException {
        // verify the type
        if (DatabaseManager.getInstance().containsKey(CourseType.class, type)) {
            this.type = type;
            this.workUnits = workUnits;
            this.classHours = classHours;
            this.courseId = courseId;

        } else {
            throw new DatabaseException("Not a valid course type");
        }
    }
    public CourseComponent(String type, int workUnits, int classHours) {
        // verify the type
      //  if (DatabaseManager.getInstance().containsKey(CourseType.class, type)) {
            this.type = type;
            this.workUnits = workUnits;
            this.classHours = classHours;

//        } else {
//            throw new DatabaseException("Not a valid course type");
//        }
    }
    public void setCourseID(int courseId)
    {
    	this.courseId = courseId;
    }
}
