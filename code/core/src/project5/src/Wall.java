package project5.src;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Wall extends Entity {

	boolean collision;

	//called when object is created
	public Wall() {
		super();

		Texture img = new Texture("wall.jpg");
		sprite = new Sprite(img);
		x = 500;
		y = 200;
		angle = 0;
		speed = 0;
		
		collision = false;
		
	}
	
	public void changeSprite(String path) {
		sprite.setTexture(new Texture(path));
	}

	/**
	 * Called every game loop for every entity.
	 */
	public void step() {
		super.step(); //call parents first

		if(collidingWith(Main.testObject)) {
			collision = true;
			changeSprite("red_wall.jpg");
		}
		else if(collision) {
			changeSprite("wall.jpg");
			collision = false;
		}
		
	}
	
	

}
