package org.swp.scheduler.database.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by jackson on 2/3/17.
 *
 * A room available as a resource.
 */

@Entity
@Table(name = "Rooms")
public class Room extends Model {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  public int roomId;
  public int roomNumber;
  public int buildingNumber;
  public int capacity;
  public String attributes;

  public String type;

  public Room() {
  }

  public Room(int roomId, int roomNumber, int buildingNumber, int capacity,
      String attributes) {
    this.roomId = roomId;
    this.roomNumber = roomNumber;
    this.buildingNumber = buildingNumber;
    this.capacity = capacity;
    this.attributes = attributes;
  }

  public String getRoom() {
    return String.valueOf(buildingNumber) + "-" + String.valueOf(roomNumber);
  }
  
  public int getRoomID() {
    return roomId;
  }

  public String toString() {
    return getRoom();
  }

}
