package com.mygdx.game.Factory;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.PlayScreen;
import com.mygdx.game.Tank;

public class TankFactory {
    private World world;
    private PlayScreen ps;
    public TankFactory(World world, PlayScreen ps){
        this.world = world;
        this.ps = ps;
    }
    public Tank createTank(int type){
        if(type==1){
            return new Tank(this.world,"tankType1.png",this.ps);
        }
        if(type==2){
            return new Tank(this.world,"tankType2.png",this.ps);
        }
        if(type==3){
            return new Tank(this.world,"tankType3.png",this.ps);
        }
        return null;
    }
}
