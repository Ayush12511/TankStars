package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class TankEnemy extends Sprite {
    public int flag;
    public int damageFlag;
    public boolean hurt;
    private int fuel;

    public Vector2 lastCollision;
    private PlayScreen playScreen;
    private World world;
    private Body b2body;

    public int getFuel() {
        return fuel;
    }

    public void setFuel(int fuel) {
        this.fuel = fuel;
    }

    public Vector2 getLastCollision() {
        return lastCollision;
    }

    public void setLastCollision(Vector2 lastCollision) {
        this.lastCollision = lastCollision;
    }

    public PlayScreen getPlayScreen() {
        return playScreen;
    }

    public void setPlayScreen(PlayScreen playScreen) {
        this.playScreen = playScreen;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public Body getB2body() {
        return b2body;
    }

    public void setB2body(Body b2body) {
        this.b2body = b2body;
    }

    public Tank getEnemy() {
        return enemy;
    }

    public Tank enemy;
    public float angle;
    public CanonballEnemy canonball;

    public CanonballEnemy getCanonball() {
        return canonball;
    }

    public boolean isHurt() {
        return hurt;
    }

    public void setHurt(boolean hurt) {
        this.hurt = hurt;
    }

    public void setEnemy(Tank enemy) {
        this.enemy = enemy;
    }

    private static final float V = 0.7f;

    public TankEnemy(World world, String path, PlayScreen ps) {
        super(new Vector2(800, 100));
        this.world = world;
        this.canonball = null;
        angle = 1;
        this.playScreen = ps;
        spriteSheet = new Texture(Gdx.files.internal(path));
        flag = 0;
        fuel=100;
        damageFlag = 10;
        defineTank();
    }

    public void defineTank() {

        BodyDef bdef = new BodyDef();
        bdef.position.set(position.x / TankStars.PPM, position.y / TankStars.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);
        b2body.setUserData(this);
        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(50 / TankStars.PPM);
        fdef.shape = shape;
        b2body.createFixture(fdef);
        shape.dispose();
    }

    public void draw(Batch spritebatch) {
        spritebatch.draw(spriteSheet, (position.x) * TankStars.PPM - 80, (position.y) * TankStars.PPM - 80, 100, 100);
    }

    public void update(float dT) {
//        position.x+=10*dT;
        super.update(dT);
        handleInput("");
    }

    public void handleInput(String str) {
//        if(damageFlag>=10) {
            Vector2 curV = b2body.getLinearVelocity();
            Vector2 curPos = b2body.getPosition();
            if (Math.abs(curV.x) > V) {
                curV.x = Math.signum(curV.x) * V;
                this.b2body.setLinearVelocity(curV.x, curV.y);
            }
//        }
        if (playScreen.turn == 2) {
            if (str.equals("")) {
                if(fuel>0) {
                    if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                        world.step(Gdx.graphics.getDeltaTime(), 1, 1);
                        this.b2body.applyLinearImpulse(V, 0, position.x, position.y, true);
                        fuel -= 1;
                    }
                    if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                        world.step(Gdx.graphics.getDeltaTime(), 1, 1);
                        this.b2body.applyLinearImpulse(-V, 0, position.x, position.y, true);
                        fuel -= 1;
                    }
                }
                if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
                    angle += 0.01;
                }
                if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
                    angle -= 0.01;
                }
                if (Gdx.input.isKeyJustPressed(Input.Keys.ALT_LEFT)) {
                    if (flag == 0) {
                        flag = 1;
                    } else {
                        playScreen.turn = 1;
                        flag = 0;
                    }
                }
                if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
                    world.step(Gdx.graphics.getDeltaTime(), 1, 1);
                    if (flag == 0) {
                        flag = 1;
                    } else {
//                        System.out.println("A1");
                        CanonballEnemy canonball = new CanonballEnemy(playScreen.canonWorld, "canonball.png", this.playScreen, this, angle);
                        this.canonball = canonball;
                        playScreen.turn = 1;
                        flag = 0;
                        fuel=100;
                    }
                }
                Hud.setFuel2(fuel);
                damage();
            }
            if (str.equals("right")) {
                world.step(Gdx.graphics.getDeltaTime(), 1, 1);
                this.b2body.applyLinearImpulse(V, 0, position.x, position.y, true);
            }
            if (str.equals("left")) {
                world.step(Gdx.graphics.getDeltaTime(), 1, 1);
                this.b2body.applyLinearImpulse(-V, 0, position.x, position.y, true);
            }

        }
    }

    public void damage() {
        double distance = 0;
        Vector2 posEnemy = new Vector2();
        if (damageFlag < 10) {
            posEnemy = playScreen.user1.lastCollision;
//            System.out.println("LC"+damageFlag+playScreen.user1.lastCollision);
            posEnemy.x *= TankStars.PPM;
            posEnemy.y *= TankStars.PPM;
            System.out.println(this.position.x * TankStars.PPM - posEnemy.x);
            System.out.println(this.position.y * TankStars.PPM - posEnemy.y);
            distance =  Math.pow((Math.pow((this.position.x * TankStars.PPM - posEnemy.x), 2) + Math.pow((this.position.y * TankStars.PPM - posEnemy.y), 2)), 0.5f);
//            System.out.println(distance);
//            if (distance < Canonball.damageLength) {
//                System.out.println("Hello");
                world.step(Gdx.graphics.getDeltaTime(), 1, 1);
                if (posEnemy.x >= this.position.x*TankStars.PPM) {
                    this.b2body.applyLinearImpulse( -V , 0, position.x, position.y, true);

                } else {
                    this.b2body.applyLinearImpulse(V, 0, position.x, position.y, true);
                }
//            }
            if (damageFlag==0){
//                System.out.println("d:"+distance);
                Hud.decreaseHealth2((int)Math.floor((400-distance/2))/10);
            }
            damageFlag++;
        }
    }
}