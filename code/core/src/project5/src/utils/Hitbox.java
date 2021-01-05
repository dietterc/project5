/*


*/

package project5.src.utils;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import project5.src.Entity;
import project5.src.Main;

public class Hitbox {
    
    Point position;
    int width;
    int height;
    float[] vertices;

    ShapeRenderer shape;


    public Hitbox(Entity owner, Point position, int width, int height){
        
        this.position = position;
        this.width = width;
        this.height = height;

        int x = position.getPoint()[0] - width / 2; 
        int y = position.getPoint()[1] - height / 2;

        float[] vertices = {x,y,x+width,y,x+width,y+height,x,y+height};
        this.vertices = vertices;

        shape = new ShapeRenderer();

        Main.hitboxList.add(this);
    }

    public void updatePosition(int xDiff, int yDiff, int angleDiff) {

        position.add(xDiff, yDiff);

        int x = position.getX() - width / 2; 
        int y = position.getY() - height / 2;
        float[] vertices = {x,y,x+width,y,x+width,y+height,x,y+height};
        this.vertices = vertices;

    }

    public void draw() {

        shape.begin(ShapeType.Line);
        shape.setColor(Color.GREEN);
        shape.polygon(vertices);
        shape.end();

    }

    public boolean overlaps(Hitbox other) {
        //algorithm from https://www.geeksforgeeks.org/find-two-rectangles-overlap/
        
        Point l1 = new Point(position.getX() - width/2, position.getY() + height/2);
        Point r1 = new Point(position.getX() + width/2, position.getY() - height/2);

        Point l2 = new Point(other.position.getX() - other.width/2, other.position.getY() + other.height/2);
        Point r2 = new Point(other.position.getX() + other.width/2, other.position.getY() - other.height/2);


        if(l1.getX() >= r2.getX() || l2.getX() >= r1.getX()) {
            return false;
        }

        if(l1.getY() <= r2.getY() || l2.getY() <= r1.getY()) {
            return false;
        }

        return true;
    }

}