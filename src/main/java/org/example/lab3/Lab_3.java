package org.example.lab3;

import org.example.labI.Lab_Interface;

import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;
import java.util.Stack;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Lab_3 implements Lab_Interface {
    String substring;
    int numberOfThreads;
    long len;
    Stack<Long> treadsInd;   // стек стратовых точек для потоков
    Stack<Integer> indexOf;  // стек индеков найденых подстрок
    RandomAccessFile raf;
    @Override
    public void Run() throws IOException {
//        TreadPoolTest();
        int processors = Runtime.getRuntime().availableProcessors();
        ExecutorService executorService = Executors.newFixedThreadPool(processors - 1);
        try {
            raf = new RandomAccessFile("src/main/resources/Lab_3/text.txt", "rw");
            substring = "surprise";
            numberOfThreads = 10;
            treadsInd = new Stack<>();
            len = raf.length() / numberOfThreads;
            indexOf = new Stack<>();
            for (int i = 0; i < numberOfThreads; i++) {
                treadsInd.push(raf.length() / numberOfThreads * i);
//                executorService.submit(new FinderTread().Init(substring, "src/main/resources/Lab_3/text.txt" , start, end, indexOf));
            }

            while (!treadsInd.empty()) {
                long start = treadsInd.pop();
                if (start >= substring.length()) { start -= substring.length(); }
                long end = start + len;
                if (end >= raf.length()) { end = raf.length(); }

                FinderTread tread = new FinderTread();
                tread.Init(substring, "src/main/resources/Lab_3/text.txt" , start, end, indexOf);
                tread.start();
//                tread.join();
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        int n;
        FileWriter fw = new FileWriter("src/main/resources/Lab_3/out.txt");
        Scanner sc = new Scanner(System.in);
        System.out.print("Input n: ");
        n = sc.nextInt();

        while (!indexOf.empty()) {
            int curr = getPrevLen(indexOf.pop());
            for (int i = 0; i < n ;i++) {
                curr = getPrevLen(curr);
            }
            raf.seek(curr);
            for (int i=0; i < n*2+1; i++) {
                fw.write(raf.readLine() + "\n");
            }
            fw.write("\n------------------------------\n");
        }

    }
    private int getPrevLen(int curr) throws IOException {
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

    private void TreadPoolTest() {
        int processors = Runtime.getRuntime().availableProcessors();
        ExecutorService executorService = Executors.newFixedThreadPool(processors - 1);

        executorService.submit(() -> {System.out.println("Hello");});
//        executorService.execute();

        executorService.shutdown();
    }
}
class FinderTread extends Thread {

    RandomAccessFile raf = null;
    String substring;
    Long startInd;
    Long endInd;
    Stack<Integer> indexOf;  // стек индеков найденых подстрок
    byte[] bytes = new byte[2048];

    @Override
    public void run() {
        try {
            while (startInd + 1024 < endInd) {
                raf.seek(Math.toIntExact(startInd));
                raf.read(bytes, 0, 1024);
                String str = new String(bytes);
                int ind = str.indexOf(substring);
                if (ind != -1) {
                    synchronized (indexOf){
                        indexOf.push((int) (ind+startInd));
                        System.out.println("Substring find, ind: " + ind+startInd );
                    }
                }
                startInd += 1024;
//                sleep(1); // INFO: демонстрационный функционал
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void Init(String _substring, String _path, long _start, long _end, Stack<Integer> _indexOf) {
        try {
            raf = new RandomAccessFile(_path, "r");
            endInd = _end;
            substring = _substring;
            indexOf = _indexOf;
            startInd = _start;
            if (_start != 0)
                startInd = _start - substring.length();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}

