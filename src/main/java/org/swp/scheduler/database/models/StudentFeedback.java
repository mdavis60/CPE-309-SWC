package org.swp.scheduler.database.models;

import javax.persistence.*;

/**
 * Created by jackson on 2/3/17.
 */

@Entity
@Table(name = "StudentFeedback")
public class StudentFeedback extends Model {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public int feedbackId;
    public String term;
    public String feedback;

    public StudentFeedback() {
    }

    public StudentFeedback(String feedback, String term) {
        this.feedback = feedback;
        this.term = term;
    }
}
