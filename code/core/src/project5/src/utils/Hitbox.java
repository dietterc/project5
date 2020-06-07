package project5.src.utils;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import project5.src.Entity;
import project5.src.Main;

public class Hitbox {
    
    ArrayList<SubHitbox> subBoxes;
    int subBoxCount;

    public Hitbox(Entity owner, Point[] points){
        subBoxCount = 0;
        subBoxes = new ArrayList<SubHitbox>(); 

        if(points.length % 4 != 0) {
            System.out.println("Hitbox error for " + owner.getName());
            return;
        }

        Main.hitboxList.add(this);

        for(int i=0;i<points.length;i+=4) {
            subBoxCount++;
            subBoxes.add(new SubHitbox(points[i],points[i+1],points[i+2],points[i+3]));
        }
    }

    public void updatePosition(int xDiff, int yDiff) {
        
        //update the position of the hitboxes 
        for(int i=0;i<subBoxes.size();i++) {
            subBoxes.get(i).updatePosition(xDiff, yDiff);
        }


    }

    public void draw() {
        
        for(int i=0;i<subBoxes.size();i++) {
            subBoxes.get(i).draw();
        }

    }

}

class SubHitbox {

    Point p1;
    Point p2;
    Point p3;
    Point p4;

    ShapeRenderer shape;

    public SubHitbox(Point p1, Point p2, Point p3, Point p4) {

        this.p1 = p1; //bottom left
        this.p2 = p2; //top left
        this.p3 = p3; //top right
        this.p4 = p4; //bottom right

        shape = new ShapeRenderer();

    }

    public void draw() {

        int x = p1.getPoint()[0];
        int y = p1.getPoint()[1];

        int width = Math.abs(x - p4.getPoint()[0]);
        int height = Math.abs(y - p2.getPoint()[1]);

        shape.begin(ShapeType.Line);
		shape.setColor(Color.BLACK);
		shape.rect(x, y, width, height);
        shape.end();

    }

    public void updatePosition(int xDiff, int yDiff) {

        p1.add(xDiff, yDiff);
        p2.add(xDiff, yDiff);
        p3.add(xDiff, yDiff);
        p4.add(xDiff, yDiff);

    }

}