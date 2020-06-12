/*


*/

package project5.src.utils;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import project5.src.Entity;
import project5.src.Main;

public class Hitbox {
    
    Point centre;
    int width;
    int height;
    float[] vertices;

    ShapeRenderer shape;


    public Hitbox(Entity owner, Point centre, int width, int height){
        
        this.centre = centre;
        this.width = width;
        this.height = height;

        int x = centre.getPoint()[0] - width / 2; 
        int y = centre.getPoint()[1] - height / 2;

        float[] vertices = {x,y,x+width,y,x+width,y+height,x,y+height};
        this.vertices = vertices;

        shape = new ShapeRenderer();

        Main.hitboxList.add(this);
    }

    public void updatePosition(int xDiff, int yDiff, int angleDiff) {

        centre.add(xDiff, yDiff);

        int x = centre.getPoint()[0] - width / 2; 
        int y = centre.getPoint()[1] - height / 2;
        float[] vertices = {x,y,x+width,y,x+width,y+height,x,y+height};
        this.vertices = vertices;

        
    }

    public void draw() {

        shape.begin(ShapeType.Line);
        shape.setColor(Color.BLACK);
        shape.polygon(vertices);
        shape.end();

    }

    public boolean overlaps(Hitbox other) {

        

        return false;
    }

}