package org.swp.scheduler.database;

import org.hibernate.Session;

/**
 * Created by jackson on 2/8/17.
 */
@FunctionalInterface
public interface DatabaseTransaction {
    Object execute(Session session);
}
