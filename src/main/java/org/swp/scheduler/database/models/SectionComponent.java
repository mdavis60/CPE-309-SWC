package org.swp.scheduler.database.models;

import org.swp.scheduler.database.DatabaseException;
import org.swp.scheduler.database.DatabaseManager;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by jackson on 2/3/17.
 */

@Entity
@Table(name = "SectionComponent")
public class SectionComponent extends Model {
  @Id
  public int sectionComponentId;
  public int profId;
  public int roomId;
  public String dow;
  public int startTime;
  public int endTime;

  public SectionComponent() {
  }
}
