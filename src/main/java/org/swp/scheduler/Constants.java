package org.swp.scheduler;

/**
 * Created by jackson on 2/10/17.
 */

public class Constants {
  public static String getTerm(String quarter, int year) throws Exception {
    if (year < 100)
      year += 2000;

    quarter = quarter.toUpperCase().trim();

    if (!quarter.equals("FALL") && !quarter.equals("SPRING")
        && !quarter.equals("WINTER") && !quarter.equals("SUMMER")) {
      throw new Exception("Not a valid quarter!");
    }

    return quarter.toUpperCase().trim() + year;
  }
}
