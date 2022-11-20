package org.example.lab3;


import lombok.Getter;
import lombok.Setter;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.util.ArrayDeque;

public class ObjectsPool {
    ArrayDeque<RandomAccessFile> objects;
    ArrayDeque<byte[]> bytes;
    @Getter
    @Setter
    long verified;
    @Getter
    @Setter
    long total;
    @Getter
    ArrayDeque<Integer> indexOf;

    ObjectsPool(int size, String path, long total) {
        bytes = new ArrayDeque<>();
        indexOf = new ArrayDeque<>();
        objects = new ArrayDeque<>();
        try {
            for (int i = 0; i < size; i++) {
                bytes.push(new byte[1024]);
                objects.push(new RandomAccessFile(path, "r"));
            }
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }
    public byte[] getBytes() { return bytes.pop(); }
    public RandomAccessFile getRaf() { return objects.pop(); }
    public void putBytes(byte[] byteMassive) {  bytes.push(byteMassive); }
    public void putRaf(RandomAccessFile file) { objects.push(file);}
    public void pushIndSubstring(int ind) {
        indexOf.push(ind);
    }
}
