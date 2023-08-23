package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class Canonball extends Sprite implements InteractiveObject{
    public final static double damageLength = 5d ;
    public Tank userTank;
    public World world;
    public Body b2body;
    public float A;
    public PlayScreen playScreen;
    public final int Velocity = 2;
    public final int V= 1;
    public int flag = 0;
    public float angle;
    public Canonball(World world, String path, PlayScreen ps, Tank userTank,float angle) {
        super(userTank.position);
        this.position.x*=TankStars.PPM;
        this.position.y*=TankStars.PPM;
        this.angle = angle;
        this.world = world;
        this.playScreen = ps;
        spriteSheet = new Texture(Gdx.files.internal(path));
        defineCanon();
        A = 0;
    }

    public void defineCanon() {

        BodyDef bdef = new BodyDef();
        bdef.position.set(position.x / TankStars.PPM, position.y / TankStars.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);
        b2body.setUserData(this);
        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(5/ TankStars.PPM);
        fdef.shape = shape;
        b2body.createFixture(fdef);
        EdgeShape top = new EdgeShape();
        top.set(new Vector2(-8/TankStars.PPM,0), new Vector2(8/TankStars.PPM,0));
        fdef.isSensor = true;
        b2body.createFixture(fdef).setUserData("top");
        shape.dispose();

    }

    public void draw(Batch spritebatch) {
        spritebatch.draw(spriteSheet, (position.x) *TankStars.PPM, (position.y)*TankStars.PPM, 20, 20);

    }

    public void update(float dT) {
//        position.x+=10*dT;
        super.update(dT);
        handleInput("");
    }

//    @Override
//    void handleInput() {
//
//    }

    public void handleInput(String str) {
        Vector2 curV = b2body.getLinearVelocity();
        Vector2 curPos = b2body.getPosition();
        if (flag==0) {
            this.b2body.applyLinearImpulse((float) (Velocity*3*Math.cos(angle)), (float) (Velocity*3*Math.sin(angle)), position.x, position.y, true);
            flag = 1;
            return;
        }
    }
    @Override
    public void hit(){
//        System.out.println("Cannon Collision");
    }
//    public void damage(){
//        Vector2 posEnemy = playScreen.user2.position;
//        posEnemy.x*=TankStars.PPM;
//        posEnemy.y*=TankStars.PPM;
//        float distance = (float) Math.pow((Math.pow((this.position.x*TankStars.PPM - posEnemy.x),2)  +  Math.pow((this.position.y*TankStars.PPM - posEnemy.y),2)),2);
//        if(distance<damageLength){
//            playScreen.user2.setHurt(true);
//            playScreen.user2.damage(distance);
//        }
//    }
}
