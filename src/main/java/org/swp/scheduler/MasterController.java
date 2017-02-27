package org.swp.scheduler;

import java.util.List;

import org.swp.scheduler.database.DatabaseManager;
import org.swp.scheduler.database.models.Model;
import org.swp.scheduler.database.models.Section;
import org.swp.scheduler.database.models.Course;
import org.swp.scheduler.database.models.Room;
import org.swp.scheduler.database.models.Teacher;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

@SuppressWarnings("restriction")
public class MasterController {
	
	private static MasterController INSTANCE = new MasterController();
	
    private static ObservableList<Section> sections = FXCollections.observableArrayList();
    private static ObservableList<Course> courses = FXCollections.observableArrayList();
    private static ObservableList<Room> rooms = FXCollections.observableArrayList();
    private static ObservableList<Teacher> teachers = FXCollections.observableArrayList();	
    
    private static ObservableList<Section> sectionDelta = FXCollections.observableArrayList();
    private static ObservableList<Course> courseDelta = FXCollections.observableArrayList();
    private static ObservableList<Room> roomDelta = FXCollections.observableArrayList();
    private static ObservableList<Teacher> teacherDelta = FXCollections.observableArrayList();
	
	private MasterController() {
		
		
	}
	
	public static MasterController getInstance() {
		return INSTANCE;
	}
	
	public static void initializeLists() {
		Section sect[] = new Section[1];
		Course cour[] = new Course[1];
		Room rm[] = new Room[1];
		Teacher teach[] = new Teacher[1];
		try {
			sections.addAll(DatabaseManager.getInstance().getAll(Section.class).toArray(sect));
			courses.addAll(DatabaseManager.getInstance().getAll(Course.class).toArray(cour));
			rooms.addAll(DatabaseManager.getInstance().getAll(Room.class).toArray(rm));
			teachers.addAll(DatabaseManager.getInstance().getAll(Teacher.class).toArray(teach));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void addToData(Section section) {
		sections.add(section);
		sectionDelta.add(section);
	}
	
	public static ObservableList<Section> getMasterData() {
		return sections;
	}
	
	public void addToCourses(Course course) {
		courses.add(course);
		courseDelta.add(course);
	}
	
	public static ObservableList<Course> getCourseData() {
		return courses;
	}

	public void addToRooms(Room room) {
		rooms.add(room);
		roomDelta.add(room);
	}
	
	public static ObservableList<Room> getRoomData() {
		return rooms;
	}

	public void addToTeachers(Teacher teacher) {
		teachers.add(teacher);
		teacherDelta.add(teacher);
	}
	
	public static ObservableList<Teacher> getTeacherData() {
		return teachers;
	}

}
