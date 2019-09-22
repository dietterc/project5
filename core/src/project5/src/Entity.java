package project5.src;

import com.badlogic.gdx.graphics.g2d.Sprite;

public abstract class Entity {

	public Sprite sprite;
	public int x;
	public int y;
	public int angle;
	public int speed;
	
	public Entity() {
		
	}
	
	/**
	 * Called every game loop for every entity.
	 */
	public void step() {
		sprite.setPosition(x - 48, y - 48);
		sprite.setRotation(angle);
	}
	
}
