package com.elsys;

public class Coordinates implements Comparable{
    private int x;
    private int y;

    Coordinates(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    int getX()
    {
        return this.x;
    }

    int getY()
    {
        return this.y;
    }

    void setX(int x)
    {
        if(x >= 0 && x <= 14)
            this.x = x;
    }

    void setY(int y)
    {
        if(y >= 0 && y <= 14)
            this.y = y;
    }

    @Override
    public int compareTo(Object o) {
        Coordinates coordinate = (Coordinates) o;
        if(this.x == coordinate.getX())
        {
            return this.y - coordinate.getY();
        }
        return this.x - coordinate.getX();
    }
}
