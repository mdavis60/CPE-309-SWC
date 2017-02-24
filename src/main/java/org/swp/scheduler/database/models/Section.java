package org.swp.scheduler.database.models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.swp.scheduler.database.DatabaseException;
import org.swp.scheduler.database.DatabaseManager;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "Section")
public class Section extends Model {
	@Id
    public int sectionId;

	public int courseComponentId;
	@Transient
	public CourseComponent courseComponent = null;
	@Transient
	public Course course = null;

    public String teacherId;
	@Transient
    public Teacher teacher = null;

    public int roomId;
	@Transient
    public Room room = null;

    public String dow;
    public String startTime;
    public String endTime;

  //For UI testing
	/*
	private StringProperty course; 
	public StringProperty prof;
	public StringProperty room; 
	public StringProperty courseComp; 
	public StringProperty dow; 
	public StringProperty startTime; 
	public StringProperty endTime;

*/
	public Section() {

	}

	public Section(CourseComponent courseComp, Teacher teacher, Room room, String dow, String startTime, String endTime) {
		this.courseComponentId = courseComp.courseComponentId;
		this.teacherId = teacher.teacherId;
		this.roomId = room.roomId;
		this.dow = dow;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public CourseComponent getCourseComponent() throws DatabaseException {
	    if (courseComponent == null) {
	    	this.courseComponent = (CourseComponent)DatabaseManager.getInstance().getSingle(CourseComponent.class, this.courseComponentId);
		}
	    return this.courseComponent;
	}

	public Course getCourse() throws DatabaseException {
	    if (this.course == null) {
	    	this.course = (Course) DatabaseManager.getInstance().getSingle(Course.class, getCourseComponent().courseId);
		}
		return this.course;
	}

	public Teacher getTeacher() throws DatabaseException {
	    if (teacher == null) {
	        this.teacher = (Teacher)DatabaseManager.getInstance().getSingle(Teacher.class, this.teacherId);
		}
		return this.teacher;
	}

	public String getProf() throws DatabaseException {
	    return this.getTeacher().teacherId;
	}

	public Room getRoom() throws DatabaseException {
		if (this.room == null) {
		    this.room = (Room)DatabaseManager.getInstance().getSingle(Room.class, this.roomId);
		}
		return this.room;
	}

	public String getDow() {
		return dow;
	}

	public void setDow(String dow) {

	}


	public void setStartTime(String startTime) {

	}


	public void setEndTime(String endTime) {

	}
	
	public StringProperty courseProperty() throws DatabaseException {
		return new SimpleStringProperty(getCourse().courseId);
	}
	
	public StringProperty profProperty() throws DatabaseException {
		return new SimpleStringProperty(getTeacher().teacherName);
	}
	
	public StringProperty roomProperty() throws DatabaseException {
		return new SimpleStringProperty(getRoom().getRoom());
	}
	
	public StringProperty courseCompProperty() throws DatabaseException {
	    return new SimpleStringProperty(getCourseComponent().type);
	}
	
	public StringProperty courseDow() {
		return new SimpleStringProperty(this.dow);
	}
	
	public StringProperty startTimeProperty() {
		return new SimpleStringProperty(this.startTime);
	}
	
	public StringProperty endTimeProperty() {
		return new SimpleStringProperty(this.endTime);
	}
	
}
