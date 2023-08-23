package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.PlayScreen;
import com.mygdx.game.TankStars;

public class GameOver implements Screen{
    private int user1;
    private int user2;
    private Texture image;
    private Skin restart;
    private Skin endGame;
    private Stage stage;
    private final TankStars game;

    public GameOver(final TankStars game, final int user1, final int user2){
        this.user1 = user1;
        this.user2 = user2;
        this.game = game;
        stage =new Stage();
        Gdx.input.setInputProcessor(stage);
        image = new Texture("gameOverScreen.png");
        restart = new Skin(Gdx.files.internal("gdx-skins-master/golden-spiral/skin/golden-ui-skin.json"));
        ImageButton restartButton = new ImageButton(restart);
        restartButton.setSize(120, 180);
        restartButton.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture("gdx-skins-master/golden-spiral/raw/progress-bar.png")));
        restartButton.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(new Texture("gdx-skins-master/golden-spiral/raw/progress-bar.png")));
        restartButton.setPosition(200,40);
        restartButton.addListener(new ClickListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer , int button){
                PlayScreen ps = new PlayScreen(game,user1,user2);
                dispose();
                game.setScreen(ps);
            }
            @Override
            public boolean touchDown(InputEvent event,float x, float y , int pointer , int button){
                return true;
            }
        });

        stage.addActor(restartButton);

        endGame = new Skin(Gdx.files.internal("gdx-skins-master/golden-spiral/skin/golden-ui-skin.json"));
        ImageButton endGameButton = new ImageButton(restart);
        endGameButton.setSize(120, 180);
        endGameButton.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture("gdx-skins-master/golden-spiral/raw/progress-bar.png")));
        endGameButton.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(new Texture("gdx-skins-master/golden-spiral/raw/progress-bar.png")));
        endGameButton.setPosition(700,40);
        endGameButton.addListener(new ClickListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer , int button){
                MenuScreen menuScreen = new MenuScreen(game);
                dispose();
                game.setScreen(menuScreen);
            }
            @Override
            public boolean touchDown(InputEvent event,float x, float y , int pointer , int button){
                return true;
            }
        });

        stage.addActor(endGameButton);
    }
    public void show() {

    }

    @Override
    public void render(float delta) {
        game.batch.begin();
        game.batch.draw(image,0,0,TankStars.V_WIDTH,TankStars.V_HEIGHT);
        game.batch.end();
        stage.act();
        stage.draw();
//        update(delta);
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

    }
}
