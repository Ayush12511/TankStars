package com.mygdx.game;

import com.badlogic.gdx.physics.box2d.*;
import sun.awt.geom.AreaOp;

import java.awt.peer.ListPeer;

public class CollisionListener implements ContactListener {
    private int flag;
    private Tank user1;
    private TankEnemy user2;
    private PlayScreen playScreen;
    public CollisionListener(Tank tank, TankEnemy tankEnemy, PlayScreen ps){
        this.user1=tank;
        this.user2=tankEnemy;
        this.playScreen = ps;
        flag=0;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public Tank getUser1() {
        return user1;
    }

    public void setUser1(Tank user1) {
        this.user1 = user1;
    }

    public TankEnemy getUser2() {
        return user2;
    }

    public void setUser2(TankEnemy user2) {
        this.user2 = user2;
    }

    public PlayScreen getPlayScreen() {
        return playScreen;
    }

    public void setPlayScreen(PlayScreen playScreen) {
        this.playScreen = playScreen;
    }

    @Override
    public void beginContact(Contact contact) {
        if(this.user1.canonball!=null){
            user1.lastCollision = user1.canonball.position;
            user2.damageFlag=0;
            user1.canonball = null;
        }
        if(this.user2.canonball!=null){
            user2.lastCollision = user2.canonball.position;
            user1.damageFlag=0;
            user2.canonball = null;
        }

    }
    @Override
    public void endContact(Contact contact) {
//        System.out.println("End contact");
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
