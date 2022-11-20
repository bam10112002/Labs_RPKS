package org.example.lab3;

import org.example.labI.Lab_Interface;

import java.io.*;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Lab_3 implements Lab_Interface {
    String filename;
    ObjectsPool pool;
    ExecutorService executorService;
    String substring;
    @Override
    public void Run() {
        File file = new File(filename);
        int i = 0;
        while (i < file.length()){
            executorService.submit(new RunnableFinder().Init(i,i+1024,pool,substring));
            i += 1024 - substring.length();
        }
        i = i - 1024 + substring.length();
        executorService.submit(new RunnableFinder().Init(i, (int)file.length(),pool,substring));
        executorService.shutdown();
        try {
            if (executorService.awaitTermination(10, TimeUnit.MINUTES))
                System.err.println("Some process is not finished");
            OutputStrings();
        } catch (InterruptedException ex) {
            System.err.println(ex.getMessage());
        }

    }

    public Lab_3() throws FileNotFoundException {
        filename = "src/main/resources/Lab_3/text.txt";
        substring = "surprise";
        File file = new File(filename);
        if (!file.exists()) {
            throw new FileNotFoundException();
        }
        int processors = Runtime.getRuntime().availableProcessors();
        executorService = Executors.newFixedThreadPool(processors - 1);
        pool = new ObjectsPool(processors - 1, filename, file.length()/1024);
    }
    private void OutputStrings() {
        if (pool.getIndexOf().isEmpty()) {
            System.out.println("Substring not founded");
            return;
        }

        int n;
        try {
            FileWriter fw = new FileWriter("src/main/resources/Lab_3/out.txt");
            RandomAccessFile raf = new RandomAccessFile(filename, "r");
            Scanner sc = new Scanner(System.in);
            System.out.print("Input n: ");
            n = sc.nextInt();
            while (!pool.getIndexOf().isEmpty()) {
                int curr = getPrevLen(pool.getIndexOf().pop(), raf);
                for (int i = 0; i < n ;i++) {
                    curr = getPrevLen(curr, raf);
                }
                raf.seek(curr);
                for (int i=0; i < n*2+1; i++) {
                    fw.write(raf.readLine() + "\n");
                }
                fw.write("\n------------------------------\n");
            }
        }
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }
    private int getPrevLen(int curr, RandomAccessFile raf) throws IOException {
        curr -=2;
        while (curr > 0) {
            raf.seek(curr);
            char ch = (char)raf.read();
            if (ch == '\n')
                return ++curr;
            curr--;
        }
        return -1;
    }

}
