package project5.src;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import project5.src.utils.*;

public class Main extends ApplicationAdapter {
	SpriteBatch batch;
	
	static EntityList masterList = new EntityList();
	public static ArrayList<Hitbox> hitboxList = new ArrayList<Hitbox>();

	private final boolean drawHitboxes = true;

	
	@Override
	public void create () {
		batch = new SpriteBatch();
		
		masterList.insert(new TestObject());
		masterList.insert(new Wall());

	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(.75f, .5f, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		
		//Go through every entity in the entity list
		masterList.startIter();
		Entity curr = masterList.getTopItem();
		while(curr != null) {
			
			curr.sprite.draw(batch);
			curr.step();
			
			curr = masterList.getNext();
		}

		batch.end();

		if(drawHitboxes) {
			for(int i=0;i<hitboxList.size();i++) {
				hitboxList.get(i).draw();
			}
		}

	}
	
	@Override
	public void dispose () {
		batch.dispose();
		//img.dispose();
	}
}
