package Lab_4;

import Lab_interface.Lab_I;

import java.io.File;
import java.util.Objects;

public class Lab_4 implements Lab_I {
    @Override
    public void Run()
    {
        File folder = new File("D:/TestDir");
        System.out.println(getSize(folder));
    }
    long getSize(File file)
    {
        if (file.isFile())
            return file.length();
        else if (file.isDirectory())
        {
            long sum = 0;
            for (File f : Objects.requireNonNull(file.listFiles()))
            {
                sum += getSize(f);
            }
            return sum;
        }
        return 0;
    }
}
