package org.swp.scheduler.database;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.swp.scheduler.database.models.LoginData;

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

    public void storeSingle(Object toStore) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();

        session.save(toStore);

        session.flush();
        transaction.commit();
    }

    // make this general later, this is a temporary method
    public static boolean userExists(String username) {
        return true;
    }

    public static LoginData getUser(String username) {
        return null;

    }
}
