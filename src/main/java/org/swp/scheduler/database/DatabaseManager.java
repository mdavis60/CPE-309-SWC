package org.swp.scheduler.database;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.swp.scheduler.database.models.Model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by jackson on 2/2/2017.
 */
public class DatabaseManager {
    private static DatabaseManager INSTANCE = new DatabaseManager();
    private SessionFactory factory;

    private DatabaseManager() {
        Configuration configuration = new Configuration().configure();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        factory = configuration.buildSessionFactory(builder.build());
    }

    public static DatabaseManager getInstance() {
        return INSTANCE;
    }

    // surfaces everything you need to interact with the database
    @SuppressWarnings("unchecked")
    public <T> T executeTransaction(DatabaseTransaction tx) throws Exception {
        Session session = factory.openSession();
        Transaction transaction = null;
        T toReturn;
        try {
            transaction = session.beginTransaction();
            toReturn = (T)tx.execute(session);
            transaction.commit();
        }
        catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
        finally {
            session.close();
        }
        return toReturn;
    }

    // hibernate session docs
    // https://docs.jboss.org/hibernate/orm/4.3/javadocs/org/hibernate/Session.html

    // METHODS FOR CONVENIENCE
    public void storeSingle(Model toStore) throws Exception {
        executeTransaction((Session session) -> session.save(toStore));
    }

    public Object getSingle(Class entityClass, Serializable id) throws Exception {
        return executeTransaction((Session session) -> session.get(entityClass, id));
    }

    public void deleteSingle(Model toDelete) throws Exception {
        executeTransaction((Session session) -> {
            session.delete(toDelete);
            return null;
        });
    }

    public List<Model> getAll(Class type) throws Exception {
        return executeTransaction((Session session) -> session.createCriteria(type).list());
    }

}
