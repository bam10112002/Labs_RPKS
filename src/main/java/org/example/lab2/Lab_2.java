package org.example.lab2;

import org.example.labI.Lab_Interface;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class Lab_2 implements Lab_Interface
{
    ArrayList<Pair> coins = new ArrayList<Pair>();
    @Override
    public void Run()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Input coins sum: ");
        int sum = scanner.nextInt();

        InitCoinSet();

        ArrayList<Pair> rez= FindChange(coins,0, sum, new ArrayList<Pair>(), 0 );

        if (rez == null)
            System.out.println("ERROR");
        else
            for (Pair p : rez)
            {
                if (p.quantity != 0)
                    System.out.println(p);
            }
    }


    private @Nullable ArrayList<Pair> FindChange(@NotNull ArrayList<Pair> coins, int ind, int sum,
                                                 ArrayList<Pair> last, int lastSum) {
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
                ArrayList<Pair> rez = FindChange(coins, ind + 1, sum, Last2, lastSum + coins.get(ind).denomination * i);
                if (rez != null)
                    return rez;
            }
        }
        return null;
    }

    private void InitCoinSet() {
        coins.add(new Pair(10,2));
        coins.add(new Pair(6,2));
        coins.add(new Pair(1,1));
        //coins.add(new Pair(0,1));
        Collections.sort(coins);
    }
}
