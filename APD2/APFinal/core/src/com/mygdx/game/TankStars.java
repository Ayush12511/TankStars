package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Screens.MenuScreen;

public class TankStars extends Game {
	public static final float PPM = 150;
	public static final int V_WIDTH = 1000;
	public static final int V_HEIGHT = 550;
	public SpriteBatch batch;
	public Texture img;
	private static TankStars instance;
	private TankStars(){}

	public static TankStars getInstance(){
		if(instance==null){
			return new TankStars();
		}else{
			return instance;
		}
	}
	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new MenuScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
