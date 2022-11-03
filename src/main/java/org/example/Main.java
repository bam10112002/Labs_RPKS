package org.example;

import org.example.lab1.Lab_1;
import org.example.lab2.Lab_2;
import org.example.lab4.Lab_4;
import org.example.lab5.Lab_5;
import org.example.labI.Lab_Interface;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Lab_Interface[] labs = new Lab_Interface[] {new Lab_1(), new Lab_2(), null, new Lab_4(), new Lab_5()};
        Scanner scanner = new Scanner(System.in);

        Lab_Interface lab = labs[scanner.nextInt()-1];
        if (lab != null) {
            lab.Run();
        }
        else {
            System.out.println("Lab in work :/");
        }
    }
}