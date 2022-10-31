package Lab_2;
import Lab_interface.Lab_I;

import java.awt.*;
import java.time.Period;
import java.util.*;

public class Lab_2 implements Lab_I
{
    ArrayList<Pair> coins = new ArrayList<Pair>();
    @Override
    public void Run()
    {
        coins.add(new Pair(5,2));
        coins.add(new Pair(2,1));
        coins.add(new Pair(1,3));
        coins.add(new Pair(0,1));
        Collections.sort(coins);

        ArrayList<Pair> rez= recFunk(coins,0, 8, new ArrayList<Pair>(), 0 );
        if (rez == null)
            System.out.println("ERROR");
        else
            for (Pair p : rez)
            {
                System.out.println(p);
            }
    }

    private ArrayList<Pair> recFunk(ArrayList<Pair> coins, int ind, int sum, ArrayList<Pair> last, int lastSum)
    {
        if (coins.size() == ind)
            return null;
        for (int i = coins.get(ind).quantity; i >= 0; i--)
        {
            if (sum == lastSum + coins.get(ind).denomination * i)
            {
                last.add(new Pair(coins.get(ind).denomination, i));
                return last;
            }
            if (sum >  lastSum + coins.get(ind).denomination * i)
            {
                ArrayList<Pair> Last2 = (ArrayList<Pair>)last.clone();
                Last2.add(new Pair(coins.get(ind).denomination, i));
                ArrayList<Pair> rez = recFunk(coins, ind + 1, sum, Last2, lastSum + coins.get(ind).denomination * i);
                if (rez != null)
                    return rez;
            }
        }
        return null;
    }
}

class Pair implements Comparable<Pair>
{
    public int denomination;
    public int quantity;
    Pair(int first, int second)
    {
        this.denomination = first;
        this.quantity = second;
    }

    @Override
    public int compareTo(Pair o) {
        return o.denomination - this.denomination;
    }

    @Override
    public String toString() {
        return "{ " +
                "coin=" + denomination +
                ", num=" + quantity +
                " }";
    }
}
