package org.swp.scheduler.database.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by jackson on 2/3/17.
 */

@Entity
@Table(name = "StudentFeedback")
public class StudentFeedback extends Model {
    @Id
    String feedbackId;


}
