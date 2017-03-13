package org.swp.scheduler.database.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by jackson on 2/17/17.
 *
 * RoomTypes is basically a persistent Enum that lives in a table called RoomTypes
 */
@Entity
@Table(name = "RoomTypes")
public class RoomType extends Model {
  @Id
  public String roomType;

  public RoomType() {

  }

  public RoomType(String type) {
    this.roomType = type;
  }
}
