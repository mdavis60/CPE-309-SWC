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
  // SessionFactory creates thread safe sessions that should be used to orchestrate transactions
  private SessionFactory factory;

  /**
   * Creates a new DatabaseManager, this is called once the first time the INSTANCE property is accessed
   */
  private DatabaseManager() {
    Configuration configuration = new Configuration().configure();
    StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
        .applySettings(configuration.getProperties());
    factory = configuration.buildSessionFactory(builder.build());
  }

  /**
   * @return Singleton object
   */
  public static DatabaseManager getInstance() {
    return INSTANCE;
  }

  /**
   * This method is the heart of the database manager, it allows any database
   * operation to be passed in as a DatabaseTransaction which has been marked
   * as a functional interface. This allows us to avoid repeating boilerplate
   * object creation and exception handling for basic tasks.
   *
   * @param tx
   * @param <T>
   * @return
   * @throws DatabaseException
   */
  @SuppressWarnings("unchecked")
  public <T> T executeTransaction(DatabaseTransaction tx)
      throws DatabaseException {
    Session session = factory.openSession();
    Transaction transaction = null;
    T toReturn;
    try {
      transaction = session.beginTransaction();
      toReturn = (T) tx.execute(session);
      transaction.commit();
    } catch (Exception e) {
      if (transaction != null) {
        transaction.rollback();
      }
      throw new DatabaseException(e);
    } finally {
      session.close();
    }
    return toReturn;
  }

  // hibernate session docs
  // https://docs.jboss.org/hibernate/orm/4.3/javadocs/org/hibernate/Session.html

  /**
   *
   * @param toStore
   * @throws DatabaseException
   */
  public void storeSingle(Model toStore) throws DatabaseException {
    executeTransaction((Session session) -> session.save(toStore));
  }

  /**
   * retrieves a single db object
   * 
   * @param entityClass
   * @param id
   * @return object from the database
   * @throws DatabaseException
   */
  public Object getSingle(Class entityClass, Serializable id)
      throws DatabaseException {
    return executeTransaction((Session session) -> session.get(entityClass, id));
  }

  /**
   *
   * @param entityClass
   * @param id
   * @return true of the key exists in the database, false otherwise
   * @throws DatabaseException
   */
  public boolean containsKey(Class entityClass, Serializable id)
      throws DatabaseException {
    return executeTransaction((Session session) -> session.get(entityClass, id)) != null;
  }

  /**
   * Removes a single row from a specified database table
   *
   * @param toDelete
   * @throws DatabaseException
   */
  public void deleteSingle(Model toDelete) throws DatabaseException {
    executeTransaction((Session session) -> {
      session.delete(toDelete);
      return null;
    });
  }

  /**
   *
   * @param type
   * @return all of the objects contained in a table
   * @throws DatabaseException
   */
  public List<Model> getAll(Class type) throws DatabaseException {
    return executeTransaction((Session session) -> session.createCriteria(type)
        .list());
  }
}
