package org.swp.scheduler.database.models;

import javax.persistence.*;

/**
 * Created by jackson on 2/3/17.
 * 
 * When students write feedback it is stored anonymously and organized by section
 */

@Entity
@Table(name = "StudentFeedback")
public class StudentFeedback extends Model {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  public int feedbackId;
  public String studentId;
  public String term;
  public String subject;
  public String feedback;

  // Subject, Message, StudentID
  public StudentFeedback() {
  }

  public StudentFeedback(String studentId, String subject, String feedback,
      String term) {
    this.studentId = studentId;
    this.subject = subject;
    this.feedback = feedback;
    this.term = term;
  }

  public StudentFeedback(String message, String term) {
    feedback = message;
    this.term = term;
  }
}
