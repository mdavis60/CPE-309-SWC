package org.swp.scheduler.database.models;

import org.swp.scheduler.database.DatabaseException;
import org.swp.scheduler.database.DatabaseManager;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by jackson on 2/3/17.
 */

@Entity
@Table(name = "CourseComponent")
public class CourseComponent extends Model {
    @Id
    public int componentId;
    public String type;
    public int workUnits;
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
}
