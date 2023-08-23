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
import com.mygdx.game.Tank;
import com.mygdx.game.TankStars;

public class tankSelecter implements Screen {
    private int user1;
    private int user2;
    private int curType;
    private Texture tankchooser;
    private Texture start;
    private boolean flag;
    private Skin next;
    private Stage stage;
    private TankStars game;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public tankSelecter(final TankStars game){
        user1=1;
        user2=1;
        this.game= game;
        stage =new Stage();
        Gdx.input.setInputProcessor(stage);
        curType=1;
        flag=true;
        tankchooser = new Texture("tankSelect1.png");
        start = new Texture("transp215x90.png");
        next = new Skin(Gdx.files.internal("gdx-skins-master/golden-spiral/skin/golden-ui-skin.json"));
        ImageButton nextButton = new ImageButton(next);
        nextButton.setSize(120, 180);
        nextButton.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture("gdx-skins-master/golden-spiral/raw/progress-bar.png")));
        nextButton.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(new Texture("gdx-skins-master/golden-spiral/raw/progress-bar.png")));
        nextButton.setPosition(900,130);
        nextButton.addListener(new ClickListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer , int button){
                if(curType==1){
                    tankchooser = new Texture("tankSelect2.png");
                } else if (curType == 2) {
                    tankchooser = new Texture("tankSelect3.png");
                }else{
                    tankchooser = new Texture("tankSelect1.png");
                }
                curType++;
            }
            @Override
            public boolean touchDown(InputEvent event,float x, float y , int pointer , int button){
                return true;
            }
        });

        stage.addActor(nextButton);
    }
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            if(Gdx.input.getX()<1100 && Gdx.input.getX()>900 && Gdx.input.getY()>675-250 && Gdx.input.getY()<675-160){
                if(flag) {
                    tankchooser = new Texture("tankSelect1.png");
                    user1 = curType;
                    curType=1;
                    flag=false;
                }else {
                    user2 = curType;
                    PlayScreen ps = new PlayScreen(this.game,user1,user2);
                    dispose();
                    game.setScreen(ps);
                }
            }
        }
    }
    public void update(float dt) {
        handleInput();
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        game.batch.begin();
        game.batch.draw(tankchooser,0,0,TankStars.V_WIDTH,TankStars.V_HEIGHT);
        game.batch.draw(start,825,160);
        game.batch.end();
        stage.act();
        stage.draw();
        update(delta);
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
