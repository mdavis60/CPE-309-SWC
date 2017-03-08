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

  private static ObservableList<Section> sections = FXCollections
      .observableArrayList();
  private static ObservableList<Course> courses = FXCollections
      .observableArrayList();
  private static ObservableList<Room> rooms = FXCollections
      .observableArrayList();
  private static ObservableList<Teacher> teachers = FXCollections
      .observableArrayList();
  private static ObservableList<CourseComponent> components = FXCollections
      .observableArrayList();

  private static ObservableList<Section> sectionDelta = FXCollections
      .observableArrayList();
  private static ObservableList<Course> courseDelta = FXCollections
      .observableArrayList();
  private static ObservableList<Room> roomDelta = FXCollections
      .observableArrayList();
  private static ObservableList<Teacher> teacherDelta = FXCollections
      .observableArrayList();
  private static ObservableList<CourseComponent> componentDelta = FXCollections
      .observableArrayList();

  private MasterController() {

  }

  public static MasterController getInstance() {
    return INSTANCE;
  }

  public static void initializeLists() {
    Section[] sect = new Section[1];
    Course[] cour = new Course[1];
    Room[] rm = new Room[1];
    Teacher[] teach = new Teacher[1];
    CourseComponent[] compon = new CourseComponent[1];
    try {
      if (!DatabaseManager.getInstance().getAll(Section.class).isEmpty()) {
        sections.addAll(DatabaseManager.getInstance().getAll(Section.class)
            .toArray(sect));
        System.out.println("sections size: " + sections.size());
        for (Section s : sections) {
          s = setupSection(s);
        }
      }
      if (!DatabaseManager.getInstance().getAll(Course.class).isEmpty()) {
        for (Model model : DatabaseManager.getInstance().getAll(Course.class)) {
          Course course = (Course) model;
          if (!courses.contains(course)) {
            courses.add(course);
          }
        }
        System.out.println("courses size: " + courses.size());
      }
      if (!DatabaseManager.getInstance().getAll(Room.class).isEmpty()) {
        rooms.addAll(DatabaseManager.getInstance().getAll(Room.class)
            .toArray(rm));
        System.out.println("rooms size: " + rooms.size());
      }
      if (!DatabaseManager.getInstance().getAll(Teacher.class).isEmpty()) {
        teachers.addAll(DatabaseManager.getInstance().getAll(Teacher.class)
            .toArray(teach));
        System.out.println("teachers size: " + teachers.size());
      }
      if (!DatabaseManager.getInstance().getAll(CourseComponent.class).isEmpty()) {
        components.addAll(DatabaseManager.getInstance().getAll(CourseComponent.class)
            .toArray(compon));
        System.out.println("component size: " + components.size());
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public void addToData(Section section) {
    sections.add(section);
    sectionDelta.add(section);
  }

  public ObservableList<Section> getMasterData() {
    return sections;
  }

  /**
   * Returns the section with the specified section id or null if none exists.
   * 
   * @param sectionId
   *          the id of the section to search for
   * @return the corresponding section or null if none exists
   */
  public Section getSection(int sectionId) {
    for (Section section : sections) {
      if (section.getId() == sectionId) {
        return section;
      }
    }
    return null;
  }

  public void addToCourses(Course course) {
    courses.add(course);
    courseDelta.add(course);
  }

  public ObservableList<Course> getCourseData() {
    return courses;
  }

  /**
   * Returns the course with the specified course id or null if none exists.
   * 
   * @param courseId
   *          the id of the course to search for
   * @return the corresponding course or null if none exists
   */
  public Course getCourse(String courseId) {
    for (Course course : courses) {
      if (course.getCourseID() == courseId) {
        return course;
      }
    }
    return null;
  }

  public void addToRooms(Room room) {
    rooms.add(room);
    roomDelta.add(room);
  }

  public ObservableList<Room> getRoomData() {
    return rooms;
  }

  /**
   * Returns the room with the specified room name or null if none exists.
   * 
   * @param roomName
   *          the name of the room to search for
   * @return the corresponding room or null if none exists
   */
  public Room getRoom(int roomName) {
    for (Room room : rooms) {
      if (room.getRoom().equals(roomName)) {
        return room;
      }
    }
    return null;
  }

  public void addToComponents(CourseComponent component) {
    components.add(component);
    componentDelta.add(component);
  }

  public ObservableList<CourseComponent> getComponentData() {
    return components;
  }

  /**
   * Returns an ObservableList of components that correspond to the given
   * course.
   * 
   * @param course
   *          the course you want to find components for
   * @return an ObservableList of components of the course input
   */
  public ObservableList<CourseComponent> getComponentsForCourse(Course course) {
    ObservableList<CourseComponent> subSet = FXCollections
        .observableArrayList();
    for (CourseComponent component : components) {
      if (component.getCourseId().equals(course.getCourseID())) {
        subSet.add(component);
      }
    }
    return subSet;

  }

  /**
   * Returns the teacher with the specified teacher name or null if none exists.
   * 
   * @param teacherName
   *          the name of the teacher to search for
   * @return the corresponding teacher or null if none exists
   */
  public Teacher getTeacher(String teacherName) {
    for (Teacher teacher : teachers) {
      if (teacher.getTeacherName().equals(teacherName)) {
        return teacher;
      }
    }
    return null;
  }

  public void addToTeachers(Teacher teacher) {
    teachers.add(teacher);
    teacherDelta.add(teacher);
  }

  public ObservableList<Teacher> getTeacherData() {
    return teachers;
  }

  /**
   * Saves the changes stored in all delta lists to the Database.
   * 
   * @throws DatabaseException
   *           throws this exception if any errors occur when writing to the
   *           Database
   */
  public void saveDataToDB() throws DatabaseException {
    for (Course c : courseDelta) {
      DatabaseManager.getInstance().storeSingle(c);
    }
    courseDelta.clear();

    for (CourseComponent c : componentDelta) {
      DatabaseManager.getInstance().storeSingle(c);
    }
    componentDelta.clear();

    for (Room r : roomDelta) {
      DatabaseManager.getInstance().storeSingle(r);
    }
    roomDelta.clear();

    for (Teacher t : teacherDelta) {
      DatabaseManager.getInstance().storeSingle(t);
    }
    teacherDelta.clear();
    
    for (Section s : sectionDelta) {
      DatabaseManager.getInstance().storeSingle(s);
    }
    sectionDelta.clear();

  }

  private static Section setupSection(Section in) {
    try {
      CourseComponent component = (CourseComponent) (DatabaseManager
          .getInstance().getSingle(CourseComponent.class, in.getComponentId()));
      in.setCourseComponent(component);
      Course course = (Course) (DatabaseManager.getInstance().getSingle(
          Course.class, component.courseId));
      in.setCourse(course);
      Room room = (Room) (DatabaseManager.getInstance().getSingle(Room.class,
          in.getRoomId()));
      in.setRoom(room);
      Teacher teacher = (Teacher) (DatabaseManager.getInstance().getSingle(
          Teacher.class, in.getTeacherId()));
      in.setRoom(room);
      in.setTeacher(teacher);
    } catch (Exception ex) {
      System.out.println(in.getCourseComponent().getCourseId());
    }
    return in;
  }

}
