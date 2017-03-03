package org.swp.scheduler;

import java.util.List;

import org.swp.scheduler.database.DatabaseException;
import org.swp.scheduler.database.DatabaseManager;
import org.swp.scheduler.database.models.CourseComponent;
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
    private static ObservableList<CourseComponent> components = FXCollections.observableArrayList();	
    
    private static ObservableList<Section> sectionDelta = FXCollections.observableArrayList();
    private static ObservableList<Course> courseDelta = FXCollections.observableArrayList();
    private static ObservableList<Room> roomDelta = FXCollections.observableArrayList();
    private static ObservableList<Teacher> teacherDelta = FXCollections.observableArrayList();
    private static ObservableList<CourseComponent> componentDelta = FXCollections.observableArrayList();
	
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
			if(!DatabaseManager.getInstance().getAll(Section.class).isEmpty()) {
				sections.addAll(DatabaseManager.getInstance().getAll(Section.class).toArray(sect));
				System.out.println("sections size: " + sections.size());
				for(Section s : sections)
				{
					System.out.println("section id: " + s.getId());
					System.out.println("room id: " + s.getRoomId());
					System.out.println("teacher id: " + s.getTeacherId());
					System.out.println("start time: " + s.getStartTime());
					System.out.println("component id: " + s.getComponentId());
					System.out.println("end time: " + s.getEndTime());

					s = setupSection(s);
				}
			}
			if(!DatabaseManager.getInstance().getAll(Course.class).isEmpty()) {
				courses.addAll(DatabaseManager.getInstance().getAll(Course.class).toArray(cour));
				System.out.println("courses size: " + courses.size());
			}
			if(!DatabaseManager.getInstance().getAll(Room.class).isEmpty()) {
				rooms.addAll(DatabaseManager.getInstance().getAll(Room.class).toArray(rm));
				System.out.println("rooms size: " + rooms.size());
			}
			if(!DatabaseManager.getInstance().getAll(Teacher.class).isEmpty()) {
				teachers.addAll(DatabaseManager.getInstance().getAll(Teacher.class).toArray(teach));
				System.out.println("teachers size: " + teachers.size());
			}
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
	
	public void addToComponents(CourseComponent component) {
		components.add(component);
		componentDelta.add(component);
	}
	
	public static ObservableList<CourseComponent> getComponentData() {
		return components;
	}

	public void addToTeachers(Teacher teacher) {
		teachers.add(teacher);
		teacherDelta.add(teacher);
	}
	
	public static ObservableList<Teacher> getTeacherData() {
		return teachers;
	}
	
	public void saveDataToDB() throws DatabaseException
	{
		for(Course c : courseDelta)
		{
			DatabaseManager.getInstance().storeSingle(c);
		}
		courseDelta.clear();
		
		for(CourseComponent c : componentDelta)
		{
			DatabaseManager.getInstance().storeSingle(c);
		}
		componentDelta.clear();
		
		for(Room r : roomDelta)
		{
			DatabaseManager.getInstance().storeSingle(r);
		}
		roomDelta.clear();
		
		for(Section s : sectionDelta)
		{
			DatabaseManager.getInstance().storeSingle(s);
		}
		sectionDelta.clear();
		
		for(Teacher t: teacherDelta) 
		{
			DatabaseManager.getInstance().storeSingle(t);
		}
		teacherDelta.clear();
	}
	
	private static Section setupSection(Section in)
	{
		try
		{
			CourseComponent component = (CourseComponent)(DatabaseManager.getInstance().getSingle(CourseComponent.class, in.getComponentId()));
			in.setCourseComponent(component);
			Course course = (Course)(DatabaseManager.getInstance().getSingle(Course.class, component.courseId));
			in.setCourse(course);
			Room room = (Room)(DatabaseManager.getInstance().getSingle(Room.class, in.getRoomId()));
			in.setRoom(room);
			Teacher teacher = (Teacher)(DatabaseManager.getInstance().getSingle(Teacher.class, in.getTeacherId()));			in.setRoom(room);
			in.setTeacher(teacher);
		} catch(Exception e) {
			System.out.println(in.getCourseComponent().getCourseId());
		}
		return in;
	}

}
