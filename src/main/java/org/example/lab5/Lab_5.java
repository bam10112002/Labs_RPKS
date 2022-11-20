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
            Map<String, Log> dict = new HashMap<>();

            BufferedReader reader = new BufferedReader(
                                    new FileReader("src/main/resources/Lab_5/Log_1.txt"));

            String line = reader.readLine();

            int current = 1;
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
                System.out.print("*");
                if (current % 20 == 0)
                    System.out.println();
                current++;
            }

            ArrayList<Log> listOfKeys = new ArrayList<>(dict.values());


            long median = listOfKeys.get(listOfKeys.size()/2).getDelta();

            System.out.println("\ninput delta in seconds: ");
            Scanner scanner = new Scanner(System.in);
            long delta = scanner.nextInt() * 1000L;
            if (delta == 0)
                delta = 4000;

            current = 1;
            StringBuilder builder = new StringBuilder();
            for (Map.Entry<String, Log> entry : dict.entrySet())
            {
                if (entry.getValue().getDelta() > median + delta)
                {
                    builder.append(entry.getValue().toString());
                    builder.append("\tdeviation by ").append(entry.getValue().getDelta() - median).append("ms\n");
                }

                System.out.print("*");
                if (current % 20 == 0)
                    System.out.println();
                current++;
            }
            System.out.println("\nMedian is: " + median + "\nDelta is: " + delta);
            System.out.println("CHECK THIS LOGS:");
            System.out.println(builder.toString());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
