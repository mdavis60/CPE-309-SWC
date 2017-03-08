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

  /**
     *
     */
  private DatabaseManager() {
    Configuration configuration = new Configuration().configure();
    StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
        .applySettings(configuration.getProperties());
    factory = configuration.buildSessionFactory(builder.build());
  }

  /**
   *
   * @return
   */
  public static DatabaseManager getInstance() {
    return INSTANCE;
  }

  /**
   *
   * @param tx
   * @param <T>
   * @return
   * @throws DatabaseException
   *           surfaces everything you need to interact with the database
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
   *
   * @param entityClass
   * @param id
   * @return
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
   * @return
   * @throws DatabaseException
   */
  public boolean containsKey(Class entityClass, Serializable id)
      throws DatabaseException {
    return executeTransaction((Session session) -> session.get(entityClass, id)) != null;
  }

  /**
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
   * @return
   * @throws DatabaseException
   */
  public List<Model> getAll(Class type) throws DatabaseException {
    return executeTransaction((Session session) -> session.createCriteria(type)
        .list());
  }
}
