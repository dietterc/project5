package com.project5;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.math.Vector2;

public class SandBoxScreen implements Screen {

	final Main game;
    final int WIDTH = 80;
    final int HEIGHT = 54;

	OrthographicCamera camera;
    World world;
    Box2DDebugRenderer debugRenderer;
    Body body;
    Player player;

	public SandBoxScreen(final Main game) {
		this.game = game;

		camera = new OrthographicCamera(WIDTH,HEIGHT);
		
        world = new World(new Vector2(0,0), true);
        debugRenderer = new Box2DDebugRenderer();

        player = new Player(world,0,0,camera);

        //floor
        BodyDef groundBodyDef = new BodyDef();
        groundBodyDef.position.set(0,-27);
        Body groundBody = world.createBody(groundBodyDef);
        PolygonShape groundBox = new PolygonShape();
        groundBox.setAsBox(32, 1);
        groundBody.createFixture(groundBox, 0.0f);
        groundBox.dispose();

        //roof
        BodyDef roofBodyDef = new BodyDef();
        roofBodyDef.position.set(0,27);
        Body roofBody = world.createBody(roofBodyDef);
        PolygonShape roofBox = new PolygonShape();
        roofBox.setAsBox(32, 1);
        roofBody.createFixture(roofBox, 0.0f);
        roofBox.dispose();

        //left wall
        BodyDef leftWallBodyDef = new BodyDef();
        leftWallBodyDef.position.set(40,0);
        Body leftWallBody = world.createBody(leftWallBodyDef);
        PolygonShape leftWallBox = new PolygonShape();
        leftWallBox.setAsBox(1, 24);
        leftWallBody.createFixture(leftWallBox, 0.0f);
        leftWallBox.dispose();

        //right wall
        BodyDef rightWallBodyDef = new BodyDef();
        rightWallBodyDef.position.set(-40,0);
        Body rightWallBody = world.createBody(rightWallBodyDef);
        PolygonShape rightWallBox = new PolygonShape();
        rightWallBox.setAsBox(1, 24);
        rightWallBody.createFixture(rightWallBox, 0.0f);
        rightWallBox.dispose();
        
	}

    @Override
	public void render(float delta) {
		ScreenUtils.clear(0.5f, 0.5f, 0.5f, 1);

		camera.update();
		game.batch.setProjectionMatrix(camera.combined);

		game.batch.begin();
        game.batch.setProjectionMatrix(camera.combined);

        player.draw(game.batch);
        
		game.batch.end();

        player.step();

        debugRenderer.render(world, camera.combined);
		world.step(1/60f, 6, 2);
	}


    @Override
	public void resize(int width, int height) {

	}

    @Override
	public void show() {

	}

    @Override
	public void hide() {

	}

    @Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

    @Override
	public void dispose() {
        player.dispose();
	}

}