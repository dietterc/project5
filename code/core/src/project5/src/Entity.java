package project5.src;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

import project5.src.utils.*;

public abstract class Entity {

	public Sprite sprite;
	protected int x;
	protected int y;
	protected int angle;
	protected int speed;
	protected String name;

	protected Hitbox hitbox;
	
	public Entity() {
		
	}
	 
	/**
	 * Called every game loop for every entity.
	 */
	public void step() {
		sprite.setPosition(x - 48, y - 48);
		sprite.setRotation(angle);
	}
	
	protected boolean collidingWith(Entity other) {
		
		Rectangle rect1 = sprite.getBoundingRectangle();
		Rectangle rect2 = other.sprite.getBoundingRectangle();
		
		if(rect1.overlaps(rect2)) {
			//System.out.println("Collision");
		}else {
			//System.out.println("-");
		}
		return false;
	}

	public String getName() {
		return name;
	}
}
