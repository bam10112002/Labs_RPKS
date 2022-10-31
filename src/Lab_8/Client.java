package Lab_8;

import java.util.Scanner;

public class Client
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        Battleship war = new Battleship();
        Web client = new Web(Web.Type.CLIENT,5012, "");

//        while(true)
//        {
//            int x = in.nextInt();
//            int y = in.nextInt();
//
//            client.Send(Integer.toString(x) + " " + Integer.toString(y)); // мой выстрел
//            String rez = client.Read();
//
//            war.shot(x, y, Objects.equals(rez, "true"));     // результат моего выстрела
//
//            rez = client.Read();                                // получаю его выстрел
//            String[] splited = rez.split(" ");
//            x = Integer.parseInt(splited[0]);
//            y = Integer.parseInt(splited[1]);
//
//            client.Send(war.isHit(x, y).toString());            // отправляю результат
//            war.hit(x, y);
//
//            war.print();
//        }


//        client.close();
    }

}
