package org.swp.scheduler.database.models;

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
    int componentId;
    String type;
    int workUnits;
    int classHours;
    int courseId;
}
