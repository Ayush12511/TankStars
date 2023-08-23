package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.PlayScreen;
import com.mygdx.game.TankStars;

public class MenuScreen implements Screen {
    private TankStars game;
    private Texture image;
    private Texture settingsicon;
    private Texture OnevOne;
    private Texture VsComp;
    private Sprite settings;
    private Skin start;
    private Skin resume;
    private Skin exit;
    private Stage stage;
    public MenuScreen(final TankStars game) {
        this.game = game;
        stage =new Stage();
        Gdx.input.setInputProcessor(stage);
        image = new Texture("MainScreen3.png");
        settingsicon = new Texture("trans2p48x48.png");
        VsComp = new Texture("transp480x150.png");
        OnevOne = new Texture("transp480x150.png");
//        image = new Texture(Gdx.files.internal("MainScreen1.png"));
//
//        ImageButton nextButton = new ImageButton(start);
//        nextButton.setSize(120, 180);
//        nextButton.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture("gdx-skins-master/golden-spiral/raw/progress-bar.png")));
//        nextButton.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(new Texture("gdx-skins-master/golden-spiral/raw/progress-bar.png")));
//
//        nextButton.setPosition(10,10);
//        nextButton.addListener(new ClickListener(){
//            @Override
//            public void touchUp (InputEvent event, float x, float y, int pointer , int button){
//                PlayScreen mp=new PlayScreen(game);
//                game.setScreen(mp);
//            }
//            @Override
//            public boolean touchDown(InputEvent event,float x, float y , int pointer , int button){
//                return true;
//            }
//        });
//
//        stage.addActor(nextButton);
    }

    @Override
    public void show() {

    }

    public void handleInput(){
        if(Gdx.input.justTouched()){
            if(Gdx.input.getX()<93 && Gdx.input.getX()>49 && Gdx.input.getY()>675-640 && Gdx.input.getY()<675-593){
                PlayScreen mp=new PlayScreen(game,1,2);
                dispose();
                game.setScreen(mp);
            }
            else if(Gdx.input.getX()<1165 && Gdx.input.getX()>686 && Gdx.input.getY()>675-254 && Gdx.input.getY()<675-104){
                StateSelect mp=new StateSelect(game);
                dispose();
                game.setScreen(mp);
            } else if (Gdx.input.getX()<1165 && Gdx.input.getX()>700 && Gdx.input.getY()>675-520 && Gdx.input.getY()<675-370) {
                StateSelect mp=new StateSelect(game);
                dispose();
                game.setScreen(mp);
            }
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.batch.draw(image,0,0,TankStars.V_WIDTH,TankStars.V_HEIGHT);
        game.batch.draw(settingsicon,49,593);
        game.batch.draw(VsComp,686,104);
        game.batch.draw(OnevOne,686,370);

        game.batch.end();
        stage.act();
        stage.draw();
        handleInput();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
