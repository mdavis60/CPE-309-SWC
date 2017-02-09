package org.swp.scheduler.database.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by jackson on 2/3/17.
 */

@Entity
@Table(name = "room")
public class Room extends Model {
    @Id
    int roomId;
    int roomNumber;
    String building;
    int capacity;
    String attributes;
    String type;

    public Room() {

    }

}
