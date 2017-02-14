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
    public int roomId;
    public int roomNumber;
    public String building;
    public int capacity;
    public String attributes;
    public String type;

    public Room() {

    }

    public String getRoom() {
    	return building + "-" + String.valueOf(roomNumber);
    }
    
}
