package project5.src;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Wall extends Entity {

	//called when object is created
	public Wall() {
		super();

		Texture img = new Texture("wall.jpg");
		sprite = new Sprite(img);
		x = 500;
		y = 200;
		angle = 0;
		speed = 0;
		
		
		
	}
	
	public void changeSprite(String path) {
		sprite.setTexture(new Texture(path));
	}

	/**
	 * Called every game loop for every entity.
	 */
	public void step() {
		super.step(); //call parents first
		
	}
	
	

}
