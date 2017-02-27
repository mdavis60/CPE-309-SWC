package org.swp.scheduler;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.metamodel.relational.Database;
import org.swp.scheduler.database.DatabaseManager;
import org.swp.scheduler.database.models.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.xml.crypto.Data;
import java.util.List;

/**
 * Created by jackson on 2/1/17.
 */
public class DBUnitTests {
    @BeforeClass
    public void setup() {
    }

    @AfterClass
    public void cleanup() {
    }

    @Test
    public void basicTest() throws Exception {
        StudentPlanData data = new StudentPlanData("term", "college", "department", -1, "Code", 1,
                "title", "component", 1, 1, 1, 1, 1);

        DatabaseManager.getInstance().storeSingle(data);
        StudentPlanData dbData = (StudentPlanData) DatabaseManager.getInstance().getSingle(StudentPlanData.class, -1);

        assert dbData.term.equals("term");

        DatabaseManager.getInstance().deleteSingle(dbData);
        StudentPlanData deletedDbData = (StudentPlanData) DatabaseManager.getInstance().getSingle(StudentPlanData.class, -1);

        assert deletedDbData == null;
    }

    @Test
    public void getAllTableTest() throws Exception {
        List<Model> list = DatabaseManager.getInstance().getAll(StudentPlanData.class);
        assert list.size() > 1;

        List<Model> list2 = DatabaseManager.getInstance().getAll(Course.class);
        List<Model> list4 = DatabaseManager.getInstance().getAll(Teacher.class);
    }

    @Test
    public void loginDataTest() throws Exception {
        LoginData data = new LoginData("username", "password", LoginData.AuthType.ADMIN);
        DatabaseManager.getInstance().storeSingle(data);
        LoginData retrievedData = (LoginData) DatabaseManager.getInstance().getSingle(LoginData.class, "username");

        assert retrievedData.type == LoginData.AuthType.ADMIN;

        DatabaseManager.getInstance().deleteSingle(retrievedData);
    }

    @Test
    public void testStudentFeedback() throws Exception {
        String message = "this was a great class schedule great job";
        String fallMessage = "this was a great class schedule great job HAPPENED IN FALL";
        String fall = Constants.getTerm("fall", 2016);
        String winter = Constants.getTerm("winter", 2016);

        StudentFeedback fallFeedback = new StudentFeedback("roweber", "Feedback", fallMessage, fall);
        StudentFeedback winterFeedback = new StudentFeedback("clee", "Feedback", message, winter);

        DatabaseManager.getInstance().storeSingle(fallFeedback);
        DatabaseManager.getInstance().storeSingle(winterFeedback);

        List<Model> fallList = DatabaseManager.getInstance().executeTransaction((Session session) -> {
            Query query = session.createQuery("from StudentFeedback where term = :term");
            query.setParameter("term", fall);
            List<Model> list = query.list();

            return list;
        });

        StudentFeedback latestFallFeedback = (StudentFeedback) fallList.get(fallList.size() - 1);

        assert latestFallFeedback.feedback.equals(fallMessage);

        DatabaseManager.getInstance().deleteSingle(fallFeedback);
        DatabaseManager.getInstance().deleteSingle(winterFeedback);
    }

    @Test
    public void testRoomType() throws Exception {
        RoomType type = new RoomType("typea");
        DatabaseManager.getInstance().storeSingle(type);

        RoomType retrievedType = (RoomType) DatabaseManager.getInstance().getSingle(RoomType.class, "typea");

        assert retrievedType.roomType.equals("typea");

        DatabaseManager.getInstance().deleteSingle(retrievedType);
    }

    @Test
    public void testCourseType() throws Exception {
        CourseType type = new CourseType("typea");
        DatabaseManager.getInstance().storeSingle(type);

        CourseType retrievedType = (CourseType) DatabaseManager.getInstance().getSingle(CourseType.class, "typea");

        assert retrievedType.courseType.equals("typea");

        DatabaseManager.getInstance().deleteSingle(retrievedType);
    }

    @Test
    public void testTeacher() throws Exception {
        String username = "asdf";

        Teacher t = new Teacher(username, "teacher");

        TimePreference p1 = new TimePreference(t, 5, 100, 200, "MWF");
        TimePreference p2 = new TimePreference(t, 2, 100, 200, "TH");

        CoursePreference c1 = new CoursePreference();

        DatabaseManager.getInstance().storeSingle(t);
        DatabaseManager.getInstance().storeSingle(p1);
        DatabaseManager.getInstance().storeSingle(p2);


        Teacher retrievedT = (Teacher)DatabaseManager.getInstance().getSingle(Teacher.class, username);

        assert retrievedT.timePreferences.size() >= 2;

        DatabaseManager.getInstance().deleteSingle(t);
        DatabaseManager.getInstance().deleteSingle(p1);
        DatabaseManager.getInstance().deleteSingle(p2);
    }


    @Test
    public void testCourse() throws Exception {
        Course course = new Course(123, "intro to cs", "CPE-123|CSC-329", "CPE");

        CourseType type = (CourseType) DatabaseManager.getInstance().getSingle(CourseType.class, "TYPEQ");
        if (type == null) {
            type = new CourseType("TYPEQ");
            DatabaseManager.getInstance().storeSingle(type);
        }

        DatabaseManager.getInstance().storeSingle(course);

        CourseComponent cc1 = new CourseComponent(type, 3, 4, course);
        CourseComponent cc2 = new CourseComponent(type, 6, 8, course);

        DatabaseManager.getInstance().storeSingle(cc1);
        DatabaseManager.getInstance().storeSingle(cc2);
        System.out.println(cc1.courseId);
        System.out.println(cc2.courseId);

        Course retrievedC = (Course) DatabaseManager.getInstance().getSingle(Course.class, course.courseId);

        System.out.println(retrievedC.componentList.size());
        assert retrievedC.componentList.size() >= 1;

        DatabaseManager.getInstance().deleteSingle(cc1);
        DatabaseManager.getInstance().deleteSingle(cc2);
        DatabaseManager.getInstance().deleteSingle(course);
    }

    @Test
    public void testSections() {
        CourseComponent cc = new CourseComponent();
        Teacher t = new Teacher();
        Room r = new Room();
        Section section = new Section(cc, t, r, "MWF", "1:00", "2:00");
    }

}

