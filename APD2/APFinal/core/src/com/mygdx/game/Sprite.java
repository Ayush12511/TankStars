package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Sprite {
    public Vector2 position;
    public Texture spriteSheet;
    public float stateTime;

    public Sprite(Vector2 position){
        System.out.println(position);
        this.position= position;
        stateTime = 0f;
    }
    public void update(float dT){
        stateTime+=dT;
    }

}
