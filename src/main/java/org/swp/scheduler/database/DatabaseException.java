package org.swp.scheduler.database;

/**
 * Created by jackson on 2/8/17.
 */
public class DatabaseException extends Exception {
    public DatabaseException (Exception e) {
        super(e);
    }

    public DatabaseException (String e) {
        super(e);
    }
}
