package project5.src;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import project5.src.utils.*;

public class TestObject extends Entity {

	int oldX;
	int oldY;
	int oldAngle;

	//called when object is created
	public TestObject() {
		super();

		Texture img = new Texture("test.jpg");
		sprite = new Sprite(img);
		x = 200;
		y = 200;
		angle = 90;
		speed = 300;

		name = "Test Object 1";

		Point centre = new Point(x,y);

		hitbox = new Hitbox(this,centre,45,45);
		
	}
	
	public void changeSprite(String path) {
		sprite.setTexture(new Texture(path));
	}

	/**
	 * Called every game loop for every entity.
	 */
	public void step() {

		movement();
		pointAtMouse();
		//System.out.println(x + ", " + y);
		super.step(); //call parents last
		hitbox.updatePosition(x - oldX, y - oldY, angle - oldAngle);

	}
	
	private void movement() {
		
		oldX = x;
		oldY = y;

		if(Gdx.input.isKeyPressed(Input.Keys.A)) x -= speed * Gdx.graphics.getDeltaTime();
		if(Gdx.input.isKeyPressed(Input.Keys.D)) x += speed * Gdx.graphics.getDeltaTime();
		if(Gdx.input.isKeyPressed(Input.Keys.W)) y += speed * Gdx.graphics.getDeltaTime();
		if(Gdx.input.isKeyPressed(Input.Keys.S)) y -= speed * Gdx.graphics.getDeltaTime();

	}
	
	private void pointAtMouse() {
		
		int mouseX = Gdx.input.getX();
		int mouseY = Math.abs(Gdx.input.getY() - 480); //because origins are different..?
		
		int xx = Math.abs(mouseX - x);
		int yy = Math.abs(mouseY - y);
		
		if(mouseX >= x && mouseY >= y) {
			angle = (int) Math.toDegrees(Math.atan2(yy,xx));
		}
		else if(mouseX <= x && mouseY >= y) {
			angle = (int) Math.toDegrees(Math.atan2(xx,yy)) + 90;
		}
		else if(mouseX <= x && mouseY <= y) {
			angle = (int) Math.toDegrees(Math.atan2(yy,xx)) + 180;
		}
		else if(mouseX >= x && mouseY <= y) {
			angle = (int) Math.toDegrees(Math.atan2(xx,yy)) + 270;
		}
		
	}

	
	
}
