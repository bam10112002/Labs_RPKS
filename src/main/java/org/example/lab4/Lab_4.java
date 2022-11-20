package org.example.lab4;

import org.example.labI.Lab_Interface;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.Objects;

public class Lab_4 implements Lab_Interface {
    @Override
    public void Run()
    {
        File folder = new File("C:\\Users\\bam10\\Documents");
        System.out.println("bit:       " + getSize(folder));
        System.out.println("bytes:     " + getSize(folder)/1024);
        System.out.println("kilobytes: " + getSize(folder)/1024/1024);
        System.out.println("megabytes: " + getSize(folder)/1024/1024/1024);
    }
    long getSize(@NotNull File file)
    {
        if (file.isFile())
            return file.length();
        else if (file.isDirectory())
        {
            long sum = 0;
            File[] files = file.listFiles();
            if(files == null)
                return sum;

            for (File f : files)
            {
                sum += getSize(f);
            }
            return sum;
        }
        return 0;
    }
}
