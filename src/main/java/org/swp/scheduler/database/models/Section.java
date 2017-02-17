package org.swp.scheduler.database.models;

import java.sql.Time;
import java.util.List;

import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Section extends Model {

  /*
   *public int courseId; 
   *public int sectionId;
   *
   *
    //https://stackoverflow.com/questions/18379766/hql-hibernate-inner-join
    //@OneToMany(mappedBy="employee",cascade=CascadeType.ALL)
    @OneToMany
    @JoinColumn(name="sectionComponentId")
    public List<SectionComponent> componentList;
   * 
   */
  
  //For UI testing
	private StringProperty course; 
	public StringProperty prof;
	public StringProperty room; 
	public StringProperty courseComp; 
	public StringProperty dow; 
	public StringProperty startTime; 
	public StringProperty endTime; 
	
	public Section(String course, String prof, String room, String courseComp, String dow, String startTime, String endTime) {
		this.course = new SimpleStringProperty(course); 
		this.prof = new SimpleStringProperty(prof); 
		this.room = new SimpleStringProperty(room); 
		this.courseComp = new SimpleStringProperty(courseComp);
		this.dow = new SimpleStringProperty(dow); 
		this.startTime = new SimpleStringProperty(startTime); 
		this.endTime = new SimpleStringProperty(endTime);
	}

	public String getCourse() {
		return course.get();
	}

	public void setCourse(String course) {
		this.course.set(course);
	}

	public String getProf() {
		return prof.get();
	}

	public void setProf(String prof) {
		this.prof.set(prof);
	}

	public String getRoom() {
		return room.get();
	}

	public void setRoom(String room) {
		this.room.set(room);
	}

	public String getCourseComp() {
		return courseComp.get();
	}

	public void setCourseComp(String courseComp) {
		this.courseComp.set(courseComp);
	}

	public String getDow() {
		return dow.get();
	}

	public void setDow(String dow) {
		this.dow.set(dow);
	}

	public String getStartTime() {
		return startTime.get();
	}

	public void setStartTime(String startTime) {
		this.startTime.set(startTime);
	}

	public String getEndTime() {
		return endTime.get();
	}

	public void setEndTime(String endTime) {
		this.endTime.set(endTime);
	}
	
	public StringProperty courseProperty() {
		return this.course;
	}
	
	public StringProperty profProperty() {
		return this.prof;
	}
	
	public StringProperty roomProperty() {
		return this.room;
	}
	
	public StringProperty courseCompProperty() {
		return this.courseComp;
	}
	
	public StringProperty courseDow() {
		return this.dow;
	}
	
	public StringProperty startTimeProperty() {
		return this.startTime;
	}
	
	public StringProperty endTimeProperty() {
		return this.endTime;
	}
	
}
