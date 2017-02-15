package org.swp.scheduler.database.models;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by jackson on 2/3/17.
 */

@Entity
public class Teacher extends Model {
    @Id
    public int teacherId;
    public String teacherName;

    public Teacher() {

    }

    public Teacher(int teacherId, String teacherName) {
        this.teacherId = teacherId;
        this.teacherName = teacherName;
    }
}
