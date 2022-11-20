package org.example.lab3;

import lombok.NonNull;

import java.io.IOException;
import java.io.RandomAccessFile;

public class RunnableFinder implements Runnable {
    RandomAccessFile raf;
    byte[] bytes;
    String sub;
    ObjectsPool pool;
    int start;
    int end;
    @Override
    public void run() {
        synchronized (pool) {
            raf = pool.getRaf();
            bytes = pool.getBytes();
        }
        try {
            raf.seek(start);
            raf.read(bytes, 0, end-start);
            int ind = new String(bytes).indexOf(sub);
            if (ind != -1){
                synchronized (pool) {
                    pool.pushIndSubstring(ind+start);
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        synchronized (pool) {
            pool.putRaf(raf);
            pool.putBytes(bytes);
            pool.setVerified(pool.getVerified()+1);
            if (pool.getVerified()%20 == 0)
                System.out.println("*");
            else
                System.out.print("*");
        }
        try {
            Thread.sleep(100L);
        } catch (InterruptedException e) {

        }
    }
    public RunnableFinder Init(int start, int end, @NonNull ObjectsPool pool,@NonNull String sub){
        this.start = start;
        this.end = end;
        this.sub = sub;
        this.pool = pool;
        return this;
    }
}
