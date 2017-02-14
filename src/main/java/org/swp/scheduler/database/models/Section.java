package org.swp.scheduler.database.models;

import java.sql.Time;

public class Section extends Model {

	public Course course; 
	public Teacher prof;
	public Room room; 
	public CourseComponent courseComp; 
	public String dow; 
	public int startTime; 
	public int endTime; 
	
	public Section() {
		
	}
	
}
