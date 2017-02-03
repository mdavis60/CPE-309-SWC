package org.swp.scheduler;

import org.swp.scheduler.database.DatabaseManager;
import org.swp.scheduler.database.InputReader;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Created by jackson on 2/1/17.
 */
public class DBUnitTests {
    @BeforeClass
    public void setup() {
    }

    @Test
    public void test() throws Exception {
        InputReader reader = new InputReader();
        reader.readPlanData("input files/StudentPlanData2017.csv");
    }
}
