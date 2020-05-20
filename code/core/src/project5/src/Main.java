package project5.src;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Main extends ApplicationAdapter {
	SpriteBatch batch;
	
	static EntityList masterList = new EntityList();

	
	@Override
	public void create () {
		batch = new SpriteBatch();
		
		masterList.insert(new TestObject());
		masterList.insert(new Wall());
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		
		//Go through every entity in the entity list
		masterList.startIter();
		Entity curr = masterList.top.item;
		while(curr != null) {
			
			curr.sprite.draw(batch);
			curr.step();
			
			curr = masterList.getNext();
		}
		
		
		
		batch.end();
	
		
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		//img.dispose();
	}
}
