package com.project5;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Main extends Game {
	
	public SpriteBatch batch;
	public BitmapFont font;
	
	@Override
	public void create() {
		batch = new SpriteBatch();
		font = new BitmapFont(); // use libGDX's default Arial font
		this.setScreen(new MainMenu(this));
	}

	@Override
	public void render() {
		super.render(); 
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		font.dispose();
	}
}
