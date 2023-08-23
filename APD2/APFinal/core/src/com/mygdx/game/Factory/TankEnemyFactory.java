package com.mygdx.game.Factory;

import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.PlayScreen;
import com.mygdx.game.Tank;
import com.mygdx.game.TankEnemy;

public class TankEnemyFactory {
    private World world;
    private PlayScreen ps;
    public TankEnemyFactory(World world, PlayScreen ps){
        this.world = world;
        this.ps = ps;
    }
    public TankEnemy createTankEnemy(int type){
        if(type==1){
            return new TankEnemy(this.world,"tankType1Enemy.png",this.ps);
        }
        if(type==2){
            return new TankEnemy(this.world,"tankType2Enemy.png",this.ps);
        }
        if(type==3){
            return new TankEnemy(this.world,"tankType3Enemy.png",this.ps);
        }
        return null;
    }
}
