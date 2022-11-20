package org.example;

import org.example.lab1.Lab_1;
import org.example.lab2.Lab_2;
import org.example.lab3.Lab_3;
import org.example.lab3.NewLab_3;
import org.example.lab4.Lab_4;
import org.example.lab5.Lab_5;
import org.example.labI.Lab_Interface;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Lab_Interface[] labs = new Lab_Interface[] {new Lab_1(), new Lab_2(), new NewLab_3(), new Lab_4(), new Lab_5()};

        while(true) {
            System.out.print("input lab number: ");
            Scanner scanner = new Scanner(System.in);

            Lab_Interface lab = labs[scanner.nextInt() - 1];
            lab.Run();
        }
    }
}