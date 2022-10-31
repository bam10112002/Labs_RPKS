import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;
import Lab_1.Lab_1;
import Lab_5.Lab_5;
import Lab_2.Lab_2;
import Lab_4.Lab_4;
import Lab_interface.Lab_I;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Lab_I[] labArr = new Lab_I[] { new Lab_1(), new Lab_2(), null, new Lab_4(), new Lab_5() };

        Scanner sc = new Scanner(System.in);
        Lab_I lab = labArr[sc.nextInt()];
        if (lab != null)
            lab.Run();
        else
            System.out.println("Lab not found");

    }
}