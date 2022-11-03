package org.example.lab2;

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
