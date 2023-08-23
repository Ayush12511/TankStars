package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class CanonballEnemy extends Sprite {
    public float damageLength;
    public TankEnemy userTank;
    public World world;
    public Body b2body;
    public float A;
    public PlayScreen playScreen;
    public final int Velocity = 2;
    public final int V= 1;
    public int flag = 0;
    public float angle;
    public CanonballEnemy(World world, String path, PlayScreen ps, TankEnemy userTank,float angle) {
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
//        if(Math.abs(curV.x)>V){
//            curV.x= Math.signum(curV.x)*V;
//            this.b2body.setLinearVelocity(curV.x,curV.y);
//        }
//        if(Math.abs(curV.x)>V){
//            curV.x= Math.signum(curV.x)*V;
//            this.b2body.setLinearVelocity(curV.x,curV.y);
//        }
//        if(playScreen.turn == 1) {
//            if (str.equals("")) {
//                if (Gdx.input.isTouched()) {
//                    world.step(Gdx.graphics.getDeltaTime(), 1, 1);
//                    float C = (float) Math.pow(Math.pow(userTank.position.x - Gdx.input.getX(),2) + Math.pow(userTank.position.y - Gdx.input.getY(),2),1/2);
//                    float A = userTank.position.x - Gdx.input.getX();
//                    float B = userTank.position.y - Gdx.input.getY();
        if (flag==0) {
            this.b2body.applyLinearImpulse((float) (-Velocity*3*Math.cos(angle)), (float) (Velocity*3*Math.sin(angle)), position.x, position.y, true);
            flag = 1;
            return;
        }
//
//                }
//                if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
////                    System.out.println("A");
//                    world.step(Gdx.graphics.getDeltaTime(), 1, 1);
//                    this.b2body.applyLinearImpulse(-V, 0, position.x, position.y, true);
//                }
//                if(Gdx.input.isKeyJustPressed(Input.Keys.ALT_LEFT)){
////                    System.out.println("A");
//
//
//                    if(flag==0){
//                        flag=1;
//                    }else {
//                        System.out.println("A");
//                        flag=0;
//                        playScreen.turn = 2;
//                    }
//                }

    }
//            if (str.equals("right")) {
//                world.step(Gdx.graphics.getDeltaTime(), 1, 1);
//                this.b2body.applyLinearImpulse(V, 0, position.x, position.y, true);
//            }
//            if (str.equals("left")) {
//                world.step(Gdx.graphics.getDeltaTime(), 1, 1);
//                this.b2body.applyLinearImpulse(-V, 0, position.x, position.y, true);
//            }

}
