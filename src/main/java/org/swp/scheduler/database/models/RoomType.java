package org.swp.scheduler.database.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by jackson on 2/17/17.
 */
@Entity
@Table(name = "RoomTypes")
public class RoomType {
    @Id
    String roomType;
}
