package org.swp.scheduler;

import org.swp.scheduler.database.models.Section;
import org.swp.scheduler.database.models.Course;
import org.swp.scheduler.database.models.Room;
import org.swp.scheduler.database.models.Teacher;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MasterController {
	
	private static MasterController INSTANCE = new MasterController();
    private static ObservableList<Section> masterData = FXCollections.observableArrayList();
    private static ObservableList<Course> courses = FXCollections.observableArrayList();
    private static ObservableList<Room> rooms = FXCollections.observableArrayList();
    private static ObservableList<Teacher> teachers = FXCollections.observableArrayList();	
	
	private MasterController() {
		
		
	}
	
	public static MasterController getInstance() {
		return INSTANCE;
	}

	public void addToData(Section section) {
		masterData.add(section);
	}
	
	public static ObservableList<Section> getMasterData() {
		return masterData;
	}
	
	public void addToCourses(Course course) {
		courses.add(course);
	}
	
	public static ObservableList<Course> getCourseData() {
		return courses;
	}

	public void addToRooms(Room room) {
		rooms.add(room);
	}
	
	public static ObservableList<Room> getRoomData() {
		return rooms;
	}

	public void addToTeachers(Teacher teacher) {
		teachers.add(teacher);
	}
	
	public static ObservableList<Teacher> getTeacherData() {
		return teachers;
	}

}
