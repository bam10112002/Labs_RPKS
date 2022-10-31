package Lab_8;

import java.io.*;
import java.util.Objects;
import java.util.Scanner;

public class Server
{
    public static void main(String[] args) throws IOException
    {
        Scanner in = new Scanner(System.in);
        Battleship war = new Battleship();
        Web server = new Web(Web.Type.SERVER,5012, "");

        while(true)
        {
            String rez;

            rez = server.Read();                                // получаю его выстрел
            String[] splited = rez.split(" ");
            int x = Integer.parseInt(splited[0]);
            int y = Integer.parseInt(splited[1]);

            server.Send(war.isHit(x, y).toString());            // отправляю результат
            war.hit(x, y);

            x = in.nextInt();
            y = in.nextInt();
            server.Send(Integer.toString(x) + " " + Integer.toString(y)); // мой выстрел
            rez = server.Read();

            war.shot(x, y, Objects.equals(rez, "true"));     // результат моего выстрела

            war.print();
        }



        //server.close();
    }
}