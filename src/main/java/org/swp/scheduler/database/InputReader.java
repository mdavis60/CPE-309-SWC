package org.swp.scheduler.database;

import au.com.bytecode.opencsv.CSVReader;
import org.hibernate.Session;
import org.swp.scheduler.database.models.StudentPlanData;

import java.io.FileReader;
import java.util.HashSet;
import java.util.List;

/**
 * Created by jackson on 2/2/2017.
 */
public class InputReader {

    public void readPlanData(String location) throws Exception {
        CSVReader reader = new CSVReader(new FileReader(location));
        List<String[]> entries = reader.readAll();
        entries.remove(0);

        DatabaseManager.getInstance().executeTransaction((Session session) -> {
            HashSet<Integer> idSet = new HashSet<>();

            for (String[] e : entries) {
                if (!idSet.contains(Integer.parseInt(e[3]))) {
                    idSet.add(Integer.parseInt(e[3]));

                    StudentPlanData planData = new StudentPlanData(e[0], e[1], e[2], Integer.parseInt(e[3]), e[4],
                            Integer.parseInt(e[5]), e[6], e[7], Integer.parseInt(e[10]), Integer.parseInt(e[11]),
                            Integer.parseInt(e[12]), Integer.parseInt(e[13]), (int) Double.parseDouble(e[14]));

                    session.save(planData);
                }
            }
            return null;
        });
    }
}
