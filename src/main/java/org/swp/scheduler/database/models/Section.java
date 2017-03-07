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

	public CourseComponent getCourseComponent() {
		try {
			if (courseComponent == null) {
				this.courseComponent = (CourseComponent)DatabaseManager.getInstance().getSingle(CourseComponent.class, this.courseComponentId);
			}
			return this.courseComponent;
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public void setCourseComponent(CourseComponent component) {
		this.courseComponent = component;
	}

	public Course getCourse() {
		try {
			if (this.course == null) {
				this.course = (Course) DatabaseManager.getInstance().getSingle(Course.class, getCourseComponent().courseId);
			}
			return this.course;
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
		
	}
	public int getComponentId()
	{
		return courseComponentId;
	}
	
	public void setCourse(Course course) {
		this.course = course;
	}

	public Teacher getTeacher() {
		try {
			if (teacher == null) {
				this.teacher = (Teacher)DatabaseManager.getInstance().getSingle(Teacher.class, this.teacherId);
			}
			return this.teacher;
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	public void setTeacher(Teacher teacher)
	{
		this.teacher = teacher;
	}

	public String getTeacherId()
	{
		return teacherId;
	}
	public Room getRoom() {
	    try {
		if (this.room == null) {
			this.room = (Room)DatabaseManager.getInstance().getSingle(Room.class, this.roomId);
		}
		return this.room;
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	public void setRoom(Room room)
	{
		this.room = room;
	}
	public int getRoomId()
	{
		return roomId;
	}

	public String getDow() {
		return dow;
	}

	public void setDow(String dow) {
		this.dow = dow;
	}
	
	public String getStartTime()
	{
		return startTime;
	}
	
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	
	public String getEndTime()
	{
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public StringProperty courseProperty() {
		return new SimpleStringProperty(getCourse().getCourseName() + " - " + getCourse().getCourseTitle());
	}

	public StringProperty profProperty() {
		return new SimpleStringProperty(getTeacher().teacherName);
	}

	public StringProperty roomProperty() {
		return new SimpleStringProperty(getRoom().getRoom());
	}

	public StringProperty courseCompProperty() {
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
	public int getId()
	{
		return sectionId;
	}

}
