package Lab_8;

import java.util.ArrayList;

public class Battleship
{
    char myMap[][] = new char[10][10];
    Ship[] ships = new Ship[10];
    char otherMap[][] = new char[10][10];

    public Battleship()
    {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++)
            {
                myMap[i][j] = otherMap[i][j] = ' ';
            }
        }
        ArrayList<Coord> shipCoord = new ArrayList<Coord>();
        shipCoord.add(new Coord(1,1));
//        shipCoord.add(new Coord(1,2));
    }

    void hit(int x, int y) // Удары по моему полю
    {
        myMap[x][y] = 'x';
    }
    void shot(int x, int y, boolean hit) // Зафаксировать поподание по чужому полю
    {
        if (hit)
            otherMap[x][y] = 'x';
        else
            otherMap[x][y] = 'o';
    }
    Boolean isHit(int x, int y)
    {
        return (myMap[x][y] >= '0' && myMap[x][y] <= '9');
    }

    void print()
    {
        for (int i = 0; i < 10; i++)
        {
            for (int j = 0; j < 10; j++)
            {
                System.out.print(myMap[i][j] + "  |  ");
            }
            System.out.print("\t");
            for (int j = 0; j < 10; j++)
            {
                System.out.print(otherMap[i][j] + "  |  ");
            }

            System.out.println("");

            for (int j = 0; j < 10; j++)
                System.out.print("------");
            System.out.print("\t");
            for (int j = 0; j < 10; j++)
                System.out.print("------");

            System.out.println("");
        }
    }
}

class Coord
{
    int x;
    int y;
    Coord(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
}
class Ship
{
    private ArrayList<Coord> coords;
    Ship(ArrayList<Coord> coords)
    {
        this.coords = coords;
    }
    ArrayList<Coord> getCoords()
    {
        return coords;
    }

}
