package project5.src.utils;

public class Point {
    
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int[] getPoint() {
        int[] retVal = {x,y};
        return retVal;
    }

    //add xDiff to x and yDiff to y
    public void add(int xDiff, int yDiff) {

        x += xDiff;
        y += yDiff;

    }

}