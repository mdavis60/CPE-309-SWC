package org.swp.scheduler.database;

import au.com.bytecode.opencsv.CSVReader;
import javafx.scene.chart.PieChart;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by jackson on 2/2/2017.
 */
public class InputReader {

    public void readPlanData(String location) throws IOException {
        CSVReader reader = new CSVReader(new FileReader(location));
        List<String[]> entries = reader.readAll();
        entries.remove(0);

        Session session = DatabaseManager.getSession();
        Transaction tx = session.beginTransaction();

        HashSet<Integer> idSet = new HashSet<Integer>();

        for (String[] e : entries) {
            if (!idSet.contains(Integer.parseInt(e[3]))) {
                idSet.add(Integer.parseInt(e[3]));

                StudentPlanData planData = new StudentPlanData(e[0], e[1], e[2], Integer.parseInt(e[3]), e[4],
                        Integer.parseInt(e[5]), e[6], e[7], Integer.parseInt(e[10]), Integer.parseInt(e[11]),
                        Integer.parseInt(e[12]), Integer.parseInt(e[13]), (int) Double.parseDouble(e[14]));

                session.save(planData);
            }
        }

        session.flush();
        tx.commit();
    }
}
