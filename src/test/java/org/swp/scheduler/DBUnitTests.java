package org.swp.scheduler;

import org.hibernate.Query;
import org.hibernate.Session;
import org.swp.scheduler.database.DatabaseManager;
import org.swp.scheduler.database.models.LoginData;
import org.swp.scheduler.database.models.Model;
import org.swp.scheduler.database.models.StudentFeedback;
import org.swp.scheduler.database.models.StudentPlanData;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

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
        String fallMessage = "this was a great class schedule great job WHICH HAPPENED IN FALL";
        String fall = Constants.getTerm("fall", 2016);
        String winter = Constants.getTerm("winter", 2016);

        StudentFeedback fallFeedback = new StudentFeedback(fallMessage, fall);
        StudentFeedback winterFeedback = new StudentFeedback(message, winter);

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
}

