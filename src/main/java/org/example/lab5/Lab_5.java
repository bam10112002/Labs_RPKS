package org.example.lab5;

import org.example.labI.Lab_Interface;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Lab_5 implements Lab_Interface {
    @Override
    public void Run(){
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Map<String, Log> dict = new TreeMap<String, Log>();

            File file = new File("src/main/resources/Lab_5/Log_1.txt");
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);

            String line = reader.readLine();

            while (line != null) {
                String[] lines = line.split("ID = ");
                String id = lines[1];
                String data = lines[0].substring(lines[0].indexOf('.') + 2, lines[0].indexOf('–') - 1);

                Log currentLog = dict.get(id);

                if (currentLog == null)
                    dict.put(id, new Log(id, data));
                else
                    currentLog.addEnd(data);

                line = reader.readLine();
            }

            long sum = 0;
            for (Map.Entry<String, Log> entry : dict.entrySet())
            {
                sum += entry.getValue().getDelta();
//                entry.getValue().print();
            }
            sum = sum / dict.size();


            System.out.println("CHECK THIS LOG:");
            long delta = 10;
            for (Map.Entry<String, Log> entry : dict.entrySet())
            {
                if (entry.getValue().getDelta() + delta > sum)
                {
                    entry.getValue().print();
                    System.out.println("\tdeviation by " + (entry.getValue().getDelta() - sum) + "ms");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
