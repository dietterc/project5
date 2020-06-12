package project5.src;

import com.badlogic.gdx.graphics.g2d.Sprite;

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
		
		if(hitbox != null) {
			if(hitbox.overlaps(other.hitbox)) {
				//System.out.println("Collision");
			}else {
				//System.out.println("-");
			}
			return false;
		}
		return false;
	}

	public String getName() {
		return name;
	}
}
