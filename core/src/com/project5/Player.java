package com.project5;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;
import java.lang.Math.*;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class Player {

    private Body physicsBody;
    private CircleShape circle;

    private Sprite bodySprite;
    private Sprite turretSprite;

    private OrthographicCamera camera;

    private float prevX, prevY;
    
    public Player(World world,int startX, int startY, OrthographicCamera c) {
        
        camera = c;

        prevX = (float)startX;
        prevY = (float)startY;

        //Set up physics body as a circle
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(startX, startY); //starting position
        physicsBody = world.createBody(bodyDef);
        physicsBody.setFixedRotation(true);

        circle = new CircleShape();
        circle.setRadius(1f);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.density = 0.0f;
        fixtureDef.friction = 0.2f;
        fixtureDef.restitution = 0.0f;
        Fixture fixture = physicsBody.createFixture(fixtureDef);

        bodySprite = new Sprite(new Texture(Gdx.files.internal("clear_hull.png")));
        bodySprite.setBounds(0, 0, 10, 10);
        bodySprite.setOriginCenter();        

        turretSprite = new Sprite(new Texture(Gdx.files.internal("clear_turret.png")));
        turretSprite.setBounds(0, 0, 10, 10);
        turretSprite.setOriginCenter();
    }

    //called every iteration of render
    public void step() {

        handleMovement();

        Vector2 worldPos = physicsBody.getPosition();
        prevX = worldPos.x;
        prevY = worldPos.y;
    }

    //called every iteration of render
    public void draw(SpriteBatch batch) {
        Vector2 worldPos = physicsBody.getPosition();

        bodySprite.setPosition(worldPos.x - (bodySprite.getWidth() / 2), worldPos.y - (bodySprite.getHeight() / 2));
        turretSprite.setPosition(worldPos.x - (bodySprite.getWidth() / 2), worldPos.y - (bodySprite.getHeight() / 2));
        bodySprite.draw(batch);
        turretSprite.draw(batch);

        //pointAtMouse();
        rotateBody();
        turretSprite.setRotation(bodySprite.getRotation());
    }

    public void dispose() {
        circle.dispose();
    }


    private void handleMovement() {

        float SPEED = 300f;
        float MAX_VELOCITY = 17;
        Vector2 pos = physicsBody.getLocalCenter();

        if (Gdx.input.isKeyPressed(Keys.D)) {
            physicsBody.applyForceToCenter(SPEED, 0.0f, true);
        }
        if (Gdx.input.isKeyPressed(Keys.A)) {
            physicsBody.applyForceToCenter(-SPEED, 0.0f, true);
        }
        if (Gdx.input.isKeyPressed(Keys.W)) {
            physicsBody.applyForceToCenter(0.0f, SPEED, true);
        }
        if (Gdx.input.isKeyPressed(Keys.S)) {
            physicsBody.applyForceToCenter(0.0f, -SPEED, true);
        }
        
        //opposite
        if (!Gdx.input.isKeyPressed(Keys.D)) {
            if(physicsBody.getLinearVelocity().x > 0) 
                physicsBody.applyForceToCenter(-SPEED, 0.0f, true);
        }
        if (!Gdx.input.isKeyPressed(Keys.A)) {
            if(physicsBody.getLinearVelocity().x < 0) 
                physicsBody.applyForceToCenter(SPEED, 0.0f, true);
        }
        if (!Gdx.input.isKeyPressed(Keys.W)) {
            if(physicsBody.getLinearVelocity().y > 0) 
                physicsBody.applyForceToCenter(0f, -SPEED, true);
        }
        if (!Gdx.input.isKeyPressed(Keys.S)) {
            if(physicsBody.getLinearVelocity().y < 0) 
                physicsBody.applyForceToCenter(0f, SPEED, true);
        }

        //speed limit - Is there a better way to do this?
        if(physicsBody.getLinearVelocity().x >= MAX_VELOCITY) 
            physicsBody.setLinearVelocity(MAX_VELOCITY,physicsBody.getLinearVelocity().y);
        if(physicsBody.getLinearVelocity().x <= -MAX_VELOCITY)
            physicsBody.setLinearVelocity(-MAX_VELOCITY,physicsBody.getLinearVelocity().y);
        if(physicsBody.getLinearVelocity().y >= MAX_VELOCITY)
            physicsBody.setLinearVelocity(physicsBody.getLinearVelocity().x,MAX_VELOCITY);
        if(physicsBody.getLinearVelocity().y <= -MAX_VELOCITY)
            physicsBody.setLinearVelocity(physicsBody.getLinearVelocity().x,-MAX_VELOCITY);

        //correct if velocity is really close to but not quite 0
        if(physicsBody.getLinearVelocity().x < 2f && physicsBody.getLinearVelocity().x > -2f) 
            physicsBody.setLinearVelocity(0,physicsBody.getLinearVelocity().y);
        if(physicsBody.getLinearVelocity().y < 2f && physicsBody.getLinearVelocity().y > -2f) 
            physicsBody.setLinearVelocity(physicsBody.getLinearVelocity().x,0);

        //temp fix for pressing both directions.
        //currently locks user in place if all keys are pressed.
        if (Gdx.input.isKeyPressed(Keys.D) && Gdx.input.isKeyPressed(Keys.A)) {
            physicsBody.setLinearVelocity(0,physicsBody.getLinearVelocity().y);
        }
        if (Gdx.input.isKeyPressed(Keys.W) && Gdx.input.isKeyPressed(Keys.S)) {
            physicsBody.setLinearVelocity(physicsBody.getLinearVelocity().x,0);
        }

    }


    private void pointAtMouse() {
		
        Vector3 pos = camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
		float mouseX = pos.x;
		float mouseY = pos.y;

        Vector2 worldPos = physicsBody.getPosition();
		float xx = Math.abs(mouseX - worldPos.x);
		float yy = Math.abs(mouseY - worldPos.y);
        int angle = 0;
		
		if(mouseX >= worldPos.x && mouseY >= worldPos.y) {
			angle = (int) Math.toDegrees(Math.atan2(yy,xx));
		}
		else if(mouseX <= worldPos.x && mouseY >= worldPos.y) {
			angle = (int) Math.toDegrees(Math.atan2(xx,yy)) + 90;
		}
		else if(mouseX <= worldPos.x && mouseY <= worldPos.y) {
			angle = (int) Math.toDegrees(Math.atan2(yy,xx)) + 180;
		}
		else if(mouseX >= worldPos.x && mouseY <= worldPos.y) {
			angle = (int) Math.toDegrees(Math.atan2(xx,yy)) + 270;
		}

        turretSprite.setRotation(angle);
	}

    private void rotateBody() {

        Vector2 worldPos = physicsBody.getPosition();
        float currentAngle = bodySprite.getRotation();
        final int ROTATION_SPEED = 20;
        System.out.println(currentAngle + "");

        if(prevX > worldPos.x) {
            if(prevY > worldPos.y) {
                //LEFT DOWN - 225
                if(Math.abs(currentAngle - 225) < ROTATION_SPEED) {
                    bodySprite.setRotation(225);
                }
                else if(currentAngle > 225 || currentAngle < 45) {
                    bodySprite.setRotation((((currentAngle - ROTATION_SPEED) % 360) + 360) % 360);
                }
                else if(currentAngle < 225 && currentAngle >= 45) {
                    bodySprite.setRotation((((currentAngle + ROTATION_SPEED) % 360) + 360) % 360);
                }
            }
            else if(prevY < worldPos.y) {
                //LEFT UP - 135
                if(Math.abs(currentAngle - 135) < ROTATION_SPEED) {
                    bodySprite.setRotation(135);
                }
                else if(currentAngle < 315 && currentAngle > 135) {
                    bodySprite.setRotation((((currentAngle - ROTATION_SPEED) % 360) + 360) % 360);
                }
                else if(currentAngle < 135 || currentAngle >= 315) {
                    bodySprite.setRotation((((currentAngle + ROTATION_SPEED) % 360) + 360) % 360);
                }
            }
            else {
                //LEFT - 180
                if(Math.abs(currentAngle - 180) < ROTATION_SPEED) {
                    bodySprite.setRotation(180);
                }
                else if(currentAngle > 180) {
                    bodySprite.setRotation((((currentAngle - ROTATION_SPEED) % 360) + 360) % 360);
                }
                else if(currentAngle < 180) {
                    bodySprite.setRotation((((currentAngle + ROTATION_SPEED) % 360) + 360) % 360);
                }
            }
        }
        else if(prevX < worldPos.x) {
            if(prevY > worldPos.y) {
                //RIGHT DOWN - 315
                if(Math.abs(currentAngle - 315) < ROTATION_SPEED) {
                    bodySprite.setRotation(315);
                }
                else if(currentAngle < 135 || currentAngle > 315) {
                    bodySprite.setRotation((((currentAngle - ROTATION_SPEED) % 360) + 360) % 360);
                }
                else if(currentAngle < 315 && currentAngle >= 135) {
                    bodySprite.setRotation((((currentAngle + ROTATION_SPEED) % 360) + 360) % 360);
                }
            }
            else if(prevY < worldPos.y) {
                //RIGHT UP - 45
                if(Math.abs(currentAngle - 45) < ROTATION_SPEED) {
                    bodySprite.setRotation(45);
                }
                else if(currentAngle < 225 && currentAngle > 45) {
                    bodySprite.setRotation((((currentAngle - ROTATION_SPEED) % 360) + 360) % 360);
                }
                else if(currentAngle > 225 || currentAngle < 45) {
                    bodySprite.setRotation((((currentAngle + ROTATION_SPEED) % 360) + 360) % 360);
                }
            }
            else {
                //RIGHT - 0
                if(Math.abs(currentAngle - 0) < ROTATION_SPEED) {
                    bodySprite.setRotation(0);
                }
                else if(currentAngle < 180) {
                    bodySprite.setRotation((((currentAngle - ROTATION_SPEED) % 360) + 360) % 360);
                }
                else if(currentAngle >= 180) {
                    bodySprite.setRotation((((currentAngle + ROTATION_SPEED) % 360) + 360) % 360);
                }
            }
        }
        else if(prevY > worldPos.y) {
            //DOWN - 270
            if(Math.abs(currentAngle - 270) < ROTATION_SPEED) {
                bodySprite.setRotation(270);
            }
            else if(currentAngle < 90 || currentAngle > 270) {
                bodySprite.setRotation((((currentAngle - ROTATION_SPEED) % 360) + 360) % 360);
            }
            else if(currentAngle >= 90 && currentAngle < 270) {
                bodySprite.setRotation((((currentAngle + ROTATION_SPEED) % 360) + 360) % 360);
            }
        }
        else if(prevY < worldPos.y) {
            //UP - 90
            if(Math.abs(currentAngle - 90) < ROTATION_SPEED) {
                bodySprite.setRotation(90);
            }
            else if(currentAngle < 270 && currentAngle > 90) {
                bodySprite.setRotation((((currentAngle - ROTATION_SPEED) % 360) + 360) % 360);
            }
            else if(currentAngle >= 270 || currentAngle < 90) {
                bodySprite.setRotation((((currentAngle + ROTATION_SPEED) % 360) + 360) % 360);
            }
        }
    }

}
