package org.swp.scheduler.database.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by jackson on 2/13/17.
 */

@Entity
@Table(name = "TimePreferences")
public class TimePreference extends Model {
    @Id
    int timePreferenceId;

    public String teacherId;
    public int preferenceLevel;

    public String days;


    public TimePreference() {
    }

    public TimePreference(Teacher teacher, int preferenceLevel) {
        this.preferenceLevel = preferenceLevel;
        this.teacherId = teacher.teacherId;
    }
}
