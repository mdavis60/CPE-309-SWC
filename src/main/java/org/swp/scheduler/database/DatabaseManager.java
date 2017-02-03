package org.swp.scheduler.database;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.List;
/**
 * Created by jackson on 2/2/2017.
 */
public class DatabaseManager {
    private static SessionFactory factory;

    public static synchronized Session getSession() {
        if (factory == null) {
            Configuration configuration = new Configuration().configure();
            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
            factory = configuration.buildSessionFactory(builder.build());
        }

        return factory.openSession();
    }

    public void DatabaseManager() {
        /*
        final Configuration configuration = new Configuration().configure();
        final StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        final SessionFactory factory = configuration.buildSessionFactory(builder.build());


        final Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        StudentPlanData planData = new StudentPlanData(222221014, "component", "2016", "college", "department",
                                                    "code", 5, "title", 4,
                                                     5, 5, 5, 5);

        session.save(planData);
        session.flush();
        tx.commit();

        // Fetching saved data
        List<StudentPlanData> contactList = session.createQuery("from StudentPlanData").list();

        for (StudentPlanData contact : contactList) {
            System.out.println("Id: " + contact.courseId());
        }
        */
    }

    public void storeStudentPlanData() {

    }
}
