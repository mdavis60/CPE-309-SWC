package org.swp.scheduler.database.models;

import javax.persistence.*;
import java.util.List;

/**
 * Created by jackson on 2/3/17.
 */

@Entity
@Table(name = "Course")
public class Course extends Model {
    @Id
    int courseId;
    String courseName;

    //https://stackoverflow.com/questions/18379766/hql-hibernate-inner-join
    //@OneToMany(mappedBy="employee",cascade=CascadeType.ALL)
    @OneToMany
    @JoinColumn(name="ID_TEAM")
    List<CourseComponent> componentList;

}
