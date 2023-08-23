package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Tank extends Sprite {
    private Stage stage;
    public int flag;
    public int damageFlag;
    public boolean hurt;
    public World world;
    public Body b2body;
    public Tank enemy;
    public PlayScreen playScreen;
    public Canonball canonball;
    public Vector2 lastCollision;
    public float angle;
    public Texture projectile;
    public int fuel;
    public Canonball getCanonball() {
        return canonball;
    }

    public void setEnemy(Tank enemy) {
        this.enemy = enemy;
    }

    private static final float V = 0.7f;

    public Tank(World world, String path,PlayScreen ps){
        super(new Vector2(110,100));
        this.canonball = null;
        this.angle = 1;
        this.world = world;
        this.playScreen = ps;
        flag = 1;
        damageFlag = 10;
        fuel = 100;
        spriteSheet = new Texture(Gdx.files.internal(path));
        projectile =  new Texture(Gdx.files.internal("projectile1.png"));
        defineTank();
    }

    public void defineTank(){

        BodyDef bdef = new BodyDef();
        bdef.position.set(position.x/TankStars.PPM,position.y/TankStars.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);
        b2body.setUserData(this);
        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(50/TankStars.PPM);
        fdef.shape = shape;
        b2body.createFixture(fdef);
        shape.dispose();

    }
    public void draw(Batch spritebatch) {
//        spritebatch.draw(projectile, (position.x) * TankStars.PPM, (position.y) * TankStars.PPM, 0, 0,100,100,1,1,angle,5,5,1000,550,false,false);
        spritebatch.draw(spriteSheet, (position.x) * TankStars.PPM - 80, (position.y) * TankStars.PPM - 80, 100,100);
    }
    public void update(float dT){
//        position.x+=10*dT;
        super.update(dT);
        handleInput("");
    }

    public void handleInput(String str){

        Vector2 curV = b2body.getLinearVelocity();
        Vector2 curPos = b2body.getPosition();
        if(Math.abs(curV.x)>V){
            curV.x= Math.signum(curV.x)*V;
            this.b2body.setLinearVelocity(curV.x,curV.y);
        }
        if(playScreen.turn == 1) {
            if (str.equals("")) {
                if(fuel>0) {

                    if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                        world.step(Gdx.graphics.getDeltaTime(), 1, 1);
                        this.b2body.applyLinearImpulse(V, 0, position.x, position.y, true);
                        fuel -= 1;
                    }
                    if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
//                    System.out.println("A");
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
                if(Gdx.input.isKeyJustPressed(Input.Keys.ALT_LEFT)){
//                    System.out.println("A");

                    if(flag==0){
                        flag=1;
                    }else {
//                        System.out.println("A");
                        flag=0;
                        playScreen.turn = 2;
                        fuel=100;
                    }
                }
                if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
                    world.step(Gdx.graphics.getDeltaTime(), 1, 1);
                    if(flag==0){
                        flag=1;
                    }else {
//                        System.out.println("A");
                        playScreen.isPresent = true;
                        Canonball canonball = new Canonball(playScreen.canonWorld, "canonball.png", this.playScreen, this,angle);
                        this.canonball = canonball;
                        flag = 0;
                        playScreen.turn = 2;
                        fuel=100;
                    }
                }
                Hud.setFuel1(fuel);
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
            posEnemy = playScreen.user2.lastCollision;
//            System.out.println("LC"+damageFlag+playScreen.user1.lastCollision);
            posEnemy.x *= TankStars.PPM;
            posEnemy.y *= TankStars.PPM;
            distance =  Math.pow((Math.pow((this.position.x * TankStars.PPM - posEnemy.x), 2) + Math.pow((this.position.y * TankStars.PPM - posEnemy.y), 2)), 0.5f);
//            System.out.println(distance);
//            if (distance < Canonball.damageLength) {
//            System.out.println("Hello");
            world.step(Gdx.graphics.getDeltaTime(), 1, 1);
            if (posEnemy.x >= this.position.x*TankStars.PPM) {
                this.b2body.applyLinearImpulse( -V , 0, position.x, position.y, true);

            } else {
                this.b2body.applyLinearImpulse(V, 0, position.x, position.y, true);
            }
            if (damageFlag==0){
                System.out.println((int)Math.floor((150-distance/2))/2);
                Hud.decreaseHealth1((int)Math.floor((400-distance/2))/10);
            }
            damageFlag++;
        }

    }
}
